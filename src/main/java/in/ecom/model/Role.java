package in.ecom.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

//import jakarta.persistence.Entity;

@Entity
@Table(name= "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private Long id;
	
	@Column(nullable = false,unique =  true)
	@NotEmpty
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	
	private List<User> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	

}
