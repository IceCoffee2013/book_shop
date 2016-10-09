<%--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 2015-03-22 | 오후 2:29
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap-theme.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/daumeditor/css/editor.css" type="text/css" charset="utf-8"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bpopup.css" type="text/css" charset="utf-8"/>
<script src="<%=request.getContextPath()%>/resource/daumeditor/js/editor_loader.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.3.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/jquery.bpopup.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/board/editor.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/board/popup.js"></script>
<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>
<head>

    <title>Post</title>
    
    <style type="text/css">
    textarea {
        resize: none;
    }
    </style>

    <script type="text/javascript">
        contextPath = "${pageContext.request.contextPath}";
    </script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/includes/nav.jsp"%>
<div class="main">
<!-- start editor -->
<div>
<c:if test="${act == 'write'}">
    <form id="frm" name="frm" action="<c:url value="/board/write.do" />" enctype="multipart/form-data" method="post" accept-charset="utf-8">
</c:if>
<c:if test="${act == 'reply'}">
    <form id="frm" name="frm" action="<c:url value="/board/reply.do" />" method="post" accept-charset="utf-8">
    <input type="hidden" name="parentBoardNumber" value="${param.parentBoardNumber}" />
</c:if>

    <input type="hidden" name="s" value="${param.s}"/>

        title : <input type="text" name="title" size="100"/><br/>
	
    <c:if test="${param.s == 'product'}">
        <table id="selected_goods" border="1">
            <tbody>
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>size</td>
                    <td>material</td>
                    <td>component</td>
                    <td>options</td>
                    <td>label</td>
                    <td>madein</td>
                    <td>price</td>
                    <td>stock</td>
                    <td>등록자</td>
                    <td>createDay</td>
                    <td>delete</td>
                </tr>

                <tr>
                    <td id="empty_goods" colspan="13">
                        Goods.
                    </td>
                </tr>
            </tbody>
        </table>

        <button id="popup">Goods.</button><br/>

        total_price : <input type="text" name="total_price" id="total_price" size="30" value="0"/><br/>

        <div id='element_to_pop_up' style='display:none;'>
            <span class='button b-close'><span>X</span></span> <!-- 닫기 버튼 (스타일은 알아서 지정) -->
            <div class='content'></div> <!-- 컨텐츠가 들어갈 영역 지정 (이미지, html 등.. 여러가지를 동적으로 부를수 있다. -->
        </div>
    </c:if>

    <!-- call editor frame -->
    <div id="editor_frame"></div>
    <textarea name="daumeditor" id="daumeditor" rows="10" cols="100" style="width:766px; height:412px;display: none;"></textarea>
    <c:if test="${param.s == 'product'}">
    Thumbnail
    <input type="file" name="thumnail" id="thumnail"/><br>
    </c:if>
    <input type="hidden" name="hid" value="${param.s}" id="hid"/>
    <input type="button" onclick="location.href='list.do?s=${param.s}'" value="Cancel"/>
    <input type="button" id="save_button" value="Submit"/>
</form>
</div>
<!-- end editor -->
</div>
</body>