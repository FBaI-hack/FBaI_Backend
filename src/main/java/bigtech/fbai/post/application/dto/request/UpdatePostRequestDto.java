package bigtech.fbai.post.application.dto.request;

public record UpdatePostRequestDto(
        String title,
        String content,
        String category,
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
