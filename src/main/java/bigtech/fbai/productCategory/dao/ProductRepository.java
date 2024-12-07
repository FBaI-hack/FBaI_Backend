package bigtech.fbai.productCategory.dao;

import bigtech.fbai.productCategory.dao.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductCategory, Long> {

    @Query("select pc from ProductCategory pc"
            + " where pc.name = :name")
    ProductCategory findByName(@Param("name") String name);
}
