package bigtech.fbai.post.application.dto.request;

import bigtech.fbai.suspect.app.dto.request.SuspectCreateRequestDto;

public record CreatePostRequestDto(String title, String category,
								   String content, String productCategoryName, String postUrl,
								   String productName, SuspectCreateRequestDto suspectCreateRequestDto) {

}
