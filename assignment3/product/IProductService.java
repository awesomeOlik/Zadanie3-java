package sk.stuba.fei.uim.oop.assignment3.product;

import java.util.List;
import java.util.Map;

public interface IProductService {

    List<Product> getAll();
    Product create(ProductRequest request);
    Product getById(Long id);
    Product updateProduct(Long id,ProductRequest request);
    List<Product> deleteProduct(Long id);
    Map<String, Integer> productAmount(Long id);
    Map<String, Integer> increaseAmount(Long id, ProductRequest request);
}
