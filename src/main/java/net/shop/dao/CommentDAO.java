package net.shop.dao;

import net.shop.vo.CommentVO;

import java.util.List;

public interface CommentDAO {

    public int selectCount(int boardNumber) throws Exception;

    public int selectCount(int boardNumber, String separatorName) throws Exception;

    public List<CommentVO> selectList(int boardNumber) throws Exception;

    public List<CommentVO> selectList(int boardNumber, String separatorName) throws Exception;

    public List<CommentVO> selectList(int boardNumber, int firstRow, int endRow) throws Exception;

    public List<CommentVO> selectList(int boardNumber, int firstRow, int endRow, String separatorName) throws Exception;

    public int insert(CommentVO commentVO) throws Exception;

    public void increaseCommentCount(int boardNumber) throws Exception;

    public CommentVO selectOne(int commentNumber) throws Exception;

    public String selectLastSequenceNumber(String searchMaxSeqNum, String searchMinSeqNum) throws Exception;

    public int update(CommentVO commentVO) throws Exception;

    public int delete(int commentNumber) throws Exception;

    public void decreaseCommentCount(int commentNumber) throws Exception;

    public int selectGroupNumber(String groupName) throws Exception;

    public int updateGroupNumber(String groupName) throws Exception;
}
