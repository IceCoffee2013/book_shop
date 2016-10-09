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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<%@ include file="/WEB-INF/jsp/includes/src.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>

<head>
<!-- CSS Files -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap-theme.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/jqueryui/jquery-ui.css">

<!-- Javascript -->
<script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.3.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/jqueryui/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>

    <title>Doora</title>

</head>
<body>

<%@ include file="/WEB-INF/jsp/includes/nav.jsp"%>
<div class="main">

    <table>
        <tr>
            <td>number</td>
            <td>${goodsVO.number}</td>
        </tr>
        <tr>
            <td>title</td>
            <td>${goodsVO.title}</td>
        </tr>
        <tr>
            <td>author</td>
            <td>${goodsVO.author}</td>
        </tr>
        <tr>
            <td>year</td>
            <td>${goodsVO.year}</td>
        </tr>
        <tr>
            <td>journal</td>
            <td>${goodsVO.journal}</td>
        </tr>
        <tr>
            <td>price</td>
            <td>${goodsVO.price}</td>
        </tr>
        <tr>
            <td>stock</td>
            <td>${goodsVO.stock}</td>
        </tr>
        <tr>
            <td>userEmail</td>
            <td>${goodsVO.userEmail}</td>
        </tr>
        <tr>
            <td>createdDate</td>
            <td><fmt:formatDate value="${goodsVO.createdDate}" pattern="yyyy-MM-dd"/></td>
        </tr>

        <tr>
            <td>description</td>
            <td>
                <pre><c:out value="${goodsVO.description}"/></pre>
            </td>
        </tr>
        <tr>

            <td colspan="2">
                <input type="button" onclick="location.href='list.do?p=${param.p}'" value="back"/>
                <sec:authorize access="isAuthenticated()">
                <input type="button" onclick="location.href='update.do?p=${param.p}&goodsNumber=${goodsVO.number}'" value="modify"/>
                <form action="<c:url value="/goods/delete.do" />" method="post" style="display: inline;">
                    <input type="hidden" name="p" value="${param.p}"/>
                    <input type="hidden" name="goodsNumber" value="${goodsVO.number}"/>
                    <input type="submit" value="delete" >
                </form>
                </sec:authorize>
            </td>
        </tr>
    </table>
    <%--<a href="<%=request.getContextPath()%>/main/main.do">Back Home</a>--%>

</div>
</body>
</html>