<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%@ include file="/WEB-INF/jsp/includes/src.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>

<script type="text/javascript">

    function doDetail(number){
        window.location.href= "<%=request.getContextPath()%>/goods/detail.do?goodsNumber="+number;
    }
</script>


<body>

<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/includes/nav.jsp"%>

    <section>
        <!-- Page content-->
        <div class="content-wrapper">
            <div class="content-heading">Orders</div>
            <div class="table-responsive b0">
                <table id="datatable1" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>
                            <strong>ORDER ID</strong>
                        </th>
                        <th>
                            <strong>USER EMAIL</strong>
                        </th>
                        <th>
                            <strong>GOOD ID</strong>
                        </th>
                        <th>
                            <strong>PRICE</strong>
                        </th>
                        <th>
                            <strong>QUANTITY</strong>
                        </th>
                        <th>
                            <strong>STATUS</strong>
                        </th>
                        <th>
                            <strong>ADDED</strong>
                        </th>
                        <th>
                            <strong>VIEW</strong>
                        </th>
                    </tr>
                    </thead>
                    <tbody>


                    <c:forEach var="list" items="${lists}">
                        <tr id="goods_${list.number}">
                            <td>${list.number}</td>
                            <td>${list.userEmail}</td>
                            <td>${list.goodNumber}</td>
                            <td>${list.price}</td>
                            <td>${list.amount}</td>
                            <td><span class="label label-success">SUCCESS</span></td>
                            <td><fmt:formatDate value="${list.orderDate}" pattern="yyyy-MM-dd"/></td>
                            <td><button onclick="doDetail(${list.goodNumber})" href=""><i class="fa fa-search" aria-hidden="true"></i></button></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>

    </section>

    <%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
</div>

</body>

</html>