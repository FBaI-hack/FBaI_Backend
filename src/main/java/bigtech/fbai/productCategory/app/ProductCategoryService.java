package bigtech.fbai.productCategory.app;

import bigtech.fbai.productCategory.dao.ProductRepository;
import bigtech.fbai.productCategory.dao.entity.ProductCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductRepository productRepository;

    public ProductCategory getProductCategory(String name) {
        return productRepository.findByName(name);
    }
}
