layui.use(["element", "layer", "okUtils", "okTab", "jQContextMenu"], function () {
  var okUtils = layui.okUtils;
  var $ = layui.jquery;
  var layer = layui.layer;

  var okTab = layui.okTab({
    // 允许同时选项卡的个数
    openTabNum: 30
  });

  /**
   * 添加新窗口
   */
  $("body").on("click", "#navBar .layui-nav-item a, #userInfo a, .notice", function () {
    // 如果不存在子级
    if ($(this).siblings().length == 0) {
      okTab.tabAdd($(this));
    }
    // 关闭其他展开的二级标签
    $(this).parent("li").siblings().removeClass("layui-nav-itemed");
    if (!$(this).attr("lay-id")) {
      var topLevelEle = $(this).parents("li.layui-nav-item");
      var childs = $("#navBar > li > dl.layui-nav-child").not(topLevelEle.children("dl.layui-nav-child"));
      childs.removeAttr("style");
    }
  });

  /**
   * 左侧菜单展开动画
   */
  $("#navBar").on("click", ".layui-nav-item a", function () {
    if (!$(this).attr("lay-id")) {
      var superEle = $(this).parent();
      var ele = $(this).next('.layui-nav-child');
      var height = ele.height();
      ele.css({"display": "block"});
      // 是否是展开状态
      if (superEle.is(".layui-nav-itemed")) {
        ele.height(0);
        ele.animate({height: height + "px"}, function () {
          ele.css({height: "auto"});
        });
      } else {
        ele.animate({height: 0}, function () {
          ele.removeAttr("style");
        });
      }
    }
  });

  /**
   * 左边菜单显隐功能
   */
  $(".ok-menu").click(function () {
    $(".layui-layout-admin").toggleClass("ok-left-hide");
    $(this).find("i").toggleClass("ok-menu-hide");
    localStorage.setItem("isResize", false);
    setTimeout(function () {
      localStorage.setItem("isResize", true);
    }, 1200);
  });

  /**
   * 刷新当前tab页
   */
  $("body").on("click", ".ok-refresh", function () {
    okTab.refresh(this);
  });

  /**
   * 关闭tab页
   */
  $("body").on("click", "#tabAction a", function () {
    var num = $(this).attr("data-num");
    okTab.tabClose(num);
  });

  $("body").on("click", "#fullScreen", function () {
    if ($(this).children("i").hasClass("okicon-screen-restore")) {
      screenFun(2).then(function () {
        $(this).children("i").eq(0).removeClass("okicon-screen-restore");
      });
    } else {
      screenFun(1).then(function () {
        $(this).children("i").eq(0).addClass("okicon-screen-restore");
      });
    }
  });

  /**
   * 全屏和退出全屏的方法
   * @param num 1代表全屏 2代表退出全屏
   * @returns {Promise}
   */
  function screenFun(num) {
    num = num || 1;
    num = num * 1;
    var docElm = document.documentElement;

    switch (num) {
      case 1:
        if (docElm.requestFullscreen) {
          docElm.requestFullscreen();
        } else if (docElm.mozRequestFullScreen) {
          docElm.mozRequestFullScreen();
        } else if (docElm.webkitRequestFullScreen) {
          docElm.webkitRequestFullScreen();
        } else if (docElm.msRequestFullscreen) {
          docElm.msRequestFullscreen();
        }
        break;
      case 2:
        if (document.exitFullscreen) {
          document.exitFullscreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
        break;
    }

    return new Promise(function (res, rej) {
      res("返回值");
    });
  }
 /**
   * 退出操作
   */
  $("#logout").click(function () {
    layer.confirm("确定要退出吗？", function (index) {
    	$.ajax({
    	    type : 'post',
    	    url : '/TrainingSystem/Logout',
    	    dataType : 'text',
    	    success:function(data){
    	        if(data=="true")
    	            window.location.href="../login.html";
    	        else
    	            layer.msg("登出失败！");     
    	    },
    	    error:function(error){
    	        layer.msg("登出失败！");
    	    }
    	});
     // window.location = "../login.html";
    });
  });
});
