package net.shop.service;

import java.util.List;

import net.shop.vo.CartVO;
import net.shop.vo.OrdersVO;
import net.shop.vo.UserVO;
import net.shop.vo.WishlistVO;


public interface UserService {
	/*
    Editor : Jisung Jeon
    Decription : return UserVO
    */
	public UserVO selectOneVo(String email) throws Exception;
    /*
    Editor : Jisung Jeon
    Decription : check the account by email
    */
    public boolean selectOne(String email) throws Exception;
    
    /*
    Editor : Jisung Jeon
    Decription : return user_no
    */
    public int selectOneNo(String email) throws Exception;
    
    /*
    Editor : Jisung Jeon
    Decription : Return lists that are searched and lined up between start and end
    */
    public List<UserVO> selectList(int start,int end,String order,String keyword) throws Exception;

    /*
    Editor : Jisung Jeon
    Decription : Return the total counts of lists
    */
    public int count() throws Exception;
    
    /*
    Editor : Jisung Jeon
    Decription : Return the total counts of resulted lists
    */
    public int count(String keyword) throws Exception;

    /*
    Editor : Jisung Jeon
    Decription : Insert the data in DB
    */
    public int insert(UserVO userVO) throws Exception;

    /*
    Editor : Jisung Jeon
    Decription : Update the data in DB
    */
    public int update(UserVO userVO) throws Exception;

    /*
    Editor : Jisung Jeon
    Decription : Delete the date in DB
    */
    public int delete(String email) throws Exception;
    
    /*
    Editor : Jisung Jeon
    Decription : Update last_date when login
    */
    public int updateDate(String email) throws Exception;
    
    /*
    Editor : Jisung Jeon
    Decription : Update authority
    */
    public int updateAuth(String email, String auth) throws Exception;
    
    /*
    Editor : Jisung Jeon
    Decription : Update password
    */
    public int updatePassword(String email,String password) throws Exception;
    
    /*
    Editor : Jisung Jeon
    Decription : add data in wishlist
    */
	public int addWishlist(String email, int number) throws Exception;
	
	/*
    Editor : Jisung Jeon
    Decription : count in wishlist
    */
	public int wishCount() throws Exception;
	
	/*
    Editor : Jisung Jeon
    Decription : count in resulted wishlist
    */
	public int wishCount(String keyword) throws Exception;
	
	/*
    Editor : Jisung Jeon
    Decription : list of wishlist
    */
	public List<WishlistVO> wishList(int start, int end, String keyword, String email) throws Exception;
	
	/*
    Editor : Jisung Jeon
    Decription : delete data in wishlist
    */
	public int delWishlist(String email, int number) throws Exception;
	
	/*
    Editor : Jisung Jeon
    Decription : check the data of list
    */
	public boolean checkWishlist(String email, int number) throws Exception;
	
	/*
    Editor : Jisung Jeon
    Decription : list of orderlist
    */
	public List<OrdersVO> ordersList(int start, int end, String email) throws Exception;
	
	/*
    Editor : Jisung Jeon
    Decription : count in orderlist
    */
	public int orderCount() throws Exception;
	
	/*
    Editor : Jisung Jeon
    Decription : count in resulted orderlist
    */
	public int orderCount(String keyword) throws Exception;
	
	/*
    Editor : Jisung Jeon
    Decription : delete data in orderlist
    */
	public int delorderlist(int no) throws Exception;
	
	/*
    Editor : Jisung Jeon
    Decription : call list of cart
    */
	public List<CartVO> cartList(String email);

    /*
	First Editor : Donghyun Seo (egaoneko@naver.com)
	Last Editor  :
	Date         : 2015-06-06
	*/
    public List<OrdersVO> ordersTotalList(int firstRow, int endRow, String keyword) throws Exception;

    /*
	First Editor : Donghyun Seo (egaoneko@naver.com)
	Last Editor  :
	Date         : 2015-06-06
	*/
    public int orderCountTotalList(String keyword) throws Exception;
}
