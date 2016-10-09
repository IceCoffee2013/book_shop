package net.shop.service;

import net.shop.vo.FileVO;

public interface CommonService {

	public FileVO selectOneVo(int boardNumber) throws Exception;
	
    public int insert(FileVO fileVO) throws Exception;
}
