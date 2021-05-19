package sk.stuba.fei.uim.oop.assignment3.products;



import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Product findById(Long id);
    Product updateProduct(Long id,ProductRequest update);
    void deleteById(Long id);
    Map<String,Integer> increaseAmount(Long id, ProductRequest amount);
    Map<String,String> productAmount(Long id);
}
