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
        <form class="form-inline" action="/search/advSearchForm.do" method="get">
            <div class="search-border-box">
                <div class="form-group search-info-box">
                <span class="field">
                    <input type="text" name="query_1" id="query_1" size="50"/>
                </span>
                    <span class="field">
                <%--<label for="searchin_1">in</label>--%>
                <select class="search-in" name="searchin_1" id="searchin_1">
                    <option value="title">Title</option>
                    <option value="author">Author</option>
                    <option value="year">Year</option>
                    <option value="journal">Journal</option>
                    <%--<option value="volume">Volume</option>--%>
                </select>
                </span>
                    <a href="#" class="delete"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                </div>
                <div class="form-group search-info-box">
                <span class="field">
                    <select class="search-conditional" name="searchconditional_2" id="searchconditional_2">
                        <option value="AND">AND</option>
                        <option value="OR">OR</option>
                        <option value="NOT">NOT</option>
                    </select>
                </span>
                    <span class="field">
                    <input type="text" name="query_2" id="query_2" size="50"/>
                </span>
                    <span class="field">
                <%--<label for="searchin_2">in</label>--%>
                    <select class="search-in" name="searchin_2" id="searchin_2">
                        <option value="title">Title</option>
                        <option value="author">Author</option>
                        <option value="year">Year</option>
                        <option value="journal">Journal</option>
                        <%--<option value="volume">Volume</option>--%>
                    </select>
                </span>
                    <a href="#" class="delete"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                </div>
                <div class="form-group search-info-box">
                <span class="field">
                    <select class="search-conditional" name="searchconditional_3" id="searchconditional_3">
                        <option value="AND">AND</option>
                        <option value="OR">OR</option>
                        <option value="NOT">NOT</option>
                    </select>
                </span>
                    <span class="field">
                <input type="text" name="query_3" id="query_3" size="50"/>
                </span>
                    <span class="field">
                <%--<label for="searchin_3">in</label>--%>
                <select class="search-in" name="searchin_3" id="searchin_3">
                    <option value="title">Title</option>
                    <option value="author">Author</option>
                    <option value="year">Year</option>
                    <option value="journal">Journal</option>
                    <%--<option value="volume">Volume</option>--%>
                </select>
                </span>
                    <a href="#" class="delete"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                </div>

                <div class="buy-more-info-link">
                    <a href="#" class="oneMore">Add More</a>
                </div>
                <div class="sub-box pull-right">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </div>

        </form>

    </section>

    <%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
</div>

</body>

<script src="http://cdn.bootcss.com/jquery/3.0.0/jquery.min.js"></script>
<script type="text/javascript">

    currentCount = 3;
    $('.oneMore').on('click', function() {
        console.log("123 start");
        var _this = $(this).parent(),
                target = _this.siblings().first(),
                clone = target.clone();

        console.log(clone.toString());
        search_condition = '<span class="field"> <select class="search-conditional" name="searchconditional_2" id="searchconditional_2"> <option value="AND">AND</option> <option value="OR">OR</option> <option value="NOT">NOT</option> </select> </span>';

        clone.prepend(search_condition);

        search_condition_name = "searchconditional_" + currentCount;
        query_name = "query_" + currentCount;
        searchin_name = "searchin_" + currentCount;
        clone.find(".search-conditional").attr("id", search_condition_name).attr("name", search_condition_name);
        clone.find("input").attr("id", query_name).attr("name", query_name).val("");
        clone.find(".search-in").attr("id", searchin_name).attr("name", searchin_name);
        clone.find('.delete').on('click', function () {
            console.log("start delete")
            if($(this).closest(".search-border-box").find(".search-info-box").length === 1) {
                $(this).parent().find("input").val("");
            } else {
                $(this).parent().remove();
                currentCount--;
            }
            return false;
        });
        currentCount++;
        _this.before(clone);
    });

    $(".delete").click(function () {
        if($(this).closest(".search-border-box").find(".search-info-box").length === 1) {
            $(this).parent().find("input").val("");
            console.log("min filter: " + currentCount)
        } else {
            $(this).parent().remove();
            currentCount--;
            console.log("Remove! current Count: " + currentCount)
        }
        return false;
    });

</script>

</html>