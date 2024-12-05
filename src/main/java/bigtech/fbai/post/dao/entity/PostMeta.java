package bigtech.fbai.post.dao.entity;

import bigtech.fbai.common.dao.entity.BaseTimeEntity;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class PostMeta extends BaseTimeEntity {
    private String category;
    private int count;
}
