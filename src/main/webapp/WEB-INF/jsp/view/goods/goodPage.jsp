<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%@ include file="/WEB-INF/jsp/includes/src.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>

<body>

<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/includes/nav.jsp"%>

    <section>
        <!-- Page content-->
        <div class="content-wrapper">
            <div class="content-heading">Product Detail</div>
            <div class="table-responsive b0">

                <table class="table table-striped table-bordered table-hover">

                    <thead>
                    <tr>
                        <th>Field</th>
                        <td>Value</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>ID</th>
                        <td>${goodsVO.number}</td>
                    </tr>
                    <tr>
                        <th>Title</th>
                        <td>${goodsVO.title}</td>
                    </tr>
                    <tr>
                        <th>Author</th>
                        <td>${goodsVO.author}</td>
                    </tr>
                    <tr>
                        <th>Year</th>
                        <td>${goodsVO.year}</td>
                    </tr>
                    <tr>
                        <th>Description</th>
                        <td>${goodsVO.description}</td>
                    </tr>
                    <tr>
                        <th>Price</th>
                        <td>${goodsVO.price}</td>
                    </tr>
                    <tr>
                        <th>Stock</th>
                        <td>${goodsVO.stock}</td>
                    </tr>
                    <tr>
                        <th>Seller Email</th>
                        <td>${goodsVO.userEmail}</td>
                    </tr>
                    <tr>
                        <th>Add Date</th>
                        <td><fmt:formatDate value="${goodsVO.createdDate}" pattern="dd/MM/yyyy"/></td>
                    </tr>
                    </tbody>
                </table>

            </div>

            <%--<input type="button" onclick="location.href='list.do?p=${param.p}'" value="back"/>--%>
            <%--<sec:authorize access="isAuthenticated()">--%>
                <%--<input type="button" onclick="location.href='update.do?p=${param.p}&goodsNumber=${goodsVO.number}'" value="modify"/>--%>
                <%--<form action="<c:url value="/goods/delete.do" />" method="post" style="display: inline;">--%>
                    <%--<input type="hidden" name="p" value="${param.p}"/>--%>
                    <%--<input type="hidden" name="goodsNumber" value="${goodsVO.number}"/>--%>
                    <%--<input type="submit" value="delete" >--%>
                <%--</form>--%>
            <%--</sec:authorize>--%>

            <div class="clearfix">
                <input type="button" href="<c:url value="/goods/update.do" />" class="btn btn-info pull-left mr" onclick="location.href='update.do?p=${param.p}&goodsNumber=${goodsVO.number}'" value="modify">Edit</input>
                <form action="<c:url value="/goods/delete.do" />" method="post">
                    <input type="hidden" name="p" value="${param.p}"/>
                    <input type="hidden" name="goodsNumber" value="${goodsVO.number}"/>
                    <input type="submit" class="btn btn-default pull-left" value="delete" >
                </form>
                <%--<a type="button" href="<c:url value="/goods/delete.do" />" class="btn btn-default pull-left">Delete</a>--%>
                <input type="button" class="btn btn-success pull-right" onclick="location.href='update.do?p=${param.p}&goodsNumber=${goodsVO.number}'" value="modify">Back</input>
            </div>

        </div>

    </section>

    <%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
</div>

<script type="text/javascript">

    $("#add_cart").click(function() {
        number = $(this).attr("vals");
        doCart(number);
    });

    function doCart(number){
        $.ajax({
            type:"POST",
            url:"/goods/addCartAjax.do",
            //提交的数据
            data:{number: number, choice:"cart"},
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            //beforeSend:function(){$("#msg").html("logining");},
            //成功返回之后调用的函数
            success:function(data){
                if (data == "200") {

                } else if (data == "401") {
                    alert("please login in !")
                }
//                alert(data);
                //$("#msg").html(decodeURI(data));
            }   ,
            complete: function(XMLHttpRequest, textStatus){
                alert(XMLHttpRequest.responseText);
//                alert(textStatus);
                //HideLoading();
            },
            error: function(){
            }
        });

    }
</script>


</body>

</html>