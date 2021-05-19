package sk.stuba.fei.uim.oop.assignment3.product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Product getProductById(Long id);
    Product updateProduct(Long id,ProductRequest request);
    Product deleteProduct(Long id);
    Product addAmountProduct(Long id,ProductRequest request);
}
