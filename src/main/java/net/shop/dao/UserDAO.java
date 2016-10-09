package net.shop.dao;

import java.util.HashMap;
import java.util.List;

import net.shop.vo.UserVO;


public interface UserDAO {

	public UserVO selectOne(String email) throws Exception;
	
	public List<UserVO> selectListMap(HashMap<String,Object> paraMap) throws Exception;

	public int count();
	
	public int count(String keyword);

	public int insert(UserVO userVO);

	public int update(UserVO userVO);

	public int delete(String email);

	public int updateDate(String email);

	public int updateData(HashMap<String,Object> paraMap);
}
