package bigtech.fbai.post.application.dto.response;

import bigtech.fbai.post.dao.entity.Post;
import bigtech.fbai.suspect.dao.entity.Suspect;
import lombok.Builder;

@Builder
public record UpdatePostResponseDto(String title, String content, int count, String category,
                                    String productCategoryName, String postUrl, String productName, Suspect suspect) {

    public static UpdatePostResponseDto from(Post post) {
        return UpdatePostResponseDto.builder()
                .title(post.getContent().getTitle())
                .content(post.getContent().getContent())
                .count(post.getMetadata().getCount())
                .category(post.getMetadata().getCategory())
                .productName(post.getContent().getProductName())
                .postUrl(post.getPostContent().getPostUrl())
                .productCategoryName(post.getContent().getProductCategoryEntity().getName())
                .suspect(post.getSuspectEntity())
                .build();
    }
}
