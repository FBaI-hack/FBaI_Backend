package bigtech.fbai.post.dao.entity;

import static jakarta.persistence.FetchType.LAZY;

import bigtech.fbai.productCategory.dao.entity.ProductCategory;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategoryEntity;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public static PostContent create(String title, String content, String productName, String postUrl,ProductCategory productCategoryEntity) {
        PostContent postContent = new PostContent();
        postContent.title = title;
        postContent.content = content;
        postContent.productName = productName;
        postContent.postUrl = postUrl;
        postContent.productCategoryEntity = productCategoryEntity;
        postContent.comments = new ArrayList<>();
        return postContent;
    }
}
