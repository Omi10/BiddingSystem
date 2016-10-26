package om.controllers;

import java.security.Principal;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import inti.ws.spring.exception.client.ForbiddenException;
import inti.ws.spring.exception.client.UnauthorizedException;
import om.entities.User;
import om.services.UserServiceImp;

@EnableOAuth2Sso
@RestController
public class MainController extends WebSecurityConfigurerAdapter {

	@Autowired
	UserServiceImp userService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public String login(HttpSession session ,Principal principal) throws ForbiddenException, UnauthorizedException {
		if (principal == null)
			throw new ForbiddenException("User google authenticaion is missing : Access Denied");
		OAuth2Authentication auth = (OAuth2Authentication) principal;
		//System.out.println(principal);
		  
		if (auth.isAuthenticated()) {
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, String> details = (LinkedHashMap<String, String>) auth.getUserAuthentication()
					.getDetails();
		
			/*String domain = details.get("hd");	
			if (!"practo.com".equalsIgnoreCase(domain))
			 throw new UnauthorizedException("Domain name other than 'practo.com' are not allowed");*/
			
			String email = details.get("email");
			String name = details.get("name");
			
			System.out.println(email+" "+name);
			User user = userService.getUserByEmail(email);
			
			if(user==null){
				user = userService.addUser(name,email);
			}
			session.setAttribute("user",user);
			return name;
		} else {
			throw new UnauthorizedException("Login failed. Please try again");
		}
		
	}

	
	@RequestMapping(value = "/logout")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/index.html";
	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().antMatchers("/**", "/login**", "/webjars/**", "/js/**").permitAll()
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().csrf().disable();
	}
}
