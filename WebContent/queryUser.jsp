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
      var errori ='<%=request.getAttribute("errorDelete")%>';
      if(errori=='yes'){
         alert("删除失败!");
      }else if(errori=='no'){
    	  alert("请选择需要删除的选项!");
      }else if(errori=='ok'){
    	  alert("删除成功!");
      }
      function allCheck(check){
  	　　　　var checkbox=document.getElementsByName("userId");
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
      function toPage(pageIndex)
  	{
  		$("#pageIndex").attr("value",pageIndex);
  	    $("#userform").attr("action", "<%=request.getContextPath()%>/user_query");
  		$("#userform").submit();
  	}
  	
  	function pagerJump()
  	{
  		var page_size=$('#pager_jump_page_size').val();
  			
   		var regu = /^[1-9]\d*$/;
   			
   		if (!regu.test(page_size)||page_size < 1 || page_size >${pageModel.totalPageSum})
   		{
   			alert('请输入[1-'+ ${pageModel.totalPageSum} +']之间的页码！');	
   		}else
   		{
  	 		$("#pageIndex").attr("value",page_size);
   	        $("#userform").attr("action", "<%=request.getContextPath()%>/user_query");
   	    	$("#userform").submit();
   		}
  	}
  </script>
</head>
<body>
 <jsp:include  page="quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">用户查询</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp">首页</a></li>
					<li>用户查询</li>
				</ol>
			</div>
			<div id="page-inner">
				<form class="form-horizontal" id="userform" role="form" action="${pageContext.request.contextPath }/user_query" method="post">
					   <input type="hidden" name="pageIndex" value="${pageModel.pageIndex}" id="pageIndex" />
                       <div class="form-group">
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">用户名:</font></label>
                          <div class="col-sm-2">
                             <input class="form-control"  type="text" name="username" value="${user.username }"/>
                          </div>
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">用户状态:</font></label>
                          <div class="col-sm-2">
                             <input class="form-control"  type="text" name="status"  value="${user.status }"/>
                          </div>
                          <div class="col-sm-2">
                             <input class="form-control"  type="submit" value="搜索" />
                          </div>
                </form>
                <form action="${pageContext.request.contextPath }/user_delete" method="post">
                <input type="hidden" name="pageIndex" value="${pageModel.pageIndex}" id="pageIndex" />
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
                       		 <th>登陆名</th>
                       		 <th>密码</th>
                       		 <th>用户名</th>
                       		 <th>状态</th>
                       		 <th>创建时间</th>
                       		 <th>操作</th>
                       		</tr>
                       		<c:forEach items="${userList }" var="item">
                       		<tr>
                       		 <td><input type="checkbox" name="userId" value="${item.id }" id="test${item.id }"/><label for="test${item.id }"></label></td>
                       		 <td>${item.loginname }</td>
                       		 <td>${item.password }</td>
                       		 <td>${item.username }</td>
                       		 <td>${item.status }</td>
                       		 <td><fmt:formatDate
						value="${item.createdate }" pattern="yyyy年MM月dd日 " /></td>
                       		 <td><a href="${pageContext.request.contextPath }/user_edit?id=${item.id}"><i class="fa fa-edit"></i></a></td>
                       		 </tr>
                       		</c:forEach>
                       		
                       </table>
                       </div>

			</form>
			<jsp:include  page="page.jsp"/>
			</div>
			<!-- /. PAGE INNER  -->
		</div>
</body>
</html>