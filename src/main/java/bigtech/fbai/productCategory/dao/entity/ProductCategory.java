package bigtech.fbai.productCategory.dao.entity;

import bigtech.fbai.post.dao.entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Entity
@Slf4j
@NoArgsConstructor
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @Column(name = "product_category_id")
    private long productCategoryId;

    private String name;
    private String imageUrl;
    private int count;


    public static ProductCategory from(String name, String imageUrl, int count) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.name = name;
        productCategory.imageUrl = imageUrl;
        productCategory.count = count;
        return productCategory;
    }

}
