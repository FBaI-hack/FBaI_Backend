package bigtech.fbai.post.application;

import bigtech.fbai.common.exception.CommonException;
import bigtech.fbai.common.exception.ErrorCode;
import bigtech.fbai.member.app.MemberService;
import bigtech.fbai.member.dao.entity.Member;
import bigtech.fbai.post.application.dto.request.CreatePostRequestDto;
import bigtech.fbai.post.application.dto.response.GetPagedPostsResponseDto;
import bigtech.fbai.post.application.dto.response.GetPostResponseDto;
import bigtech.fbai.post.dao.PostRepository;
import bigtech.fbai.post.dao.entity.Post;
import bigtech.fbai.post.dao.entity.PostContent;
import bigtech.fbai.productCategory.app.ProductCategoryService;
import bigtech.fbai.productCategory.dao.entity.ProductCategory;
import bigtech.fbai.suspect.app.SuspectService;
import bigtech.fbai.suspect.dao.entity.Suspect;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final SuspectService suspectService;
    private final ProductCategoryService productCategoryService;

    public GetPagedPostsResponseDto getPosts(String category, int page) {
        List<Post> posts = postRepository.findPostsByCategory(category,page);
        return GetPagedPostsResponseDto.from(posts);
    }

    public GetPostResponseDto getPost(Long postId) {
        Post post = postRepository.findPostAndCommentsBy(postId)
            .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_POST));

        return GetPostResponseDto.from(post);
    }

    @Transactional
    public boolean createPost(Long memberId, CreatePostRequestDto createPostRequestDto) {
        Member member = memberService.findMember(memberId);

        Suspect suspect = findOrCreateSuspect(createPostRequestDto);
        ProductCategory productCategory = findProductCategory(createPostRequestDto.productCategoryName());

        String title = createPostRequestDto.title();
        String content = createPostRequestDto.content();
        String category = createPostRequestDto.category();
        String postUrl = createPostRequestDto.postUrl();
        String productName = createPostRequestDto.productName();

        PostContent postContent = PostContent.create(title,content,productName,postUrl,productCategory);

        Post post = Post.create(postContent,category,member,suspect);

        postRepository.save(post);
        return true;
    }

    private ProductCategory findProductCategory(String productCategoryName) {
        ProductCategory productCategory = null;
        if(productCategoryService.getProductCategory(productCategoryName) != null){
            productCategory = productCategoryService.getProductCategory(productCategoryName);
            productCategory.countPlus();
        }
        return productCategory;
    }

    private Suspect findOrCreateSuspect(CreatePostRequestDto createPostRequestDto) {
        String name = createPostRequestDto.suspect_name();
        String email = createPostRequestDto.suspect_email();
        String account = createPostRequestDto.suspect_account();
        String bank = createPostRequestDto.suspect_bank();
        String platform = createPostRequestDto.suspect_platform();

        if (name.isEmpty() || email.isEmpty() || account.isEmpty() || bank.isEmpty() || platform.isEmpty()) {
            return null;
        }

        Suspect suspect = suspectService.getSuspect(name, email, account, bank, platform) ;
        if(suspect != null){
            suspect.countIncrement();
            return suspect;
        }

        suspect = suspectService.createSuspect(name, email, account, bank, platform);
        suspect.countIncrement();
        return suspect;
    }
}
