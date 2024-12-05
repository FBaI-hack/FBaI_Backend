package bigtech.fbai.suspect.dao;

import bigtech.fbai.suspect.dao.entity.Suspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuspectRepository extends JpaRepository<Suspect,Long> {

    @Query("select s from Suspect s"
            + " where s.name = :name"
            + " and s.email = :email"
            + " and s.bank = :bank"
            + " and s.account = :account"
            + " and s.platform = :platform")
    Suspect findBySuspectInfo(@Param("name") String name, @Param("email") String email, @Param("bank") String bank, @Param("account") String account, @Param("platform") String platform);
}
