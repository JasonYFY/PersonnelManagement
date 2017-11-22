<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工查询</title>
<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorD")%>';
      if(errori=='yes'){
         alert("删除失败!");
      }else if(errori=='noC'){
    	  alert("请选择需要删除的选项!");
      }else if(errori=='no'){
    	  alert("删除成功!");
      }
      function allCheck(check){
  	　　　　var checkbox=document.getElementsByName("employeeId");
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
				<h1 class="page-header">员工查询</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp">首页</a></li>
					<li>员工管理</li>
					<li>员工查询</li>
				</ol>
			</div>
			<div id="page-inner">
				<form class="form-horizontal" role="form" action="${pageContext.request.contextPath }/Employee_query" method="post">
                       <div class="form-group">
                       	<label class="col-sm-1 control-label"><font size="3px" color="#696969">职位:</font></label>
                          <div class="col-sm-2">
                             <select class="form-control input-sm" style="width: 140px" name="jobId"   >
								<option value="">--请选择职位--</option>
								<c:forEach items="${requestScope.jobList }" var="list">
								<option value="${list.id }">${list.jobName }</option>
								</c:forEach>
								</select>
                          </div>
                          <label class="col-sm-1 control-label"><font size="3px" color="#696969">姓名:</font></label>
                          <div class="col-sm-2">
                             <input class="form-control"  type="text" name="employeeName"/>
                          </div>
                          <label class="col-sm-2 control-label"><font size="3px" color="#696969">身份证号码:</font></label>
                          <div class="col-sm-2">
                             <input class="form-control"  type="text" name="cardId" />
                          </div>
                          </div>
                          <div class="form-group">
                          <label class="col-sm-1 control-label"><font size="3px" color="#696969">性别:</font></label>
                          <div class="col-sm-2">
                          <select class="form-control input-sm" style="width: 130px" name="sex">
                          		<option value="">--请选择性别--</option>
								<option value="1">男</option>
								<option value="0">女</option>
								</select>
                          </div>
                          <label class="col-sm-1 control-label"><font size="3px" color="#696969">手机:</font></label>
                          <div class="col-sm-2">
                             <input class="form-control"  type="text" name="phone" />
                          </div>
                          <label class="col-sm-2 control-label"><font size="3px" color="#696969">所属部门:</font></label>
                          <div class="col-sm-2">
                          <select class="form-control input-sm" style="width: 150px" name="deptId"  >
								<option value="">--请选择部门--</option>
								<c:forEach items="${requestScope.deptList }" var="list">
								<option value="${list.id }">${list.deptName }</option>
								</c:forEach>
								</select>
                          </div>
                          </div>
                          <div class="form-group">
                          <div class="col-sm-2">
                             <input class="form-control"  type="submit" value="搜索" />
                          </div>
                          </form>
                          <form action="${pageContext.request.contextPath }/Employee_delete" method="post">
                          <div class="col-sm-2">
                            <c:if test="${activeUser.status==2 }">
                             <input class="form-control"  type="submit" value="删除" />
                            </c:if>
                          </div>
                       </div>
                       <div class="table-responsive table-bordered">
                       <table class="table">
                       		<tr>
                       		<th><input type="checkbox" id="test0" onclick="allCheck(this)"/><label for="test0"></label></th>
                       		 <th>姓名</th>
                       		 <th>性别</th>
                       		 <th>手机号码</th>
                       		 <th>邮箱</th>
                       		 <th>职位</th>
                       		 <th>学历</th>
                       		 <th>身份证号码</th>
                       		 <th>部门</th>
                       		 <th>联系地址</th>
                       		 <th>建档日期</th>
                       		 <th>操作</th>
                       		</tr>
                       		<c:forEach items="${empList }" var="item">
                       		<tr>
                       		 <td><input type="checkbox" name="employeeId" value="${item.employee.id }" id="test${item.employee.id }"/><label for="test${item.employee.id }"></label></td>
                       		 <td>${item.employee.employeeName }</td>
                       		 <td>
                       		 <c:choose>
                       		  <c:when test="${item.employee.sex==1 }">
								男
							 </c:when>
							 <c:otherwise>女</c:otherwise>
							 </c:choose>
                       		 </td>
                       		 <td>${item.employee.phone }</td>
                       		 <td>${item.employee.email }</td>
                       		 <td>${item.job.jobName }</td>
                       		 <td>${item.employee.education }</td>
                       		 <td>${item.employee.cardId }</td>
                       		 <td>${item.dept.deptName }</td>
                       		 <td>${item.employee.address }</td>
                       		 <td><fmt:formatDate
						value="${item.employee.createDate }" pattern="yyyy年MM月dd日 " /></td>
                       		 <td><a href="${pageContext.request.contextPath }/Employee_edit?id=${item.employee.id}"><i class="fa fa-edit"></i></a></td>
                       		 </tr>
                       		</c:forEach>
                       		
                       </table>
                       </div>

			</form>
			</div>
			<!-- /. PAGE INNER  -->
		</div>
</body>
</html>