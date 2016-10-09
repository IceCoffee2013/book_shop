package net.shop.service;

import net.shop.dao.CartDAO;
import net.shop.dao.GoodsDAO;
import net.shop.dao.OrdersDAO;
import net.shop.vo.CartVO;
import net.shop.vo.GoodsVO;
import net.shop.vo.OrdersVO;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Resource(name = "goodsDAO")
    private GoodsDAO goodsDAO;
    
    @Resource(name = "ordersDAO")
    private OrdersDAO ordersDAO;
    
    @Resource(name = "cartDAO")
    private CartDAO cartDAO;

    @Override
    public int selectCount() throws Exception {
        return goodsDAO.selectCount();
    }

    @Override
    public int selectCount(String keyword) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();

//        map.put("memberId", memberId);
        map.put("keyword", keyword);

        return goodsDAO.selectCount(map);
    }

    @Override
    public GoodsVO selectOne(int goodsNumber) throws Exception {
        return goodsDAO.selectOne(goodsNumber);
    }

    @Override
    public List<GoodsVO> selectList(int firstRow, int endRow) throws Exception {
        return goodsDAO.selectList(firstRow, endRow);
    }

    @Override
    public List<GoodsVO> selectList(int firstRow, int endRow, String keyword) throws Exception {

        RowBounds rowBounds = new RowBounds(firstRow - 1, endRow - firstRow + 1);


        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("memberId", memberId);
        map.put("rowBounds", rowBounds);
        map.put("keyword", keyword);
        return goodsDAO.selectList(map);
    }

    @Override
    public int insert(GoodsVO goodsVO) {
        return goodsDAO.insert(goodsVO);
    }

    @Override
    public int update(GoodsVO goodsVO) {
        return goodsDAO.update(goodsVO);
    }

    @Override
    public int delete(int goodsNumber) {
        return goodsDAO.delete(goodsNumber);
    }

    @Override
	public int addOrderlist(OrdersVO ordersVO) throws Exception {
		return ordersDAO.insert(ordersVO);
	}

	@Override
	public int addCartlist(CartVO cartVO) throws Exception {
		// TODO Auto-generated method stub
		return cartDAO.insert(cartVO);
	}

	@Override
	public int cartDelete(int number) {
		// TODO Auto-generated method stub
		return cartDAO.delete(number);
	}

	@Override
	public CartVO cartOne(int goodNumber, String email) {
		// TODO Auto-generated method stub
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("goodNumber", goodNumber);
		paraMap.put("userEmail", email);
		return cartDAO.selectOne(paraMap);
	}

	@Override
	public int cartDelete(int goodNumber, String email) {
		// TODO Auto-generated method stub
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("goodNumber", goodNumber);
		paraMap.put("userEmail", email);
		return cartDAO.deleteMap(paraMap);
	}

	@Override
	public int cartChange(int quantity, int cartNumber) {
		// TODO Auto-generated method stub
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("number", cartNumber);
		paraMap.put("quantity", quantity);
		return cartDAO.changeMap(paraMap);
	}

    @Override
    public int selectCountForStock(int maxStock, int minStock, String keyword) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("maxStock", maxStock);
        map.put("minStock", minStock);
        map.put("keyword", keyword);

        return goodsDAO.selectCountForStock(map);
    }

    @Override
    public List<GoodsVO> selectListForStock(int maxStock, int minStock, int firstRow, int endRow, String keyword) throws Exception {
        RowBounds rowBounds = new RowBounds(firstRow - 1, endRow - firstRow + 1);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("maxStock", maxStock);
        map.put("minStock", minStock);
        map.put("rowBounds", rowBounds);
        map.put("keyword", keyword);

        return goodsDAO.selectListForStock(map);
    }

    @Override
    public void decreaseGoodsStock(int goodsNumber, int quantity) throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("goodsNumber", goodsNumber);
        map.put("quantity", quantity);

        goodsDAO.decreaseGoodsStock(map);
    }
}
