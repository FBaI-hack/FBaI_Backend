package bigtech.fbai.post.application.dto.response;

import bigtech.fbai.member.dao.entity.Member;
import bigtech.fbai.post.dao.entity.Comment;
import java.time.LocalDateTime;

public record PostCommentResponseDto(Long memberId, String memberName, String profileImgUrl, Long commentId, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {

    public static PostCommentResponseDto from(Comment comment) {
        Member member = comment.getMember();
        return new PostCommentResponseDto(member.getId(), member.getNickname(), member.getImageUrl(), comment.getCommentId(),
            comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt());
    }
}
