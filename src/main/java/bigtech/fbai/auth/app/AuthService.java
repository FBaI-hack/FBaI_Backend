package bigtech.fbai.auth.app;

import bigtech.fbai.auth.app.dto.AuthRegisterRequestDto;
import bigtech.fbai.auth.app.dto.LoginRequestDto;
import bigtech.fbai.auth.app.dto.LoginResponseDto;
import bigtech.fbai.auth.dao.AuthMemberRepository;
import bigtech.fbai.auth.dao.entity.AuthMember;
import bigtech.fbai.common.dto.CommonSuccessDto;
import bigtech.fbai.config.security.form.CustomAuthenticationProvider;
import bigtech.fbai.config.security.jwt.JwtTokenGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthMemberRepository memberRepository;
    private final AuthValidator validator;
    private final PasswordEncoder encoder;
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final JwtTokenGenerator tokenGenerator;


    public CommonSuccessDto register(AuthRegisterRequestDto dto) {
        List<AuthMember> findMembers = memberRepository.findByEmailOrNickname(dto.email(), dto.nickname());
        validate(dto, findMembers);

        String encodedPassword = encode(dto.password());
        AuthMember member = AuthMember.create(dto.email(), dto.nickname(), encodedPassword);
        memberRepository.save(member);

        return CommonSuccessDto.success();
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        Authentication token = customAuthenticationProvider.authenticate(authentication);
        String accessToken = tokenGenerator.generateToken(token);

        return new LoginResponseDto(accessToken);
    }

    private void validate(AuthRegisterRequestDto dto, List<AuthMember> findMembers) {
        validator.validateEmailAndNickname(findMembers, dto.email(), dto.nickname());
    }

    private String encode(String password) {
        return encoder.encode(password);
    }
}
