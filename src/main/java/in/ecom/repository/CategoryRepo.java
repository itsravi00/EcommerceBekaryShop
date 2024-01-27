package in.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ecom.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
