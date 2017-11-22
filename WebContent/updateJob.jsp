<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>职位编辑</title>
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
				<h1 class="page-header">职位编辑</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp">首页</a></li>
					<li>职位管理</li>
					<li>职位编辑</li>
				</ol>
			</div>
			<div id="page-inner">
				<form class="form-horizontal" role="form" action="${pageContext.request.contextPath }/job_update" method="post">
				<input type="hidden" name="id" value="${requestScope.job.id}"/>
                       <div class="form-group">
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">职位名称:</font></label>
                          <div class="col-sm-3">
                             <input class="form-control"  type="text" name="jobName" id="jobName" value="${requestScope.job.jobName}"/>
                             <span id="ex" style="color: red;"></span>
                          </div>
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">详细信息:</font></label>
                          <div class="col-sm-3">
                             <input class="form-control"  type="text" name="remark" value="${requestScope.job.remark }"/>
                          </div>
                       </div>
                       <div class="form-group">
                       <c:if test="${activeUser.status==2 }">
                          <div class="col-sm-2">
                             <input class="form-control"  type="submit" id="submit" value="修改" />
                          </div>
                        </c:if>
                          <div class="col-sm-2">
                             <a href="queryJob.jsp"><input class="form-control"  type="button" value="返回" /></a>
                          </div>
                       </div>
                          </form>
			</div>
		</div>
<script>
	$('#jobName').on('change',function(){
        var jobName = $(this).val();
        if(jobName!=""){
        	$.ajax({
                url : "./check_jobName",
                type : 'post',
                data : {jobName:jobName},
                dataType : 'text',
                success : function(data){
                  $('#ex').html("(提示---"+data+")")
                  if(data=="职位名称已被使用"){
                      $('#jobName').focus();
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
</body>
</html>