layui.use(['form', 'layer', 'upload', 'laydate', 'okAddlink'], function () {
    var form = layui.form;
    var upload = layui.upload;
    var $ = layui.jquery;
    var laydate = layui.laydate;
    var $form = $('form');
    laydate.render({
        elem: '#uDate', //指定元素
        max: "2019-1-1",
        value: '2017-09-10',
    });
});























