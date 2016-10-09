<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<header class="topnavbar-wrapper">
	<!-- START Top Navbar-->
	<nav role="navigation" class="navbar topnavbar">
		<!-- START navbar header-->
		<div class="navbar-header">
			<a href="<%=request.getContextPath()%>/main/main.do" class="navbar-brand">
				<div class="brand-logo">
					<img src="<%=request.getContextPath()%>/app/img/logo.png" alt="App Logo" class="img-responsive">
				</div>
				<div class="brand-logo-collapsed">
					<img src="<%=request.getContextPath()%>/app/img/logo-single.png" alt="App Logo" class="img-responsive">
				</div>
			</a>
		</div>
		<!-- END navbar header-->
		<!-- START Nav wrapper-->
		<div class="nav-wrapper">
			<!-- START Left navbar-->
			<ul class="nav navbar-nav">
				<li>
					<!-- Button used to collapse the left sidebar. Only visible on tablet and desktops-->
					<a href="#" data-trigger-resize="" data-toggle-state="aside-collapsed" class="hidden-xs">
						<em class="fa fa-navicon"></em>
					</a>
					<!-- Button to show/hide the sidebar on mobile. Visible on mobile only.-->
					<a href="#" data-toggle-state="aside-toggled" data-no-persist="true" class="visible-xs sidebar-toggle">
						<em class="fa fa-navicon"></em>
					</a>
				</li>
				<!-- START User avatar toggle-->
				<li>
					<!-- Button used to collapse the left sidebar. Only visible on tablet and desktops-->
					<a id="user-block-toggle" href="<%=request.getContextPath()%>/user/userEdit.do" data-toggle="collapse">
						<em class="icon-user"></em>
					</a>
				</li>
				<!-- END User avatar toggle-->
				<!-- START lock screen-->
				<li>
					<a href="#" title="Lock screen">
						<em class="icon-lock"></em>
					</a>
				</li>
				<!-- END lock screen-->
			</ul>
			<!-- END Left navbar-->
			<!-- START Right Navbar-->
			<ul class="nav navbar-nav navbar-right">
				<!-- Search icon-->
				<li>
					<a href="#" data-search-open="">
						<em class="icon-magnifier"></em>
					</a>
				</li>
				<!-- Fullscreen (only desktops)-->
				<li class="visible-lg">
					<a href="#" data-toggle-fullscreen="">
						<em class="fa fa-expand"></em>
					</a>
				</li>
				<!-- START Alert menu-->
				<li class="dropdown dropdown-list">
					<a href="#" data-toggle="dropdown">
						<em class="icon-bell"></em>
						<div class="label label-danger">11</div>
					</a>
					<!-- START Dropdown menu-->
					<ul class="dropdown-menu animated flipInX">
						<li>
							<!-- START list group-->
							<div class="list-group">
								<!-- list item-->
								<a href="#" class="list-group-item">
									<div class="media-box">
										<div class="pull-left">
											<em class="fa fa-twitter fa-2x text-info"></em>
										</div>
										<div class="media-box-body clearfix">
											<p class="m0">New followers</p>
											<p class="m0 text-muted">
												<small>1 new follower</small>
											</p>
										</div>
									</div>
								</a>
								<!-- list item-->
								<a href="#" class="list-group-item">
									<div class="media-box">
										<div class="pull-left">
											<em class="fa fa-envelope fa-2x text-warning"></em>
										</div>
										<div class="media-box-body clearfix">
											<p class="m0">New e-mails</p>
											<p class="m0 text-muted">
												<small>You have 10 new emails</small>
											</p>
										</div>
									</div>
								</a>
								<!-- list item-->
								<a href="#" class="list-group-item">
									<div class="media-box">
										<div class="pull-left">
											<em class="fa fa-tasks fa-2x text-success"></em>
										</div>
										<div class="media-box-body clearfix">
											<p class="m0">Pending Tasks</p>
											<p class="m0 text-muted">
												<small>11 pending task</small>
											</p>
										</div>
									</div>
								</a>
								<!-- last list item-->
								<a href="#" class="list-group-item">
									<small>More notifications</small>
									<span class="label label-danger pull-right">14</span>
								</a>
							</div>
							<!-- END list group-->
						</li>
					</ul>
					<!-- END Dropdown menu-->
				</li>
				<!-- END Alert menu-->
				<!-- START Offsidebar button-->

				<sec:authorize access="isAnonymous()">
				<li>
					<a href="<%=request.getContextPath()%>/main/login.do" data-toggle-state="offsidebar-open" data-no-persist="true">
						<em class="fa fa-sign-in"></em>
					</a>
				</li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<c:url value="/j_spring_security_logout" var="logoutUrl" />
					<li>
						<a href="${logoutUrl}" data-toggle-state="offsidebar-open" data-no-persist="true">
							<em class="fa fa-sign-out"></em>
						</a>
					</li>
				</sec:authorize>
				<li>
					<a href="<%=request.getContextPath()%>/goods/cart.do" data-toggle-state="offsidebar-open" data-no-persist="true">
						<em class="fa fa-shopping-cart"></em>
					</a>
				</li>
				<!-- END Offsidebar menu-->
			</ul>
			<!-- END Right Navbar-->
		</div>
		<!-- END Nav wrapper-->
		<!-- START Search form-->
		<form role="search" action="search.html" class="navbar-form">
			<div class="form-group has-feedback">
				<input type="text" placeholder="Type and hit enter ..." class="form-control">
				<div data-search-dismiss="" class="fa fa-times form-control-feedback"></div>
			</div>
			<button type="submit" class="hidden btn btn-default">Submit</button>
		</form>
		<!-- END Search form-->
	</nav>
	<!-- END Top Navbar-->
</header>
<!-- sidebar-->
<aside class="aside">
	<!-- START Sidebar (left)-->
	<div class="aside-inner">
		<nav data-sidebar-anyclick-close="" class="sidebar">
			<!-- START sidebar nav-->
			<ul class="nav">
				<!-- Iterates over all sidebar items-->
				<li class="nav-heading ">
					<span data-localize="sidebar.heading.HEADER">Main Navigation</span>
				</li>
				<%--<li class=" ">--%>
					<%--<a href="#dashboard" title="Dashboard" data-toggle="collapse">--%>
						<%--<div class="pull-right label label-info">3</div>--%>
						<%--<em class="icon-speedometer"></em>--%>
						<%--<span data-localize="sidebar.nav.DASHBOARD">Product</span>--%>
					<%--</a>--%>
					<%--<ul id="dashboard" class="nav sidebar-subnav collapse">--%>
						<%--<li class="sidebar-subnav-header">Product</li>--%>
						<%--<li class=" ">--%>
							<%--<a href="dashboard.html" title="Dashboard v1">--%>
								<%--<span>Dashboard v1</span>--%>
							<%--</a>--%>
						<%--</li>--%>
						<%--<li class=" ">--%>
							<%--<a href="dashboard_v2.html" title="Dashboard v2">--%>
								<%--<span>Dashboard v2</span>--%>
							<%--</a>--%>
						<%--</li>--%>
					<%--</ul>--%>
				<%--</li>--%>
				<li class=" ">
					<a href="<%=request.getContextPath()%>/goods/products.do?s=product" title="Widgets">
						<div class="pull-right label label-success">30</div>
						<em class="icon-grid"></em>
						<span data-localize="sidebar.nav.WIDGETS">Product</span>
					</a>
				</li>
				<li class=" ">
					<a href="#layout" title="Layouts" data-toggle="collapse">
						<em class="icon-layers"></em>
						<span>Search</span>
					</a>
					<ul id="layout" class="nav sidebar-subnav collapse">
						<li class="sidebar-subnav-header">Search</li>
						<li class=" ">
							<a href="<%=request.getContextPath()%>/goods/products.do?s=product" title="Goods">
								<span>Products Search</span>
							</a>
						</li>
						<li class=" ">
							<a href="<%=request.getContextPath()%>/search/advSearch.do" title="Graph">
								<span>Advance Search</span>
							</a>
						</li>
						<li class=" ">
							<a href="<%=request.getContextPath()%>/search/graph.do" title="Graph">
								<span>Graph Search</span>
							</a>
						</li>
					</ul>
				</li>

				<sec:authorize access="isAuthenticated()">
				<li class="nav-heading ">
					<span data-localize="sidebar.heading.COMPONENTS">My Account</span>
				</li>
				<li class=" ">
					<a href="#elements" title="Elements" data-toggle="collapse">
						<em class="icon-chemistry"></em>
						<span data-localize="sidebar.nav.element.ELEMENTS">Profile</span>
					</a>
					<ul id="elements" class="nav sidebar-subnav collapse">
						<li class="sidebar-subnav-header">Account</li>
						<li class=" ">
							<a href="<%=request.getContextPath()%>/user/userEdit.do" title="My Account">
								<span data-localize="sidebar.nav.element.BUTTON">My Account</span>
							</a>
						</li>
						<li class=" ">
							<a href="<%=request.getContextPath()%>/user/wishlist.do" title="Wishlist">
								<span data-localize="sidebar.nav.element.NOTIFICATION">Wishlist</span>
							</a>
						</li>
						<li class=" ">
							<a href="<%=request.getContextPath()%>/user/orders.do" title="My Orders">
								<span>My Orders</span>
							</a>
						</li>
						<li class=" ">
							<a href="<%=request.getContextPath()%>/goods/cart.do" title="My Cart">
								<span>My Cart</span>
							</a>
						</li>
					</ul>
				</li>
				<li class=" ">
					<a href="#forms" title="Forms" data-toggle="collapse">
						<em class="icon-note"></em>
						<span data-localize="sidebar.nav.form.FORM">Carts</span>
					</a>
					<ul id="forms" class="nav sidebar-subnav collapse">
						<li class="sidebar-subnav-header">Forms</li>
						<li class=" ">
							<a href="form-standard.html" title="Standard">
								<span data-localize="sidebar.nav.form.STANDARD">Standard</span>
							</a>
						</li>
						<li class=" ">
							<a href="form-extended.html" title="Extended">
								<span data-localize="sidebar.nav.form.EXTENDED">Extended</span>
							</a>
						</li>
						<li class=" ">
							<a href="form-validation.html" title="Validation">
								<span data-localize="sidebar.nav.form.VALIDATION">Validation</span>
							</a>
						</li>
						<li class=" ">
							<a href="form-wizard.html" title="Wizard">
								<span>Wizard</span>
							</a>
						</li>
						<li class=" ">
							<a href="form-upload.html" title="Upload">
								<span>Upload</span>
							</a>
						</li>
						<li class=" ">
							<a href="form-xeditable.html" title="xEditable">
								<span>xEditable</span>
							</a>
						</li>
						<li class=" ">
							<a href="form-imagecrop.html" title="Cropper">
								<span>Cropper</span>
							</a>
						</li>
					</ul>
				</li>
				<li class=" ">
					<a href="#tables" title="Tables" data-toggle="collapse">
						<em class="icon-grid"></em>
						<span data-localize="sidebar.nav.table.TABLE">Orders</span>
					</a>
					<ul id="tables" class="nav sidebar-subnav collapse">
						<li class="sidebar-subnav-header">Tables</li>
						<li class=" ">
							<a href="table-standard.html" title="Standard">
								<span data-localize="sidebar.nav.table.STANDARD">Standard</span>
							</a>
						</li>
						<li class=" ">
							<a href="table-extended.html" title="Extended">
								<span data-localize="sidebar.nav.table.EXTENDED">Extended</span>
							</a>
						</li>
						<li class=" ">
							<a href="table-datatable.html" title="DataTables">
								<span data-localize="sidebar.nav.table.DATATABLE">DataTables</span>
							</a>
						</li>
						<li class=" ">
							<a href="table-jqgrid.html" title="jqGrid">
								<span>jqGrid</span>
							</a>
						</li>
					</ul>
				</li>
				</sec:authorize>

				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="nav-heading ">
					<span data-localize="sidebar.heading.MORE">More</span>
				</li>
				<li class=" ">
					<a href="#pages" title="Manager" data-toggle="collapse">
						<em class="icon-doc"></em>
						<span data-localize="sidebar.nav.pages.PAGES">Manager</span>
					</a>
					<ul id="pages" class="nav sidebar-subnav collapse">
						<li class="sidebar-subnav-header">Manager</li>
						<li class=" ">
							<a href="<%=request.getContextPath()%>/admin/statistics.do" title="Statistics">
								<span data-localize="sidebar.nav.pages.LOGIN">Statistics</span>
							</a>
						</li>
						<li class=" ">
							<a href="<%=request.getContextPath()%>/user/userList.do" title="User Info">
								<span data-localize="sidebar.nav.pages.REGISTER">User Info</span>
							</a>
						</li>
						<li class=" ">
							<a href="<%=request.getContextPath()%>/goods/list.do" title="Good Info">
								<span data-localize="sidebar.nav.pages.RECOVER">Good Info</span>
							</a>
						</li>
						<%--<li class=" ">--%>
							<%--<a href="<%=request.getContextPath()%>/admin/search.do" title="Search users">--%>
								<%--<span data-localize="sidebar.nav.pages.RECOVER">Search users</span>--%>
							<%--</a>--%>
						<%--</li>--%>
					</ul>
				</li>
				</sec:authorize>
			</ul>
			<!-- END sidebar nav-->
		</nav>
	</div>
	<!-- END Sidebar (left)-->
</aside>