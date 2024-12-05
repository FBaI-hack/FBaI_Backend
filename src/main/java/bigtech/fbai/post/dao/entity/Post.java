package bigtech.fbai.post.dao.entity;

import static jakarta.persistence.FetchType.LAZY;
import static org.hibernate.annotations.OnDeleteAction.CASCADE;
import static org.hibernate.annotations.OnDeleteAction.SET_NULL;

import bigtech.fbai.member.dao.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long postId;


    private String title;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = CASCADE)
    private Member member;

    @OneToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategoryEntity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "suspect_id")
    @OnDelete(action = SET_NULL)
    private Suspect suspectEntity;

    private String content;
    private String category;
    private int count;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String postUrl;
    private String productName;

}