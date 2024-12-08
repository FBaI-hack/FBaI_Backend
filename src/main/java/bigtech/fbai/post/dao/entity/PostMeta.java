package bigtech.fbai.post.dao.entity;

import bigtech.fbai.common.dao.entity.BaseTimeEntity;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
public class PostMeta{
    private Category category;
    private int count;

    public static PostMeta create(Category category, int count) {
        PostMeta postMeta = new PostMeta();
        postMeta.category = category;
        postMeta.count = count;
        return postMeta;
    }

    public void update(Category category, int count) {
        this.category =category;
        this.count =count;
    }
}
