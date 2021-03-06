<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.TrainingSystem.service.leader.GroundManage"
    import="java.util.ArrayList"%>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../css/oksub.css">
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <style>
        .layui-form-label {
            text-align: left;
        }

        .layui-container {
            width: 40% !important;
            margin-top: 30px;
        }
    </style>
</head>

<body>
    <div class="layui-container">
        <form class="layui-form" lay-filter="alter-form" action="/TrainingSystem/AjaxAlterGround" type="post"><!-- action路径写自己的servlet -->
            <div class="layui-form-item">
                <label class="layui-form-label">场地编号</label>
                <div class="layui-input-block">
                    <input type="text" value=<%=request.getParameter("gid") %> class="layui-input" lay-verify="required" id="team_id" readonly
                        name="gid">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">场地名称</label>
                <div class="layui-input-block">
                    <input type="text" value=<%=request.getParameter("gname") %> class="layui-input" lay-verify="required" id="team" 
                        name="gname">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">场地状态</label>
                <div class="layui-input-block">
                    <select name="ground" lay-verify="required" lay-filter="address">
                        <option value=""></option>
                        <option value="1">可进行训练</option>
                        <option value="0">不可进行训练</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="alter-manage">修改</button>
            </div>
        </form>
    </div>
    <script type="text/javascript" src="../../layui/layui.js"></script>
    <script src="../js/public.js"></script>
    <script>
        layui.use(['form', 'laydate'], function () {
            var form = layui.form,
                laydate = layui.laydate;
            laydate.render({
                elem: '#datetime'
                , type: 'datetime'
            });
            form.on("submit(alter-manage)", function (data) {

                window.parent.location.reload();
            });
        });
    </script>
</body>

</html>