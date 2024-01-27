package in.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecom.model.Category;
import in.ecom.repository.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
//	For Fetch  All Categories
	public List<Category> getAllCategories(){
		
		return this.categoryRepo.findAll();
		
	}
	
//	For Add a Categories
	public void addCategory(Category category) {
		
		this.categoryRepo.save(category);
		
	}
	
	
	//for Delete Categories
	public void deleteCategory(Long id){
		
		this.categoryRepo.deleteById(id);
		
	}
	
//	for update Categories
	public Optional<Category> getCategoryById(Long id) {
	return	this.categoryRepo.findById(id);
	}

}
