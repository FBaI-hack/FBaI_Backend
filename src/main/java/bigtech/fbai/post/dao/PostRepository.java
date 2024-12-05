package bigtech.fbai.post.dao;

import bigtech.fbai.post.dao.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p"
            + " where p.category = :category"
            + " order by p.postId asc"
            + " limit 10 offset :page ")
    List<Post> findPostsByCategory(@Param("category")String category, @Param("page") int page);
}