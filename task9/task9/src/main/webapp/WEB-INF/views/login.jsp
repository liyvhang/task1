<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>



<div  style="display: flex; justify-content:space-around;align-content: center;width: 100%">
    <div style=" margin-top: 142px;">
        <img src="/out/img/login.png">
    </div>
    <div>

    </div>
<form action="/login"  method="post" style=" margin-top: 250px;margin-right: 20px;text-align: center">
    <table border="0">
        <tr>
            账号: <input class="easyui-validatebox" required="true" missingMessage="姓名必须填写" size="20" type="text"  placeholder="请输入用户名" name="name"> <br><br>
        </tr>
        <tr>
            密码: <input class="easyui-validatebox" required="true" missingMessage="密码必须填写" size="20" type="password" placeholder="请输入用户密码" name="password"> <br><br>
        </tr>
    <input type="submit" value="登陆" style="min-width:12%;"> <br><br>
    </table>
</form>
</div>



