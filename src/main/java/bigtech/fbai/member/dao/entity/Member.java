package bigtech.fbai.member.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@EqualsAndHashCode(of = {"id"})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String email;

    private String introduce;

    private String imageUrl;

    public void update(String nickname, String imageUrl, String introduce) {
        if (nickname != null) {
            this.nickname = nickname;
        }

        if (imageUrl != null) {
            this.imageUrl = imageUrl;
        }

        if (introduce != null) {
            this.introduce = introduce;
        }
    }
}
