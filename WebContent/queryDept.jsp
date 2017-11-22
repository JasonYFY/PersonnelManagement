<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询部门</title>
<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorDelete")%>';
      if(errori=='yes'){
         alert("删除失败!");
      }else if(errori=='no'){
    	  alert("请选择需要删除的选项!");
      }else if(errori=='ok'){
    	  alert("删除成功!");
      }
      function allCheck(check){
    	  var checkbox=document.getElementsByName("deptId");
          if(check.checked){
	　　　　　　for(var i=0;i<checkbox.length;i++){
	　　　　　　　　checkbox[i].checked="checked";
	　　　　　　}    	
	　　　　}else{
	　　　　　　for(var i=0;i<checkbox.length;i++){
	　　　　　　　　checkbox[i].checked="";
	　　　　　　} 
	　　　　}
	　}　　　　
  </script>
</head>
<body>
 <jsp:include  page="quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">查询部门</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp">首页</a></li>
					<li>部门管理</li>
					<li>查询部门</li>
				</ol>
			</div>
			<div id="page-inner">
				<form class="form-horizontal" role="form" action="${pageContext.request.contextPath }/dept_query" method="post">
                       <div class="form-group">
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">部门名称:</font></label>
                          <div class="col-sm-2">
                             <input class="form-control"  type="text" name="deptName"/>
                          </div>
                          <div class="col-sm-2">
                             <input class="form-control"  type="submit" value="搜索" />
                          </div>
                          </form>
                          <form action="${pageContext.request.contextPath }/dept_delete" method="post">
                          <div class="col-sm-2">
                            <c:if test="${activeUser.status==2 }">
                             <input class="form-control"  type="submit" value="删除" />
                            </c:if>
                          </div>
                       </div>
                       <div class="table-responsive table-bordered">
                       <table class="table">
                       		<tr>
                       		<th><input type="checkbox" id="test0"  onclick="allCheck(this)"/><label for="test0"  ></label></th>
                       		 <th>部门名称</th>
                       		 <th>详细信息</th>
                       		 <th>操作</th>
                       		</tr>
                       		<c:forEach items="${deptList }" var="item">
                       		<tr>
                       		 <td><input type="checkbox" name="deptId" value="${item.id }" id="test${item.id }"/><label for="test${item.id }"></label></td>
                       		 <td>${item.deptName }</td>
                       		 <td>${item.remark }</td>
                       		 <td><a href="${pageContext.request.contextPath }/dept_edit?id=${item.id}"><i class="fa fa-edit"></i></a></td>
                       		 </tr>
                       		</c:forEach>
                       </table>
              </div>
			</form>
			</div>
		
</body>
</html>