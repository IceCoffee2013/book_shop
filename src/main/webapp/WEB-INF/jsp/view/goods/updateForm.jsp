<%--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  : 
 * Date         : 4/16/15 | 1:12 PM
 * Description  : 
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      : 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<%@ include file="/WEB-INF/jsp/includes/src.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>

<head>

    <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.3.min.js"></script>

    <title>상품 수정</title>
</head>
<body>

<%@ include file="/WEB-INF/jsp/includes/nav.jsp"%>
<div class="main">

    <form action="<c:url value="/goods/update.do" />" method="post">
        <input type="hidden" name="goodsNumber" value="${goodsVO.number}"/>
        <input type="hidden" name="p" value="${param.p}"/>
        title : <input type="text" name="title" size="100" value="${goodsVO.title}"/><br/>
        author : <input type="text" name="author" size="20" value="${goodsVO.author}"/><br/>
        year : <input type="text" name="year" size="20" value="${goodsVO.year}"/><br/>
        journal : <input type="text" name="journal" size="20" value="${goodsVO.journal}"/><br/>
        price : <input type="text" name="price" size="20" value="${goodsVO.price}"/><br/>
        stock : <input type="text" name="stock" size="20" value="${goodsVO.stock}"/><br/>

        description : <br/>
        <textarea name="description" cols="40" rows="5">${goodsVO.description}</textarea>
        <br/>
        <input type="submit" value="submit"/>
    </form>

</div>

</body>
</html>
