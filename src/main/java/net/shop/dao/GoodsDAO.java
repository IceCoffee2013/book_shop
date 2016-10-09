package net.shop.dao;

import net.shop.vo.GoodsVO;

import java.util.HashMap;
import java.util.List;

/**
 * @author Langley
 */
public interface GoodsDAO {

    public int selectCount() throws Exception;

    public int selectCount(HashMap<String,Object> map) throws Exception;

    public GoodsVO selectOne(int goodsNumber) throws Exception;

    public List<GoodsVO> selectListByTitle(String title) throws Exception;

    public List<GoodsVO> selectListByVenue(String venue) throws Exception;

    public List<GoodsVO> selectList(int firstRow, int endRow) throws Exception;

    public List<GoodsVO> selectList(HashMap<String,Object> map) throws Exception;

    public List<GoodsVO> selectListByGraph(HashMap<String,Object> map) throws Exception;

    public List<GoodsVO> selectListAll(HashMap<String,Object> map) throws Exception;

    public List<GoodsVO> selectListByAuthor(String author) throws Exception;

    public int insert(GoodsVO goodsVO);

    public int update(GoodsVO goodsVO);

    public int delete(int goodsNumber);

    public int selectCountForStock(HashMap<String,Object> map) throws Exception;

    public List<GoodsVO> selectListForStock(HashMap<String,Object> map) throws Exception;

    public void decreaseGoodsStock(HashMap<String,Object> map) throws Exception;

    public void increaseGoodsStock(HashMap<String,Object> map) throws Exception;
}
