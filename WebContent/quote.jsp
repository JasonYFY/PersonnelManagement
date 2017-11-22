<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>

<link rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/font-awesome.css" rel="stylesheet" />
<link href="css/morris-0.4.3.min.css" rel="stylesheet" />
<link href="css/custom-styles.css" rel="stylesheet" />
<link rel="stylesheet" href="css/cssCharts.css">

<script src="js/jquery-1.10.2.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/materialize.min.js"></script>
<script src="js/jquery.metisMenu.js"></script>
<script src="js/raphael-2.1.0.min.js"></script>
<script src="js/morris.js"></script>
<script src="js/easypiechart.js"></script>
<script src="js/easypiechart-data.js"></script>
<script src="js/jquery.chart.js"></script>
<script src="js/custom-scripts.js"></script>
<script src="js/dataTables.bootstrap.js"></script>
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle waves-effect waves-dark"
				data-toggle="collapse" data-target=".sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand waves-effect waves-dark" href="index.jsp"><i
				class="large material-icons"><i class="material-icons dp48">thumbs_up_down</i></i>
				<strong>人事管理系统</strong></a>

		</div>

		<ul class="nav navbar-top-links navbar-right">
			<li><a class="dropdown-button waves-effect waves-dark"></a></li>
			<li><a class="dropdown-button waves-effect waves-dark"><i
					class="fa fa-clock-o fa-fw"></i>&nbsp;<fmt:formatDate
						value="${now }" pattern="yyyy年MM月dd日   EEEE" /></a></li>
						
			<!-- <li><a class="dropdown-button waves-effect waves-dark" href="#!"
				data-activates="dropdown3"><i class="fa fa-tasks fa-fw"></i> <i
					class="material-icons right">arrow_drop_down</i></a></li>
			<li><a class="dropdown-button waves-effect waves-dark" href="#!"
				data-activates="dropdown2"><i class="fa fa-bell fa-fw"></i> <i
					class="material-icons right">arrow_drop_down</i></a></li> -->
					
			<li><a class="dropdown-button waves-effect waves-dark" href="#!"
				data-activates="dropdown1"><i class="fa fa-user fa-fw"></i> 
				<b>${sessionScope.activeUser.username }</b> <i class="material-icons right">arrow_drop_down</i></a></li>
		</ul>
		</nav>
		<!-- Dropdown Structure -->
		<ul id="dropdown1" class="dropdown-content">
			<!-- <li><a href="#"><i class="fa fa-user fa-fw"></i> My Profile</a>
			</li>
			<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li> -->
			<li><a href="${pageContext.request.contextPath }/user_loginout"><i class="fa fa-sign-out fa-fw"></i> 注销</a></li>
		</ul>

		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">


				<li><a href="#" class="waves-effect waves-dark"><i
						class="fa fa-user fa-fw"></i>用户管理<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="queryUser.jsp">用户查询</a></li>
						<li><a href="addUser.jsp">添加用户</a></li>
					</ul></li>
				<li><a href="#" class="waves-effect waves-dark"><i
						class="fa fa-sitemap"></i>部门管理<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="queryDept.jsp">部门查询</a></li>
						<li><a href="addDept.jsp">添加部门</a></li>
					</ul></li>
				<li><a href="#" class="waves-effect waves-dark"><i
						class="fa fa-table"></i>职位管理<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="queryJob.jsp">职位查询</a></li>
						<li><a href="addJob.jsp">添加职位</a></li>
					</ul></li>
				<li><a href="#" class="waves-effect waves-dark"><i
						class="fa fa-qrcode"></i>员工管理<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="queryEmployee.jsp">员工查询</a></li>
						<li><a href="addEmployee.jsp">添加员工</a></li>
					</ul></li>
				<li><a href="" class="waves-effect waves-dark"><i
						class="fa fa-fw fa-file"></i>下载中心<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="queryDocument.jsp">文档查询</a></li>
						<li><a href="uploadFile.jsp">上传文档</a></li>
					</ul></li>

			</ul>

		</div>
		</nav>
	</div>
</body>
</html>