<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>


<script>
    function validate() {
        var pwd1 = document.getElementById("pwd1").value;
        var pwd2 = document.getElementById("pwd2").value;
        <!-- 对比两次输入的密码 -->
        if (pwd1 == pwd2) {
            document.getElementById("tishi").innerHTML = "<font color='green'>两次密码相同</font>";
            document.getElementById("submit1").disabled = false;
        } else {
            document.getElementById("tishi").innerHTML = "<font color='red'>两次密码不相同</font>";
            document.getElementById("submit1").disabled = true;
        }
    }


    // 获取验证码
    function getCode() {
        var phone = document.getElementById('phone').value;
        var get_code_url = "/phone/msgCode";
        $.ajax({
            type: "get",
            /*返回类型*/
            contentType: "application/json;charset=utf-8",
            url: get_code_url,
            data: {"phone": phone},
            /*返回类型*/
//            dataType: "json",
            complete: function () {
            },
            success: function (result) {
                alert(result.message);
                if (result.status == 2) {
                    $("#getsmsBack").attr("value", "点击发送");
                    $("#getsmsBack").removeAttr("disabled");
                }
//console.debug(result);
                if (result.status == 1) {

                    time($("#getsms"));
                }

            },
            error: function (result) {
                alert(result);
            }
        });
    }

    //验证码倒计时
    var wait = 60;

    function time(obj) {
        if (wait == 0) {
            $("#send1").removeAttr("disabled");
            $("#send1").val("获取验证码");
            wait = 60;
        } else {
            $("#send1").attr("disabled", "true");
            $("#send1").val(wait + "秒后重试");
            wait--;
            setTimeout(function () {     //倒计时方法
                time(obj);
            }, 1000);    //间隔为1s
        }
    }
</script>


<h1>用户注册</h1>
<div style="display: flex; justify-content:space-around;align-content: center;">
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

             
            <form method="post" action="/phone">
                <span class="block input-icon input-icon-right">
                     请输入帐号：
                    <input class="easyui-validatebox" required="true" missingMessage="姓名必须填写" size="20"
                           value="${user.name}"
                           placeholder="用户名" type="text" name="name"/> <br><br>
                </span>

                <span class="block input-icon input-icon-right">
                请输入手机：
                <input class="easyui-validatebox" required="true" missingMessage="电话必须填写" size="20"
                value="${user.phone}"
                       placeholder="手机号" type="text" name="phone" id="phone"/> <br><br>
                </span>

                <span class="block input-icon input-icon-right">
                    输入验证码：
                    <input class="easyui-validatebox" required="true" missingMessage="验证码必须填写" size="20" type="text"

                           name="msgCode" id="code" placeholder="验证码"/>
                </span>


                <input type="button" class="add-on btn btn-default" onclick="getCode()" id="send1" value="点击发送">
                <br><br>
                <%--<input type="button" class="add-on btn btn-access"   id="getsmsBack" class="getsmsBack" disabled="disabled" >--%>

                <span class="block input-icon input-icon-right">
                    请输入密码：
                    <input class="easyui-validatebox" required="true" missingMessage="密码必须填写" size="20" type="password"
                    value="${user.password}"
                           name="password" id="pwd1" placeholder="密码"/> <br><br>
                </span>
                <span class="block input-icon input-icon-right">
                    请确认密码：
                    <input class="easyui-validatebox" required="true" missingMessage="密码必须填写" size="20"
                    <%--value="${user.password}"--%>
                           type="password" name="password1" id="pwd2" onkeyup="validate()"/> <span id="tishi"
                                                                                                   placeholder="确认密码"/> <br><br>
                </span>
                <input type="submit" id="submit1" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset"
                                                                                                        value="重新输入">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/email" style="color:#8A54A2 ;">用邮箱注册也可以呦</a>
                <input name="createAt" value="<%=System.currentTimeMillis()%>" type="hidden">
                <input name="updateAt" value="<%=System.currentTimeMillis()%>" type="hidden"> <br><br>
                   </ul> 
            </form>
        </div>
    </div>
</div>

