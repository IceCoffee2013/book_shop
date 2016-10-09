package net.shop.dao;

import net.shop.vo.FileVO;

public interface FileDAO {

	public FileVO selectOne(int boardNumber) throws Exception;
	
	public int insert(FileVO fileVO) throws Exception;
}
