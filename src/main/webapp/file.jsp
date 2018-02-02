<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/2 0002
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<h1>文件上传，基于FDFS</h1>
<DIV>
    <FORM ACTION="/fdfsupload.op" METHOD="post" enctype="multipart/form-data">
       <label>请选择上传的文件：</label>
        <input type="file" name="file"/>
        <input type="submit" name="文件上传"/>
    </FORM>
</DIV>

<h2>上传结果</h2>
<a href="http://39.107.110.188/${path}">点击查看</a>
</body>
</html>
