<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="description" content="Bootstrap Admin App + jQuery">
	<meta name="keywords" content="app, responsive, jquery, bootstrap, dashboard, admin">
	<title>Doora</title>
	<!-- =============== VENDOR STYLES ===============-->
	<!-- FONT AWESOME-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/vendor/fontawesome/css/font-awesome.min.css">
	<!-- SIMPLE LINE ICONS-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/vendor/simple-line-icons/css/simple-line-icons.css">
	<!-- =============== BOOTSTRAP STYLES ===============-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/app/css/bootstrap.css" id="bscss">
	<!-- =============== APP STYLES ===============-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/app/css/app.css" id="maincss">
</head>

<body>
<div class="wrapper">
	<div class="block-center mt-xl wd-xl">
		<!-- START panel-->
		<div class="panel panel-dark panel-flat">
			<div class="panel-heading text-center">
				<a href="#">
					<img src="<%=request.getContextPath()%>/app/img/logo.png" alt="Image" class="block-center img-rounded">
				</a>
			</div>
			<div class="panel-body">
				<p class="text-center pv">SIGN IN TO CONTINUE.</p>
				<form role="form" data-parsley-validate="" novalidate="" class="mb-lg" action="<c:url value='/j_spring_security_check' />" method="post">
					<div class="form-group has-feedback">
						<input id="exampleInputEmail1" name="email" type="email" placeholder="Enter email" autocomplete="off" required class="form-control">
						<span class="fa fa-envelope form-control-feedback text-muted"></span>
					</div>
					<div class="form-group has-feedback">
						<input id="exampleInputPassword1" name="password" type="password" placeholder="Password" required class="form-control">
						<span class="fa fa-lock form-control-feedback text-muted"></span>
					</div>
					<div class="clearfix">
						<div class="checkbox c-checkbox pull-left mt0">
							<label>
								<input type="checkbox" value="" name="remember-me">
								<span class="fa fa-check"></span>Remember Me</label>
						</div>
						<div class="pull-right"><a href="recover.html" class="text-muted">Forgot your password?</a>
						</div>
					</div>
					<button type="submit" class="btn btn-block btn-primary mt-lg">Login</button>
				</form>
				<p class="pt-lg text-center">Need to Signup?</p><a href="<%=request.getContextPath()%>/user/userAdd.do" class="btn btn-block btn-default">Register Now</a>
			</div>
		</div>
		<!-- END panel-->
		<div class="p-lg text-center">
			<span>&copy;</span>
			<span>2016</span>
			<span>-</span>
			<span>Langley</span>
			<br>
		</div>
	</div>
</div>
<!-- =============== VENDOR SCRIPTS ===============-->
<!-- MODERNIZR-->
<script src="<%=request.getContextPath()%>/vendor/modernizr/modernizr.custom.js"></script>
<!-- JQUERY-->
<script src="<%=request.getContextPath()%>/vendor/jquery/dist/jquery.js"></script>
<!-- BOOTSTRAP-->
<script src="<%=request.getContextPath()%>/vendor/bootstrap/dist/js/bootstrap.js"></script>
<!-- STORAGE API-->
<script src="<%=request.getContextPath()%>/vendor/jQuery-Storage-API/jquery.storageapi.js"></script>
<!-- PARSLEY-->
<script src="<%=request.getContextPath()%>/vendor/parsleyjs/dist/parsley.min.js"></script>
<!-- =============== APP SCRIPTS ===============-->
<script src="<%=request.getContextPath()%>/app/js/app.js"></script>
</body>

</html>