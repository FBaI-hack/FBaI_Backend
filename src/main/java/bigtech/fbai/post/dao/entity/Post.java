package bigtech.fbai.post.dao.entity;

import static jakarta.persistence.FetchType.LAZY;
import static org.hibernate.annotations.OnDeleteAction.CASCADE;
import static org.hibernate.annotations.OnDeleteAction.SET_NULL;

import bigtech.fbai.common.dao.entity.BaseTimeEntity;
import bigtech.fbai.member.dao.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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
    private Long postId;

    @Embedded
    private PostContent postContent;

    @Embedded
    private PostMeta metaData;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = CASCADE)
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "suspect_id")
    @OnDelete(action = SET_NULL)
    private Suspect suspectEntity;

    public PostContent getContent() {
        return postContent;
    }

    public PostMeta getMetadata() {
        return metaData;
    }
}