package in.ecom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import in.ecom.globle.GlobleData;
import in.ecom.service.CategoryService;
import in.ecom.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping({"/", "/home"})
	public String home(Model model){
		 model.addAttribute("cartCount",GlobleData.cart.size());
			
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		 model.addAttribute("cartCount",GlobleData.cart.size());
		model.addAttribute("categories",categoryService.getAllCategories());
		model.addAttribute("products",productService.getAllProducts());
		
		return "shop";
		
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategoryId(Model model,@PathVariable Long id) {
		 model.addAttribute("cartCount",GlobleData.cart.size());
		model.addAttribute("categories",categoryService.getAllCategories());
		model.addAttribute("products",productService.getAllProductByCategoryId(id));
		
		return "shop";
		
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model,@PathVariable Long id) {
		 model.addAttribute("cartCount",GlobleData.cart.size());
		 model.addAttribute("product",productService.getProductById(id).get()); 
		
		return "viewProduct";
		
	}
	

}
