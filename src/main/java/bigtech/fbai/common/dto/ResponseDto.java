package bigtech.fbai.common.dto;

import bigtech.fbai.common.exception.ArgumentNotValidExceptionDto;
import bigtech.fbai.common.exception.CommonException;
import bigtech.fbai.common.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Schema(name = "ResponseDto", description = "API 응답 DTO")
public record ResponseDto<T>(
    @JsonIgnore HttpStatus httpStatus,
    @Schema(name = "success", description = "API 호출 성공 여부") @NotNull Boolean success,
    @Schema(name = "data", description = "API 호출 성공 시 응답 데이터") @Nullable T data,
    @Schema(name = "error", description = "API 호출 실패 시 응답 에러") @Nullable ExceptionDto error) {

    public static <T> ResponseDto<T> ok(@Nullable final T data) {
        return new ResponseDto<>(HttpStatus.OK, true, data, null);
    }

    public static <T> ResponseDto<T> created(@Nullable final T data) {
        return new ResponseDto<>(HttpStatus.CREATED, true, data, null);
    }

    public static ResponseDto<Object> fail(final MethodArgumentNotValidException e) {
        return new ResponseDto<>(
            HttpStatus.BAD_REQUEST, false, null, new ArgumentNotValidExceptionDto(e));
    }

    public static ResponseDto<Object> fail(final MissingServletRequestParameterException e) {
        return new ResponseDto<>(
            HttpStatus.BAD_REQUEST, false, null, ExceptionDto.of(ErrorCode.MISSING_REQUEST_PARAMETER));
    }

    public static ResponseDto<Object> fail(final MethodArgumentTypeMismatchException e) {
        return new ResponseDto<>(
            HttpStatus.INTERNAL_SERVER_ERROR,
            false,
            null,
            ExceptionDto.of(ErrorCode.INVALID_PARAMETER_FORMAT));
    }

    public static ResponseDto<Object> fail(final CommonException e) {
        return new ResponseDto<>(
            e.getErrorCode().getHttpStatus(), false, null, ExceptionDto.of(e.getErrorCode()));
    }

    public static ResponseDto<Object> fail(final AuthenticationException exception) {
        return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, false, null,
            ExceptionDto.of(ErrorCode.NOT_MATCH_USER));
    }
}
