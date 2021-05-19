package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService{

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository){
        this.repository = repository;

        Product p1 = new Product();
        p1.setName("Byzon");
        p1.setDescription("Very nice, very pog.");
        p1.setAmount(1);
        p1.setUnit("What is unit?");
        p1.setPrice(20000);
        this.repository.save(p1);

        Product p2 = new Product();
        p2.setName("Python");
        p2.setDescription("Very long, sometimes bites.");
        p2.setAmount(5);
        p2.setUnit("Still don't know what unit is.");
        p2.setPrice(0.99);
        this.repository.save(p2);
    }

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setAmount(request.getAmount());
        newProduct.setUnit(request.getUnit());
        newProduct.setPrice(request.getPrice());

        return this.repository.save(newProduct);
    }

    @Override
    public Product getById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public Product updateProduct(Long id, ProductRequest request) {
        Product product = findMyProduct(id);

        if(request.getName() != null)
            product.setName(request.getName());
        if(request.getDescription() != null)
            product.setDescription(request.getDescription());

        return this.repository.save(product);
    }

    @Override
    public List<Product> deleteProduct(Long id) {
        Product product = findMyProduct(id);

        this.repository.deleteById(product.getId());

        return this.repository.findAll();
    }

    @Override
    public Map<String, Integer> productAmount(Long id) {
        HashMap<String, Integer> map = new HashMap<>();
        Product product = findMyProduct(id);
        map.put("amount", product.getAmount());

        return map;
    }

    @Override
    public Map<String, Integer> increaseAmount(Long id, ProductRequest request) {
        Product product = findMyProduct(id);

        product.setAmount(product.getAmount() + request.getAmount());

        HashMap<String, Integer> map = new HashMap<>();
        map.put("amount", product.getAmount());

        return map;
    }

    public Product findMyProduct(Long myId){
        return this.repository.findById(myId).orElseThrow();
    }

}
