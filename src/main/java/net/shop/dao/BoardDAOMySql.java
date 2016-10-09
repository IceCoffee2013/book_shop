package net.shop.dao;

import net.shop.vo.BoardVO;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("boardDAO")
public class BoardDAOMySql implements BoardDAO {
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int selectCount() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.BoardDao.selectCount");
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public int selectCount(String separatorName) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.BoardDao.selectCountBySeparator", separatorName);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public int selectCount(HashMap<String, Object> map) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.BoardDao.selectCountByHashMap", map);
        }finally{
            sqlSession.close();
        }
    }

    public List<BoardVO> selectList(int firstRow, int endRow) throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        RowBounds rowBounds = new RowBounds(firstRow - 1, endRow - firstRow + 1);

        try{
            return sqlSession.selectList("net.BoardDao.selectList", rowBounds);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public List<BoardVO> selectList(int firstRow, int endRow, String separatorName) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        RowBounds rowBounds = new RowBounds(firstRow - 1, endRow - firstRow + 1);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("rowBounds", rowBounds);
        map.put("separatorName", separatorName);

        try{
            return sqlSession.selectList("net.BoardDao.selectListBySeparator", map);
        }finally{
            sqlSession.close();
        }
    }

    @Override
    public List<BoardVO> selectList(HashMap<String, Object> map) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectList("net.BoardDao.selectListByHashMap", map);
        }finally{
            sqlSession.close();
        }
    }

    public int insert(BoardVO boardVO) throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.insert("net.BoardDao.insert", boardVO);
        }finally{
            sqlSession.close();
        }
    }

    public BoardVO selectOne(int boardNumber) throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.BoardDao.selectOne", boardNumber);
        }finally{
            sqlSession.close();
        }
    }

    public void increaseReadCount(int boardNumber) throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            sqlSession.update("net.BoardDao.increaseReadCount", boardNumber);
        }finally{
            sqlSession.close();
        }
    }

    public String selectLastSequenceNumber(String searchMaxSeqNum, String searchMinSeqNum) throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("searchMaxSeqNum", searchMaxSeqNum);
        map.put("searchMinSeqNum", searchMinSeqNum);

        try{
            return sqlSession.selectOne("net.BoardDao.selectLastSequenceNumber", map);
        }finally{
            sqlSession.close();
        }
    }

    public int update(BoardVO boardVO) throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.update("net.BoardDao.update", boardVO);
        } finally{
            sqlSession.close();
        }
    }

    public int delete(int boardNumber) throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.delete("net.BoardDao.delete", boardNumber);
        } finally{
            sqlSession.close();
        }
    }

    @Override
    public int selectGroupNumber(String groupName) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.BoardDao.selectGroupNumber", groupName);
        } finally{
            sqlSession.close();
        }
    }

    @Override
    public int updateGroupNumber(String groupName) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.update("net.BoardDao.updateGroupNumber", groupName);
        } finally{
            sqlSession.close();
        }
    }

    @Override
    public int selectLastBoardNumberByEmail(String userEmail) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.BoardDao.selectLastBoardNumberByEmail", userEmail);
        } finally{
            sqlSession.close();
        }
    }

    @Override
    public int setGoodsCountZero(int boardNumber) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.update("net.BoardDao.setGoodsCountZero", boardNumber);
        } finally{
            sqlSession.close();
        }
    }
}
