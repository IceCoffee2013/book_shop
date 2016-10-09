package net.shop.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import net.shop.service.GoodsService;
import net.shop.service.LoginService;
import net.shop.service.UserService;
import net.shop.util.Constant;
import net.shop.util.ImageUtil;
import net.shop.util.Util;
import net.shop.vo.*;

import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Langley
 *
 */
@Controller
@RequestMapping(value= "/user")
public class UserController {
//	public static final String loadPath = "/Users/Langley/Desktop/lab_shop/src/main/webapp/resource/upload/";

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name = "goodsService")
	private GoodsService goodsService;

	@Resource(name = "util")
	private Util util;
	
	@Resource(name = "imageUtil")
	private ImageUtil imageUtil;
	
	@RequestMapping(value= "/userList.do")
	public ModelAndView userList(@RequestParam(value="p",required=false) String p,
			@RequestParam(value="order",required=false) String order,
			@RequestParam(value="q",required=false) String q,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
        
		ModelAndView modelandview = new ModelAndView();
		
		String requestPageString = p;		//paging
		String orderCond = order;			//lining up
		String keyword = q;					//searching
		
        if(requestPageString == null || requestPageString.equals("")) {
            requestPageString = "1";
        }
        if(orderCond == null || orderCond.equals("")) {
        	orderCond = "no_asc";
        }
        if(keyword == null || keyword.equals("")) {
        	keyword = null;
        }
        
        int requestPage = Integer.parseInt(requestPageString);
		
        if(requestPage <= 0){
            throw new IllegalArgumentException("requestPage <= 0 : " + requestPage);
        }
        
        int totalCount;
        if(keyword  == null || keyword.equals(""))  {
            totalCount = userService.count();
        } else {
            totalCount = userService.count(keyword);
        }

        // page cut
        PagingVO pagingVO = util.paging(requestPage, 10, totalCount);
        modelandview.addObject("pagingVO", pagingVO);
        modelandview.setViewName("/user/userList");
        
        if(totalCount == 0){
            modelandview.addObject("userVOList", Collections.<UserVO>emptyList());
            request.setAttribute("hasUser", false);
            return modelandview;
        }
        
		List<UserVO> lists = userService.selectList(pagingVO.getFirstRow()-1,pagingVO.getEndRow(),orderCond,keyword);
		if(lists.isEmpty()){
			request.setAttribute("hasUser", false);
            return modelandview;
		}
		
		modelandview.addObject("userVOList", lists);
		modelandview.addObject("order", orderCond);
		modelandview.addObject("keyword", keyword);
		request.setAttribute("hasUser", true);
		
		return modelandview;
	}
	
	@RequestMapping("/userAdd.do")
	public String userAdd() throws Exception{
		return "/user/userAdd";
	}
	@SuppressWarnings("static-access")
	@RequestMapping(value= "/userAdd.do", method=RequestMethod.POST)
	public String userAdd(@RequestParam("firstName")String firstName,
			@RequestParam("lastName")String lastName,
			@RequestParam("email")String email,
			@RequestParam("password")String password,
			@RequestParam("address")String address,
			@RequestParam("postcode")String postcode,
			@RequestParam(value="thumnail",required=false) MultipartFile multipartFile,
						  @RequestParam(value = "card") String card,
			Model model,HttpServletRequest request) throws Exception{

		String loadPath = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_PATH);
		
		String imagePath = "default.jpg";
		int intcode;
		
		if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty() || postcode.isEmpty()){
			model.addAttribute("say", "Wrong Input");
			model.addAttribute("url", request.getContextPath()+"/user/userAdd.do");
			return "/error/alert";
		}
		
		if(firstName.length() < 3 || password.length() < 3 || email.length() < 6 || lastName.length() < 3){
			model.addAttribute("say", "Wrong Input: firstName < 3 or password < 3 or email < 6 or lastName < 3.");
			model.addAttribute("url", request.getContextPath()+"/user/userAdd.do");
			return "/error/alert";
		}
		
		try{
			intcode = Integer.parseInt(postcode);
		}catch(Exception e){
			model.addAttribute("say", "Wrong Postcode");
			model.addAttribute("url", request.getContextPath()+"/user/userAdd.do");
			return "/error/alert";
		}
		
		if (intcode>999999 || intcode<100000) {
			model.addAttribute("say", "Wrong Postcode");
			model.addAttribute("url", request.getContextPath()+"/user/userAdd.do");
			return "/error/alert";
		}
		
		if (userService.selectOne(email)){
			model.addAttribute("say", "It has already used");
			model.addAttribute("url", request.getContextPath()+"/user/userAdd.do");
			return "/error/alert";
		}
		
		//upload thumnail
		if ( multipartFile.getSize() > 0 ) {
			String fileName = multipartFile.getOriginalFilename();
			if ( imageUtil.isImageFile ( fileName))
			{
				Calendar cal = Calendar.getInstance();
				String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
				File uploadFile =  File.createTempFile(loadPath, fileType);
				multipartFile.transferTo(uploadFile);
				String tempName =  cal.getTimeInMillis() + "";
				String replaceName = tempName+"_thum"+ fileType;
				File thumbnail =  new File (loadPath+replaceName);
				imageUtil.uploadImage(uploadFile, thumbnail, 100, 100);
				imagePath = replaceName;
				System.err.println("db img path:"+ imagePath);
			}
			else{
				model.addAttribute("say", "Wrong Image");
				model.addAttribute("url", request.getContextPath()+"/user/userAdd.do");
				return "/error/alert";
			}
		}
		
		String encode = loginService.encoding(password);
		UserVO userVO = new UserVO(firstName,lastName,email,encode,address,intcode,imagePath,card);
		userService.insert(userVO);

		// handle confirm email. TODO
		String urlPath = "http://" + request.getServerName() + ":" + request.getServerPort();
		EmailVO emailVO = util.getRegisterConfirmEmailVO(userService.selectOneVo(email), urlPath);
		util.sendEmail(emailVO);

		return "redirect:/main/main.do";
	}

	@RequestMapping("/userEdit.do")
	public String userEdit(Authentication auth,Model model,HttpServletRequest request) throws Exception{
		if (isRememberMeAuthenticated()) {
			//send login for update
			setRememberMeTargetUrlToSession(request);
			model.addAttribute("loginUpdate", true);
			return "/main/login";
		} else {
			UserDetails vo = (UserDetails) auth.getPrincipal();
			UserVO userVO = userService.selectOneVo(vo.getUsername());
			model.addAttribute("uservo", userVO);
			return "/user/userEdit";
		}
	}
	@SuppressWarnings("static-access")
	@RequestMapping(value= "/userEdit.do", method=RequestMethod.POST)
	public String userEdit(@RequestParam("firstName")String firstName,
			@RequestParam("lastName")String lastName,
			@RequestParam("address")String address,
			@RequestParam("postcode")String postcode,
			@RequestParam(value="thumnail",required=false) MultipartFile multipartFile,
						   @RequestParam(value = "card") String card,
			Model model,Authentication auth,HttpServletRequest request) throws Exception{

		String loadPath = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_PATH);

		UserDetails vo = (UserDetails) auth.getPrincipal();
		String email = vo.getUsername();
		UserVO userVO = userService.selectOneVo(email);
		String imagePath = userVO.getImagePath();
		int intcode;
		
		if(firstName.isEmpty() || lastName.isEmpty() || address.isEmpty()){
			firstName = userVO.getFirstName();
			lastName = userVO.getLastName();
			address = userVO.getAddress();
		}
		try{
			intcode = Integer.parseInt(postcode);
		}catch(Exception e){
			model.addAttribute("say", "Wrong Postcode");
			model.addAttribute("url", request.getContextPath()+"/user/userAdd.do");
			return "/error/alert";
		}
		
		if (intcode>999999 || intcode<100000) {
			model.addAttribute("say", "Wrong Postcode");
			model.addAttribute("url", request.getContextPath()+"/user/userAdd.do");
			return "/error/alert";
		}
		
		if(firstName.length() < 3 || lastName.length() < 3 || address.length()<3){
			model.addAttribute("say", "Wrong Input");
			model.addAttribute("url", request.getContextPath()+"/user/userEdit.do");
			return "/error/alert";
		}
		
		//upload thumnail
		if ( multipartFile.getSize() > 0 ) {
			String fileName = multipartFile.getOriginalFilename();
			if ( imageUtil.isImageFile (fileName))
			{
				Calendar cal = Calendar.getInstance();
				String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
//				File uploadFile =  File.createTempFile( "/Users/Langley", fileType);  // TODO
				File uploadFile =  File.createTempFile(loadPath, fileType);  // TODO
				multipartFile.transferTo(uploadFile);
				String tempName =  cal.getTimeInMillis() + "";
				String replaceName = tempName+"_thum"+ fileType;
				File thumbnail =  new File (loadPath+replaceName);
				imageUtil.uploadImage( uploadFile, thumbnail, 100, 100);
				imagePath = replaceName;
			}
			else{
				model.addAttribute("say", "Wrong Image");
				model.addAttribute("url", request.getContextPath()+"/user/userAdd.do");
				return "/error/alert";
			}
		}

		UserVO loadVO = new UserVO(firstName,lastName,email,address,intcode,imagePath,card);
		userService.update(loadVO);
	
		return "redirect:/main/main.do";
	}
	
	@RequestMapping("/userDelete.do")
	public String userDelete() throws Exception{
		return "/user/userDelete";
	}
	@RequestMapping(value= "/userDelete.do", method=RequestMethod.POST)
	public String userDelete(Authentication auth,HttpServletRequest request, Model model) throws Exception{
		String reason = request.getParameter("check");
		System.out.println(reason);
		UserDetails vo = (UserDetails) auth.getPrincipal();
		String email = vo.getUsername();
		if(userService.orderCount(email)!=0){
			model.addAttribute("say", "You have orders");
			model.addAttribute("url", request.getContextPath()+"/main/main.do");
			return "/error/alert";
		}
		userService.delete(email);
		request.getSession().invalidate();
		return "redirect:/main/main.do";
	}

	/**
	 * Admin Only
	 * Delete Other User
	 * @param auth
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/adminDelete.do", method=RequestMethod.POST)
	public String adminDelete(@RequestParam("userEmail")String userEmail,
			Authentication auth,HttpServletRequest request, Model model) throws Exception{
		System.out.println("userEmail:" + userEmail);
		UserDetails vo = (UserDetails) auth.getPrincipal();
		String email = vo.getUsername();
		String authority = userService.selectOneVo(email).getAuthority();
		if (authority == null || !authority.equals("ROLE_ADMIN")) {
			model.addAttribute("say", "You Are Not Admin.");
			model.addAttribute("url", request.getContextPath()+"/main/main.do");
			return "/error/alert";
		}
		if (email.equals(userEmail)) {
			model.addAttribute("say", "Can Not Delete Yourself.");
			model.addAttribute("url", request.getContextPath()+"/main/main.do");
			return "/error/alert";
		}
		if (userEmail == null || userService.selectOneVo(userEmail) == null) {
			model.addAttribute("say", "Delete User Is NULL.");
			model.addAttribute("url", request.getContextPath()+"/main/main.do");
			return "/error/alert";
		}

		userService.delete(userEmail);
//		request.getSession().invalidate();
		return "redirect:/user/userList.do";
	}
	
	//change Authority
	@RequestMapping(value="/giveAuth.do")
	public String userGiveAuth(@RequestParam("email")String email,
			@RequestParam("auth")String auth) throws Exception{
		
		if(auth == null) auth="true";
		String trimAuth = auth;
		String putAuth = null;

		if (trimAuth.equals("user")) {
			putAuth = "ROLE_USER";
		} else if (trimAuth.equals("admin")) {
			putAuth = "ROLE_ADMIN";
		} else {
			putAuth = "ROLE_BAN";
		}
		
		userService.updateAuth(email, putAuth);
		return "redirect:/user/userList.do";
	}

	@RequestMapping(value="/emailConfirm.do")
	public ModelAndView userConfirm(@RequestParam("id")int id,
							  @RequestParam("email")String email,
							  @RequestParam("pwd") String password,
							  @RequestParam("expire") long expire,
									HttpServletRequest request) throws Exception{

		ModelAndView modelAndView = new ModelAndView();
		long currentTime = System.currentTimeMillis();
		System.err.println("id:" + id + " email:" + email + " pwd:"+ password + " expire:" + expire);

		if (currentTime > expire) {
			modelAndView.addObject("say", "Email Expire!");
			modelAndView.addObject("url", request.getContextPath() + "/main/login.do");
			modelAndView.setViewName("/error/alert");
			return modelAndView;
		}

		UserVO userVO = userService.selectOneVo(email);
		if (userVO == null) {
			modelAndView.addObject("say", "User not found!");
			modelAndView.addObject("url", request.getContextPath() + "/main/login.do");
			modelAndView.setViewName("/error/alert");
			return modelAndView;
		}

		if (userVO.getEmail().equals(email) && userVO.getPassword().equals(password)) {
			String auth = "ROLE_USER";
			userService.updateAuth(email, auth);
			modelAndView.addObject("say", "Success!");
			modelAndView.addObject("url", request.getContextPath() + "/main/login.do");
			modelAndView.setViewName("/error/alert");
			return modelAndView;
		}

		modelAndView.addObject("say", "invalid email!");
		modelAndView.addObject("url", request.getContextPath() + "/main/login.do");
		modelAndView.setViewName("/error/alert");

		return modelAndView;
	}
	
	//ajax add wishlist
	@RequestMapping(value="/addWishlist.do", method=RequestMethod.POST)
	public void addWishlist(@RequestParam("email")String email,
	@RequestParam("check")String check, @RequestParam("no")int boardNumber,
	HttpServletResponse response) throws Exception{
		
		if(check == null) check="no";
		
		if(check.compareTo("true") != 0){
			response.getWriter().print("404");
			return;
		}
		
		if(userService.checkWishlist(email, boardNumber)){
			response.getWriter().print("400");
			return;
		}else{
			userService.addWishlist(email, boardNumber);
			response.getWriter().print("200");
			return;
		}
	}
	
	@RequestMapping(value="/wishlist.do")
	public ModelAndView wishList(@RequestParam(value="p",required=false) String p,
			@RequestParam(value="q",required=false) String q,
			HttpServletRequest request, Authentication auth) throws Exception {
		
		ModelAndView modelandview = new ModelAndView();
		String requestPageString = p;		//paging
		String keyword = q;					//searching
		UserDetails vo = (UserDetails) auth.getPrincipal();
		String email = vo.getUsername();
		
		if(requestPageString == null || requestPageString.equals("")) {
            requestPageString = "1";
        }
        if(keyword == null || keyword.equals("")) {
        	keyword = null;
        }
        
        int requestPage = Integer.parseInt(requestPageString);
        
        if(requestPage <= 0){
            throw new IllegalArgumentException("requestPage <= 0 : " + requestPage);
        }
        
        int totalCount;
        if(keyword  == null || keyword.equals(""))  {
            totalCount = userService.wishCount();
        } else {
            totalCount = userService.wishCount(keyword);
        }
        
        PagingVO pagingVO = util.paging(requestPage, 10, totalCount);
        modelandview.addObject("pagingVO", pagingVO);
        modelandview.setViewName("/user/wishlist");
        
        if(totalCount == 0){
            modelandview.addObject("wishlist", Collections.<UserVO>emptyList());
            request.setAttribute("hasUser", false);
            return modelandview;
        }
        
        List<WishlistVO> lists = userService.wishList(pagingVO.getFirstRow()-1,pagingVO.getEndRow(),keyword,email);
        if(lists.isEmpty()){
			request.setAttribute("hasUser", false);
            return modelandview;
		}
        modelandview.addObject("wishlist", lists);
		modelandview.addObject("keyword", keyword);
		request.setAttribute("hasUser", true);
		
		return modelandview; 
	}
	
	//delete wishlist
	@RequestMapping(value="/delWishlist.do")
	public String delWishlist(@RequestParam("choice")String choice,
			@RequestParam("email")String email,@RequestParam("no")int boardNumber,
			HttpServletRequest request, Model model) throws Exception{
		
		if(choice == null || choice.isEmpty()){
			model.addAttribute("say", "Wrong Input");
			model.addAttribute("url", request.getContextPath()+"/user/wishlist.do");
			return "/error/alert";
		}
		
		if(choice.compareTo("true") != 0){
			return "redirect:/user/wishlist.do";
		}
		
		if(userService.delWishlist(email,boardNumber) == 0){
			model.addAttribute("say", "Wrong already deleted");
			model.addAttribute("url", request.getContextPath()+"/user/wishlist.do");
			return "/error/alert";
		}
		
		return "redirect:/user/wishlist.do";	
	}
	
	//ajax change password
	@RequestMapping(value="/changePwd.do", method=RequestMethod.POST)
	public void changePwd(@RequestParam("password") String password,
			HttpServletResponse response,Authentication auth
			) throws Exception {
		
		if(password.length() < 3 || password.isEmpty()){
			response.getWriter().print("404");
			return;
		}
		
		UserDetails vo = (UserDetails) auth.getPrincipal();
		String email = vo.getUsername();
		String encode = loginService.encoding(password);
		userService.updatePassword(email, encode);
		response.getWriter().print("Successfully Changed");
	}

	//ajax check Email
	@RequestMapping(value="/checkEmail.do", method=RequestMethod.POST)
	public void checkEmail(@RequestParam("email") String email,
			HttpServletResponse response) throws Exception {
		
		if(email.length() < 3 || email.isEmpty()){
			response.getWriter().print("404");
			return;
		}
		
		if (userService.selectOne(email)){
			response.getWriter().print("400");
			return;
		}else{
			response.getWriter().print("You can use this email");
		}
		
	}
	
	//orderlist
	@RequestMapping("/orders.do")
	public ModelAndView orderList(@RequestParam(value="p",required=false) String p,
			HttpServletRequest request, Authentication auth) throws Exception{
		
		ModelAndView modelandview = new ModelAndView();
		String requestPageString = p;		//paging
		UserDetails vo = (UserDetails) auth.getPrincipal();
		String email = vo.getUsername();
	
		if(requestPageString == null || requestPageString.equals("")) {
            requestPageString = "1";
        }
		
		int requestPage = Integer.parseInt(requestPageString);
		
        if(requestPage <= 0){
            throw new IllegalArgumentException("requestPage <= 0 : " + requestPage);
        }
        
		int totalCount = userService.orderCount();
		
        PagingVO pagingVO = util.paging(requestPage, 200, totalCount);
        modelandview.addObject("pagingVO", pagingVO);
        modelandview.setViewName("/user/userOrder");
        if(totalCount == 0){
            modelandview.addObject("lists", null);
            return modelandview;
        }
        
		List<OrdersVO> lists = userService.ordersList(pagingVO.getFirstRow()-1,pagingVO.getEndRow(),email);
		if(lists.isEmpty()){
			modelandview.addObject("lists", null);
            return modelandview;
		}
		modelandview.addObject("lists", lists);

		return modelandview;
	}
	
	//delete orderlist
	@RequestMapping(value="/delOrderlist.do")
	public String delOrderlist(@RequestParam("choice")String choice,
			@RequestParam("no")int number,
			HttpServletRequest request, Model model) throws Exception{
			
		if(choice == null || choice.isEmpty()){
			model.addAttribute("say", "Wrong Input");
			model.addAttribute("url", request.getContextPath()+"/user/orders.do");
			return "/error/alert";
		}
			
		if(choice.compareTo("true") != 0){
			return "redirect:/user/orders.do";
		}
			
		if(userService.delorderlist(number) == 0){
			model.addAttribute("say", "Wrong already deleted");
			model.addAttribute("url", request.getContextPath()+"/user/orders.do");
			return "/error/alert";
		}
			
		return "redirect:/user/orders.do";	
	}
		
	/**
	 * Check if user is login by remember me cookie
	 */
	private boolean isRememberMeAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() == null) {
			//System.out.println("false");
			return false;
		}
		//System.out.println("true");
		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
		
	}
	
	/**
	 * save targetURL in session
	 */
	private void setRememberMeTargetUrlToSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.setAttribute("targetUrl", "/user/userEdit");
		}
	}
}
