<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档查询</title>
<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorD")%>';
      if(errori=='yes'){
         alert("删除失败!");
      }else if(errori=='no'){
    	  alert("删除成功!");
      }
      function allCheck(check){
    	  var checkbox=document.getElementsByName("docId");
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
				<h1 class="page-header">文档查询</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp">首页</a></li>
					<li>下载中心</li>
					<li>文档查询</li>
				</ol>
			</div>
			<div id="page-inner">
				<form class="form-horizontal" role="form" action="${pageContext.request.contextPath }/doc_query" method="post">
                       <div class="form-group">
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">标题:</font></label>
                          <div class="col-sm-2">
                             <input class="form-control"  type="text" name="title"/>
                          </div>
                          <div class="col-sm-2">
                             <input class="form-control"  type="submit" value="搜索" />
                          </div>
                          </form>
                       </div>
                       <div class="table-responsive table-bordered">
                       <table class="table">
                       		<tr>
                       		<th><input type="checkbox" id="test0"  onclick="allCheck(this)"/><label for="test0"  ></label></th>
                       		 <th>标题</th>
                       		 <th>创建时间</th>
                       		 <th>创建人</th>
                       		 <th>描述</th>
                       		 <c:if test="${activeUser.status==2 }">
                       		 	<th>操作</th>
                       		 </c:if>
                       		 <th>下载</th>
                       		</tr>
                       		<c:forEach items="${fileList }" var="item">
                       		<tr>
                       		 <td><input type="checkbox" name="docId" value="${item.id }" id="test${item.id }"/><label for="test${item.id }"></label></td>
                       		 <td>${item.title }</td>
                       		 <td><fmt:formatDate
						value="${item.createDate }" pattern="yyyy年MM月dd日 " /></td>
                       		 <td>${item.user.username }</td>
                       		 <td>${item.remark }</td>
                       		 <c:if test="${activeUser.status==2 }">
                       		 <td><a href="${pageContext.request.contextPath }/doc_delete?fileId=${item.id}"><i class="fa fa-times-circle"></i></a></td>
                       		 </c:if>
                       		 <td><a href="${pageContext.request.contextPath }/doc_download?fileId=${item.id}"><i class="fa fa-cloud-download"></i></a></td>
                       		 </tr>
                       		</c:forEach>
                       </table>
              </div>
			</div>
		
</body>
</html>