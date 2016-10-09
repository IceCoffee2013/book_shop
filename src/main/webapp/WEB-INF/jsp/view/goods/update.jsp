<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>

<%@ include file="/WEB-INF/jsp/includes/header.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/src.jsp" %>
<body>

<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/includes/nav.jsp" %>

    <section>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="pull-right">
                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn btn-link">
                            <em class="fa fa-ellipsis-v fa-lg text-muted"></em>
                        </button>
                        <ul role="menu" class="dropdown-menu dropdown-menu-right animated fadeInLeft">
                            <li>
                                <a href="#">
                                    <span>Block goods</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="text-warning">Delete goods</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="h4 text-center">Goods Information</div>
                <div class="row pv-lg">
                    <div class="col-lg-2"></div>
                    <div class="col-lg-8">
                        <form class="form-horizontal" action="<c:url value="/goods/update.do" />" enctype="multipart/form-data" method="post">
                            <input type="hidden" name="goodsNumber" value="${goodsVO.number}"/>
                            <input type="hidden" name="p" value="${param.p}"/>

                            <div class="form-group">
                                <label for="inputContact1" class="col-sm-2 control-label">Title</label>
                                <div class="col-sm-10">
                                    <input id="inputContact1" type="text" placeholder="" name="title" value="${goodsVO.title}"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputContact2" class="col-sm-2 control-label">Author</label>
                                <div class="col-sm-10">
                                    <input id="inputContact2" type="text" name="author" value="${goodsVO.author}"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputContact3" class="col-sm-2 control-label">Year</label>
                                <div class="col-sm-10">
                                    <input id="inputContact3" type="date" name="year" value="${goodsVO.year}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputContact4" class="col-sm-2 control-label">Journal</label>
                                <div class="col-sm-10">
                                    <input id="inputContact4" type="text" name="journal" value="${goodsVO.journal}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputContact5" class="col-sm-2 control-label">Price</label>
                                <div class="col-sm-10">
                                    <input id="inputContact5" type="text" name="price" value="${goodsVO.price}"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputContact6" class="col-sm-2 control-label">Stock</label>
                                <div class="col-sm-10">
                                    <input id="inputContact6" type="text" name="stock" value="${goodsVO.stock}"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputContact7" class="col-sm-2 control-label">Venue</label>
                                <div class="col-sm-10">
                                    <input id="inputContact7" type="text" name="venue" value="${goodsVO.venue}"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputContact8" class="col-sm-2 control-label">Description</label>
                                <div class="col-sm-10">
                                    <textarea id="inputContact8" row="4" name="description"
                                              class="form-control">${goodsVO.description}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="file" name="thumnail"/><br>
                            </div>
                            <%--<div class="form-group">--%>
                                <%--<div class="col-sm-offset-2 col-sm-10">--%>
                                    <%--<div class="checkbox">--%>
                                        <%--<label>--%>
                                            <%--<input type="checkbox">Favorite contact?</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-info">Update</button>
                                </div>
                            </div>
                        </form>
                        <%--<div class="text-right"><a href="#" class="text-muted">Delete this contact?</a>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
        </div>

</section>

<%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>
</div>

<script type="text/javascript">

    function doCart(number) {
        $.ajax({
            type: "POST",
            url: "/goods/addCartAjax.do",
            //提交的数据
            data: {number: number, choice: "cart"},
            //返回数据的格式
            datatype: "html",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            //beforeSend:function(){$("#msg").html("logining");},
            //成功返回之后调用的函数
            success: function (data) {
                if (data == "200") {

                }
                alert(data);
                //$("#msg").html(decodeURI(data));
            },
            complete: function (XMLHttpRequest, textStatus) {
                alert(XMLHttpRequest.responseText);
                alert(textStatus);
                //HideLoading();
            },
            error: function () {
            }
        });

    }

    function doDetail(number) {
        window.location.href = "<%=request.getContextPath()%>/goods/detail.do?goodsNumber=" + number;
    }
</script>

</body>

</html>