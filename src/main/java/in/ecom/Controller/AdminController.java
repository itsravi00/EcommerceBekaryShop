package in.ecom.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.ecom.dto.ProductDto;
import in.ecom.model.Category;
import in.ecom.model.Product;
import in.ecom.service.CategoryService;
import in.ecom.service.ProductService;

@Controller
public class AdminController {
	public static String uploadDir = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\productImages";
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;

	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
		
		
	}
	
	@GetMapping("/admin/categories")
	public String categories(Model model) {
		model.addAttribute("categories", this.categoryService.getAllCategories());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String categoriesAdd(Model model) {
		model.addAttribute("category", new Category());
		
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String saveCategories(@ModelAttribute("category") Category category) {
		this.categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategories(@PathVariable Long id) {
		this.categoryService.deleteCategory(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCategories(@PathVariable Long id,Model model) {
		
		Optional<Category> category = this.categoryService.getCategoryById(id);
		if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}else {
			return "404";
		}
	}
	
	//Start product
	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("products", this.productService.getAllProducts());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String productAdd(Model model) {
		model.addAttribute("productDTO", new ProductDto());
		model.addAttribute("categories", this.categoryService.getAllCategories());
		
		return "productsAdd"; 
	}
	
	@PostMapping("/admin/products/add")
	public String saveProductAdd(@ModelAttribute("productDTO")ProductDto productDto,
			@RequestParam("productImage")MultipartFile file,
			@RequestParam("imgName")String imgName) throws IOException {
		
		Product product = new Product();
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setCategory(this.categoryService.getCategoryById(productDto.getCategoryId()).get());
		product.setPrice(productDto.getPrice());
		product.setWeight(productDto.getWeight());
		product.setDescription(productDto.getDescription());
		
		String imageUuid ="";
		
		
		if(!file.isEmpty()) {
			imageUuid = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUuid);
			Files.write(fileNameAndPath,  file.getBytes());
		}else {
			imageUuid = imgName;
		}
		
		product.setImageName(imageUuid);
		
		this.productService.saveProduct(product);
		
		
		
		return "redirect:/admin/products"; 
	}
	
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProducts(@PathVariable Long id) {
		this.productService.deleteProductById(id);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/update/{id}")
	public String updateProducts(@PathVariable Long id,Model model) {
		
		Product product = this.productService.getProductById(id).get();
		ProductDto productDto = new ProductDto();
		
		productDto.setId(productDto.getId());
		productDto.setName(product.getName());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setPrice(product.getPrice());
		productDto.setWeight(product.getWeight());
		productDto.setDescription(product.getDescription());
		productDto.setImageName(product.getImageName());
		
		model.addAttribute("productDTO", productDto);
		model.addAttribute("categories", this.categoryService.getAllCategories());
		
		return "productsAdd";
	}
	
	
	
}
