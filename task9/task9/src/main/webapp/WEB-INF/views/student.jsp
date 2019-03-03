<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ page isELIgnored="false"%>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
    /*将post method 改变为delete*/
    $(function () {
        $(".delete").click(function () {
            var href = $(this).attr("href");
            $("#formdelete").attr("action", href).submit();
            return false;
        })
    })
</script>
<div>
    <br><br>
    <br><br>
    <form action="/student/name/">
        <input type="text" name="name" placeholder="请输入姓名">
        <input type="submit" value="根据姓名查询">
    </form>
</div>

<a href="/a/student">添加用户</a>
<table border="1" style=" color:#CC3300 ; table-layout : auto">
    <tbody>
    <tr>
        <td>ID</td>
        <td>头像</td>
        <td>就业职位</td>
        <td>职业类型</td>
        <td>学生名字</td>
        <td>学生简介</td>
        <td>薪资</td>
        <td>创建时间</td>
        <td>更新时间</td>
        <td>编辑人</td>
        <td>编辑</td>
        <td>删除</td>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td><p style="font-family:KaiTi;font-weight:bold;">${student.id}</p></td>
            <td><img src=${student.img} height="50" width="50"></td>
            <td><p style="font-family:KaiTi;font-weight:bold;">${student.position}</p></td>
            <td><p style="font-family:KaiTi;font-weight:bold;">${student.professionName}</p></td>
            <td><p style="font-family:KaiTi;font-weight:bold;">${student.name}</p></td>
            <td><p style="font-family:KaiTi;font-weight:bold;">${student.intro}</p></td>
            <td>${student.salary}</td>
                <%--<td>${student.employmentStatus}</td>--%>
                <%--<td>${student.excellenceDegree}</td>--%>
            <td>
                <jsp:useBean id="dateValue1" class="java.util.Date"/>
                    <%--使用jsp:setProperty标签将时间戳设置到Date的time属性中--%>
                <jsp:setProperty name="dateValue1" property="time" value="${student.createAt}"/>
                    <%--使用fmt标签转换long格式为设置好的date格式--%>
                <fmt:formatDate value="${dateValue1}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>
                <jsp:useBean id="dateValue" class="java.util.Date"/>
                    <%--使用jsp:setProperty标签将时间戳设置到Date的time属性中--%>
                <jsp:setProperty name="dateValue" property="time" value="${student.updateAt}"/>
                    <%--使用fmt标签转换long格式为设置好的date格式--%>
                <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm"/>
            </td>
            <td>${student.updateBy}</td>
            <td><a href="/student/${student.id}">编辑</a></td>
            <td><a class="delete" href="/student/${student.id}">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div style="text-align:center">
    <a href="?start=0">首 页</a>
    <c:if test="${page.start-page.count>=0}">
        <a href="?start=${page.start-page.count}">上一页</a>
    </c:if>
    <c:if test="${page.start-page.count<0}">
        <a href="javascript:void(0)">上一页</a>
    </c:if>

    <c:if test="${page.start+page.count<=page.last}">
        <a href="?start=${page.start+page.count}">下一页</a>
    </c:if>
    <c:if test="${page.start+page.count>page.last}">
        <a href="javascript:void(0)">下一页</a>
    </c:if>
    <a href="?start=${page.last}">末页</a>
</div>
<form id="formdelete" action="" method="POST">
    <input type="hidden" name="_method" value="DELETE">
</form>