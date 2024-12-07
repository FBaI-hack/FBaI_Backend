package bigtech.fbai.post.ui;

import bigtech.fbai.common.annotation.Auth;
import bigtech.fbai.common.dto.CommonSuccessDto;
import bigtech.fbai.common.dto.ResponseDto;
import bigtech.fbai.post.application.PostService;
import bigtech.fbai.post.application.dto.request.CreatePostRequestDto;
import bigtech.fbai.post.application.dto.response.GetPagedPostsResponseDto;
import bigtech.fbai.post.application.dto.response.GetPostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseDto<GetPagedPostsResponseDto> getPosts(@RequestParam String category, @RequestParam int page){
        return ResponseDto.ok(postService.getPosts(category,page));
    }

    @GetMapping("/{post_id}")
    public ResponseDto<GetPostResponseDto> getPost(@PathVariable("post_id") Long postId) {
        return ResponseDto.ok(postService.getPost(postId));
    }
    @PostMapping("")
    public CommonSuccessDto createPost(@Auth Long memberId, @RequestBody CreatePostRequestDto createPostRequestDto){
        postService.createPost(memberId,createPostRequestDto);
        return CommonSuccessDto.success();
    }


}
