<%--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  : 
 * Date         : 5/13/15 | 11:06 PM
 * Description  : 
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      : 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bpopup.css" type="text/css" charset="utf-8"/>

    <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.3.min.js"></script>
    <script src="<%=request.getContextPath()%>/resource/js/jquery.bpopup.min.js"></script>

    <title>Product</title>

    <script type="text/javascript">
        contextPath = "${pageContext.request.contextPath}";

        ;(function($) {
            // DOM Ready
            $(function() {
                // Binding a click event
                // From jQuery v.1.7.0 use .on() instead of .bind()
                $('.popup').bind('click', function(e) {

                    var id = '.element_to_pop_up_' + $(this).attr('name');

                    // Prevents the default action to be triggered.
                    e.preventDefault();
                    // Triggering bPopup when click event is fired
                    $(id).bPopup();
                });
            });
        })(jQuery);

    </script>
</head>
<body>
<table id="box-table-a" class="table table-hover">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">size</th>
        <th scope="col">material</th>
        <th scope="col">component</th>
        <th scope="col">options</th>
        <th scope="col">manufacturer</th>
        <th scope="col">madein</th>
        <th scope="col">price</th>
        <th scope="col">stock</th>
        <th scope="col">userEmail</th>
        <th scope="col">createdDate</th>
    </tr>

    <c:choose>
        <c:when test="${hasGoods == false}">
            <tr>
                <td colspan="12">
                    Goods.
                </td>
            </tr>
        </c:when>

        <c:otherwise>
            <c:forEach var="list" items="${goodsVOList}">
                <tr id="goods_${list.number}">
                    <td>${list.number}</td>
                    <td>
                        <a href="#" class="popup" name="${list.number}">
                                ${list.name}
                        </a>
                        <div class='element_to_pop_up_${list.number} b_pop_up' style='display:none;'>
                            <span class='button b-close'><span>X</span></span> <!-- 닫기 버튼 (스타일은 알아서 지정) -->
                            <div class='content'>description<br/>${list.description}</div> <!-- 컨텐츠가 들어갈 영역 지정 (이미지, html 등.. 여러가지를 동적으로 부를수 있다. -->
                        </div>
                    </td>
                    <td>${list.size}</td>
                    <td>${list.material}</td>
                    <td>${list.component}</td>
                    <td>${list.options}</td>
                    <td>${list.manufacturer}</td>
                    <td>${list.madein}</td>
                    <td>${list.price}</td>
                    <td>${list.stock}</td>
                    <td>${list.userEmail}</td>
                    <td><fmt:formatDate value="${list.createdDate}" pattern="yyyy-MM-dd"/></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>
</body>
</html>
