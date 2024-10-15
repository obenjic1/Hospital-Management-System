package com.ppp.user.controller;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ppp.user.model.User;
import com.ppp.user.repository.UserRepository;


@Controller
public class authController {
	@Autowired
    private MessageSource messageSource;
	@Autowired
	private UserRepository userRepository;
	

	@GetMapping("/")
    public String getPage(HttpServletRequest request, Model model, Authentication authentication) {
        Locale locale = RequestContextUtils.getLocale(request);
        String content = messageSource.getMessage("greeting", null, locale);
        model.addAttribute("greeting", content);
		Principal pr = request.getUserPrincipal();
		if(pr == null) {
			return "user_auth/login";
		}else {
			User user = userRepository.findByUsername(authentication.getName()) ;

			model.addAttribute("user", user);
			return "index";
		}
		
    }

	// Login controller
		@GetMapping("/login")
		public String login(HttpServletRequest http, Model model , Authentication authentication) {
			 Principal pr = http.getUserPrincipal();
			 if(pr == null) {
				 return "user_auth/login";
			 }else {
				 User user = userRepository.findByUsername(authentication.getName()) ;

				 model.addAttribute("user", user);
				 return "index";
			 }
		}

		// Logout controller
		@GetMapping("/logout")
		public String logout(HttpServletRequest request, Authentication authentication) {
			User user = (User) authentication.getDetails();
		//	user.setActive(false);
			request.getSession().invalidate();
			return "user_auth/login";
		}
		// reset password
		@GetMapping("/password/forgotten")
		public String displayPwdForm() {
		
			return "user_auth/resetpassword";
		}
		
		  //Public page
	    @RequestMapping(value = "/login/sw.js")
	    public String serveWorker() {

	        return "sw";
	    }
		  //Public page
	    @RequestMapping(value = "/sw.js")
	    public String serve2Worker() {

	        return "sw";
	    }


}
