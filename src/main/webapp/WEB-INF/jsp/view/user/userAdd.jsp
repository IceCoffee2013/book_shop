<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@ include file="/WEB-INF/jsp/includes/src.jsp"%>
<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>
<script type="text/javascript">

	function validateEmail(email) {
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(email);
	}

	$(function(){
		var check = 0;

		$("#check_email").on("click",function(){
			var email = $("#email").val();

			if(email === ""){
				$("#email").focus();
				alert("Input Email");
				return false;
			}

			if(!validateEmail(email)){
				$("#email").focus();
				$("#email").val('');
				alert("Please write down available form of email");
				return false;
			}

			var email2 = $("#email").serialize();

			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/user/checkEmail.do",
				data:email2,
				success:function(result){
					if(result === "404"){
						alert("Failed! Try again");
						$("#email").focus();
						$("#email").val('');
					}else if(result === "400"){
						alert("This email is used");
						$("#email").focus();
						$("#email").val('');
					}else{
						check = 1;
						$("#txt").text(result);
					}
				}
			});
		});

		$("#submit_form").on("submit",function(){
			var postcode = $("#postcode").val();
			var trimcode = postcode.trim();

			if(trimcode.length !== 6){
				alert("Check your postcode again");
				$("#postcode").focus();
				return false;
			}

			if(check !== 1){
				alert("Check your email again");
				$("#email").focus();
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

	});
</script>
<body>

<div class="wrapper">
	<%@ include file="/WEB-INF/jsp/includes/nav.jsp"%>

	<section>
		<!-- Page content-->
		<div class="content-wrapper">
			<div class="content-heading">Sign Up</div>
			<div class="table-responsive b0">

				<form action="" method="post" enctype="multipart/form-data" id="submit_form" name="submit_form">
					<div class="form-group">
						<label for="firstName">First Name</label>
						<input type="text" class="span3" id="firstName" name="firstName" maxlength="50" required="required" placeholder="First name" autocomplete="off"><br>
					</div>
					<div class="form-group">
						<label for="lastName">Last Name</label>
						<input type="text" class="span3" id="lastName" name="lastName" maxlength="50" required="required" placeholder="Last name" autocomplete="off"><br>
					</div>
					<div class="form-group">
						<label for="email">Email</label>
						<input type="text" class="span3" id="email" name="email" maxlength="250" required="required" placeholder="Email " autocomplete="off">
						<input type="button" class="btn btn-primary btn-sm" id="check_email" name="check_email" value="Check" ><p id="txt"></p>
					</div>
					<div class="form-group">
						<label for="password">Password</label>
						<input type="password" class="span3" id="password" name="password" maxlength="20" required="required" placeholder="Password" autocomplete="off"><br>
					</div>
					<div class="form-group">
						<label for="address">Address</label>
						<input type="text" class="span3" id="address" name="address" maxlength="250" required="required" placeholder="Address" autocomplete="off"><br>
					</div>
					<div class="form-group">
						<label for="postcode">Postcode</label>
						<input type="text" class="span3" id="postcode" name="postcode" maxlength="6" required="required" placeholder="Postcode" autocomplete="off">&nbsp;<span id="errmsg"></span><br><br>
					</div>
					<div class="form-group">
						<label for="card">Card</label>
						<input type="text" class="span3" id="card" name="card" maxlength="16" required="required" placeholder="Card" autocomplete="off">&nbsp;<span id="errmsg3"></span><br><br>
					</div>
					<div class="form-group">
						<input type="file" name="thumnail"/><br>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary" value="submit">Submit</button>
					</div>
				</form>
				<a href="<%=request.getContextPath()%>/main/main.do">Back Home</a>

			</div>
		</div>

	</section>

	<%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
</div>

</body>

</html>