<%--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  : 
 * Date         : 4/16/15 | 12:07 PM
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

    <title>Doora</title>
</head>
<body>

<%@ include file="/WEB-INF/jsp/includes/nav.jsp"%>
<div class="main">

    <form action="<c:url value="/goods/buy.do" />" method="post">
        title : <input type="text" name="title" size="100"/><br/>
        author : <input type="text" name="author" size="20"/><br/>
        year : <input type="text" name="year" size="50"/><br/>
        journal : <input type="text" name="journal" size="20"/><br/>
        <%--options : <input type="text" name="options"/><br/>--%>
        <%--manufacturer : <input type="text" name="manufacturer" size="50"/><br/>--%>
        <%--madein : <input type="text" name="madein" size="50"/><br/>--%>
        price : <input type="text" name="price" size="20"/><br/>
        stock : <input type="text" name="stock" size="20"/><br/>

        description : <br/>
        <textarea name="description" cols="40" rows="5"></textarea>
        <br/>

        <input type="button" onclick="location.href='list.do?s=${param.s}'" value="cancel"/>
        <input type="submit" value="submit">
    </form>

</div>

</body>
</html>