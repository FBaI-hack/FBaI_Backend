package bigtech.fbai.post.application.dto.response;

import bigtech.fbai.post.dao.entity.Comment;
import java.util.List;
import lombok.Builder;

@Builder
public record PostCommentsResponseDto(List<PostCommentResponseDto> childComments) {

    public static PostCommentsResponseDto from(List<Comment> comments) {
        List<PostCommentResponseDto> dtos = comments.stream()
            .map(PostCommentResponseDto::from)
            .toList();

        return PostCommentsResponseDto.builder()
            .childComments(dtos)
            .build();
    }
}
