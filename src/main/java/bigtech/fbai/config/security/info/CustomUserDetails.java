package bigtech.fbai.config.security.info;

import bigtech.fbai.auth.dao.entity.AuthMember;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private final String id;
    private final String password;

    public CustomUserDetails(AuthMember authMember) {
        this.id = String.valueOf(authMember.getId());
        this.password = authMember.getPassword();
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
