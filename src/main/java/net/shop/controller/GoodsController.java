package net.shop.controller;

import net.shop.error.GoodsNotFoundException;
import net.shop.service.BoardService;
import net.shop.service.GoodsService;
import net.shop.service.UserService;
import net.shop.util.Constant;
import net.shop.util.ImageUtil;
import net.shop.util.Util;
import net.shop.vo.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * @author Langley
 */
@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @Resource(name = "goodsService")
    private GoodsService goodsService;

    @Resource(name = "boardService")
    private BoardService boardService;
    
    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "util")
    private Util util;

    @Resource(name = "imageUtil")
    private ImageUtil imageUtil;

    @RequestMapping(value = "/list.do")
    public ModelAndView goodsList(@RequestParam(value = "p", required = false) String requestPageString,
                                  @RequestParam(value="q",required=false) String keyword,
                                  @RequestParam(value = "s", required = false, defaultValue = "default") String s,
                                  Authentication auth) throws Exception{

        ModelAndView modelAndView = new ModelAndView();

        if(s.equals("product")){
            modelAndView.addObject("isProduct", true);
        } else  {
            modelAndView.addObject("isProduct", false);
        }


        String memberId = util.isMemberId(auth);

        if(requestPageString == null || requestPageString.equals("")) requestPageString = "1";
        int requestPage = Integer.parseInt(requestPageString);
        if(requestPage <= 0) return (ModelAndView)new ModelAndView("redirect:/goods/list.do");

        if(keyword == null || keyword.equals("")) {
            keyword = null;
        }

        int totalCount = goodsService.selectCount(keyword);

        PagingVO pagingVO = util.paging(requestPage, 10, totalCount);
        modelAndView.addObject("pagingVO", pagingVO);
        modelAndView.setViewName("/goods/products");

        if(totalCount == 0){
            modelAndView.addObject("goodsVOList", Collections.<GoodsVO>emptyList());
            modelAndView.addObject("hasGoods", false);
            System.err.println("modelAndView0: " + modelAndView);  //TODO
            return modelAndView;
        }

        List<GoodsVO> goodsVOList = goodsService.selectList(pagingVO.getFirstRow(), pagingVO.getEndRow(), keyword);

        modelAndView.addObject("goodsVOList", goodsVOList);
        modelAndView.addObject("hasGoods", true);

        System.err.println("modelAndView1: " + modelAndView);  //TODO
        return modelAndView;
    }

    @RequestMapping(value = "/pagination.do" , method = RequestMethod.POST)
    public @ResponseBody DatatablesPageVO<BookItemVO> paginationDataTables(@RequestParam(value = "start", required = false) String start,
                                                                           @RequestParam(value = "length", required = false) String length,
                                                                           @RequestParam(value = "search[value]", required = false) String query,
                                                                           HttpServletResponse response) throws Exception{

        System.err.println("iDisplayStart:"+start+" | iDisplayLength:"+length);
        int pageNumber = 1;
        if (start != null && Integer.parseInt(start) > 1) {
            pageNumber = Integer.parseInt(start);
        }

        int pageLength = Integer.parseInt(length);
        DatatablesPageVO<BookItemVO> datatablesPageVO = new DatatablesPageVO<>();

        int goodsCount;
        List<GoodsVO> goodsVOs;
        if (query != null && !query.isEmpty()) {
            goodsCount = goodsService.selectCount(query);
            goodsVOs = goodsService.selectList(pageNumber, pageNumber + pageLength - 1, query);
        } else {
            goodsCount = goodsService.selectCount();
            goodsVOs = goodsService.selectList(pageNumber, pageNumber + pageLength - 1);
        }

        datatablesPageVO.setiTotalDisplayRecords(goodsCount);
        datatablesPageVO.setiTotalRecords(goodsCount);
        List<BookItemVO> bookItemVOs = new ArrayList<>();
        for (GoodsVO goodsVO : goodsVOs) {
            BookItemVO bookItemVO = new BookItemVO();
            bookItemVO.setId(goodsVO.getNumber());
            bookItemVO.setTitle(goodsVO.getAuthor());
            bookItemVO.setPrice(goodsVO.getPrice());
            bookItemVO.setStock(goodsVO.getStock()+"");
            bookItemVO.setStatus("<span class=\"label label-success\">Stock</span>");
            bookItemVO.setAdded(goodsVO.getCreatedDate());
            bookItemVO.setView("<button type=\"button\" onclick=\"doDetail(" + goodsVO.getNumber() + ")\" id=\"" + goodsVO.getNumber() +"\" class=\"btn btn-sm btn-default btn-cart\"><em class=\"fa fa-search\"></em></button>");
            bookItemVO.setBuy("<button type=\"button\" onclick=\"doCart(" + goodsVO.getNumber() + ")\" id=\"" + goodsVO.getNumber() +"\" class=\"btn btn-sm btn-default btn-cart\"><em class=\"fa fa-shopping-cart\"></em></button>");

            bookItemVOs.add(bookItemVO);
        }
        datatablesPageVO.setAaData(bookItemVOs);
        System.err.println("db size:" + goodsVOs.size() + " | bookitems size:" + bookItemVOs.size());
        return datatablesPageVO;
    }

    @RequestMapping(value = "/products.do")
    public ModelAndView productsList(@RequestParam(value = "p", required = false) String requestPageString,
                                  @RequestParam(value="q",required=false) String keyword,
                                  @RequestParam(value = "s", required = false, defaultValue = "default") String s,
                                  Authentication auth) throws Exception{

        ModelAndView modelAndView = new ModelAndView();

        if(s.equals("product")){
            modelAndView.addObject("isProduct", true);
        } else  {
            modelAndView.addObject("isProduct", false);
        }

//        String memberId = util.isMemberId(auth);

        if(requestPageString == null || requestPageString.equals("")) requestPageString = "1";
        int requestPage = Integer.parseInt(requestPageString);
        if(requestPage <= 0) return (ModelAndView)new ModelAndView("redirect:/goods/products.do");

        if(keyword == null || keyword.equals("")) {
            keyword = null;
        }

        int totalCount = goodsService.selectCount(keyword);

        PagingVO pagingVO = util.paging(requestPage, 10, totalCount);
        modelAndView.addObject("pagingVO", pagingVO);
        modelAndView.setViewName("/goods/products");

        if(totalCount == 0){
            modelAndView.addObject("goodsVOList", Collections.<GoodsVO>emptyList());
            modelAndView.addObject("hasGoods", false);
            System.err.println("modelAndView0: " + modelAndView);  //TODO
            return modelAndView;
        }

        List<GoodsVO> goodsVOList = goodsService.selectList(pagingVO.getFirstRow(), pagingVO.getEndRow(), keyword);

        modelAndView.addObject("goodsVOList", goodsVOList);
        modelAndView.addObject("hasGoods", true);

        System.err.println("modelAndView1: " + modelAndView);  //TODO
        return modelAndView;
    }

    @RequestMapping(value = "/add.do")
    public String goodsWrite(Authentication auth) throws Exception{

        util.isMemberId(auth);
        return "/goods/add";
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public String goodsWrite(@RequestParam(value = "title", required = true) String title,
                             @RequestParam(value = "author", required = true) String author,
                             @RequestParam(value = "year", required = true) String year,
                             @RequestParam(value = "journal", required = true) String journal,
                             @RequestParam(value = "description", required = true) String description,
                             @RequestParam(value = "price", required = true) Integer price,
                             @RequestParam(value = "stock", required = true) Integer stock,
                             @RequestParam(value = "venue", required = true) String venue,
                             @RequestParam(value="thumnail",required=false) MultipartFile multipartFile,
                             HttpServletRequest request,
                             Authentication auth) throws Exception{

        String memberId = util.isMemberId(auth);

        int userNumber = userService.selectOneNo(memberId);

        //upload thumnail
        String loadPath = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_PATH);
        String imagePath = "default.jpg";
        if ( multipartFile.getSize() > 0 ) {
            String fileName = multipartFile.getOriginalFilename();
            if (imageUtil.isImageFile(fileName)) {
                Calendar cal = Calendar.getInstance();
                String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
                File uploadFile =  File.createTempFile(loadPath, fileType);
                multipartFile.transferTo(uploadFile);
                String tempName =  cal.getTimeInMillis() + "";
                String replaceName = tempName+"_thum"+ fileType;
                File thumbnail =  new File (loadPath+replaceName);
                imageUtil.uploadImage(uploadFile, thumbnail, 100, 100);
                imagePath = replaceName;
            }
            else{
                System.err.println("goods add img upload error!");
            }
        }

        GoodsVO goodsVO = new GoodsVO();
        goodsVO.setTitle(title);
        goodsVO.setAuthor(author);
        goodsVO.setYear(year);
        goodsVO.setJournal(journal);
        goodsVO.setDescription(description);
        goodsVO.setPrice(price);
        goodsVO.setStock(stock);
        goodsVO.setUserNumber(userNumber);
        goodsVO.setUserEmail(memberId);
        goodsVO.setVenue(venue);
        goodsVO.setImagePath(imagePath);  // TODO

        goodsService.insert(goodsVO);

        return "redirect:/goods/list.do";
    }

    @RequestMapping(value = "/detail.do")
    public ModelAndView goodsRead(@RequestParam(value = "goodsNumber", required = true) Integer goodsNumber,
                                  @RequestParam(value = "mode", required = false, defaultValue = "default") String mode, Authentication auth)
            throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        GoodsVO goodsVO = goodsService.selectOne(goodsNumber);

        if(goodsVO == null) throw new GoodsNotFoundException("goods not found : " + goodsNumber);

        System.err.println("goods title:"+goodsVO.getNumber());

        boolean isAdmin = false;
        if (auth != null) {
            String memberId = util.isMemberId(auth);
            UserVO userVO = userService.selectOneVo(memberId);
            if (userVO.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }
        modelAndView.addObject("isAdmin", isAdmin);
        System.err.println("GoodsVO"+goodsVO.getVenue());
        modelAndView.addObject("goodsVO", goodsVO);
        modelAndView.setViewName("/goods/detail");

        return modelAndView;
    }

    @RequestMapping(value = "/update.do")
    public ModelAndView goodsUpdate(@RequestParam(value = "goodsNumber", required = true) Integer goodsNumber,
                                    Authentication auth) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        String memberId = util.isMemberId(auth);

        GoodsVO goodsVO = goodsService.selectOne(goodsNumber);
        if(goodsVO == null) throw new GoodsNotFoundException("[update goods] good not found: " + goodsNumber);

        util.isEqualMemberId(goodsVO.getUserEmail(), memberId);

        modelAndView.addObject("goodsVO", goodsVO);
        modelAndView.setViewName("/goods/update");
        return modelAndView;
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public String goodsUpdate(@RequestParam(value = "goodsNumber", required = true) Integer goodsNumber,
                              @RequestParam(value = "title", required = true) String title,
                              @RequestParam(value = "author", required = true) String author,
                              @RequestParam(value = "year", required = true) String year,
                              @RequestParam(value = "journal", required = true) String journal,
                              @RequestParam(value = "description", required = true) String description,
                              @RequestParam(value = "price", required = true) Integer price,
                              @RequestParam(value = "stock", required = true) Integer stock,
                              @RequestParam(value = "venue", required = true) String venue,
                              @RequestParam(value = "p", required = false) String page,
                              @RequestParam(value="thumnail",required=false) MultipartFile multipartFile,
                              HttpServletRequest request,
                              Authentication auth) throws Exception {

        String memberId = util.isMemberId(auth);

        GoodsVO goodsVO = goodsService.selectOne(goodsNumber);
        if(goodsVO == null) throw new GoodsNotFoundException("good not found : " + goodsNumber);

        util.isEqualMemberId(goodsVO.getUserEmail(), memberId);

        GoodsVO newGoodsVO = new GoodsVO();
        newGoodsVO.setNumber(goodsNumber);

        //upload thumnail
        String loadPath = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_PATH);
        String imagePath = "default.jpg";
        if (multipartFile.getSize() > 0 ) {
            String fileName = multipartFile.getOriginalFilename();
            if (imageUtil.isImageFile(fileName)) {
                Calendar cal = Calendar.getInstance();
                String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
                File uploadFile =  File.createTempFile(loadPath, fileType);
                multipartFile.transferTo(uploadFile);
                String tempName =  cal.getTimeInMillis() + "";
                String replaceName = tempName+"_thum"+ fileType;
                File thumbnail =  new File (loadPath+replaceName);
                imageUtil.uploadImage(uploadFile, thumbnail, 100, 100);
                imagePath = replaceName;
            }
            else{
                System.err.println("goods add img upload error!");
            }
        }

        if(!goodsVO.getTitle().equals(title)) newGoodsVO.setTitle(title);
        if(!goodsVO.getAuthor().equals(author)) newGoodsVO.setAuthor(author);
        if(!goodsVO.getYear().equals(year)) newGoodsVO.setYear(year);
        if(!goodsVO.getJournal().equals(journal)) newGoodsVO.setJournal(journal);
        if(!goodsVO.getDescription().equals(description)) newGoodsVO.setDescription(description);
        if(!goodsVO.getVenue().equals(venue)) newGoodsVO.setVenue(venue);
        if(!goodsVO.getImagePath().equals(imagePath)) newGoodsVO.setImagePath(imagePath);
        newGoodsVO.setPrice(price);
        newGoodsVO.setStock(stock);

        int updateCount = goodsService.update(newGoodsVO);
        if (updateCount == 0) throw new GoodsNotFoundException("good not found : " + goodsNumber);

        return "redirect:/goods/detail.do?p=" + page + "&goodsNumber=" + goodsNumber;
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    public String goodsDelete(@RequestParam(value = "goodsNumber", required = true) Integer goodsNumber,
                              @RequestParam(value = "p", required = false) String page,
                              Authentication auth) throws Exception {

        String memberId = util.isMemberId(auth);

        GoodsVO goodsVO = goodsService.selectOne(goodsNumber);
        if(goodsVO == null) throw new GoodsNotFoundException("good not found : " + goodsNumber);

        util.isEqualMemberId(goodsVO.getUserEmail(), memberId);

        goodsService.delete(goodsNumber);

        return "redirect:/goods/list.do?p=" + page;
    }

    //cart list
    @RequestMapping("/cart.do")
	public ModelAndView cartList(HttpServletRequest request, Authentication auth) throws Exception{
   
    	ModelAndView modelandview = new ModelAndView();
    	UserDetails vo = (UserDetails) auth.getPrincipal();
    	String email = vo.getUsername();
            
        List<CartVO> lists = userService.cartList(email);
        if(lists.isEmpty()){
        	request.setAttribute("hasUser", false);
            System.err.println("list cart is empty | email: " + email);
        	return modelandview;
        }
        UserVO userVO = userService.selectOneVo(email);
        modelandview.setViewName("/goods/cart");
        modelandview.addObject("cartlist", lists);
        modelandview.addObject("user", userVO);
    	request.setAttribute("hasUser", true);
        System.err.println("cart size: " + lists.size());
    		
    	return modelandview;
	}
    
    //ajax add cart
    @RequestMapping(value="/addCartAjax.do", method=RequestMethod.POST)
    public void addCartAjax(@RequestParam(value="number") int goodNumber,
    		@RequestParam(value = "choice") String choice,
    		HttpServletResponse response, Authentication auth)throws Exception{

    	if(choice == null || choice.isEmpty()){
    		response.getWriter().print("400");
			return;
		}

		// user not login in
        if (auth == null) {
            System.err.println("auth not login");
            response.getWriter().print("401");
            return;
        }
    	UserDetails vo = (UserDetails) auth.getPrincipal();
//        if (vo == null) {
//            System.err.println("cart not login");
//        }
//        System.err.println("user detail: " + vo.getUsername());
    	String email = vo.getUsername();

        // allow duplicate add
    	if(goodsService.cartOne(goodNumber,email) != null){
    	    CartVO cartVO = goodsService.cartOne(goodNumber, email);
            int quantity = cartVO.getQuantity() + 1;

            if(goodsService.cartChange(quantity,cartVO.getNumber()) == 0){
                response.getWriter().print("400");
                return;
            }
    		response.getWriter().print("200");
			return;
    	}
    	
		CartVO cartVO = new CartVO(goodNumber, 1, email);
		
    	if(choice.compareTo("go") != 0){
    		goodsService.addCartlist(cartVO);
			response.getWriter().print("200");
			return;
		}else{
			goodsService.addCartlist(cartVO);
    		userService.delWishlist(email, goodNumber);
    		response.getWriter().print("202");
			return;
		}
    	
    }
    
    //ajax change Quantity of specific data
    @RequestMapping(value="/changeQuan.do", method=RequestMethod.POST)
    public void changeQuan(@RequestParam(value="quantity") int quantity,
    		@RequestParam(value = "number") int cartNumber,
    		HttpServletResponse response, Authentication auth)throws Exception{
    	
    	if(quantity == 0){
    		response.getWriter().print("400");
			return;
    	}
    	
    	if(goodsService.cartChange(quantity,cartNumber) == 0){
    		response.getWriter().print("400");
			return;
		}
    	
    	response.getWriter().print(quantity);
    }

    //add cart
	@RequestMapping(value="/addCart.do")
    public String addCart(@RequestParam(value="number") int goodNumber, Model model,
    		@RequestParam(value="quantity", required = false) String quantity,
    		@RequestParam(value = "p", required = false) String page,
    		@RequestParam(value = "s", required = false) String seperate,
    		HttpServletRequest request, Authentication auth)throws Exception{
        
    	if(quantity == null || quantity.isEmpty()){
			model.addAttribute("say", "Wrong Input");
			model.addAttribute("url", request.getContextPath()+"/board/read.do?s="+seperate+"&p=" + page + "&goodNumber=" + goodNumber);
			return "/error/alert";   // TODO modify url
		}

    	String termQan = quantity.trim().toLowerCase();
    	
    	int quan = 1;
    	
    	try{
    		quan = Integer.parseInt(termQan);
    	}catch(Exception e){
    		model.addAttribute("say", "Wrong Input");
			model.addAttribute("url", request.getContextPath()+"/board/read.do?s="+seperate+"&p=" + page + "&goodNumber=" + goodNumber);
			return "/error/alert";
    	}
    	
    	UserDetails vo = (UserDetails) auth.getPrincipal();
		String email = vo.getUsername();
		
    	if(goodsService.cartOne(goodNumber, email) != null){
    		model.addAttribute("say", "Already listed");
			model.addAttribute("url", request.getContextPath()+"/board/read.do?s="+seperate+"&p=" + page + "&goodNumber=" + goodNumber);
			return "/error/alert";
		}
		
		CartVO cartVO = new CartVO(goodNumber, Integer.parseInt(quantity), email);
    	goodsService.addCartlist(cartVO);
    	
        model.addAttribute("say", "Added it");
		model.addAttribute("url", request.getContextPath()+"/board/read.do?s="+seperate+"&p=" + page + "&goodNumber=" + goodNumber);
    	return "/error/alert";
    			
    }
    
    //delete cart
	@RequestMapping(value="/delCart.do")
  	public String delCart(@RequestParam("choice")String choice,@RequestParam("no")int number,
  			HttpServletRequest request, Model model) throws Exception{
  		
  		if(choice == null || choice.isEmpty()){
  			model.addAttribute("say", "Wrong Input");
  			model.addAttribute("url", request.getContextPath()+"/goods/cart.do");
  			return "/error/alert";
  		}
  		
  		if(choice.compareTo("true") != 0){
  			return "redirect:/goods/cart.do";
  		}
  		
  		if(goodsService.cartDelete(number) == 0){
			model.addAttribute("say", "Wrong already deleted");
			model.addAttribute("url", request.getContextPath()+"/goods/cart.do");
			return "/error/alert";
		}
  		return "redirect:/goods/cart.do";	
  	}
  	
  	//add order
	@RequestMapping(value="/addOrders.do")
  	public String addOrders(@RequestParam("no")int[] goodNumbers,
  			@RequestParam("addr")String address,
  			@RequestParam("post")int postcode,
  			@RequestParam("name")String receiver,
  			HttpServletRequest request, Model model
  			,Authentication auth) throws Exception{
  		
  		if(address == null || address.isEmpty() || address.isEmpty() || receiver == null || receiver.isEmpty()){
  			model.addAttribute("say", "Wrong Input");
  			model.addAttribute("url", request.getContextPath()+"/goods/cart.do");
  			return "/error/alert";
  		}
  		
  		UserDetails vo = (UserDetails) auth.getPrincipal();
		String email = vo.getUsername();
		UserVO userVO = userService.selectOneVo(email);
		String sender = userVO.getLastName();

        boolean check = false;
        String say = "[";
        List<OrdersItemVO> ordersItemVOs = new ArrayList<>();
		
		if(goodNumbers != null) {
            for (int goodNumber : goodNumbers) {
                System.err.println("goods number: " + goodNumber + " | email: " + email ); // TODO
            	CartVO cartVO = goodsService.cartOne(goodNumber, email);
                if (cartVO == null) {
                    System.err.println("cart is null");
                }
                System.err.println("cartVO: " + cartVO.getGoodName());
          		int price = cartVO.getPrice();
          		int quantity = cartVO.getQuantity();

                if(!checkStock(goodNumber, quantity)){
                    check = true;
                    say += goodsService.selectOne(goodNumber);
                    say += ", ";
                }

//                ordersItemVOs.add(new OrdersItemVO("Ok", email, sender, goodNumber, quantity, address, postcode, price, receiver));
                ordersItemVOs.add(new OrdersItemVO(goodNumber, quantity, price));
            }

            if (check){
                say += "] quantity error, lack of goods.";

                model.addAttribute("say", say);
                model.addAttribute("url", request.getContextPath()+"/goods/cart.do");
                return "/error/alert";
            }

            int totalPrice = 0;
            for(OrdersItemVO ordersItemVO : ordersItemVOs){
                OrdersVO ordersVO = new OrdersVO(ordersItemVO.getGoodNumber(), ordersItemVO.getAmount(), ordersItemVO.getPrice()
                        , email, sender, userVO.getAddress(), userVO.getPostcode(), userVO.getLastName());
                ordersVO.setTotalPrice(totalPrice);
                goodsService.addOrderlist(ordersVO);
                totalPrice += ordersItemVO.getPrice() * ordersItemVO.getAmount();
                goodsService.cartDelete(ordersItemVO.getGoodNumber(), email);
                decreaseStock(ordersItemVO.getGoodNumber(), ordersItemVO.getAmount());
            }


		}else{
        	model.addAttribute("say", "Wrong Input");
  			model.addAttribute("url", request.getContextPath()+"/goods/cart.do");
  			return "/error/alert";
        }

        // TODO Send email when complete order.
        EmailVO emailVO = new EmailVO();
        String title = "Order Success!";
        String content = "Order from user:" + email;
        emailVO.setReciver(email);
        emailVO.setSubject(title);
        emailVO.setContent(content);
        util.sendEmail(emailVO);
		
  		model.addAttribute("say", "Buy it successfully");
		model.addAttribute("url", request.getContextPath()+"/user/orders.do");
		return "/error/alert";
  	}

    private boolean checkStock(int goodNumber, int quantity) throws Exception{
        GoodsVO goodsVO = goodsService.selectOne(goodNumber);
        if (goodsVO.getStock() < quantity) {
            return false;
        }
        return true;
    }

    private void decreaseStock(int goodNumber, int quantity) throws Exception {
        goodsService.decreaseGoodsStock(goodNumber, quantity);
    }


}
