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
    private CategoryService categoryService;
    private CategoryRepositoryImpl categoryRepository=new CategoryRepositoryImpl();

    public ProductServiceImpl(ProductRepositoryImpl productRepository, CategoryService categoryService, CategoryRepositoryImpl categoryRepository) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
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
        List<Product> finallist=new ArrayList<>();
        for (Product product:products
             ) {
           Category category1=categoryRepository.findById(product.getCategory().getId());
           if(Objects.equals(category1.getId(), category.getId())){
               finallist.add(product);
           }
        }
        return finallist;
    }

    @Override
    public Integer save(Product entity) {

        return productRepository.save(entity);
    }
}
