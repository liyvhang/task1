<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/13
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>


<body>
<div style="width:100%;text-align:center">
    <br><br>
    <br><br>
    <br><br>
    <br><br>
    <form  method="post" action="/a/student">
        学生头像 <select name="img" style="min-width:12%;">
        <option value="/out/img/girl1.png">女一</option>
        <option value="/out/img/girl2.png">女二</option>
        <option value="/out/img/man2.png">男一</option>
        <option value="/out/img/man1.png">男二</option>
        </select> <br><br>
        就业职位：<input name="position" value="${student.position}" type="text" style="min-width:12%;" placeholder="请输入你的职位"> <br><br>
        职业类型：<select name="professionName" style="min-width:12%;">
            <option value="Java">Java</option>
            <option value="css">css</option>
            <option value="js">js</option>
            <option value="Python">Python</option>
            <option value="IOS">IOS</option>
            <option value="ui">ui</option>
            <option value="Android">Android</option>
            <option value="op">op</option>
            <option value="pm">pm</option>
            <option value="qa">qa</option>
            <option value="动画师">动画师</option>
        </select> <br><br>
        学生名字：<input name="name" value="${student.name}" type="text" style="min-width:12%;" placeholder="请输入您的名字"> <br><br>
        学生简介：<input name="intro" value="${student.intro}" type="text" style="min-width:12%;" placeholder="请输入你想说的话"> <br><br>
        学生薪资：<input name="salary" value="${student.salary}" type="text" style="min-width:12%;" placeholder="请输入你的薪资"> <br><br>
        就业状态：<select name="employmentStatus" style="min-width:12%;">
        <option value="true">true</option>
        <option value="false">false</option>
    </select> <br><br>
        优秀程度： <select name="excellenceDegree" style="min-width:12%;">
        <option value="true">true</option>
        <option value="false">false</option>
    </select> <br><br>

        <input type="submit" value="添加学生信息" style="min-width:12%;"> <br><br>
        <input name="createAt" value="<%=System.currentTimeMillis()%>" type="hidden"> <br><br>
        <input name="updateAt" value="<%=System.currentTimeMillis()%>" type="hidden"> <br><br>
        <input name="updateBy" value="${student.name}" type="hidden"> <br><br>
    </form>
</div>
</body>

