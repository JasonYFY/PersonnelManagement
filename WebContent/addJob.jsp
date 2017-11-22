<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加职位</title>
<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorJ")%>';
      if(errori=='yes'){
         alert("职位名称重复，添加失败!");
      }else if(errori=='no'){
    	  alert("添加成功!");
      }  
  </script>
</head>
<body>
 <jsp:include  page="quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">添加职位</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp">首页</a></li>
					<li>职位管理</li>
					<li>添加职位</li>
				</ol>
			</div>
			<div id="page-inner">
				<form action="${pageContext.request.contextPath }/job_add" method="post">
					<table >
						<tr>
							<td>职位名称:<input type="text" name="jobName" required/></td>
							<td>详细描述:<input type="text" name="jobRemark"/></td>
						</tr>
						<tr>
							<td><input class="waves-effect waves-light btn" type="submit" value="添加"/></td>
							<td><input class="waves-effect waves-light btn" type="reset" value="取消"/></td>
						</tr>
					</table>
				
				</form>

			</div>
		</div>
</body>
</html>