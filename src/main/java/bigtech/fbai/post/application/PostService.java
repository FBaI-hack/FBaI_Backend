package bigtech.fbai.post.application;

import bigtech.fbai.common.exception.CommonException;
import bigtech.fbai.common.exception.ErrorCode;
import bigtech.fbai.member.app.MemberService;
import bigtech.fbai.member.dao.entity.Member;
import bigtech.fbai.post.application.dto.request.CreatePostRequestDto;
import bigtech.fbai.post.application.dto.request.UpdatePostRequestDto;
import bigtech.fbai.post.application.dto.response.CreatePostResponseDto;
import bigtech.fbai.post.application.dto.response.GetPagedPostsResponseDto;
import bigtech.fbai.post.application.dto.response.GetPostResponseDto;
import bigtech.fbai.post.dao.PostRepository;
import bigtech.fbai.post.dao.entity.Post;
import bigtech.fbai.post.dao.entity.PostContent;
import bigtech.fbai.post.dao.entity.PostMeta;
import bigtech.fbai.productCategory.app.ProductCategoryService;
import bigtech.fbai.productCategory.dao.entity.ProductCategory;
import bigtech.fbai.suspect.app.SuspectService;
import bigtech.fbai.suspect.dao.entity.Suspect;
import java.util.List;
import java.util.Objects;
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

    public Post findPost(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_COMMENT));
    }

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
    public CreatePostResponseDto createPost(Long memberId, CreatePostRequestDto createPostRequestDto) {
        Member member = memberService.findMember(memberId);

        String productCategoryName = createPostRequestDto.productCategoryName();
        String title = createPostRequestDto.title();
        String content = createPostRequestDto.content();
        String category = createPostRequestDto.category();
        String postUrl = createPostRequestDto.postUrl();
        String productName = createPostRequestDto.productName();
        String name = createPostRequestDto.suspect_name();
        String email = createPostRequestDto.suspect_email();
        String account = createPostRequestDto.suspect_account();
        String bank = createPostRequestDto.suspect_bank();
        String platform = createPostRequestDto.suspect_platform();

        Suspect suspect = findOrCreateSuspect(name, email, account, bank, platform);
        ProductCategory productCategory = findProductCategory(createPostRequestDto.productCategoryName());



        PostContent postContent = PostContent.create(title,content,productName,postUrl,productCategory);

        Post post = Post.create(postContent,category,member,suspect);

        postRepository.save(post);
        return new CreatePostResponseDto(title, category, content, productCategoryName, postUrl, productName, suspect);
    }

    private ProductCategory findProductCategory(String productCategoryName) {
        ProductCategory productCategory = null;
        if(productCategoryService.getProductCategory(productCategoryName) != null){
            productCategory = productCategoryService.getProductCategory(productCategoryName);
            productCategory.countPlus();
        }
        return productCategory;
    }

    private Suspect findOrCreateSuspect(String name, String email, String account, String bank, String platform) {
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

    @Transactional
    public void updatePost(Long memberId, Long postId, UpdatePostRequestDto updatePostRequestDto) {
        Post post = validatePostOwnership(memberId, postId);

        ProductCategory productCategory = productCategoryService.getProductCategory(updatePostRequestDto.productCategoryName());
        if (productCategory == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_RESOURCE);
        }

        Suspect suspect = findOrCreateSuspect(
                updatePostRequestDto.suspectName(),
                updatePostRequestDto.suspectEmail(),
                updatePostRequestDto.suspectAccount(),
                updatePostRequestDto.suspectBank(),
                updatePostRequestDto.suspectPlatform()
        );

        PostContent postContent = post.getContent();
        postContent.update(
                updatePostRequestDto.title(),
                updatePostRequestDto.content(),
                updatePostRequestDto.productName(),
                updatePostRequestDto.postUrl(),
                productCategory
        );

        PostMeta postMeta = post.getMetadata();
        postMeta.update(updatePostRequestDto.category(), updatePostRequestDto.count());

        post.update(postContent, postMeta, suspect);
        log.info("postUrl: {}", post.getPostContent().getPostUrl());
        log.info("ProductName: {}", post.getPostContent().getProductName());

        postRepository.save(post);
    }

    public void deletePost(Long memberId, Long postId) {
        Post post = validatePostOwnership(memberId, postId);
        postRepository.delete(post);
    }

    private Post validatePostOwnership(Long postId, Long memberId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_POST));

        if (!Objects.equals(memberId, post.getMember().getId())) {
            throw new CommonException(ErrorCode.INVALID_ACCESS_URL);
        }

        return post;
    }
}
