package bigtech.fbai.post.application.dto.request;

public record CreateCommentRequestDto(Long postId, String content, Long parentCommentId) {

}
