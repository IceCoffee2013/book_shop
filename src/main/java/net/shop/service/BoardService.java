package net.shop.service;

import net.shop.vo.BoardVO;

import java.util.List;


public interface BoardService {

    public int selectCount() throws Exception;

    public int selectCount(String separatorName) throws Exception;

    public int selectCount(String separatorName, String keyword) throws Exception;

    public List<BoardVO> selectList(int firstRow, int endRow) throws Exception;

    public List<BoardVO> selectList(int firstRow, int endRow, String separatorName) throws Exception;

    public List<BoardVO> selectList(int firstRow, int endRow, String separatorName, String keyword) throws Exception;

    public int insert(BoardVO boardVO) throws Exception;

    public BoardVO selectOne(int boardNumber) throws Exception;

    public void increaseReadCount(int boardNumber) throws Exception;

    public String selectLastSequenceNumber(String searchMaxSeqNum, String searchMinSeqNum) throws Exception;

    public int update(BoardVO boardVO) throws Exception;

    public int delete(int boardNumber) throws Exception;

    public int generateNextGroupNumber(String groupName) throws Exception;

    public int selectLastBoardNumberByEmail(String userEmail) throws Exception;

    public int setGoodsCountZero(int boardNumber) throws Exception;
}
