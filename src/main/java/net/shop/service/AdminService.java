package net.shop.service;

import java.util.List;

import net.shop.vo.OrdersVO;


public interface AdminService {

    public List<OrdersVO> selectList(int start,int end, String keyword) throws Exception;

    public List<OrdersVO> selectList(int start,int end) throws Exception;

    public int count(String keyword) throws Exception;

}
