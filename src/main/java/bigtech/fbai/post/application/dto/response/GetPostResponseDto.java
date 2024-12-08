package bigtech.fbai.post.application.dto.response;

import bigtech.fbai.member.dao.entity.Member;
import bigtech.fbai.post.dao.entity.Category;
import bigtech.fbai.post.dao.entity.Post;
import bigtech.fbai.post.dao.entity.PostContent;
import bigtech.fbai.post.dao.entity.PostMeta;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetPostResponseDto(Long postId, String memberName, String profileImgUrl, String title, String content,
                                 LocalDateTime createdAt, LocalDateTime updatedAt, int count, Category category,
                                 String postUrl, String productName,
                                 int like,
                                 PostCommentsResponseDto comments) {


    public static GetPostResponseDto from(Post post) {
        Member member = post.getMember();
        PostContent postContent = post.getContent();
        PostMeta metadata = post.getMetadata();
        PostCommentsResponseDto commentDtos = PostCommentsResponseDto.from(
            postContent.getComments());

        return GetPostResponseDto.builder()
            .postId(post.getPostId())
            .memberName(member.getNickname())
            .profileImgUrl(member.getImageUrl())
            .title(postContent.getTitle())
            .content(postContent.getContent())
            .createdAt(post.getCreatedAt())
            .updatedAt(post.getUpdatedAt())
            .count(metadata.getCount())
            .category(metadata.getCategory())

            .comments(commentDtos)
            .build();
    }
}
