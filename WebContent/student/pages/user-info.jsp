<%@ page language='java' contentType="text/html; charset=UTF-8" pageEncoding='UTF-8'%>
<!DOCTYPE html>
<html>
<head>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/student/";  
request.setAttribute("path", basePath);  
%> 
    <meta charset="utf-8">
    <title>基本资料</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${path}css/oksub.css">
    <link rel="stylesheet" href="${path}layui/css/layui.css">
</head>
<body class="user-info">
    <form class="layui-form" action="" method="post" id="myform" name="myform">
        <div class="user_left">
            <div class="layui-form-item">
                <label class="layui-form-label">学号</label>
                <div class="layui-input-block">
                    <!-- <input type="text" value="000000006" disabled class="layui-input layui-disabled"> -->
                    <input type="text" value=<%=session.getAttribute("userID") %> class="layui-input" name="userID">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" value=<%=session.getAttribute("userName") %> class="layui-input userName" placeholder="姓名" name="userName">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="text" value=<%=session.getAttribute("userGender") %> class="layui-input userGender" placeholder="性别" name="userGender">
                </div>
            </div>
<%--             <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="text" value=<%=session.getAttribute("userPassword") %> class="layui-input userPassword" placeholder="密码" name="userPassword">
                </div>
            </div> --%>
            <div class="layui-form-item">
                <label class="layui-form-label">出生年月</label>
                <div class="layui-input-block">
                	<input type="text" value=<%=session.getAttribute("userBirthday") %> placeholder="出生日期" class="layui-input userBirthday" name="userBirthday">
                    <!-- <input id="uDate" type="text" value="" placeholder="请输入出生年月" lay-verify="required" class="layui-input userBirthday"> -->
                </div>
            </div>
<%--             <div class="layui-form-item">
                <label class="layui-form-label">人员类别</label>
                <div class="layui-input-block">
                	<input type="text" value="${userinfo.getStudent_Class() }" class="layui-input userClass" placeholder="类别" name="userClass">
                    <select name="userClass" lay-verify="required", id="userSelect">
                        <option value="${userinfo.getStudent_Class() }"></option>
                        <option value="0">一类</option>
                        <option value="1">二类</option>
                        <option value="2">三类</option>
                      </select>
                </div>
            </div> --%>
            <div class="layui-form-item">
                <label class="layui-form-label">学员队</label>
                <div class="layui-input-block">
                    <input type="text" value=<%=session.getAttribute("groupName") %> placeholder="学员队" class="layui-input" class="layui-input userGroup" name="userGroup">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                   <!--  <button class="layui-btn" lay-submit="" lay-filter="changeUser">提交</button>
        -->             <button type="reset" class="layui-btn layui-bg-green">确定</button>
                </div>
            </div>
        </div>
    </form>
    <script type="text/javascript" src="${path}layui/layui.js"></script>
    <script type="text/javascript" src="${path}js/user-info.js"></script>
</body>
</html>
