package com.example.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class BankAdmin {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;
    
    @OneToMany(mappedBy = "bankAdmin", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<Customer> customers;


	public BankAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankAdmin(String name, String username, String password, List<Customer> customers) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.customers = customers;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "BankAdmin [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", customers=" + customers + "]";
	}
    
    
}
