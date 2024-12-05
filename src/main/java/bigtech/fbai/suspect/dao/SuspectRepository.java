package bigtech.fbai.suspect.dao;

import bigtech.fbai.suspect.dao.entity.Suspect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuspectRepository extends JpaRepository<Suspect,Long> {

}
