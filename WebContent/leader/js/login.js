$(function(){
	layui.use('layer', function() {
		var layer = layui.layer;
		$(function() {
			var victor = new Victor("body", "container");
			victor([ "#18bbff", "#00486b" ]).set();
			$("#submit").click(
					function(e) {
						if ($('#username').val() != ""&& $('#password').val() != "") {
							e.preventDefault();
							$.ajax({
								type : 'post',
								url : 'servlet/dologin',
								dataType : 'text',
								data : 'username=' + encodeURIComponent($('#username').val())
										+ '&password=' + $('#password').val(),
								success : function(data) {
									if (data == "false") {
										layer.open({
											title : '登陆失败',
											content : '用户名或者密码错误，请重试！',
											icon : 2,
											btn : '确定'
										});
									} else {
										window.location.href = "pages/index.jsp";//验证成功跳转界面
									}
								},
								error : function(err) {
									layer.open({
										title : '登陆失败',
										content : '系统内部错误，请检查服务器设置',
										icon : 2,
										btn : '确定'
									});
								}
							});
						}
					});
		});
	});

});
