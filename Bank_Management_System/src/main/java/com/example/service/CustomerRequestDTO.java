package com.example.service;

public class CustomerRequestDTO {

    private String name;
    private String email;
    private String address;
    private String phone;
    private String date;
    private String type;
    private String fileName;
    private String filePath;
    private double amount;
    
    

	public CustomerRequestDTO() {
        super();
    }

    public CustomerRequestDTO(String name, String email, String address, String phone, String date, String type,
                              String fileName, String filePath) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.type = type;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public double getAmount() {
    	return amount;
    }
    
    public void setAmount(double amount) {
    	this.amount = amount;
    }
    
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

    // Getters and Setters for all fields...
    
}
