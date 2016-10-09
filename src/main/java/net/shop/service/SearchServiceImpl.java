package net.shop.service;

import net.shop.dao.GoodsDAO;
import net.shop.entity.Query;
import net.shop.entity.QueryType;
import net.shop.vo.EdgeVO;
import net.shop.vo.GoodsVO;
import net.shop.vo.GraphVO;
import net.shop.vo.NodeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Langley on 29/09/2016.
 */
@Service("searchService")
public class SearchServiceImpl implements SearchService {

    @Resource(name = "goodsDAO")
    private GoodsDAO goodsDAO;
    private final static long RANDOM_SEED = -2610024992121431119L;
    private final static int XY_ROUND = 10;
    private final static int SIZE_ROUND = 1;
    private final static Random random = new Random(RANDOM_SEED);

    @Override
    public List<GoodsVO> advanceSearch(List<Query> queryList) throws Exception {
        List<GoodsVO> result = new ArrayList<>();
        if (queryList == null || queryList.isEmpty()) {
            return result;
        }

        // first search.
        result.addAll(searchByType(queryList.remove(0)));
        if (queryList.isEmpty()) {
            return result;
        }

        for (Query q : queryList) {
            result = resultFilter(result, q);
        }
        return result;
    }

    @Override
    public List<GoodsVO> searchByType(Query query) throws Exception {
        if (query == null || query.getQuery() == null || query.getQueryType() == null) {
            System.err.println("query null");
            return new ArrayList<GoodsVO>();
        }
        return searchByType(query.getQuery(), query.getSearchIn());
    }

    @Override
    public List<GoodsVO> searchByType(String query, QueryType searchIn) throws Exception {
        List<GoodsVO> result = new ArrayList<>();
        if (searchIn == QueryType.TITLE) {
            return goodsDAO.selectListByTitle(query);
        }
        if (searchIn == QueryType.AUTHOR) {
            return goodsDAO.selectListByAuthor(query);
        }
        // TODO year & journal sql part.
        if (searchIn == QueryType.YEAR) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("keyword", query);
            return goodsDAO.selectListAll(map);
        }
        if (searchIn == QueryType.JOURNAL) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("keyword", query);
            return goodsDAO.selectListAll(map);
        }

        System.err.println("result type: " + result.size());
        return result;
    }

    @Override
    public List<GoodsVO> graphSearch(String venue, String author) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("venue", venue);
        map.put("author", author);
        return goodsDAO.selectListByGraph(map);
    }

    @Override
    public GraphVO graphSearchByVenue(String venue) throws Exception {
        List<GoodsVO> goodsVOs = goodsDAO.selectListByVenue(venue);
        return graphSearchByAuthor(goodsVOs);
    }

    @Override
    public GraphVO graphSearchByAuthor(String author) throws Exception {
        List<GoodsVO> goodsVOs = goodsDAO.selectListByAuthor(author);
        return graphSearchByAuthor(goodsVOs);
    }

    @Override
    public GraphVO graphSearchByTitle(String title) throws Exception {
        List<GoodsVO> goodsVOs = goodsDAO.selectListByTitle(title);
        return graphSearchByAuthor(goodsVOs);
    }

    /**
     * use author as center, author -> title -> venue.
     * @param goodsVOs
     * @return
     * @throws Exception
     */
    private GraphVO graphSearchByAuthor(List<GoodsVO> goodsVOs) throws Exception {
        System.err.println("author size:" + goodsVOs.size());
        List<NodeVO> nodeVOs = new ArrayList<>();
        List<EdgeVO> edgeVOs = new ArrayList<>();
        if (goodsVOs.isEmpty()) {
            return null;
        }

        int authorID = 0;
        for (GoodsVO goodsVO : goodsVOs) {
            authorID++;
            List<GoodsVO> relatedNodes = graphSearch(goodsVO.getVenue(), goodsVO.getAuthor());

            System.err.println("target: " + goodsVO.getAuthor() +"related size: " + relatedNodes.size());

            for (GoodsVO node : relatedNodes) {
                // author
                NodeVO authorNodeVO = new NodeVO();
                authorNodeVO.setId("A" + authorID);
                authorNodeVO.setLabel(node.getAuthor());
                authorNodeVO.setColor("red");
                if (!nodeVOs.contains(authorNodeVO)) {
                    nodeVOs.add(authorNodeVO);
                }
                // title
                NodeVO titleNodeVO = new NodeVO();
                titleNodeVO.setId("T" + node.getNumber());
                titleNodeVO.setLabel(node.getTitle());
                titleNodeVO.setColor("pink");
                if (!nodeVOs.contains(titleNodeVO)) {
                    nodeVOs.add(titleNodeVO);
                }
                // venue
                NodeVO venueNodeVO = new NodeVO();
                venueNodeVO.setId("V" + node.getNumber());
                venueNodeVO.setLabel(node.getVenue());
                venueNodeVO.setColor("yellow");
                if (!nodeVOs.contains(venueNodeVO)) {
                    nodeVOs.add(venueNodeVO);
                }

                // edges
                // venue -> title
                EdgeVO venueEdgeVO = new EdgeVO();
                venueEdgeVO.setFrom(venueNodeVO.getId());
                venueEdgeVO.setTo(titleNodeVO.getId());
                venueEdgeVO.setArrow("to");
                venueEdgeVO.setLabel("publishedIn");
                if (!edgeVOs.contains(venueEdgeVO)) {
                    edgeVOs.add(venueEdgeVO);
                }

                // title -> author
                EdgeVO titleEdgeVO = new EdgeVO();
                titleEdgeVO.setFrom(titleNodeVO.getId());
                titleEdgeVO.setTo(authorNodeVO.getId());
                titleEdgeVO.setArrow("to");
                titleEdgeVO.setLabel("authoredBy");
                if (!edgeVOs.contains(titleEdgeVO)) {
                    edgeVOs.add(titleEdgeVO);
                }

            }
        }

        GraphVO graphVO = new GraphVO();
        graphVO.setNodes(nodeVOs);
        graphVO.setEdges(edgeVOs);
        return graphVO;
    }

    //    @Override
//    public GraphVO graphSearchByAuthor(String author) throws Exception {
//        List<GoodsVO> relatedGoods = goodsDAO.selectListByAuthor(author);
//
//        System.err.println("relatedGoods:" + relatedGoods.size());
//
//        GraphVO result = new GraphVO();
//        List<NodeVO> nodeVOs = new ArrayList<>();
//        List<EdgeVO> edgeVOs = new ArrayList<>();
//
//        if (relatedGoods.isEmpty()) {
////            NodeVO tmp = new NodeVO();
////            tmp.setX(0);
////            tmp.setY(0);
////            tmp.setSize(3);
////            tmp.setLabel(author);
////            tmp.setId("n" + 0);
////            nodeVOs.add(tmp);
//            result.setNodes(nodeVOs);
//            result.setEdges(edgeVOs);
//            return result;
//        }
//
//        NodeVO father = new NodeVO();
//        father.setX(0);
//        father.setY(0);
//        father.setSize(3);
//        father.setLabel(author);
//        father.setId("n" + 0);
//        nodeVOs.add(father);
//
//        for (int i = 0; i < relatedGoods.size(); i++) {
//            int no = i + 1;
//            String id = "n" + no;
//            NodeVO child = generateNodeVO(relatedGoods.get(i), false);
//            child.setId(id);
//            nodeVOs.add(child);
//            EdgeVO edgeVO = generateEdgeVO(father, child, i);
//            edgeVOs.add(edgeVO);
//        }
//
//        result.setNodes(nodeVOs);
//        result.setEdges(edgeVOs);
//
//        return result;
//    }

//    @Override
//    public GraphVO graphSearchByTitle(String title) throws Exception {
//        List<GoodsVO> tmp = goodsDAO.selectListByTitle(title);
//        if (tmp.isEmpty()) {
//            return null;
//        }
//        GoodsVO goodsVO = tmp.get(0);
//        if (goodsVO == null || goodsVO.getAuthor() == null) {
//            return null;
//        }
//        return graphSearchByAuthor(goodsVO.getAuthor());
//    }

//    private NodeVO generateNodeVO(GoodsVO goodsVO, boolean isTarget) {
//        NodeVO result = new NodeVO();
//        int x;
//        int y;
//        int size;
//        if (isTarget) {
//            x = 0;
//            y = 0;
//            size = 3;
//            result.setLabel(goodsVO.getAuthor());
//        } else {
//            x = random.nextInt(XY_ROUND);
//            y = random.nextInt(XY_ROUND);
//            size = random.nextInt(SIZE_ROUND) + 1;
//            result.setLabel(goodsVO.getTitle());
//        }
//        result.setX(x);
//        result.setY(y);
//        result.setSize(size);
//        return result;
//    }
//
//    private EdgeVO generateEdgeVO(NodeVO source, NodeVO target, int id) {
//        EdgeVO result = new EdgeVO();
//        result.setId("e"+id);
//        result.setSource(source.getId());
//        result.setTarget(target.getId());
//        return result;
//    }

    private List<GoodsVO> resultFilter(List<GoodsVO> target, Query query) throws Exception{
        List<GoodsVO> result = new ArrayList<>();
        if (query == null || query.getQuery() == null || query.getSearchIn() == null) {
            System.err.println("query == null || query.getQuery() == null || query.getQueryType() == null");
            return result;
        }
        QueryType queryType = query.getQueryType();
        if (query.getQueryType() == QueryType.NONE) {
            queryType = QueryType.AND;
        }

        if (queryType == QueryType.AND) {
            result = searchByType(query.getQuery(), query.getSearchIn());
            result.retainAll(target);
            return result;
        }

        if (queryType == QueryType.OR) {
            List<GoodsVO> queryResult = searchByType(query.getQuery(), query.getSearchIn());
            result.addAll(target);
            for (GoodsVO a : queryResult) {
                if (!result.contains(a)) {
                    result.add(a);
                }
            }
            return result;
        }

        if (queryType == QueryType.NOT) {
            List<GoodsVO> queryResult = searchByType(query.getQuery(), query.getSearchIn());
            result.addAll(target);
            for (GoodsVO a : queryResult) {
                if (result.contains(a)) {
                    result.remove(a);
                }
            }
            return result;
        }

        return result;
    }

}
