package net.shop.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.shop.service.GoodsService;
import net.shop.util.Util;
import net.shop.vo.GoodsVO;
import net.shop.vo.PagingVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import com.mysql.jdbc.StringUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Langley
 */
@Controller
@RequestMapping(value="/main")
public class MainController {
	//private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "goodsService")
	private GoodsService goodsService;

	@Resource(name = "util")
	private Util util;
	
	/* For showing main page */
	@RequestMapping(value="/main.do")
	public ModelAndView showMain(Authentication auth) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		
		if(auth != null){
			UserDetails vo = (UserDetails) auth.getPrincipal();
			modelAndView.addObject("vo", vo);
		}

		//----------------------------
		int totalCount = goodsService.selectCount();

        Random random = new Random();
		int perPageMain = 20;
		int round =  (totalCount / perPageMain);
        int mainPage = random.nextInt(round - 1) + 1;
		PagingVO pagingVO = util.paging(mainPage, perPageMain, totalCount);
		modelAndView.addObject("pagingVO", pagingVO);
		modelAndView.setViewName("/main/main");

		if(totalCount == 0){
			modelAndView.addObject("goodsVOList", Collections.<GoodsVO>emptyList());
			modelAndView.addObject("hasGoods", false);
			System.err.println("modelAndView main: " + modelAndView);
			return modelAndView;
		}

		List<GoodsVO> goodsVOList = goodsService.selectList(pagingVO.getFirstRow(), pagingVO.getEndRow());
		Collections.shuffle(goodsVOList);
		modelAndView.addObject("goodsVOList", goodsVOList);
		modelAndView.addObject("hasGoods", true);

		System.err.println("modelAndView main: " + modelAndView);
		return modelAndView;


//		modelAndView.setViewName("/goods/products");
//		return modelAndView;
	}
	
	@RequestMapping("/admin.do")
	public ModelAndView showAdmin() throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/main/login");
		return modelAndView;
	}
	
	@RequestMapping("/login.do")
	public String login(@RequestParam(value = "error", required = false) String error,
			HttpServletRequest request,Model model,Authentication auth) throws Exception{
		
		if (error != null) {
			//login form for update page
            //if login error, get the targetUrl from session again.
			String targetUrl = getRememberMeTargetUrlFromSession(request);
			System.out.println(targetUrl);
			
			if(StringUtils.hasText(targetUrl)){
				model.addAttribute("targetUrl", targetUrl);
				model.addAttribute("loginUpdate", true);
				return "/user/userEdit";
			}		
			
			model.addAttribute("say", "Check your Email and Password again");
			model.addAttribute("url", request.getContextPath()+"/main/login.do");
			return "/error/alert";
		}
		return "/main/login";
	}
	
	/**
	 * get targetURL from session
	 */
	private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		if(session!=null){
			targetUrl = session.getAttribute("targetUrl")==null?"":session.getAttribute("targetUrl").toString();
		}
		return targetUrl;
	}
}
