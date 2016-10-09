<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<%@ include file="/WEB-INF/jsp/includes/src.jsp"%>
<%--<%@ include file="/WEB-INF/jsp/includes/src1.jsp"%>--%>
<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>

<script type="text/javascript">
    $(document).ready(function(){

        $(".change_auth").click(function(){
            console.log("click auth. start")
            var email = $(this).attr("vals");

			BootstrapDialog.show({
				message: 'Select Authority!',
				buttons: [{
					label: 'Ban',
					action: function () {
						var link = 'giveAuth.do?email='+email+'&auth=ban';
						$(location).attr('href', link);
					}
				},{
					label: 'User',
					action: function () {
						var link = 'giveAuth.do?email='+email+'&auth=user';
						$(location).attr('href', link);
					}
				}, {
					label: 'Admin',
					cssClass: 'btn-primary',
					action: function(){
						var link = 'giveAuth.do?email='+email+'&auth=admin';
						$(location).attr('href', link);
					}
				}, {
					label: 'Close',
					action: function(dialogItself){
						dialogItself.close();
					}
				}]
			});


        });

		$(".delete_user").click(function(){
			console.log("click delete user. start")
			var email = $(this).attr("vals");
			var hint = 'Warning! Do you want to delete user(' + email + ') ?';

			BootstrapDialog.confirm({
				title: 'WARNING',
				message: hint,
				type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
				closable: true, // <-- Default value is false
				draggable: true, // <-- Default value is false
				btnCancelLabel: 'Cancel', // <-- Default value is 'Cancel',
				btnOKLabel: 'Conform', // <-- Default value is 'OK',
				btnOKClass: 'btn-warning', // <-- If you didn't specify it, dialog type will be used,
				callback: function(result) {
					// result will be true if button was click, while it will be false if users close the dialog directly.
					if(result) {
						delete_user(email);
						var link = '/user/userList.do';
						$(location).attr('href', link);
//						alert('Yup.');
					}
//					else {
//						alert('Nope.');
//					}
				}
			});

//			BootstrapDialog.show({
//				message: hint,
//				buttons: [{
//					label: 'Conform',
//					cssClass: 'btn btn-danger',
//					action: function(){
//						delete_user(email);
//						dialogItself.close();
//					}
//				}, {
//					label: 'Cancel',
//					action: function(dialogItself){
//						dialogItself.close();
//					}
//				}]
//			});


		});

		function delete_user(email) {
			$.ajax({
				type: "POST",
				url: "/user/adminDelete.do",
				data: {userEmail: email},
				datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
				//在请求之前调用的函数
				//beforeSend:function(){$("#msg").html("logining");},
				//成功返回之后调用的函数
				success: function (data) {
					if (data == "200") {

					}
//					alert(data);
					//$("#msg").html(decodeURI(data));
				},
				complete: function (XMLHttpRequest, textStatus) {
//					alert(XMLHttpRequest.responseText);
//					alert(textStatus);
					//HideLoading();
				},
				error: function () {
				}
			});

		}

    });

</script>

<body>

<div class="wrapper">
	<%@ include file="/WEB-INF/jsp/includes/nav.jsp"%>

	<section>
		<!-- Page content-->
		<div class="content-wrapper">
			<div class="content-heading">User Info</div>
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
                            <strong>EMAIL</strong>
                        </th>
						<th>
							<strong>GROUP</strong>
						</th>
						<th>
							<strong>AUTH</strong>
						</th>
						<th>
							<strong>CREATE DATE</strong>
						</th>
						<th class="text-center">
							<strong>LAST DATE</strong>
						</th>
						<th>
							<strong>DELETE</strong>
						</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${userVOList}">
						<tr>
							<th>${list.number }</th>
							<td>${list.lastName }</td>
							<td>${list.email }</td>
							<td>${list.authority }</td>
							<td>
                                <button type="button" class="btn btn-sm btn-default change_auth" vals="${list.email }">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                </button>
                                <%--<a href="#" class="change_auth" vals="${list.email }" ><i class="fa fa-user" aria-hidden="true"></i></a>--%>
                            </td>
							<td><fmt:formatDate value="${list.createdDate}" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${list.lastDate}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <button type="button" class="btn btn-sm btn-default delete_user" vals="${list.email }">
                                    <i class="fa fa-trash-o" aria-hidden="true"></i>
                                </button>
                            </td>
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