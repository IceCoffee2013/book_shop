package net.shop.dao;

import net.shop.vo.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository("userDAO")
public class UserDAOMySql implements UserDAO {
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public UserVO selectOne(String email) throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.UserDao.selectOne",email);
        }finally{
            sqlSession.close();
        }
    }

    public int count() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.UserDao.count");
        }finally{
            sqlSession.close();
        }
    }

    public int count(String keyword) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.selectOne("net.UserDao.countByKeyword",keyword);
        }finally{
            sqlSession.close();
        }
    }
    
    public int insert(UserVO userVO) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.insert("net.UserDao.insert", userVO);
        }finally{
            sqlSession.close();
        }
    }

    public int update(UserVO userVO) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.update("net.UserDao.update", userVO);
        }finally{
            sqlSession.close();
        }
    }

    public int delete(String email) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.delete("net.UserDao.delete",email);
        }finally{
            sqlSession.close();
        }
    }

	@Override
	public int updateDate(String email) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.update("net.UserDao.updateDate", email);
        }finally{
            sqlSession.close();
        }
	}

	@Override
	public List<UserVO> selectListMap(HashMap<String, Object> paraMap)
			throws Exception {
		// TODO Auto-generated method stub
		 SqlSession sqlSession = sqlSessionFactory.openSession();
	  
	     try{
	         return sqlSession.selectList("net.UserDao.selectList", paraMap);
	     }finally{
	    	 sqlSession.close();
	     }
		
	}

	@Override
	public int updateData(HashMap<String,Object> paraMap) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            return sqlSession.update("net.UserDao.updateData", paraMap);
        }finally{
            sqlSession.close();
        }
	}
}
