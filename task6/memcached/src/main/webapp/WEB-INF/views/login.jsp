<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page isELIgnored="false"%>


<div  style="display: flex; justify-content:space-around;align-content: center;width: 100%">
    <div style=" margin-top: 142px;">
        <img src="/out/img/login.png">
    </div>

<form action="/login" style=" margin-top: 250px;margin-right: 20px;text-align: center">
    <table border="0">
<div>
        <tr>
            账号: <input name="name" value="${user.name}" type="text" style="min-width:12%;" placeholder="请输入用户名"> <br><br>
        </tr></div>
        <span style="color: #CC3300">
        <i>${message}</i> <br>
        <i>${error1}</i>  <br>
        </span>
        <div>
        <tr>
            密码: <input name="password" value="${user.password}" type="password" placeholder="请输入用户密码" style="min-width:12%;">
        </tr></div>
        <div>
    <input type="submit" value="登陆" style="min-width:12%; margin-top: 20px"></div>
    </table>
</form>
</div>



