package in.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecom.model.Category;
import in.ecom.model.Product;
import in.ecom.repository.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
//	for fetch All Product
	public List<Product> getAllProducts(){
		return this.productRepo.findAll();
	}
	
	//for save product
	public void saveProduct(Product product) {
		this.productRepo.save(product);
	}
	
//	for delete product
	public void deleteProductById(Long id) {
		this.productRepo.deleteById(id);
	}
	
	//for update product
	public Optional<Product> getProductById(Long id) {
		return this.productRepo.findById(id);
	}
	
//	for Search by Categories
	public List<Product> getAllProductByCategoryId(Long id){
		return this.productRepo.findAllByCategory_Id(id);
	}
	

}
