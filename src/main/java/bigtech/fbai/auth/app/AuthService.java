package bigtech.fbai.auth.app;

import bigtech.fbai.auth.app.dto.AuthRegisterRequestDto;
import bigtech.fbai.auth.dao.AuthMemberRepository;
import bigtech.fbai.auth.dao.entity.AuthMember;
import bigtech.fbai.common.dto.CommonSuccessDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthMemberRepository authMemberRepository;
    private final AuthValidator validator;
    private final PasswordEncoder encoder;

    public CommonSuccessDto register(AuthRegisterRequestDto dto) {
        List<AuthMember> findMembers = authMemberRepository.findByEmailOrNickname(dto.email(), dto.nickname());
        validate(dto, findMembers);

        String encodedPassword = encodePassword(dto);
        AuthMember member = AuthMember.create(dto.email(), dto.nickname(), encodedPassword);
        authMemberRepository.save(member);

        return CommonSuccessDto.success();
    }

    private void validate(AuthRegisterRequestDto dto, List<AuthMember> findMembers) {
        validator.validateEmailAndNickname(findMembers, dto.email(), dto.nickname());
    }

    private String encodePassword(AuthRegisterRequestDto dto) {
        return encoder.encode(dto.password());
    }
}
