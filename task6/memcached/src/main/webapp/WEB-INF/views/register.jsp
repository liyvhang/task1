
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<div  style="display: flex; justify-content:space-around;align-content: center;">
<div style=" margin-top: 142px;">
    <img src="/out/img/login.png">
</div>
<div class="widget-body" style="width:500px;margin-left: 33%;margin-top: 250px;">
    <div class="widget-main">
        <h4 class="header blue lighter bigger">
            <i class="ace-icon fa fa-coffee green"></i>
            请输入您的信息
        </h4>
        <div class="space-6"></div>
        <span style="color: #CC3300">
            <i>${items}</i>
            <i>${error}</i>
        </span>
        <form action="/register" method="post">
            <fieldset>
                <span class="block input-icon input-icon-right">
                    用户名:
                    <input type="text" name="name" value="${user.name}" class="form-control" placeholder="用户名"/> <br><br>
                </span>
                <span class="block input-icon input-icon-right">
                密&nbsp;&nbsp;&nbsp;码:
                    <input type="password" name="password" value="${user.password}" class="form-control" placeholder="密码"/> <br><br>
                                </span>
                <%--<span class="block input-icon input-icon-right">--%>
                <%--q&nbsp;&nbsp;&nbsp;q&nbsp;&nbsp;&nbsp;:--%>
                    <%--<input type="text" name="qq" class="form-control" placeholder="qq"/> <br><br>--%>
                <%--</span>--%>

                <%--<span class="block input-icon input-icon-right">--%>
                <%--邮&nbsp;&nbsp;&nbsp;箱:--%>
                    <%--<input type="text" name="email" class="form-control" placeholder="邮箱"/> <br><br>--%>
                <%--</span>--%>

                <%--<span class="block input-icon input-icon-right">--%>
                <%--手机号:--%>
                    <%--<input type="text" name="phone" class="form-control" placeholder="手机号"/> <br><br>--%>
                <%--</span>--%>

                <div class="space"></div>

                <input type="submit" value="提&nbsp;交" style="width:100px;">
                <input name="createAt" value="<%=System.currentTimeMillis()%>" type="hidden"> <br><br>
                <input name="updateAt" value="<%=System.currentTimeMillis()%>" type="hidden"> <br><br>
                <div class="space-4"></div>
            </fieldset>
        </form>
    </div>
</div>
</div>

