package bigtech.fbai.post.dao.entity;

import bigtech.fbai.common.dao.entity.BaseTimeEntity;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
public class PostMeta{
    private String category;
    private int count;

    public static PostMeta create(String category, int count) {
        PostMeta postMeta = new PostMeta();
        postMeta.category = category;
        postMeta.count = count;
        return postMeta;
    }
}
