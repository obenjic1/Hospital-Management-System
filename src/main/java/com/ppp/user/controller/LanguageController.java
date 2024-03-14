package com.ppp.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("language")
public class LanguageController {
	
	 @GetMapping("/change-locale")
	    public String changeLocale(@RequestParam(name = "lang", required = false) String lang,
	                               HttpServletRequest request,
	                               HttpServletResponse response,
	                               RedirectAttributes redirectAttributes) {

	        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
	        localeResolver.setLocale(request, response, StringUtils.parseLocale(lang));

	        redirectAttributes.addFlashAttribute("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE", localeResolver.resolveLocale(request));

	        return "redirect:/";
	    }

}
