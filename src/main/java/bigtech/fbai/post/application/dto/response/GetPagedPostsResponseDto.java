package bigtech.fbai.post.application.dto.response;

import bigtech.fbai.post.dao.entity.Post;
import java.util.List;

public record GetPagedPostsResponseDto(List<GetPagedPostResponseDto> posts) {

    public static GetPagedPostsResponseDto from(List<Post> posts) {
        List<GetPagedPostResponseDto> dtos = posts.stream()
                .map(post -> new GetPagedPostResponseDto(post.getMember().getNickname(), post.getPostId(),
                        post.getContent().getTitle(), post.getMetadata().getCount(), post.getMetadata().getCreatedAt()))
                .toList();
        return new GetPagedPostsResponseDto(dtos);
    }
}
