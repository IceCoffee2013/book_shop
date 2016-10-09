package net.shop.controller;

import net.shop.entity.Query;
import net.shop.service.GoodsService;
import net.shop.service.SearchService;
import net.shop.service.UserService;
import net.shop.util.Util;
import net.shop.vo.GoodsVO;
import net.shop.vo.GraphVO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Langley on 01/10/2016.
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "goodsService")
    private GoodsService goodsService;

    @Resource(name = "searchService")
    private SearchService searchService;

    @Resource(name = "util")
    private Util util;

    @RequestMapping(value = "/advSearch.do")
    public ModelAndView advanceSearch(Authentication auth) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/goods/advSearch");
        return modelAndView;
    }

    @RequestMapping(value = "/advSearchForm.do")
    public ModelAndView advanceSearchForm(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        List<Query> queryList = new ArrayList<>();
        Enumeration e = request.getParameterNames();
        String parameterName;
        // TODO check query form is not empty by js.
        while (e.hasMoreElements()) {
            parameterName = (String) e.nextElement();
            String values[] = request.getParameterValues(parameterName);
            System.out.println(">>>>" + parameterName + " | " + values.toString());
            if (parameterName.indexOf("query") != -1) {
                String[] queryString = request.getParameterValues(parameterName);
                // searchconditional_1 searchin_1
                String index = parameterName.split("_")[1];
                System.out.println("Name: " + parameterName + " | index: " + index);
                String[] searchConditional = request.getParameterValues("searchconditional_" + index);
                String[] searchIn = request.getParameterValues("searchin_" + index);
                if (queryString == null || queryString[0].equals("")) {
                    continue;
                }
                Query query;
                if (searchConditional == null) {
                    query = new Query(queryString[0], null, searchIn[0]);
                } else {
                    query = new Query(queryString[0], searchConditional[0], searchIn[0]);
                }
                System.out.println("query object: " + query.toString());
                queryList.add(query);
            }
        }

        List<GoodsVO> result = new ArrayList<>();
        System.err.println("query:" + queryList.size() + " : " + queryList);
        result.addAll(searchService.advanceSearch(queryList));
        System.err.println("adv search:" + result.size());

        modelAndView.addObject("goodsVOList", result);
        modelAndView.addObject("hasGoods", true);
        modelAndView.setViewName("/goods/searchResult");
        return modelAndView;
    }

    @RequestMapping(value = "/graph.do")
    public ModelAndView graphPage(Authentication auth) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/goods/graph");
        return modelAndView;
    }

    @RequestMapping(value = "/graph.ajax", method = RequestMethod.POST)
    public
    @ResponseBody
    GraphVO goodsList(@RequestParam(value = "query") String query,
                      @RequestParam(value = "type") String type) throws Exception {
        System.err.println("query:" + query + " | type:" + type);
        GraphVO graphVO = new GraphVO();
        String input = query.trim();
        if (type.equals("Author")) {
            graphVO = searchService.graphSearchByAuthor(input);
        }
        if (type.equals("Title")) {
            graphVO = searchService.graphSearchByTitle(input);
        }
        if (type.equals("Venue")) {
            graphVO = searchService.graphSearchByTitle(input);
        }
        return graphVO;
    }

}
