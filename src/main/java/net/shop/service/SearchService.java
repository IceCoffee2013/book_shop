package net.shop.service;

import net.shop.entity.Query;
import net.shop.entity.QueryType;
import net.shop.vo.GoodsVO;
import net.shop.vo.GraphVO;

import java.util.List;

/**
 * Created by Langley on 29/09/2016.
 */
public interface SearchService {

    /**
     * return related goods having same author.
     * @param author
     * @return
     * @throws Exception
     */
    public GraphVO graphSearchByAuthor(String author) throws Exception;

    public GraphVO graphSearchByTitle(String title) throws Exception;

    public GraphVO graphSearchByVenue(String venue) throws Exception;

    public List<GoodsVO> graphSearch(String venue, String author) throws Exception;

    public List<GoodsVO> advanceSearch(List<Query> queryList) throws Exception;

    public List<GoodsVO> searchByType(Query query) throws Exception;

    public List<GoodsVO> searchByType(String query, QueryType queryType) throws Exception;

}
