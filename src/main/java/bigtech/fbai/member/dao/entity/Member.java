package bigtech.fbai.member.dao.entity;

import bigtech.fbai.common.exception.CommonException;
import bigtech.fbai.common.exception.ErrorCode;
import jakarta.persistence.Column;
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

    @Column(length = 500)
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

    public void validateMemberId(Long memberId) {
        if (!this.id.equals(memberId)) {
            throw new CommonException(ErrorCode.ACCESS_DENIED);
        }
    }
}
