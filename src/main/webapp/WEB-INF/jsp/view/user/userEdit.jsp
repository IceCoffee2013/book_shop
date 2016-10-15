<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@ include file="/WEB-INF/jsp/includes/src.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>

<script type="text/javascript">

	$(function(){
		$("#input_pwd").on("click",function(){

			var pass1 = $("#password").val();
			var pass2 = $("#password_chk").val();

			if(pass1 === "" || pass2 === ""){
				$("#password").focus();
				alert("Input password");
				return false;
			}

			if(pass1 !== pass2){
				$("#password").focus();
				$("#password").val('');
				$("#password_chk").val('');
				alert("Two password are different");
				return false;
			}

			var pass = $("#password").serialize();

			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/user/changePwd.do",
				data:pass,
				success:function(result){
					if(result === "404"){
						alert("Failed! Try again");
						$("#password").focus();
						$("#password").val('');
						$("#password_chk").val('');
					}else{
						$("#txt").text(result);
						$("#password").val('');
						$("#password_chk").val('');
						$('.collapse').collapse('hide');
					}
				}
			});

		});

		$("#edit_form").on("submit",function(){
			var postcode = $("#postcode").val();
			var trimcode = postcode.trim();

			if(trimcode.length !== 6){
				alert("Check your postcode again");
				$("#postcode").focus();
				return false;
			}

		});

		$("#postcode").keypress(function (e) {
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
				//display error message
				$("#errmsg,#errmsg2").html("Digits Only").show().fadeOut("slow");
				return false;
			}
		});

		$("#card").keypress(function (e) {
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
				//display error message
				$("#errmsg3").html("Digits Only").show().fadeOut("slow");
				return false;
			}
		});

		$("#password_btn").on("click",function(){
			$("#txt").text("");
		});
	});
</script>

<body>

<div class="wrapper">
	<%@ include file="/WEB-INF/jsp/includes/nav.jsp"%>

	<section>
		<!-- Page content-->
		<div class="content-wrapper">
			<div class="content-heading">Edit</div>
			<div class="table-responsive b0">

				<form action="" method="post" id="edit_form" name="edit_form" enctype="multipart/form-data">
					<div>
						<img alt="" src="<%=request.getContextPath()%>/resource/upload/${uservo.imagePath}" height="42" >
					</div>
					<div class="form-group">
						<label for="firstName">First Name</label>
						<input type="text" class="span3" id="firstName" name="firstName" maxlength="50" required="required"  autocomplete="off" value="${uservo.firstName}">
					</div>
					<div class="form-group">
						<label for="lastName">Last Name</label>
						<input type="text" class="span3" id="lastName" name="lastName" maxlength="50" required="required"  autocomplete="off" value="${uservo.lastName}">
					</div>
					<button type="button" class="btn btn-primary btn-xs" id="password_btn" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">Change Password</button><p id="txt"></p>
					<div class="collapse" id="collapseExample">
						<div class="well">
							<fieldset>
								<legend>Chage password</legend>
								<input type="password" class="span3" id="password" name="password" maxlength="20" placeholder="Password" autocomplete="off" >
								<input type="password" class="span3" id="password_chk" name="password2" maxlength="20" placeholder="Password again" autocomplete="off" >
								<input type="button" class="btn btn-primary btn-sm" id="input_pwd" name="input_pwd" value="Submit" >
							</fieldset>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label for="address">Address</label>
						<input type="text" class="span3" id="address" name="address" maxlength="250" required="required" autocomplete="off" value="${uservo.address}"><br>
					</div>
					<div class="form-group">
						<label for="postcode">Post code</label>
						<input type="text" class="span3" id="postcode" name="postcode" maxlength="6" required="required" autocomplete="off" value="${uservo.postcode}">&nbsp;<span id="errmsg"></span><br>
					</div>
					<div class="form-group">
						<label for="card">Card</label>
						<input type="text" class="span3" id="card" name="card" maxlength="16" required="required" autocomplete="off" value="${uservo.cardNumber}">&nbsp;<span id="errmsg3"></span><br>
					</div>
					<input type="file" name="thumnail"/>
					<br>
					<button type="submit" class="btn btn-primary" value="submit">Submit</button>
				</form>
				<br>
				<a href="<%=request.getContextPath()%>/user/userDelete.do">Delete Account</a><br>
				<a href="<%=request.getContextPath()%>/main/main.do">Back Home</a>

			</div>
		</div>

	</section>

	<%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
</div>

</body>

</html>