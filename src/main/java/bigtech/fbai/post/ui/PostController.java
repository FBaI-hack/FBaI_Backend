package bigtech.fbai.post.ui;

import bigtech.fbai.common.dto.ResponseDto;
import bigtech.fbai.post.application.PostService;
import bigtech.fbai.post.application.dto.response.GetPagedPostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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



}
