package bigtech.fbai.post.application.dto.request;

import bigtech.fbai.post.dao.entity.Category;

public record CreatePostRequestDto(String title, Category category,
								   String content, String productCategoryName, String postUrl,
								   String productName,
								   String suspect_name, String suspect_email, String suspect_bank,
								   String suspect_account, String suspect_platform) {

}
