<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@ include file="/WEB-INF/jsp/includes/src.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>

<script src="<%=request.getContextPath()%>/resource/js/vis.min.js"></script>
<link href="<%=request.getContextPath()%>/resource/css/vis.min.css" rel="stylesheet" type="text/css" />
<%--<script src="<%=request.getContextPath()%>/resource/js/sigma.require.js"></script>--%>
<%--<script src="<%=request.getContextPath()%>/resource/js/sigma.parsers.json.min.js"></script>--%>

<style type="text/css">
    #container {
        max-width: 600px;
        height: 400px;
        margin: auto;
    }
    #mynetwork {
        width: 900px;
        height: 500px;
        border: 1px solid lightgray;
    }
    /*p {*/
        /*max-width:600px;*/
    /*}*/
</style>

<script>

    $(document).ready(function (){
        $('#search_form').bind("submit", function(){
            console.log("#search_form")
            var options = {
                url: '/search/graph.ajax',
                type: 'post',
                dataType: 'text',
                data: $("#search_form").serialize(),
                success: function (data) {
                    $("#container").empty();
                    console.log(data);
//                    console.log(typeof(data) == "string");
                    if (data !== null && data !== "") {
                        var obj = JSON.parse(data)
                        drawGraph(obj)
                    }
                }
            };
            $.ajax(options);
            return false;
        })

        $('#search').click(function(){
            console.log("#search")
            $('#search_form').submit();
        })

    });

    function drawGraph(data) {
        // create a network
        var container = document.getElementById('mynetwork');
//        var data = {
//            nodes: nodes,
//            edges: edges
//        };
        var options = {};
        var network = new vis.Network(container, data, options);
        console.log("draw")
    }

</script>

<body>

<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/includes/nav.jsp"%>

    <section>
        <!-- Page content-->
        <div class="content-wrapper">
            <div class="content-heading">Graph</div>
            <div class="table-responsive b0">

                <div class="row">

                    <form method="POST" id="search_form">
                        <div class="form-group col-md-8">
                            <input type="text" name="query" class="form-control" placeholder="Search Graph, eg：title, author, venue">
                        </div>
                        <div class="form-group col-md-2">
                            <select name="type" class="form-control">
                                <option>Author</option>
                                <option>Title</option>
                                <option>Venue</option>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <a id="search" class="btn-search"><i class="fa fa-search" aria-hidden="true"></i></a>
                            <%--<button id="search" class="btn btn-primary">Submit</button>--%>
                        </div>
                    </form>

                </div>

                <%--<div id="container"></div>--%>
                <div id="mynetwork"></div>
            </div>
        </div>

    </section>

    <%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
</div>

</body>

</html>