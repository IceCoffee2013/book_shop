package net.shop.dao;

import net.shop.vo.BoardVO;
import net.shop.vo.BoardVO;

import java.util.HashMap;
import java.util.List;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 2015-03-20
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface BoardDAO {

    public int selectCount() throws Exception;

    public int selectCount(String separatorName) throws Exception;

    public int selectCount(HashMap<String,Object> map);

    public List<BoardVO> selectList(int firstRow, int endRow) throws Exception;

    public List<BoardVO> selectList(int firstRow, int endRow, String separatorName) throws Exception;

    public List<BoardVO> selectList(HashMap<String,Object> map) throws Exception;

    public int insert(BoardVO boardVO) throws Exception;

    public BoardVO selectOne(int boardNumber) throws Exception;

    public void increaseReadCount(int boardNumber) throws Exception;

    public String selectLastSequenceNumber(String searchMaxSeqNum, String searchMinSeqNum) throws Exception;

    public int update(BoardVO boardVO) throws Exception;

    public int delete(int boardNumber) throws Exception;

    public int selectGroupNumber(String groupName) throws Exception;

    public int updateGroupNumber(String groupName) throws Exception;

    public int selectLastBoardNumberByEmail(String userEmail) throws Exception;

    public int setGoodsCountZero(int boardNumber) throws Exception;
}
