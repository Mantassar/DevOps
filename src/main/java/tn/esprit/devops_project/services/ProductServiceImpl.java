package tn.esprit.devops_project.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.devops_project.services.Iservices.IProductService;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {

    final ProductRepository productRepository;
    final StockRepository stockRepository;

    @Override
    public Product addProduct(Product product, Long idStock) {
        log.info("Adding a new product to stock with ID: {}", idStock);

        Stock stock = stockRepository.findById(idStock).orElseThrow(() -> {
            log.error("Stock with ID {} not found", idStock);
            return new NullPointerException("Stock not found");
        });

        product.setStock(stock);
        Product savedProduct = productRepository.save(product);
        log.info("Product with ID {} added successfully", savedProduct.getIdProduct());

        return savedProduct;
    }

    @Override
    public Product retrieveProduct(Long id) {
        log.info("Retrieving product with ID: {}", id);

        Product product = productRepository.findById(id).orElseThrow(() -> {
            log.error("Product with ID {} not found", id);
            return new NullPointerException("Product not found");
        });

        log.info("Product with ID {} retrieved successfully", id);
        return product;
    }

    @Override
    public List<Product> retreiveAllProduct() {
        log.info("Retrieving all products");

        List<Product> products = productRepository.findAll();
        log.info("Retrieved {} products", products.size());

        return products;
    }

    @Override
    public List<Product> retrieveProductByCategory(ProductCategory category) {
        log.info("Retrieving products by category: {}", category);

        List<Product> products = productRepository.findByCategory(category);
        log.info("Retrieved {} products in category {}", products.size(), category);

        return products;
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            log.info("Product with ID {} deleted successfully", id);
        } else {
            log.warn("Product with ID {} not found; nothing to delete", id);
        }
    }

    @Override
    public List<Product> retreiveProductStock(Long id) {
        log.info("Retrieving products in stock with ID: {}", id);

        List<Product> products = productRepository.findByStockIdStock(id);
        log.info("Retrieved {} products from stock with ID {}", products.size(), id);

        return products;
    }
}
