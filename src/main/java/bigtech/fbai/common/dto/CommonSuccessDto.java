package bigtech.fbai.common.dto;

import lombok.Builder;

@Builder
public record CommonSuccessDto(boolean isSuccess) {

  public static CommonSuccessDto success() {
    return new CommonSuccessDto(true);
  }

  public static CommonSuccessDto fail() {
    return new CommonSuccessDto(false);
  }
}
