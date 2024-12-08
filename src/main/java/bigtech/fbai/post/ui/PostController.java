package bigtech.fbai.post.ui;

import bigtech.fbai.common.annotation.Auth;
import bigtech.fbai.common.dto.CommonSuccessDto;
import bigtech.fbai.common.dto.ResponseDto;
import bigtech.fbai.post.application.PostService;
import bigtech.fbai.post.application.dto.request.CreatePostRequestDto;
import bigtech.fbai.post.application.dto.request.UpdatePostRequestDto;
import bigtech.fbai.post.application.dto.response.CreatePostResponseDto;
import bigtech.fbai.post.application.dto.response.GetPagedPostsResponseDto;
import bigtech.fbai.post.application.dto.response.GetPostResponseDto;
import bigtech.fbai.post.application.dto.response.UpdatePostResponseDto;
import bigtech.fbai.post.dao.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public ResponseDto<GetPagedPostsResponseDto> getPosts(@RequestParam Category category, @RequestParam int page){
        return ResponseDto.ok(postService.getPosts(category,page));
    }

    @GetMapping("/{post_id}")
    public ResponseDto<GetPostResponseDto> getPost(@PathVariable("post_id") Long postId) {
        return ResponseDto.ok(postService.getPost(postId));
    }
    @PostMapping("")
    public ResponseDto<CreatePostResponseDto> createPost(@Auth Long memberId, @RequestBody CreatePostRequestDto createPostRequestDto){
        return ResponseDto.created(postService.createPost(memberId,createPostRequestDto));
    }
    @PatchMapping("/{post_id}")
    public CommonSuccessDto updatePost(@Auth Long memberId,@PathVariable("post_id") Long postId, @RequestBody UpdatePostRequestDto updatePostRequestDto){
        postService.updatePost(memberId,postId,updatePostRequestDto);
        return CommonSuccessDto.success();
    }
    @DeleteMapping("/{post_id}")
    public CommonSuccessDto deletePost(@Auth Long memberId,@PathVariable("post_id") Long postId){
        postService.deletePost(memberId, postId);
        return CommonSuccessDto.success();
    }


}
