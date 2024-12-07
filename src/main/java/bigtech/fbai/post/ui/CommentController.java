package bigtech.fbai.post.ui;

import bigtech.fbai.common.annotation.Auth;
import bigtech.fbai.common.dto.CommonSuccessDto;
import bigtech.fbai.common.dto.ResponseDto;
import bigtech.fbai.post.application.CommentService;
import bigtech.fbai.post.application.dto.request.CreateCommentRequestDto;
import bigtech.fbai.post.application.dto.request.UpdateCommentRequestDto;
import bigtech.fbai.post.application.dto.response.GetCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseDto<Page<GetCommentResponseDto>> getComments(@Auth Long memberId,
        @PageableDefault(size = 10) Pageable pageable) {
        return ResponseDto.ok(commentService.findComments(memberId, pageable));
    }

    @PostMapping
    public ResponseDto<CommonSuccessDto> createComment(@Auth Long memberId, @RequestBody CreateCommentRequestDto dto) {
        return ResponseDto.created(commentService.createComment(memberId, dto));
    }

    @PatchMapping("/{comment_id}")
    public ResponseDto<CommonSuccessDto> updateComment(@Auth Long memberId, @PathVariable("comment_id") Long commentId,
        @RequestBody UpdateCommentRequestDto dto) {
        return ResponseDto.ok(commentService.updateComment(memberId, commentId, dto));
    }

    @DeleteMapping("/{comment_id}")
    public ResponseDto<CommonSuccessDto> deleteComment(@Auth Long memberId,
        @PathVariable("comment_id") Long commentId) {
        return ResponseDto.ok(commentService.deleteComment(memberId, commentId));
    }
}
