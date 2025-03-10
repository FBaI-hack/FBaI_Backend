package bigtech.fbai.common.dto;

import bigtech.fbai.common.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ExceptionDto {

  @Schema(name = "code", description = "에러 코드")
  @NotNull
  private final Integer code;

  @Schema(name = "message", description = "에러 메시지")
  @NotNull
  private final String message;

  public ExceptionDto(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
  }

  public ExceptionDto(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public static ExceptionDto of(ErrorCode errorCode) {
    return new ExceptionDto(errorCode);
  }

  public static ExceptionDto of(ErrorCode errorCode, String message) {
    return new ExceptionDto(errorCode.getCode(), message);
  }
}
