package in.ecom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import in.ecom.globle.GlobleData;
import in.ecom.model.Product;
import in.ecom.service.ProductService;

@Controller
public class CartController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCat(@PathVariable Long id) {
		GlobleData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}
	
	 @GetMapping("/cart")
	 public String cartGet(Model model) {
		 model.addAttribute("cartCount",GlobleData.cart.size());
		 model.addAttribute("total", GlobleData.cart.stream().mapToDouble(Product :: getPrice ).sum());
		 model.addAttribute("cart", GlobleData.cart);
		 
		 return "cart";
	 }
	 
	 @GetMapping("/cart/removeItem/{index}")
	 public String cartItemRemove(@PathVariable int index) {
		 GlobleData.cart.remove(index);
		 
		 return "redirect:/cart";
	 }
	 @GetMapping("/checkout")
	 public String checkout(Model model) {
		 model.addAttribute("total", GlobleData.cart.stream().mapToDouble(Product :: getPrice ).sum());
			
		 return "/checkout";
	 }
	 

}
