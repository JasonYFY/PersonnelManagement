<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户查询</title>
<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorU")%>';
      if(errori=='yes'){
         alert("修改失败!");
      }else if(errori=='no'){
    	  alert("修改成功!");
      }
  </script>
</head>
<body>
 <jsp:include  page="quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">用户编辑</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp">首页</a></li>
					<li>用户管理</li>
					<li>用户编辑</li>
				</ol>
			</div>
			<div id="page-inner">
				<form class="form-horizontal" role="form" action="${pageContext.request.contextPath }/user_update" method="post">
				<input type="hidden" name="id" value="${requestScope.user.id}"/>
                       <div class="form-group">
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">用户名:</font></label>
                          <div class="col-sm-2">
                             <input class="form-control"  type="text" name="username" value="${requestScope.user.username}"/>
                          </div>
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">密码:</font></label>
                          <div class="col-sm-2">
                             <input class="form-control"  type="text" name="password" value="${requestScope.user.password }"/>
                          </div>
                       </div>
                       <div class="form-group">
                       	<label class="col-sm-2 control-label"><font size="4px" color="#696969">登陆名:</font></label>
                          <div class="col-sm-2">
                             <input class="form-control"  type="text" name="loginname" value="${requestScope.user.loginname }" readonly="readonly"/>
                          </div>
                       	 <label class="col-sm-2 control-label"><font size="4px" color="#696969">状态:</font></label>
                          <div class="col-sm-2">
                             <input class="form-control"  type="text" name="status" value="${requestScope.user.status }"/>
                          </div>
                       </div>
                       <div class="form-group">
                       <c:if test="${activeUser.status==2 }">
                          <div class="col-sm-2">
                             <input class="form-control"  type="submit" value="修改" />
                          </div>
                       </c:if>
                          <div class="col-sm-2">
                             <a href="queryUser.jsp"><input class="form-control"  type="button" value="返回" /></a>
                          </div>
                       </div>
                          </form>
			</div>
		</div>
</body>
</html>