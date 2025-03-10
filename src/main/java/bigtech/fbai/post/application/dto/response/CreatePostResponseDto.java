package bigtech.fbai.post.application.dto.response;

import bigtech.fbai.post.dao.entity.Category;
import bigtech.fbai.suspect.dao.entity.Suspect;

public record CreatePostResponseDto(String title, Category category,
                                    String content, String productCategoryName, String postUrl,
                                    String productName,
                                    Suspect suspect) {
    public CreatePostResponseDto(String title, Category category,
                                 String content, String productCategoryName, String postUrl,
                                 String productName,
                                 Suspect suspect){
        this.title = title;
        this.category = category;
        this.content = content;
        this.productCategoryName = productCategoryName;
        this.postUrl = postUrl;
        this.productName = productName;
        this.suspect = suspect;

    }

}
