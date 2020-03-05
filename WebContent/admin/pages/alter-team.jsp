<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.TrainingSystem.service.leader.GroupManage"
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
        <form class="layui-form" lay-filter="alter-form" action="/TrainingSystem/AjaxAlterGroup" type="post"><!-- action路径写自己的servlet -->
            <div class="layui-form-item">
                <label class="layui-form-label">队号</label>
                <div class="layui-input-block">
                    <input type="text" value=<%=request.getParameter("gid") %> class="layui-input" lay-verify="required" id="gid" readonly
                        name="gid">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">队名</label>
                <div class="layui-input-block">
                    <input type="text" value="" class="layui-input" lay-verify="required" id="gname" 
                        name="gname">
                </div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">创建时间</label>
                <div class="layui-input-block">
                    <input id="uDate" type="text" value="" placeholder="" lay-verify="required" class="layui-input" 
                     name="gdate">
            	</div>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="alter-manage">修改</button>
            </div>
        </form>
    </div>
    <script type="text/javascript" src="../../layui/layui.js"></script>
    <script src="../js/public.js"></script>
    <script type="text/javascript" src="../layui/layui.js"></script>
    <script type="text/javascript" src="../js/user-info.js"></script>
    <script>
        layui.use(['form', 'laydate'], function () {
            var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery;
    /*             laydate = layui.laydate;
            laydate.render({
                elem: '#datetime'
                , type: 'datetime'
            }); */
            form.on("submit(alter-manage)", function (data) {

                window.parent.location.reload();
            });
        });
    </script>
</body>

</html>