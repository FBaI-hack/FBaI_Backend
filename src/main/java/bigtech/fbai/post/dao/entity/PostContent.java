package bigtech.fbai.post.dao.entity;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
@Embeddable
public class PostContent {

    private String title;

    private String content;

    private String productName;

    private String postUrl;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategoryEntity;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();
}
