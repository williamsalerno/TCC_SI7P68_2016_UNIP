package br.com.springmvc.timetrialfactory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springmvc.timetrialfactory.daos.UserDAO;
import br.com.springmvc.timetrialfactory.models.User;

@Controller
@Transactional
public class UsersController {
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping("/login")
	public void login(User user){
		userDao.load(user.getId());
	}

}
