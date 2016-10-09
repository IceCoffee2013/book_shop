package net.shop.dao;

import java.util.HashMap;
import java.util.List;

import net.shop.vo.WishlistVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("wishlistDAO")
public class WishlistDAOMySql implements WishlistDAO {
	SqlSessionFactory sqlSessionFactory;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    
	@Override
	public int insert(HashMap<String,Object> paraMap) throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.insert("net.WishlistDao.insert", paraMap);
        }finally{
            sqlSession.close();
        }
    }

	@Override
	public int count() throws Exception{
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.WishlistDao.count");
        }finally{
            sqlSession.close();
        }
	}

	@Override
	public int count(String keyword) throws Exception{
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.WishlistDao.countByKeyword",keyword);
        }finally{
            sqlSession.close();
        }
	}
	
	@Override
	public List<WishlistVO> selectListMap(HashMap<String, Object> paraMap) throws Exception{
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();
		  
	     try{
	         return sqlSession.selectList("net.WishlistDao.selectList", paraMap);
	     }finally{
	    	 sqlSession.close();
	     }
	}

	@Override
	public int delete(HashMap<String, Object> paraMap) throws Exception{
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.insert("net.WishlistDao.delete", paraMap);
        }finally{
            sqlSession.close();
        }
	}

	@Override
	public WishlistVO selectOne(HashMap<String, Object> paraMap) throws Exception{
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.WishlistDao.selectOne",paraMap);
        }finally{
            sqlSession.close();
        }
	}

}
