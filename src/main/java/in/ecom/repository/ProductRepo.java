package in.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ecom.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	
	List<Product> findAllByCategory_Id(Long id);

}
