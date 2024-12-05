package bigtech.fbai.post.application.dto.response;

import java.time.LocalDateTime;

public record GetPagedPostResponseDto(String memberName, long postId,
                                      String title, int count,
                                      LocalDateTime createdAt) {

    public GetPagedPostResponseDto(String memberName, long postId,
                                   String title, int count,
                                   LocalDateTime createdAt) {
        this.memberName = memberName;
        this.postId = postId;
        this.title = title;
        this.count = count;
        this.createdAt = createdAt;
    }
}
