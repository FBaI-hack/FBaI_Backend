package bigtech.fbai.auth.ui;

import bigtech.fbai.auth.app.AuthService;
import bigtech.fbai.auth.app.dto.AuthRegisterRequestDto;
import bigtech.fbai.auth.app.dto.LoginRequestDto;
import bigtech.fbai.auth.app.dto.LoginResponseDto;
import bigtech.fbai.common.dto.CommonSuccessDto;
import bigtech.fbai.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseDto<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        return ResponseDto.ok(authService.login(dto));
    }

    @PostMapping("/register")
    public ResponseDto<CommonSuccessDto> register(@RequestBody AuthRegisterRequestDto dto) {
        return ResponseDto.created(authService.register(dto));
    }
}
