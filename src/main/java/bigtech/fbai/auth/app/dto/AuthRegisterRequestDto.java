package bigtech.fbai.auth.app.dto;

public record AuthRegisterRequestDto(
    String email,

    String password,

    String nickname) {

}
