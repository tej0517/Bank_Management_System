package com.example.entity;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String email;
	private String address;
	private String phone;
	private String date;
	private String type;
	private String fileName;
	private String filePath;

	@Lob
	private byte[] fileData; // Add this field if you want to store file data as a blob.

	private Double balance = 0.0;
	private Boolean isApproved = false;

	@ManyToOne
	@JoinColumn(name = "bank_admin_id")
	 private BankAdmin bankAdmin;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String email, String address, String phone, String date, String type, String fileName,
			String filePath, byte[] fileData, Double balance, Boolean isApproved, BankAdmin bankAdmin) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.date = date;
		this.type = type;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileData = fileData;
		this.balance = balance;
		this.isApproved = isApproved;
		this.bankAdmin = bankAdmin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public BankAdmin getBankAdmin() {
		return bankAdmin;
	}

	public void setBankAdmin(BankAdmin bankAdmin) {
		this.bankAdmin = bankAdmin;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", phone="
				+ phone + ", date=" + date + ", type=" + type + ", fileName=" + fileName + ", filePath=" + filePath
				+ ", fileData=" + Arrays.toString(fileData) + ", balance=" + balance + ", isApproved=" + isApproved
				+ ", bankAdmin=" + bankAdmin + "]";
	}

}
