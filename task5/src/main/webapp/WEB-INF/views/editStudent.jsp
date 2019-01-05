<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/13
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
<div style="width:500px;margin:0px auto;text-align:center">
    <div style="text-align: center;margin-top: 40px">
        <form method="post" action="/student">
            <input type="hidden" name="_method" value="PUT"> <br><br>
            学生头像：<input name="img" value="${student.img}" type="text" style="min-width:12%;"> <br><br>
            就业职位：<input name="position" value="${student.position}" type="text" style="min-width:12%;"> <br><br>
            职业类型：<input name="professionName" value="${student.professionName}" type="text" style="min-width:12%;"> <br><br>
            学生名字：<input name="name" value="${student.name}" type="text" style="min-width:12%;"> <br><br>
            学生简介：<input name="intro" value="${student.intro}" type="text" style="min-width:12%;"> <br><br>
            学生薪资：<input name="salary" value="${student.salary}" type="text" style="min-width:12%;"> <br><br>
            就业状态：<select name="employmentStatus" style="min-width:30%;">
            <option value="true">true</option>
            <option value="false">false</option>
        </select> <br><br>
            优秀程度： <select name="excellenceDegree" style="min-width:30%;">
            <option value="true">true</option>
            <option value="false">false</option>
        </select> <br><br>
            编辑人名：<input name="updateBy" value="${student.updateBy}" type="text" style="min-width:12%;"> <br><br>
            <input type="submit" value="编辑学生信息" style="min-width:10%;">
            <input type="hidden" value="${student.id}" name="id">
            <input type="hidden" value="${student.createAt}" name="createAt">
            <input type="hidden" value="<%=System.currentTimeMillis()%>" name="updateAt">
        </form>
    </div>
</div>
</body>
