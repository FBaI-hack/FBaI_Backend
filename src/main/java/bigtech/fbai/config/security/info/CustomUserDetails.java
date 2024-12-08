package bigtech.fbai.config.security.info;

import bigtech.fbai.auth.dao.entity.AuthMember;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private final String id;
    private final String password;

    @Getter
    private final String email;

    public CustomUserDetails(AuthMember authMember) {
        this.id = String.valueOf(authMember.getId());
        this.password = authMember.getPassword();
        this.email = authMember.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
