layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $form = $('form');

    //添加验证规则verify
    form.verify({
        pass: [
            /^[\S]{6,16}$/
            , '密码必须6到16位，且不能出现空格'
        ],
        confirmPwd: function (value, item) {
            if (!new RegExp($("#newPwd").val()).test(value)) {
                return "两次输入密码不一致，请重新输入！";
            }
        },

    });

    //修改密码
    form.on("submit(changePwd)", function (data) {
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        var newPwd=$("#newPwd").val();
        var oldPwd=$("#oldPwd").val();
        $.ajax({
            type:"post",
            url:"/TrainingSystem/ChangePass",
            data:"oldPwd="+oldPwd+"&newPwd="+newPwd,
            dataType:"text",
            success:function(data){
                if(data=="true"){
                    layer.close(index);
                    layer.msg("修改成功！");
                }
                else {
                    layer.close(index);
                    layer.msg("修改失败！");
                }
            },
            error:function(error){
                layer.close(index);
                layer.msg("修改失败！");
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

});

