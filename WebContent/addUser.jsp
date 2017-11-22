<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>

<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorA")%>';
      if(errori=='yes'){
         alert("登录名重复，添加失败!");
      }else if(errori=='no'){
    	  alert("添加成功!");
      }
  </script>
 
</head>
<body>
 <jsp:include  page="quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">添加用户</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp">首页</a></li>
					<li>用户管理</li>
					<li>添加用户</li>
				</ol>
			</div>
			<div id="page-inner">
				<form action="${pageContext.request.contextPath }/user_register" method="post">
					<table >
						<tr>
							<td>姓名:<input type="text" name="username" required/></td>
							<td>状态:<span id="exs" style="color: red;"></span><input type="text" name="status" id="status" required/></td>
						</tr>
						<tr>
							<td width="50%">登录名:<span id="ex" style="color: red;"></span><input type="text" name="loginname" id="loginname" required/></td>
							<td width="50%">密码:<input type="text" name="password" required/></td>
						</tr>
						<tr>
							<td class="waves-effect waves-light btn" style="margin-right: 80px"><input type="submit" id="submit" value="确定"/></td>
							<td class="waves-effect waves-light btn"><input type="reset" value="取消"/></td>
						</tr>
					</table>
				
				</form>

			</div>
			<!-- /. PAGE INNER  -->
		</div>
		
	<script>
	$('#loginname').on('blur',function(){
        var loginname = $(this).val();
        if(loginname!=""){
        	$.ajax({
                url : "./check_loginname",
                type : 'post',
                data : {loginname:loginname},
                dataType : 'text',
                success : function(data){
                  $('#ex').html("(提示---"+data+")")
                  if(data=="登陆名已被使用"){
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
    $('#status').on('blur',function(){
    	var status = $(this).val();
    	if(status!=""){
	    	if(status==1||status==2){
	    		$('#exs').html("");
	    		$('#submit').removeAttr("disabled")
	    	}else{
	    		$('#exs').html("(提示---状态只能填写数字1或2)");
	    		$('#status').focus();
	            $('#submit').attr("disabled","disabled")
	    	}
    	}
    })
 	</script>
</body>
</html>