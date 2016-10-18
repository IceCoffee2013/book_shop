package net.shop.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import net.shop.dao.UserDAO;
import net.shop.vo.UserVO;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginService implements UserDetailsService{

	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Resource(name="passwordEncoder")
	private ShaPasswordEncoder encoder;
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		try {
			userService.updateDate(email);
			UserVO userVO = userDAO.selectOne(email);
			if(userVO == null){
				return null;
			}
			String password = userVO.getPassword();
			String role = userVO.getAuthority();

			if("ROLE_BAN".equals(role)){
				System.err.println("ban:" + userVO.getEmail());
				return null;
			}

			Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
			if("ROLE_USER".equals(role)){
				roles.add(new SimpleGrantedAuthority("ROLE_USER"));
				System.err.println("ROLE_USER | " + role);
			}else{
				roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				System.err.println("ROLE_ADMIN | " + role);
			}

			System.err.println("roles: " + roles);
			UserDetails user = new User(email,password,roles);
			return user;
 		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String encoding(String str){
		return encoder.encodePassword(str,null);
	}
}
