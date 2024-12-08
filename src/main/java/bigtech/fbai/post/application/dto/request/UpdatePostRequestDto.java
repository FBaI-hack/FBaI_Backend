package bigtech.fbai.post.application.dto.request;

import bigtech.fbai.post.dao.entity.Category;

public record UpdatePostRequestDto(
        String title,
        String content,
        Category category,
        int count,
        String productCategoryName,
        String postUrl,
        String productName,
        String suspectName,
        String suspectEmail,
        String suspectBank,
        String suspectAccount,
        String suspectPlatform
) {
}
