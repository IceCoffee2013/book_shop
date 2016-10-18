package net.shop.service;

import java.util.List;

import net.shop.vo.CartVO;
import net.shop.vo.OrdersVO;
import net.shop.vo.UserVO;
import net.shop.vo.WishlistVO;


public interface UserService {

    public UserVO selectOneVo(String email) throws Exception;

    public boolean selectOne(String email) throws Exception;
    
    public int selectOneNo(String email) throws Exception;
    
    public List<UserVO> selectList(int start,int end,String order,String keyword) throws Exception;

    public int count() throws Exception;
    
    public int count(String keyword) throws Exception;

    public int insert(UserVO userVO) throws Exception;

    public int update(UserVO userVO) throws Exception;

    public int delete(String email) throws Exception;
    
    public int updateDate(String email) throws Exception;
    
    public int updateAuth(String email, String auth) throws Exception;
    
    public int updatePassword(String email,String password) throws Exception;
    
	public int addWishlist(String email, int number) throws Exception;
	
	public int wishCount() throws Exception;
	
	public int wishCount(String keyword) throws Exception;
	
	public List<WishlistVO> wishList(int start, int end, String keyword, String email) throws Exception;
	
	public int delWishlist(String email, int number) throws Exception;
	
	public boolean checkWishlist(String email, int number) throws Exception;
	
	public List<OrdersVO> ordersList(int start, int end, String email) throws Exception;
	
	public int orderCount() throws Exception;
	
	public int orderCount(String keyword) throws Exception;
	
	public int delorderlist(int no) throws Exception;
	
	public List<CartVO> cartList(String email);

    public List<OrdersVO> ordersTotalList(int firstRow, int endRow, String keyword) throws Exception;

    public int orderCountTotalList(String keyword) throws Exception;

}
