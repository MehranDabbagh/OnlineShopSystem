package service.serviceImplementation;

import entity.Category;
import entity.Product;
import repository.jdbc.CategoryRepositoryImpl;
import repository.jdbc.ProductRepositoryImpl;
import service.CategoryService;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductServiceImpl  implements ProductService {
    private ProductRepositoryImpl productRepository=new ProductRepositoryImpl();
    public ProductServiceImpl(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;

    }

    public ProductServiceImpl() {

    }
    @Override
    public void update(Product product) {
productRepository.update(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        List<Product> products=productRepository.findAll();
        List<Product> finalList=new ArrayList<>();
        for (Product product:products
             ) {

           if(Objects.equals(product.getCategory().getId(), category.getId())){
               finalList.add(product);
           }
        }
        return finalList;
    }

    @Override
    public Integer save(Product entity) {

        return productRepository.save(entity);
    }
}
