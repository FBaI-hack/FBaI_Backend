package bigtech.fbai.auth.dao;

import bigtech.fbai.auth.dao.entity.AuthMember;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthMemberRepository extends JpaRepository<AuthMember, Long> {

    Optional<AuthMember> findByEmail(String email);

    List<AuthMember> findByEmailOrNickname(String email, String nickname);
}
