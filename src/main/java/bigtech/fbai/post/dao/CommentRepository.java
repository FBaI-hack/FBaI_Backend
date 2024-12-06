package bigtech.fbai.post.dao;

import bigtech.fbai.post.dao.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c"
        + " join c.member m"
        + " where m.id=:member_id")
    Page<Comment> findAllByMemberId(@Param("member_id") Long memberId, Pageable pageable);
}
