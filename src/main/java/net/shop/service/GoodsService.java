package net.shop.service;

import net.shop.vo.CartVO;
import net.shop.vo.GoodsVO;
import net.shop.vo.OrdersVO;

import java.util.List;


public interface GoodsService {

    public int selectCount() throws Exception;

    public int selectCount(String keyword) throws Exception;

    public GoodsVO selectOne(int goodsNumber) throws Exception;

    public List<GoodsVO> selectList(int firstRow, int endRow) throws Exception;

    public List<GoodsVO> selectList(int firstRow, int endRow, String keyword) throws Exception;

    public int insert(GoodsVO goodsVO);

    public int update(GoodsVO goodsVO);

    public int delete(int goodsNumber);

	public int addOrderlist(OrdersVO ordersVO) throws Exception;
	
	public int addCartlist(CartVO cartVO) throws Exception;

	public int cartDelete(int number);
	
	public int cartDelete(int goodNumber,String email);
	
	public CartVO cartOne(int goodNumber, String email);

	public int cartChange(int quantity, int cartNumber);

    public int selectCountForStock(int maxStock, int minStock, String keyword) throws Exception;

    public List<GoodsVO> selectListForStock(int maxStock, int minStock, int firstRow, int endRow, String keyword) throws Exception;

    public void decreaseGoodsStock(int goodsNumber, int quantity) throws Exception;
}
