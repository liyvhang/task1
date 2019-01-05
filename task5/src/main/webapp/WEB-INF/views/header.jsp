<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<%@ page isELIgnored="false"%>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/out/css/mycss.css">
</head>


<%--欢迎您：<%=request.getSession().getAttribute("name")%>--%>

<%--${sessionScope.name}--%>
<c:if test="${empty sessionScope.name}">
    <div class="wrap">
        <button onclick="window.location.href='/register'">注册</button>
        <button onclick="window.location.href='/login'">登陆</button>
    </div>
</c:if>

<c:if test="${!empty sessionScope.name}">
    <div class="wrap">
        欢迎您：${name}
        <button onclick="window.location.href='/loginOut'">注销</button>
    </div>
</c:if>

<%--<div class="wrap">--%>
    <%--<button onclick="window.location.href='/register'">注册</button>--%>
    <%--<button onclick="window.location.href='/login'">登陆</button>--%>
    <%--<button onclick="window.location.href='/loginOut'">注销</button>--%>
<%--</div>--%>
<div class="wrap-media ">
    <a href="/register">注册</a>
    丨
    <a href="/login">登陆</a>
    丨
    <a href="">注销</a>
</div>
<nav>
    <button onclick="window.location.href='/u/partner'">关于</button>
    <button onclick="window.location.href='/u/student'">学员</button>
    <button onclick="window.location.href='/job'">职业</button>
    <button onclick="window.location.href='/home'">首页</button>
</nav>
<input type="checkbox" id="bridge" style="display: none" >
<div class="nav-media">
    <label for="bridge">
        <div></div>
        <div></div>
        <div></div>
    </label>
</div>
