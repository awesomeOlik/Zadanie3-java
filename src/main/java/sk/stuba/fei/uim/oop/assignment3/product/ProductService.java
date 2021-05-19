package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private ProductRepository productrepository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.productrepository = repository;
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<Product>(this.productrepository.findAll());
    }

    @Override
    public Product create(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setAmount(request.getAmount());
        product.setUnit(request.getUnit());
        product.setPrice(request.getPrice());
        return this.productrepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return this.productrepository.findById(id).orElseThrow();
    }

    @Override
    public Product updateProduct(Long id,ProductRequest request) {
        Product product=getProductById(id);
        if(request.getName()!=null){
        product.setName(request.getName());
        }

        if (request.getDescription()!=null){
        product.setDescription(request.getDescription());
        }
        this.productrepository.save(product);
        return product;
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product=getProductById(id);
        this.productrepository.delete(product);
        return product;
    }

    @Override
    public Product addAmountProduct(Long id,ProductRequest request) {
        Product product=getProductById(id);
        product.setAmount(product.getAmount()+request.getAmount());
        this.productrepository.save(product);
        return product;
    }

}
