package bigtech.fbai.post.ui;

import bigtech.fbai.common.annotation.Auth;
import bigtech.fbai.common.dto.ResponseDto;
import bigtech.fbai.post.application.CommentService;
import bigtech.fbai.post.application.dto.response.GetCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseDto<Page<GetCommentResponseDto>> getComments(@Auth Long memberId, @PageableDefault(size = 10) Pageable pageable) {
        return ResponseDto.ok(commentService.findComments(memberId, pageable));
    }
}
