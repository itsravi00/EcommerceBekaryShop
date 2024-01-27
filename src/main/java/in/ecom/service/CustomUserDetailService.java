package in.ecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.ecom.model.CustomUserDetails;
import in.ecom.model.User;
import in.ecom.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findUserByEmail(email);
    user.orElseThrow(() -> new UsernameNotFoundException("User Not Availble"));
		
		return user.map(CustomUserDetails :: new).get();
	}

}
