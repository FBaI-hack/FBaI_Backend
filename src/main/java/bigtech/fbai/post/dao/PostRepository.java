package bigtech.fbai.post.dao;

import bigtech.fbai.post.dao.entity.Category;
import bigtech.fbai.post.dao.entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p"
            + " where p.metaData.category = :category"
            + " order by p.postId asc"
            + " limit 10 offset :page ")
    List<Post> findPostsByCategory(@Param("category") Category category, @Param("page") int page);

    @Query("select p from Post p"
        + " join fetch p.postContent.comments c"
        + " join fetch p.member m"
        + " where p.postId=:post_id")
    Optional<Post> findPostAndCommentsBy(@Param("post_id") Long postId);
}