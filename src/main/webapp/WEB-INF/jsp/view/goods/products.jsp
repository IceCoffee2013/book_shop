<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>

<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/src.jsp"%>
<body>

<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/includes/nav.jsp"%>

    <section>
        <!-- Page content-->
        <div class="content-wrapper">
            <div class="content-heading">
                Products

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a class="btn btn-success pull-right" href="<c:url value="/goods/add.do" />">Add</a>
                </sec:authorize>
            </div>
            <div class="table-responsive b0">
                <table id="datatable4" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th style="width:80px">
                            <strong>ID</strong>
                        </th>
                        <th>
                            <strong>NAME</strong>
                        </th>
                        <th>
                            <strong>PRICE</strong>
                        </th>
                        <th style="width:80px">
                            <strong>QUANTITY</strong>
                        </th>
                        <th class="text-center">
                            <strong>STATUS</strong>
                        </th>
                        <th>
                            <strong>ADDED</strong>
                        </th>
                        <th class="text-center">
                            <strong>VIEW</strong>
                        </th>
                        <th class="text-center">
                            <strong>BUY</strong>
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>

    </section>

    <%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
</div>

<script type="text/javascript">

    function doCart(number){
        $.ajax({
            type:"POST",
            url:"/goods/addCartAjax.do",
            //提交的数据
            data:{number: number, choice:"cart"},
            //返回数据的格式
            datatype: "html",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            //beforeSend:function(){$("#msg").html("logining");},
            //成功返回之后调用的函数
            success:function(data){
                if (data == "200") {

                }
                alert(data);
                //$("#msg").html(decodeURI(data));
            }   ,
            complete: function(XMLHttpRequest, textStatus){
                alert(XMLHttpRequest.responseText);
                alert(textStatus);
                //HideLoading();
            },
            error: function(){
            }
        });

    }

    function doDetail(number){
        window.location.href= "<%=request.getContextPath()%>/goods/detail.do?goodsNumber="+number;
    }
</script>

</body>

</html>