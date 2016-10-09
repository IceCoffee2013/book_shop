<%--
  Created by IntelliJ IDEA.
  User: Donghyun Seo (egaoneko@naver.com)
  Last Editor: Jisung jeon
  Date: 2015-03-22
  Time: 오후 2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/src.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
		function confirmation(question) {
			var defer = $.Deferred();
			$('<div></div>')
					.html(question)
					.dialog({
						autoOpen: true,
						modal: true,
						title: 'Confirmation',
						buttons: {
							"Yes": function () {
								defer.resolve("true");
								$(this).dialog("close");
							},
							"No": function () {
								defer.resolve("false");
								$(this).dialog("close");
							}
						},
						close: function () {
							$(this).remove();
						}
					});
			return defer.promise();
		}

//		$(".del_cart").click(function(){
//			var question = "Do you want to delete it?";
//			var data = $(this).attr("vals");
//			confirmation(question).then(function (answer) {
//				var ansbool = (String(answer) === "true");
//				if(ansbool){
//					var link = 'delCart.do?&no='+data+'&choice='+ansbool;
//					$(location).attr('href', link);
//				}
//			});
//		});

        $(".del_cart").click(function(){
            var question = "Do you want to delete it?";
            var data = $(this).attr("vals");
            var link = 'delCart.do?&no='+data+'&choice='+true;
            $(location).attr('href', link);
        });

		<%--$(".order").click(function(){--%>
			<%--var question = "Do you want to order it?";--%>
			<%--var data = [];--%>
			<%--var addr = "${user.address }";--%>
			<%--var post = "${user.postcode }";--%>
			<%--var name = "${user.lastName }";--%>
			<%--//alert(addr);--%>
			<%--data.push($(this).attr("vals"));--%>
			<%--confirmation(question).then(function (answer) {--%>
				<%--var ansbool = (String(answer) === "true");--%>
				<%--if(ansbool){--%>
					<%--var link = '<%=request.getContextPath()%>/goods/addOrders.do?&no='+data+'&addr='+addr+'&post='+post+'&name='+name;--%>
					<%--$(location).attr('href', link);--%>
				<%--}--%>
			<%--});--%>
		<%--});--%>

        $(".order").click(function(){
			var question = "Do you want to order it?";
			var data = [];
			var addr = "${user.address }";
			var post = "${user.postcode }";
			var name = "${user.lastName }";
			//alert(addr);
			data.push($(this).attr("vals"));
//			confirmation(question).then(function (answer) {
//				var ansbool = (String(answer) === "true");
//				if(ansbool){
            var link = '<%=request.getContextPath()%>/goods/addOrders.do?&no='+data+'&addr='+addr+'&post='+post+'&name='+name;
            $(location).attr('href', link);
//				}
//			});
		});

		$(".change_quan").click(function(){
			var question = "Do you want to change it?";
			var number = $(this).attr("vals");
			var quantity = $("#qty_"+number).val();
			var price = $("#price_"+number).attr("vals");
			if(quantity === 0){
				alert("No zero quantity");
				return;
			}
			confirmation(question).then(function (answer) {
				var ansbool = (String(answer) === "true");
				if(ansbool){
					$.ajax({
						type:"POST",
						url:"<%=request.getContextPath()%>/goods/changeQuan.do",
						data:{quantity:quantity, number:number},
						success:function(result){
							if(result === "400"){
								alert("Error");
							}else{
								var total = 0;
								$("#qty_"+number).val(result);
								$("#sub_tot_"+number).text(price*result);
								$("table tbody").find("td.sub_tot").each(function(){
									var sub = $(this).text();
									total = total + parseInt(sub);
								});
								$("#total").text(total);
							}
						}
					});
				}
			});
		});

		<%--$("#total_order").click(function(){--%>
			<%--var question = "Do you want to order it?";--%>
			<%--var data = [];--%>
			<%--var addr = "${user.address }";--%>
			<%--var post = "${user.postcode }";--%>
			<%--var name = "${user.lastName }";--%>

			<%--$('.order').each(function(){--%>
				<%--data.push($(this).attr("vals"));--%>
			<%--});--%>
			<%--//alert(data);--%>
			<%--confirmation(question).then(function (answer) {--%>
				<%--var ansbool = (String(answer) === "true");--%>
				<%--if(ansbool){--%>
					<%--var link = '<%=request.getContextPath()%>/goods/addOrders.do?&no='+data+'&addr='+addr+'&post='+post+'&name='+name;--%>
					<%--$(location).attr('href', link);--%>
				<%--}--%>
			<%--});--%>
		<%--});--%>

        $("#total_order").click(function(){
			var question = "Do you want to order it?";
			var data = [];
			var addr = "${user.address }";
			var post = "${user.postcode }";
			var name = "${user.lastName }";

			$('.order').each(function(){
				data.push($(this).attr("vals"));
			});
			//alert(data);
            var link = '<%=request.getContextPath()%>/goods/addOrders.do?&no='+data+'&addr='+addr+'&post='+post+'&name='+name;
            $(location).attr('href', link);
		});

		$(".number").keypress(function (e) {
			//if the letter is not digit then display error and don't type anything
			var number = $(this).attr("vals");

			if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
				//display error message
				$("#errmsg"+number).html("Digits Only").show().fadeOut("slow");
				return false;
			}
		});

	});

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
			<div class="content-heading">Cart</div>

			<c:choose>
				<c:when test="${hasUser == false}">
					<tr>
						<td colspan="5">
							No result
						</td>
					</tr>
				</c:when>
				<c:otherwise>


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
									<strong>PRICE</strong>
								</th>
								<th style="width:80px">
									<strong>QUANTITY</strong>
								</th>
								<th class="text-center">
									<strong>STATUS</strong>
								</th>
								<th>
									<strong>BUY</strong>
								</th>
								<th class="text-center">
									<strong>CANCEL</strong>
								</th>
							</tr>
							</thead>
							<tbody>
							<c:set var="s" value="0"></c:set>
							<c:forEach var="list" items="${cartlist}">
							<c:set var="s" value="${s + list.quantity * list.price}"></c:set>
							<tr>
								<td>${list.number}</td>
								<td><a href="<%=request.getContextPath()%>/goods/detail.do?goodsNumber=${list.number}">${list.goodName}</a></td>
								<td id="price_${list.number}" class="prc" vals="${list.price }">${list.price }</td>
								<td id="sub_tot_${list.number}" class="sub_tot">${list.quantity}</td>
								<td class="text-center">
									<span class="label label-success">Stock</span>
								</td>
								<td><a href="#" class="order" id="order" vals="${list.goodNumber}">Buy</a></td>
								<td><a href="#" class="del_cart" vals="${list.number}">Cancel</a></td>
							</tr>
							</c:forEach>

							</tbody>
						</table>
					</div>

					<div class="clearfix">
						<p class="pull-left h3">GRAND TOTAL</p>
						<p class="pull-right mr h3" id="total">$ ${s}</p>
					</div>

					<div class="clearfix">
						<%--<button type="button" class="btn btn-info pull-left mr">Edit</button>--%>
						<%--<button type="button" onclick="window.print();" class="btn btn-default pull-left">Print</button>--%>
						<button type="button" class="btn btn-success pull-right" id="total_order">Buy All</button>
					</div>

				</c:otherwise>
			</c:choose>


		</div>

	</section>

	<%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
</div>




</body>

</html>