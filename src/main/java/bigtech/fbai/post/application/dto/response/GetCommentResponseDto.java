package bigtech.fbai.post.application.dto.response;

import java.time.LocalDate;

public record GetCommentResponseDto(Long commentId, String content, Long postId, LocalDate createAt) {

}
