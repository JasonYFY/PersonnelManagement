<%@page import="com.jason.service.domain.EmployeeS"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改员工</title>

<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorU")%>';
      if(errori=='yes'){
         alert("修改失败!");
      }else if(errori=='no'){
    	  alert("修改成功!");
      }
      
  </script>
 <%
 	EmployeeS s = (EmployeeS)request.getAttribute("emp"); 
 	String bDate = null;
 	java.util.Date d = s.getEmployee().getBirthday();
 	if(d!=null){
 		bDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
 	}
 	pageContext.setAttribute("bDate", bDate);
 
 %>
</head>
<body>
 <jsp:include  page="quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">修改员工</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp">首页</a></li>
					<li>员工管理</li>
					<li>修改员工</li>
				</ol>
			</div>
			<div id="page-inner">
				<form action="${pageContext.request.contextPath }/Employee_update" method="post">
					<input type="hidden" name="id" value="${emp.employee.id }"/>
					<table >
						<tr>
							<td>姓名:<input type="text" name="employeeName" value="${emp.employee.employeeName }" required/></td>
							<td width="50%">身份证号码:<span id="ex" style="color: red;"></span><input type="text" name="cardId" id="cardId" value="${emp.employee.cardId }" required/></td>
						</tr>
						<tr>
							<td>性别:
								<select class="form-control input-sm" style="width: 80px" name="sex">
								<c:choose>
                       		  <c:when test="${emp.employee.sex==1 }">
								<option value="1" selected="selected">男</option>
							 </c:when>
							 <c:otherwise>
								<option value="1">男</option>
							 </c:otherwise>
							 </c:choose>
							 <c:choose>
							 <c:when test="${emp.employee.sex==0 }">
								<option value="0" selected="selected">女</option>
							 </c:when>
							 <c:otherwise>
								<option value="0" >女</option>
							 </c:otherwise>
							 </c:choose>
								</select>
							</td>
							<td>职位:
								<select class="form-control input-sm" style="width: 150px" name="jobId"  required >
								<option value="">--请选择职位--</option>
								<c:forEach items="${requestScope.jobList }" var="list">
								<c:choose>
									<c:when test="${emp.employee.jobId==list.id }">
									<option value="${list.id }" selected="selected">${list.jobName }</option>
									</c:when>
									<c:otherwise>
									<option value="${list.id }" >${list.jobName }</option>
									</c:otherwise>
								</c:choose>
								</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td><br/>学历:<input type="text" name="education" value="${emp.employee.education }"/></td>
							<td><br/>邮箱:<span id="exE" style="color: red;"></span><input type="text" name="email" id="email" value="${emp.employee.email }"/></td>
						</tr>
						<tr>
							<td>手机:<span id="exP" style="color: red;"></span><input type="text" name="phone" id="phone" value="${emp.employee.phone }" required/></td>
							<td>电话号码:<input type="text" name="tel" value="${emp.employee.tel }"/></td>
						</tr>
						<tr>
							<td>政治面貌:<input type="text" name="party" value="${emp.employee.party }"/></td>
							<td>QQ号码:<input type="text" name="qqNum" value="${emp.employee.qqNum }"/></td>
						</tr>
						<tr>
							<td>联系地址:<input type="text" name="address" value="${emp.employee.address }"/></td>
							<td>邮政编码:<input type="text" name="postCode" value="${emp.employee.postCode }"/></td>
						</tr>
						<tr>
							<td>出生日期:<input type="date" name="birthday" value="${bDate }"/></td>
							<td>民族:<input type="text" name="race" value="${emp.employee.race }"/></td>
						</tr>
						<tr>
							<td>所学专业:<input type="text" name="speciality" value="${emp.employee.speciality }"/></td>
							<td>爱好:<input type="text" name="hobby" value="${emp.employee.hobby}"/></td>
						</tr>
						<tr>
							<td>备注:<br/><input type="text" name="remark" style="width: 95%" value="${emp.employee.remark}"/></td>
							<td>所属部门:
								<select class="form-control input-sm" style="width: 150px" name="deptId" required >
								<option value="">--请选择部门--</option>
								<c:forEach items="${requestScope.deptList }" var="list">
								<c:choose>
									<c:when test="${emp.employee.deptId==list.id }">
									<option value="${list.id }" selected="selected">${list.deptName }</option>
									</c:when>
									<c:otherwise>
									<option value="${list.id }" >${list.deptName }</option>
									</c:otherwise>
								</c:choose>
								</c:forEach>
								</select>
								</td>
						</tr>
						<tr>
							<td>
							<c:if test="${activeUser.status==2 }">
							<input class="waves-effect waves-light btn" type="submit" id="submit" value="确定"/>
							</c:if>
							</td>
							<td><a href="queryEmployee.jsp"><input class="waves-effect waves-light btn" type="button" value="返回"/></a></td>
						</tr>
					</table>
				
				</form>

			</div>
			<!-- /. PAGE INNER  -->
		</div>
		
<script>
	$('#cardId').on('blur',function(){
		var cardId = $(this).val();
        var reg = /^[0-9]{18}$/;
       	if(!reg.test(cardId)){
        	$('#ex').html("(提示---请填写正确的身份证格式)");
        	$('#cardId').focus();
            $('#submit').attr("disabled","disabled")
        }else{
	        $.ajax({
	            url : "./check_cardId",
	            type : 'post',
	            data : {cardId:cardId},
	            dataType : 'text',
	            success : function(data){
	              $('#ex').html("(提示---"+data+")")
	              if(data=="身份证已被使用"){
	                  $('#loginname').focus();
	                  $('#submit').attr("disabled","disabled")
	              }else{
	              	$('#submit').removeAttr("disabled")
	              }
	            },
	            error : function() {
	                alert("请求失败")
	            }
	        })
        }
    })
	</script>
<script type="text/javascript">
    $('#phone').on('blur',function(){
    	var phone = $(this).val();
    	var reg = /^1[0-9]{10}$/;
    	if(reg.test(phone)){
    		$('#exP').html("");
    		$('#submit').removeAttr("disabled")
    	}else{
    		$('#exP').html("(提示---请填写正确的手机号)");
    		$('#phone').focus();
            $('#submit').attr("disabled","disabled")
    	}
    })
    $('#email').on('blur',function(){
    	var email = $(this).val();
    	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    	if(reg.test(email)){
    		$('#exE').html("");
    		$('#submit').removeAttr("disabled")
    	}else{
    		$('#exE').html("(提示---请填写正确的邮箱地址)");
    		$('#email').focus();
            $('#submit').attr("disabled","disabled")
    	}
    })
</script>
</body>
</html>