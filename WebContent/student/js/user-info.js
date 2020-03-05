layui.use(['form', 'layer', 'upload', 'laydate', 'okAddlink'], function () {
    var form = layui.form;
    var upload = layui.upload;
    var $ = layui.jquery;
    var laydate = layui.laydate;
    var $form = $('form');
    laydate.render({
        elem: '#uDate', //指定元素
        max: maxDate(),
        value: nowDate()
    });
    laydate.render({
        elem: '#uDate2', //指定元素
        max: maxDate(),
        value: nowDate()
    });
    function nowDate() {
        var now = new Date();
        return now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate();
    };
    function maxDate() {
        var now = new Date();
        return (now.getFullYear() + 5) + "-" + (now.getMonth() + 1) + "-" + now.getDate();
    }
});























