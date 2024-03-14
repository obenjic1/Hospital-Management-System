package com.ppp.user.controller;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;


@Controller
public class authController {
	@Autowired
    private MessageSource messageSource;
	
	
//	@GetMapping("/")
//	public String homePage(Model model,  Locale locale) {
//		String greeting = messageSource.getMessage("greeting", null, locale);
//        model.addAttribute("greeting", greeting);
//		return "index";
//	}
	
	@GetMapping("/")
    public String getPage(HttpServletRequest request, Model model) {
        Locale locale = RequestContextUtils.getLocale(request);
        String content = messageSource.getMessage("greeting", null, locale);
        model.addAttribute("greeting", content);
        
        return "index";
    }
	
//	@GetMapping("/changeLanguage")
//    public String changeLanguage(@RequestParam("lang") String language, HttpServletRequest request) {
//        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
//        localeResolver.setLocale(request, null, new Locale(language));
//        return "redirect:/index";
//    }
	
	// Login controller
		@GetMapping("/login")
		public String login(HttpServletRequest http) {
			 Principal pr = http.getUserPrincipal();
			 if(pr == null) {
				 return "user_auth/login";
			 }else {
				 System.out.println("prrr" + pr.getName());
				 return "/";
			 }
		}

		// Logout controller
		@GetMapping("/logout")
		public void Logout() {
			
			return;
		}
		

}
