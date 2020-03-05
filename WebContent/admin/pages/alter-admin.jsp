<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.TrainingSystem.service.leader.AdminManage"
 %>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../css/oksub.css">
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <style>
        body,
        html {
            width: 100%;
            height: 100%;
        }

        .alter-form {
            position: absolute;
            width: 60%;
            left: 20%;
            top: 20px;
        }
    </style>
</head>

<body class="childrenBody seting-pass">
    <form class="layui-form alter-form" action="/TrainingSystem/AjaxAlterAdmin" type="get" lay-filter="alter-form">
        <div class="layui-form-item">
            <label class="layui-form-label">管理员编号</label>
            <div class="layui-input-block">
                <input type="text" value=<%=request.getParameter("id") %> lay-verify="required" class="layui-input" id="id" name="id" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">管理员姓名</label>
            <div class="layui-input-block">
                <input type="text" value=<%=request.getParameter("name") %> placeholder="用户名" lay-verify="required" class="layui-input" id="username"
                    name="name">
            </div>
        </div>
<!--         <div class="layui-form-item">
            <label class="layui-form-label">出生年月</label>
            <div class="layui-input-block">
                <input type="text" value="" class="layui-input" lay-verify="required" id="birthday" name="birthday"
                    placeholder="请选择日期">
            </div>
        </div> -->
<!--         <div class="layui-form-item">
            <label class="layui-form-label">民族</label>
            <div class="layui-input-block">
                <input type="text" value="" placeholder="民族" lay-verify="required" id="nation" name="nation"
                    class="layui-input pwd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">籍贯</label>
            <div class="layui-input-block">
                <input type="text" value="" placeholder="籍贯" lay-verify="required" class="layui-input" id="adress"
                    name="address">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">职务</label>
            <div class="layui-input-block">
                <input type="text" value="" placeholder="职务" lay-verify="required" class="layui-input" id="adress"
                    name="address">
            </div>
        </div> -->
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="add-manage">添加</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
    <script type="text/javascript" src="../../layui/layui.js"></script>
    <script src="../js/public.js"></script>
    <script>
        layui.use(['form', 'layer', 'laydate', 'jquery'], function () {
            var form = layui.form,
                layer = layui.layer,
                $ = layui.jquery,
                $form = $('form'),
                laydate = layui.laydate;
            laydate.render({
                elem: '#birthday',
                type: "date"
            });
            //$.ajax({
                //type: "get",
                //dataType: "json",
                /* data: "user_id=" + getQueryString("user_id"), */
                //url: "/TrainingSystem/AjaxAlterAdmin?id="+getQueryString("id")+"&name="+getQueryString("name"),
/*                 success: function (data) {
                    form.val("alter-form", {
                        "id":data.id,
                        "name": data.name
                         , "birthday": data.birthday
                        , "nation": data.nation
                        , "address": data.address
                        , "occupy": data.occupy
                    });
                },
                error: function (err) {
                    layer.msg("修改学员信息失败！");
                } */
            //});
            form.on("submit(add-manage)", function (data) {
/*              	$.ajax({
                type: "get",
                dataType: "text",
                //data: "user_id=" + getQueryString("user_id"),
                url: "/TrainingSystem/AjaxAlterAdmin?id="+data.id+"&name="+data.name,
            }); */
                window.parent.location.reload();
        });
       });
    </script>
</body>

</html>