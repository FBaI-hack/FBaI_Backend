package bigtech.fbai.member.dao;

import bigtech.fbai.member.dao.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
