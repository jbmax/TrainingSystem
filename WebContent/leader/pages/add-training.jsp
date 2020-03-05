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
        <form class="layui-form" lay-filter="alter-form" action="/TrainingSystem/AjaxAddtrain" type="post">
             <div class="layui-form-item">
                <label class="layui-form-label">训练计划</label>
                <div class="layui-input-block">
                    <input type="text" value=<%=request.getParameter("pid") %> class="layui-input layui-disabled" lay-verify="required" readonly id="ID" name="ID">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">截止时间</label>
                <div class="layui-input-block">
                    <input id="uDate" type="text" value="" placeholder="" lay-verify="required" class="layui-input" 
                     name="edate">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">参加人</label>
                <div class="layui-input-block">
                    <input type="text" value="所属人员" class="layui-input layui-disabled" lay-verify="required" readonly id="attend"
                        name="attend">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">场地</label>
                <div class="layui-input-block">
                    <select name="ground" lay-verify="required" lay-filter="address">
                        <option value=""></option>
                        <% ArrayList<String> list = GroundManage.selectGroundAval(); 
                        for(int i=0;i<list.size();i++) 
                        { %> 
                               <option value=<%=i+1%>><%=list.get(i)%></option> 
                        <%}%>
<!--                         <option value="地址一">地址一</option>
                        <option value="地址二">地址二</option>
                        <option value="地址三">地址三</option> -->
                    </select>
                    
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">负责人</label>
                <div class="layui-input-block">
                    <input type="text" value="" class="layui-input" lay-verify="required" id="administrator"
                        name="oper">
                </div>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="alter-manage">添加</button>
            </div>
        </form>
    </div>
    <script type="text/javascript" src="../../layui/layui.js"></script>
    <script src="../js/public.js"></script>
    <script type="text/javascript" src="../layui/layui.js"></script>
    <script type="text/javascript" src="../js/user-info.js"></script>
    <script>
        layui.use(['form', 'layer', 'laydate', 'jquery'], function () {
            var form = layui.form,
                layer = layui.layer,
                $ = layui.jquery,
                address="";
            form.on('select(ground)', function (data) {
                address=data.value; //得到被选中的值
            });
            form.on("submit(alter-manage)", function (data) {

                window.parent.location.reload();
            });
        });
    </script>
</body>
</html>