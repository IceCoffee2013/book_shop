package net.shop.dao;

import net.shop.vo.GoodsVO;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author Langley
 */
@Repository("goodsDAO")
public class GoodsDAOMySql implements GoodsDAO {
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public int selectCount() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.GoodsDao.selectCount");
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public int selectCount(HashMap<String, Object> map) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.GoodsDao.selectCountByKeyword", map);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public GoodsVO selectOne(int goodsNumber) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.GoodsDao.selectOne", goodsNumber);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public List<GoodsVO> selectListByTitle(String title) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectList("net.GoodsDao.selectOneByTitle", title);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public List<GoodsVO> selectListByVenue(String venue) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectList("net.GoodsDao.selectOneByVenue", venue);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public List<GoodsVO> selectList(int firstRow, int endRow) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        RowBounds rowBounds = new RowBounds(firstRow - 1, endRow - firstRow + 1);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("rowBounds", rowBounds);
//        map.put("memberId", memberId);

        try{
            return sqlSession.selectList("net.GoodsDao.selectList", map);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public List<GoodsVO> selectList(HashMap<String, Object> map) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectList("net.GoodsDao.selectListByKeyword", map);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public List<GoodsVO> selectListByGraph(HashMap<String, Object> map) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectList("net.GoodsDao.selectListByGraph", map);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public List<GoodsVO> selectListAll(HashMap<String, Object> map) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectList("net.GoodsDao.selectListByKeywordAll", map);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public int insert(GoodsVO goodsVO) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.insert("net.GoodsDao.insert", goodsVO);
        }finally{
            sqlSession.close();
        }
    }

//    @Override
//    public int insert(int boardNumber, int goodsNumber) {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("boardNumber", boardNumber);
//        map.put("goodsNumber", goodsNumber);
//
//        try{
//            return sqlSession.insert("net.GoodsDao.insertBoardGoods", map);
//        }finally{
//            sqlSession.close();
//        }
//    }

    @Override
    public int update(GoodsVO goodsVO) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.update("net.GoodsDao.update", goodsVO);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public int delete(int goodsNumber) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.delete("net.GoodsDao.delete", goodsNumber);
        }finally{
            sqlSession.close();
        }
    }

//    @Override
//    public void increaseGoodsCount(int boardNumber) throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        try{
//            sqlSession.update("net.GoodsDao.increaseGoodsCount", boardNumber);
//        }finally{
//            sqlSession.close();
//        }
//    }
//
//    @Override
//    public void decreaseGoodsCount(int boardNumber) throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        try{
//            sqlSession.update("net.GoodsDao.decreaseGoodsCount", boardNumber);
//        }finally{
//            sqlSession.close();
//        }
//    }

//    @Override
//    public List<Integer> selectBoardGoodsByBoard(int boardNumber) throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        try{
//            return sqlSession.selectList("net.GoodsDao.selectBoardGoodsByBoard", boardNumber);
//        }finally{
//            sqlSession.close();
//        }
//    }
//
//    @Override
//    public List<Integer> selectBoardGoodsByGoods(int goodsNumber) throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        try{
//            return sqlSession.selectList("net.GoodsDao.selectBoardGoodsByGoods", goodsNumber);
//        }finally{
//            sqlSession.close();
//        }
//    }

//    @Override
//    public int deleteBoardGoodsByBoard(int boardNumber) throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        try{
//            return sqlSession.delete("net.GoodsDao.deleteBoardGoodsByBoard", boardNumber);
//        }finally{
//            sqlSession.close();
//        }
//    }
//
//    @Override
//    public int deleteBoardGoodsByGoods(int goodsNumber) throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        try{
//            return sqlSession.delete("net.GoodsDao.deleteBoardGoodsByGoods", goodsNumber);
//        }finally{
//            sqlSession.close();
//        }
//    }

    @Override
    public int selectCountForStock(HashMap<String, Object> map) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.GoodsDao.selectCountForStock", map);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public List<GoodsVO> selectListForStock(HashMap<String, Object> map) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectList("net.GoodsDao.selectListForStock", map);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public List<GoodsVO> selectListByAuthor(String author) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectList("net.GoodsDao.selectListByAuthor", author);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public void decreaseGoodsStock(HashMap<String,Object> map) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            sqlSession.update("net.GoodsDao.decreaseStockCount", map);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public void increaseGoodsStock(HashMap<String,Object> map) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            sqlSession.update("net.GoodsDao.increaseStockCount", map);
        }finally{
            sqlSession.close();
        }
    }
}
