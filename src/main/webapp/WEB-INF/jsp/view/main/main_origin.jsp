<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@ include file="/WEB-INF/jsp/includes/src.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>

<body>

<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/includes/nav.jsp"%>

    <section>
        <!-- Page content-->
        <div class="content-wrapper">
            <div class="content-heading">Products</div>
            <div class="table-responsive b0">
                <table id="datatable1" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th style="width:80px">
                            <strong>ID</strong>
                        </th>
                        <th>
                            <strong>NAME</strong>
                        </th>
                        <th>
                            <strong>DESCRIPTION</strong>
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
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>0001</td>
                        <td>Product 1</td>
                        <td>Description for Product</td>
                        <td>$ 12.20</td>
                        <td>233</td>
                        <td class="text-center">
                            <span class="label label-success">Stock</span>
                        </td>
                        <td>04/10/2015</td>
                        <td class="text-center">
                            <button type="button" class="btn btn-sm btn-default">
                                <em class="fa fa-search"></em>
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>0002</td>
                        <td>Product 2</td>
                        <td>Description for Product</td>
                        <td>$ 13.20</td>
                        <td>243</td>
                        <td class="text-center">
                            <span class="label label-success">Stock</span>
                        </td>
                        <td>04/10/2015</td>
                        <td class="text-center">
                            <button type="button" class="btn btn-sm btn-default">
                                <em class="fa fa-search"></em>
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </section>

    <%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
</div>

</body>

</html>