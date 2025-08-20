//package com.ppp.user.controller;
//
//import java.io.UnsupportedEncodingException;
//import com.ppp.user.model.User;
//import javax.mail.MessagingException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.security.auth.login.AccountNotFoundException;
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.weaver.bcel.Utility;
////#import org.aspectj.apache.bcel.classfile.Utility;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import org.springframework.ui.Model;
//
//
//import com.ppp.user.repository.UserRepository;
//import com.ppp.user.service.impl.UserServiceImpl;
//import com.ppp.user.model.User;
//
//import net.bytebuddy.utility.RandomString;
//
//@Controller
//public class restPasswordController {
//	
//	
//	
//	@Autowired
//	private UserServiceImpl userServiceImpl;
//	 @Autowired
//	 private JavaMailSender mailSender;
//	 
//	 
//		
//	
//		@PostMapping("/user/resetPassword/{email}")
//		public String resetPassword(HttpServletRequest request, @PathVariable("email") String userEmail, Model model)
//				throws Exception {
//			User user = userServiceImpl.findUserByEmail(userEmail);
//
//			if (user != null) {
//				String email = request.getParameter("email");
//				String token = RandomString.make(45);
//				try {
//					userServiceImpl.updateResetPasswordToken(token, email);
//					String resetPasswordLink = getSiteURL(request) + "/reset_password?token=" + token;
//
//					sendEmail(email, resetPasswordLink);
//					model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
//				} catch (AccountNotFoundException ex) {
//					model.addAttribute("error", ex.getMessage());
//				}
//			}
//			return "login";
//		}
//
//		@GetMapping("/reset_password")
//		public String showResetPasswordForm(@RequestParam(value = "token") String token, Model model) {
//			User user = userServiceImpl.getByResetPasswordToken(token);
//			model.addAttribute("token", token);
//
//			if (user == null) {
//				model.addAttribute("message", "Invalid Token");
//				return "message";
//			}
//			model.addAttribute("token", token);
//			return "reset_password_form";
//
//		}
//
//		@PostMapping("/reset_password")
//		public String processResetPassword(HttpServletRequest request, Model model) {
//			String token = request.getParameter("token");
//			String password = request.getParameter("password");
//
//			User user = userServiceImpl.getByResetPasswordToken(token);
//			model.addAttribute("title", "Reset your password");
//
//			if (user == null) {
//				model.addAttribute("message", "Invalid Token");
//				return "message";
//			} else {
//				userServiceImpl.updatePassword(user, password);
//
//				model.addAttribute("message", "You have successfully changed your password.");
//			}
//
//			return "message";
//		}
//
//		// send email function
//
////		public void sendEmail(String email, String resetPasswordLink) throws Exception {
////			MimeMessage message = mailSender.createMimeMessage();
////			MimeMessageHelper helper = new MimeMessageHelper(message, true);
////
////			helper.setFrom(new InternetAddress("contact@shopme.com", "Billing System Support"));
////			helper.setTo(email);
////
////			String subject = "Here's the link to reset your password";
////
////			String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
////					+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + resetPasswordLink
////					+ "\">Change my password</a></p>" + "<br>"
////					+ "<p>Ignore this email if you remember your password or you have not made the request.</p>";
////
////			helper.setSubject(subject);
////			// Set content as HTML
////			helper.setText(content, true);
////
////			mailSender.send(message);
////		}
//
//
//
//		public static String getSiteURL(HttpServletRequest request) {
//			String siteURL = request.getRequestURL().toString();
//			return siteURL.replace(request.getServletPath(), "");
//		}
//
//	}
//
//    
//	
//					
//	
//	   
//
//
