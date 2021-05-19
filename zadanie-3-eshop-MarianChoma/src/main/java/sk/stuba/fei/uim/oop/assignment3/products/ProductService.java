package sk.stuba.fei.uim.oop.assignment3.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ProductService implements IProductService{
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        Product product=new Product();
        product.setName(request.getName());
        product.setAmount(request.getAmount());
        product.setPrice(request.getPrice());
        product.setUnit(request.getUnit());
        product.setDescription(request.getDescription());
        return this.repository.save(product);
    }
    private Product checkingProduct(Optional<Product> product){
        return product.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Product findById(Long id){
        return checkingProduct(this.repository.findById(id));
    }

    @Override
    public Product updateProduct(Long id, ProductRequest update) {
        var product=findById(id);

        if(update.getName()!=null)
            product.setName(update.getName());
        if(update.getDescription()!=null)
            product.setDescription(update.getDescription());

        return this.repository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.delete(findById(id));
    }

    @Override
    public Map<String, Integer> increaseAmount(Long id, ProductRequest amount) {
        HashMap<String, Integer> map = new HashMap<>();
        var product=findById(id);
        product.increaseAmount(amount.getAmount());
        map.put("amount",product.getAmount());
        this.repository.save(product);
        return map;
    }

    @Override
    public Map<String, String> productAmount(Long id) {
        HashMap<String, String> map = new HashMap<>();
        var product=findById(id);
        map.put("amount",String.valueOf(product.getAmount()));
        return map;
    }

}
