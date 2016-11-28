package com.example.springbootweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springbootweb.bean.UserForm;
@Service
public class UserService {
	
	public List<UserForm> listUserForm;

	public List<UserForm> getListUserForm() {
		return listUserForm;
	}

	public void setListUserForm(List<UserForm> listUserForm) {
		this.listUserForm = listUserForm;
	}
	

}
