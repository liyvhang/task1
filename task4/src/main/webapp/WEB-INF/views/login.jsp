<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>



<div  style="display: flex; justify-content:space-around;align-content: center;width: 100%">
    <div style=" margin-top: 142px;">
        <img src="/out/img/login.png">
    </div>
    <div>

    </div>
<form action="/login" style=" margin-top: 250px;margin-right: 20px;text-align: center">
    <table border="0">
        <tr>
            账号: <input name="name" value="${user.name}" type="text" style="min-width:12%;" placeholder="请输入用户名"> <br><br>
        </tr>
        <tr>
            密码: <input name="password" value="${user.password}" type="password" placeholder="请输入用户密码" style="min-width:12%;"> <br><br>
        </tr>
    <input type="submit" value="登陆" style="min-width:12%;"> <br><br>
    </table>
</form>
</div>



