<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta charset="utf-8">
    <title>体能训练信息系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="../../res/images/mysite.png" type="image/x-icon" />
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../css/okadmin.css">
</head>

<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin okadmin">
        <!--头部导航-->
        <div class="layui-header okadmin-header">
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item">
                    <a class="ok-menu ok-show-menu" href="javascript:" title="菜单切换">
                        <i class="layui-icon layui-icon-shrink-right"></i>
                    </a>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a class="ok-refresh" href="javascript:" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs">
                    <a id="fullScreen" class=" pr10 pl10" href="javascript:">
                        <i class="layui-icon layui-icon-screen-full"></i>
                    </a>
                </li>

                <li class="no-line layui-nav-item">
                    <a href="javascript:">
                        <% out.print((String)session.getAttribute("userName")); %>
                    </a>
                    <dl id="userInfo" class="layui-nav-child">
                        <dd><a lay-id="9" href="javascript:" data-url="user-info.jsp">基本资料</a></dd>
                        <dd>
                            <hr />
                        </dd>
                         <dd><a lay-id="10" href="javascript:" data-url="user-pwd.jsp">修改密码</a></dd>
                        <dd>
                            <hr />
                        </dd>
                   <dd><a href="javascript:void(0)" id="logout">退出登录</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
        <!--遮罩层-->
        <div class="ok-make"></div>
        <!--左侧导航区域-->
        <div class="layui-side layui-side-menu okadmin-bg-20222A ok-left">
            <div class="layui-side-scroll okadmin-side">
                <div class="okadmin-logo">体能训练信息系统</div>
                <!--左侧导航菜单-->
                <ul id="navBar" class="layui-nav okadmin-nav okadmin-bg-20222A layui-nav-tree">
                    <li class="layui-nav-item">
                        <a href="javascript:" lay-id="8">
                            <i is-close=true class="layui-icon layui-icon-form"></i>
                            训练管理
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;" lay-id="81" data-url="plan-list.html">上级计划</a></dd>
                            <dd><a href="javascript:;" lay-id="82" data-url="training-manage.html">组织实施</a></dd>
                        </dl>
                    </li>
<!--                     <li class="layui-nav-item">
                        <a href="javascript:" lay-id="2">
                            <i is-close=true class="layui-icon layui-icon-face-surprised"></i>
                            学员队体能
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;" lay-id="21" data-url="average-radar.html">平均分雷达图</a></dd>
                            <dd><a href="javascript:;" lay-id="22" data-url="pass-radar.html">及格率雷达图</a></dd>
                            <dd><a href="javascript:;" lay-id="23" data-url="averare-line-charts.html">平均分趋势图</a></dd>
                        </dl>
                    </li> -->
<!--                     <li class="layui-nav-item">
                        <a href="javascript:" lay-id="3" data-url="rank.html">
                            <i is-close=true class="layui-icon layui-icon-form"></i>
                            平均分排名
                        </a>
                    </li> -->
                    <li class="layui-nav-item">
                        <a href="javascript:" lay-id="4">
                            <i is-close=true class="layui-icon layui-icon-user"></i>
                            成绩管理
                        </a>
                        <dl class="layui-nav-child">
                            <dd class="layui-this"><a href="javascript:;" lay-id="41"
                                    data-url="score-manage.html">全队成绩</a></dd>
                            <dd><a href="javascript:;" lay-id="42" data-url="score-upload.html">成绩导入</a></dd>
                            <dd><a href="javascript:;" lay-id="43" data-url="score-download.html">成绩导出</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:" lay-id="5">
                            <i is-close=true class="layui-icon layui-icon-user"></i>
                            体型健康
                        </a>
                        <dl class="layui-nav-child">
                            <dd class="layui-this"><a href="javascript:;" lay-id="51"
                                    data-url="health-manage.html">全队数据</a></dd>
                            <dd><a href="javascript:;" lay-id="52" data-url="health-upload.html">数据导入</a></dd>
                            <dd><a href="javascript:;" lay-id="53" data-url="health-download.html">数据导出</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:" lay-id="6" data-url="standard.html">
                            <i is-close=true class="layui-icon layui-icon-vercode"></i>
                            体能考核标准
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <!-- 内容主体区域 -->
        <div class="content-body">
            <div class="layui-tab ok-tab" lay-filter="ok-tab" lay-allowClose="true" lay-unauto>
                <div class="layui-icon okadmin-tabs-control ok-right-nav-menu" style="right: 0;">
                    <ul class="okadmin-tab">
                        <li class="no-line okadmin-tab-item">
                            <div class="layui-icon layui-icon-down" href="javascript:;"></div>
                            <dl id="tabAction" class="okadmin-tab-child layui-anim-upbit layui-anim">
                                <dd><a data-num="1" href="javascript:">关闭当前标签页</a></dd>
                                <dd><a data-num="2" href="javascript:">关闭其他标签页</a></dd>
                                <dd><a data-num="3" href="javascript:">关闭所有标签页</a></dd>
                            </dl>
                        </li>
                    </ul>
                </div>

                <ul id="tabTitle" class="layui-tab-title ok-tab-title not-scroll">
                    <li  class="layui-this"><strong style="display: none;" is-close="false" ></strong>
                        <i is-close="true" class="layui-icon layui-icon-file-b"></i>
                        
                    </li>
                </ul>
                <div id="tabContent" class="layui-tab-content ok-tab-content">
                    <div class="layui-tab-item layui-show"><iframe src="training-manage.html"
                            frameborder="0" scrolling="yes" width="100%" height="100%"></iframe></div>
                </div>
            </div>
        </div>
    </div>
    <script src="../layui/layui.js"></script>
    <script src="../js/okadmin.js"></script>
</body>

</html>