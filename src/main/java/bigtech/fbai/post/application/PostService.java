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
import bigtech.fbai.post.dao.entity.PostMeta;
import bigtech.fbai.productCategory.app.ProductCategoryService;
import bigtech.fbai.productCategory.dao.entity.ProductCategory;
import bigtech.fbai.suspect.app.SuspectService;
import bigtech.fbai.suspect.app.dto.request.SuspectCreateRequestDto;
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
    public boolean createPost(long memberId, CreatePostRequestDto createPostRequestDto) {
        Member member = memberService.findMember(memberId);

        Suspect suspect = findOrCreateSuspect(createPostRequestDto.suspectCreateRequestDto());
        ProductCategory productCategory = findProductCategory(createPostRequestDto.productCategoryName());

        String title = createPostRequestDto.title();
        String content = createPostRequestDto.content();
        String category = createPostRequestDto.category();
        String postUrl = createPostRequestDto.postUrl();
        String productName = createPostRequestDto.productName();

        PostContent postContent = PostContent.create(title,content,productName,postUrl,productCategory);
        PostMeta postMeta = PostMeta.create(category, 0);

        Post post = Post.create(postContent,postMeta,member,suspect);

        postRepository.save(post);
        return true;
    }

    private ProductCategory findProductCategory(String productCategoryName) {
        ProductCategory productCategory = null;
        if(productCategoryService.getProductCategory(productCategoryName) != null){
            productCategory = productCategoryService.getProductCategory(productCategoryName);
        }
        return productCategory;
    }

    private Suspect findOrCreateSuspect(SuspectCreateRequestDto suspectCreateRequestDto) {
        Suspect suspect = null;
        if(suspectCreateRequestDto != null){
            suspect = suspectService.getSuspect(suspectCreateRequestDto.name(), suspectCreateRequestDto.email(), suspectCreateRequestDto.bank(),
                    suspectCreateRequestDto.account(), suspectCreateRequestDto.platform());
            if(suspect == null){
                suspect = suspectService.createSuspect(suspectCreateRequestDto);
            }
        }
        return suspect;
    }
}
