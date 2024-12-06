package bigtech.fbai.config.security.info;

import bigtech.fbai.auth.dao.AuthMemberRepository;
import bigtech.fbai.auth.dao.entity.AuthMember;
import bigtech.fbai.common.exception.CommonException;
import bigtech.fbai.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthMemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AuthMember entity = memberRepository.findByEmail(email)
            .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_LOGIN_USER));
        return new CustomUserDetails(entity);
    }
}
