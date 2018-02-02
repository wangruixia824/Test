<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/30 0030
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>明日竞赛排名</title>
    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
        function getData() {
            $.ajax({
                url:"rankpage.op",
                data:"page=1&size=10",
                type:"get",
                success:function (data) {

                    if(data.code==200){
                        $("#sp1").append("总页数："+data.data.totalPages);
                        for(i in data.data.content){
                            var obj = data.data.content[i];
                            $("#sp1").append("<br/>"+obj.score);
                        }
                    }else{
                        alert(data.msg);
                    }
                }
            });
        }
    </script>
</head>
<body>
<div>
    <h1>新增排名信息</h1>
    <form action="/rankadd.op" method="post">
        <input placeholder="请输入组号" name="groupNo"/></br>
        <input placeholder="请输入项目的名称" name="projectName"/></br>
        <input placeholder="请输入分数" name="score"/></br>
        <input type="submit" value="新增排序"/>
    </form>
</div>
<hr/>
<a href="/index.op">定时任务开启</a>
<div>
    <input type="button" onclick="getData()" value="刷新数据"/>
    <table>
        <thead>
            <tr>
                <th>序号</th>
                <th>分数</th>
                <th>组号</th>
                <th>项目名称</th>
                <th>操作</th>
            </tr>
        </thead>
    </table>
    <span id="sp1"></span>
</div>

</body>
</html>
