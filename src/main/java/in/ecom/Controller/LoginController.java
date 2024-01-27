package in.ecom.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ecom.globle.GlobleData;
import in.ecom.model.Role;
import in.ecom.model.User;
import in.ecom.repository.RoleRepository;
import in.ecom.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		GlobleData.cart.clear();
		model.addAttribute("user", new User());
		return "login";
	}
	
	
	  @PostMapping("/login") public String login(@Valid @ModelAttribute("user")
	  User user, BindingResult bindingResult) {
		  if(bindingResult.hasErrors()) {
			  return "login";
	  
		  }
		  return "redirect:/";
	  }
	 
	
	
	@GetMapping("/register")
	public String register() {
		
		return "register";
	}
	
	@PostMapping("/register")
	public String saveUser(@ModelAttribute("user") User user, HttpServletRequest httpServletRequest)throws ServletException {
		String password = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findById((long) 2).get());
		
		user.setRoles(roles);
		userRepository.save(user);
		httpServletRequest.login(user.getEmail(), password);
		
		
		return "redirect:/";
		
	}
	
	@GetMapping("/forgotpassword")
    public String showForgotPasswordForm(Model model) {
        model.addAttribute("forgotPasswordForm", new User());
        return "forgetPassword";
    }

}
