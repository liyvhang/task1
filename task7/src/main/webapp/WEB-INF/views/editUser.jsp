<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

<style>
    * {
        margin: 0;
        padding: 0;
    }

    #imglist {
        list-style: none;
        width: 500px;
        margin: 50px auto;
    }

    #imglist li {
        float: left;
        margin-top: 10px;
    }
</style>




<div id="outerdiv"
     style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
    <div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src=""/></div>
</div>


<a href="/home">返回首页</a> <br><br>


<form action="u/user/updateImg/${name}" id="form1">
    <div style="width:500px;margin:0px auto;text-align:center">
        <div style="text-align: center;margin-top: 40px">
            <!-- Table goes in the document BODY -->
            <table class="hovertable" border=1 id="tab1">
                <tr>
                    <th>个人信息展示</th>
                    <th>个人信息</th>
                </tr>
                <tr onmouseover="this.style.backgroundColor='#ffff66';"
                    onmouseout="this.style.backgroundColor='#d4e3e5';">
                    <td>头&nbsp;&nbsp;&nbsp;&nbsp;像：</td>
                    <td>
                        <ul id="imglist">
                            <li><img class="pimg" src=${img} width="100px"/></li>
                        </ul>
                    </td>
                </tr>
                <tr onmouseover="this.style.backgroundColor='#ffff66';"
                    onmouseout="this.style.backgroundColor='#d4e3e5';">
                    <td> 用户名：</td>
                    <td> ${name}</td>
                </tr>
            </table>

            <table class="hovertable" border=1 id="tab2" style="display:none">
                <tr>
                    <th>个人信息展示</th>
                    <th>编辑个人信息</th>
                </tr>
                <tr onmouseover="this.style.backgroundColor='#ffff66';"
                    onmouseout="this.style.backgroundColor='#d4e3e5';">
                    <td>头&nbsp;&nbsp;&nbsp;&nbsp;像：</td>
                    <td>
                        <input type="file" class="easyui-validatebox" required="true" missingMessage="图片必须填写" name="multipartFile" id="file" value="${user.img}" value="文件上传"
                               onchange="photoCheck(this)">
                        <div>
                            <%--显示图片的div--%>
                            <h3 style="color: #1cc286"><p style="font-family:KaiTi;font-weight:bold;">图片预览</p></h3>
                            <img src="" id="imgShow" width="100px"></img>
                        </div>
                    </td>
                </tr>
                <tr onmouseover="this.style.backgroundColor='#ffff66';"
                    onmouseout="this.style.backgroundColor='#d4e3e5';">
                    <td> 用户名：</td>
                    <td>
                        <input type="text" name="name" id="name" value="${user.name}" class="easyui-validatebox"
                               required="true"
                               missingMessage="名字必须填写" size="20">
                    </td>
                </tr>
                <tr onmouseover="this.style.backgroundColor='#ffff66';"
                    onmouseout="this.style.backgroundColor='#d4e3e5';">
                    <td></td>
                    <td><input type="button" class="button black side" value="提交" onclick="updateIMG(this)">
                        <input type="hidden" value="${user.id}" name="id">
                        <input type="hidden" value="${user.createAt}" name="createAt">
                        <input type="hidden" value="<%=System.currentTimeMillis()%>" name="updateAt">
                    </td>
                </tr>
            </table>
            <br> <br>
            <input type="button" onclick="f1();" class="button red side" value="点击切换"/>
        </div>
    </div>
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
    function imgShow(outerdiv, innerdiv, bigimg, _this) {
        var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
        $(bigimg).attr("src", src);//设置#bigimg元素的src属性

        /*获取当前点击图片的真实大小，并显示弹出层及大图*/
        $("<img/>").attr("src", src).load(function () {
            var windowW = $(window).width();//获取当前窗口宽度
            var windowH = $(window).height();//获取当前窗口高度
            var realWidth = this.width;//获取图片真实宽度
            var realHeight = this.height;//获取图片真实高度
            var imgWidth, imgHeight;
            var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

            if (realHeight > windowH * scale) {//判断图片高度
                imgHeight = windowH * scale;//如大于窗口高度，图片高度进行缩放
                imgWidth = imgHeight / realHeight * realWidth;//等比例缩放宽度
                if (imgWidth > windowW * scale) {//如宽度扔大于窗口宽度
                    imgWidth = windowW * scale;//再对宽度进行缩放
                }
            } else if (realWidth > windowW * scale) {//如图片高度合适，判断图片宽度
                imgWidth = windowW * scale;//如大于窗口宽度，图片宽度进行缩放
                imgHeight = imgWidth / realWidth * realHeight;//等比例缩放高度
            } else {//如果图片真实高度和宽度都符合要求，高宽不变
                imgWidth = realWidth;
                imgHeight = realHeight;
            }
            $(bigimg).css("width", imgWidth);//以最终的宽度对图片缩放

            var w = (windowW - imgWidth) / 2;//计算图片与窗口左边距
            var h = (windowH - imgHeight) / 2;//计算图片与窗口上边距
            $(innerdiv).css({"top": h, "left": w});//设置#innerdiv的top和left属性
            $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
        });

        $(outerdiv).click(function () {//再次点击淡出消失弹出层
            $(this).fadeOut("fast");
        });
    }
</script>

<style type="text/css">
    body {
        background: #f5faff;
    }

    .demo_con {
        width: 960px;
        margin: 40px auto 0;
    }

    .button {
        width: 140px;
        line-height: 38px;
        text-align: center;
        font-weight: bold;
        color: #fff;
        text-shadow: 1px 1px 1px #333;
        border-radius: 5px;
        margin: 0 20px 20px 0;
        position: relative;
        overflow: hidden;
    }

    .button:nth-child(6n) {
        margin-right: 0;
    }

    .button.gray {
        color: #8c96a0;
        text-shadow: 1px 1px 1px #fff;
        border: 1px solid #dce1e6;
        box-shadow: 0 1px 2px #fff inset, 0 -1px 0 #a8abae inset;
        background: -webkit-linear-gradient(top, #f2f3f7, #e4e8ec);
        background: -moz-linear-gradient(top, #f2f3f7, #e4e8ec);
        background: linear-gradient(top, #f2f3f7, #e4e8ec);
    }

    .button.black {
        border: 1px solid #333;
        box-shadow: 0 1px 2px #8b8b8b inset, 0 -1px 0 #3d3d3d inset, 0 -2px 3px #8b8b8b inset;
        background: -webkit-linear-gradient(top, #656565, #4c4c4c);
        background: -moz-linear-gradient(top, #656565, #4a4a4a);
        background: linear-gradient(top, #656565, #4a4a4a);
    }

    .button.red {
        border: 1px solid #b42323;
        box-shadow: 0 1px 2px #e99494 inset, 0 -1px 0 #954b4b inset, 0 -2px 3px #e99494 inset;
        background: -webkit-linear-gradient(top, #d53939, #b92929);
        background: -moz-linear-gradient(top, #d53939, #b92929);
        background: linear-gradient(top, #d53939, #b92929);
    }

    .button.yellow {
        border: 1px solid #d2a000;
        box-shadow: 0 1px 2px #fedd71 inset, 0 -1px 0 #a38b39 inset, 0 -2px 3px #fedd71 inset;
        background: -webkit-linear-gradient(top, #fece34, #d8a605);
        background: -moz-linear-gradient(top, #fece34, #d8a605);
        background: linear-gradient(top, #fece34, #d8a605);
    }

    .button.green {
        border: 1px solid #64c878;
        box-shadow: 0 1px 2px #b9ecc4 inset, 0 -1px 0 #6c9f76 inset, 0 -2px 3px #b9ecc4 inset;
        background: -webkit-linear-gradient(top, #90dfa2, #84d494);
        background: -moz-linear-gradient(top, #90dfa2, #84d494);
        background: linear-gradient(top, #90dfa2, #84d494);
    }

    .button.blue {
        border: 1px solid #1e7db9;
        box-shadow: 0 1px 2px #8fcaee inset, 0 -1px 0 #497897 inset, 0 -2px 3px #8fcaee inset;
        background: -webkit-linear-gradient(top, #42a4e0, #2e88c0);
        background: -moz-linear-gradient(top, #42a4e0, #2e88c0);
        background: linear-gradient(top, #42a4e0, #2e88c0);
    }

    .round,
    .side,
    .tags {
        padding-right: 30px;
    }

    .round:after {
        position: absolute;
        display: inline-block;
        content: "\003c";
        top: 50%;
        right: 10px;
        margin-top: -10px;
        width: 17px;
        height: 20px;
        padding-left: 3px;
        line-height: 18px;
        font-size: 10px;
        font-weight: normal;
        border-radius: 10px;
        text-shadow: -2px 0 1px #333;
        -webkit-transform: rotate(-90deg);
        -moz-transform: rotate(-90deg);
        transform: rotate(-90deg);
    }

    .gray.round:after {
        box-shadow: 1px 0 1px rgba(255, 255, 255, 1) inset, 1px 0 1px rgba(0, 0, 0, .2);
        background: -webkit-linear-gradient(top, #dce1e6, #dde2e7);
        background: -moz-linear-gradient(top, #dce1e6, #dde2e7);
        background: linear-gradient(top, #dce1e6, #dde2e7);
        text-shadow: -2px 0 1px #fff;
    }

    .black.round:after {
        box-shadow: 1px 0 1px rgba(255, 255, 255, .5) inset, 1px 0 1px rgba(0, 0, 0, .9);
        background: -webkit-linear-gradient(top, #333, #454545);
        background: -moz-linear-gradient(top, #333, #454545);
        background: linear-gradient(top, #333, #454545);
    }

    .red.round:after {
        box-shadow: 1px 0 1px rgba(255, 255, 255, .6) inset, 1px 0 1px rgba(130, 25, 25, .9);
        background: -webkit-linear-gradient(top, #b12222, #b42323);
        background: -moz-linear-gradient(top, #b12222, #b42323);
        background: linear-gradient(top, #b12222, #b42323);
    }

    .yellow.round:after {
        box-shadow: 1px 0 1px rgba(255, 255, 255, .8) inset, 1px 0 1px rgba(148, 131, 4, .9);
        background: -webkit-linear-gradient(top, #cf9d00, #d2a000);
        background: -moz-linear-gradient(top, #cf9d00, #d2a000);
        background: linear-gradient(top, #cf9d00, #d2a000);
    }

    .green.round:after {
        box-shadow: 1px 0 1px rgba(255, 255, 255, .8) inset, 1px 0 1px rgba(51, 126, 66, .9);
        background: -webkit-linear-gradient(top, #64c878, #6dcb80);
        background: -moz-linear-gradient(top, #64c878, #6dcb80);
        background: linear-gradient(top, #64c878, #6dcb80);
    }

    .blue.round:after {
        box-shadow: 1px 0 1px rgba(255, 255, 255, .8) inset, 1px 0 1px rgba(18, 85, 128, .9);
        background: -webkit-linear-gradient(top, #1e7db9, #2b85bd);
        background: -moz-linear-gradient(top, #1e7db9, #2b85bd);
        background: linear-gradient(top, #1e7db9, #2b85bd);
    }

    .side:after {
        position: absolute;
        display: inline-block;
        content: "\00bb";
        top: 3px;
        right: -4px;
        width: 38px;
        height: 30px;
        font-weight: normal;
        line-height: 26px;
        border-radius: 0 0 5px 5px;
        text-shadow: -2px 0 1px #333;
        -webkit-transform: rotate(-90deg);
        -moz-transform: rotate(-90deg);
        transform: rotate(-90deg);
    }

    .gray.side:after {
        text-shadow: -2px 0 1px #fff;
        border-top: 1px solid #d4d4d4;
        box-shadow: -2px 0 1px #eceef1 inset;
        background: -webkit-linear-gradient(right, #e1e6ea, #f2f2f6 60%);
        background: -moz-linear-gradient(right, #e1e6ea, #f2f2f6 60%);
        background: linear-gradient(right, #e1e6ea, #f2f2f6 60%);
    }

    .black.side:after {
        border-top: 1px solid #222;
        box-shadow: -2px 0 1px #606060 inset;
        background: -webkit-linear-gradient(right, #373737, #555 60%);
        background: -moz-linear-gradient(right, #373737, #555 60%);
        background: linear-gradient(right, #373737, #555 60%);
    }

    .red.side:after {
        border-top: 1px solid #aa1e1e;
        box-shadow: -2px 0 1px #c75959 inset;
        background: -webkit-linear-gradient(right, #b82929, #d73f3f 60%);
        background: -moz-linear-gradient(top, #b82929, #d73f3f 60%);
        background: linear-gradient(top, #b82929, #d73f3f 60%);
    }

    .yellow.side:after {
        border-top: 1px solid #ba8f06;
        box-shadow: -2px 0 1px #deb842 inset;
        background: -webkit-linear-gradient(right, #d5a406, #fdc40b 60%);
        background: -moz-linear-gradient(right, #d5a406, #fdc40b 60%);
        background: linear-gradient(right, #d5a406, #fdc40b 60%);
    }

    .green.side:after {
        border-top: 1px solid #53b567;
        box-shadow: -2px 0 1px #8ad599 inset;
        background: -webkit-linear-gradient(right, #69ca7c, #91e5a5 60%);
        background: -moz-linear-gradient(right, #69ca7c, #91e5a5 60%);
        background: linear-gradient(right, #69ca7c, #91e5a5 60%);
    }

    .blue.side:after {
        border-top: 1px solid #1971a8;
        box-shadow: -2px 0 1px #559dca inset;
        background: -webkit-linear-gradient(right, #2482bd, #3fa2e0 60%);
        background: -moz-linear-gradient(right, #2482bd, #3fa2e0 60%);
        background: linear-gradient(right, #2482bd, #3fa2e0 60%);
    }

    .tags:after {
        font-weight: normal;
        position: absolute;
        display: inline-block;
        content: "FREE";
        top: -3px;
        right: -33px;
        color: #fff;
        text-shadow: none;
        width: 85px;
        height: 25px;
        line-height: 28px;
        -webkit-transform: rotate(45deg) scale(.7, .7);
        -moz-transform: rotate(45deg) scale(.7, .7);
        transform: rotate(45deg) scale(.7, .7);
    }

    .gray.tags:after {
        background: #8c96a0;
        border: 2px solid #fff;
    }

    .black.tags:after {
        background: #333;
        border: 2px solid #777;
    }

    .red.tags:after {
        background: #b42323;
        border: 2px solid #df4141;
    }

    .yellow.tags:after {
        background: #d2a000;
        border: 2px solid #fcc100;
    }

    .green.tags:after {
        background: #64c878;
        border: 2px solid #9bebac;
    }

    .blue.tags:after {
        background: #1e7db9;
        border: 2px solid #54b1e9;
    }

    .button.rarrow,
    .button.larrow {
        overflow: visible;
    }

    .rarrow:after,
    .rarrow:before,
    .larrow:after,
    .larrow:before {
        position: absolute;
        content: "";
        display: block;
        width: 28px;
        height: 28px;
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        transform: rotate(45deg);
    }

    .rarrow:before {
        width: 27px;
        height: 27px;
        top: 6px;
        right: -13px;
        clip: rect(auto auto 26px 2px);
    }

    .rarrow:after {
        top: 6px;
        right: -12px;
        clip: rect(auto auto 26px 2px);
    }

    .gray.rarrow:before {
        background: #d6dbe0;
    }

    .gray.rarrow:after {
        box-shadow: 0 1px 0 #fff inset, -1px 0 0 #b7babd inset;
        background: -webkit-linear-gradient(top left, #f2f3f7, #e4e8ec);
        background: -moz-linear-gradient(top left, #f2f3f7, #e4e8ec);
        background: linear-gradient(top left, #f2f3f7, #e4e8ec);
    }

    .black.rarrow:before {
        background: #333;
    }

    .black.rarrow:after {
        box-shadow: 0 1px 0 #8B8B8B inset, -1px 0 0 #3d3d3d inset, -2px 0 0 #8B8B8B inset;
        background: -webkit-linear-gradient(top left, #656565, #4C4C4C);
        background: -moz-linear-gradient(top left, #656565, #4C4C4C);
        background: linear-gradient(top left, #656565, #4C4C4C);
    }

    .red.rarrow:before {
        background: #B42323;
    }

    .red.rarrow:after {
        box-shadow: 0 1px 0 #E99494 inset, -1px 0 0 #954B4B inset, -2px 0 0 #E99494 inset;
        background: -webkit-linear-gradient(top left, #D53939, #B92929);
        background: -moz-linear-gradient(top left, #D53939, #B92929);
        background: linear-gradient(top left, #D53939, #B92929);
    }

    .yellow.rarrow:before {
        background: #D2A000;
    }

    .yellow.rarrow:after {
        box-shadow: 0 1px 0 #FEDD71 inset, -1px 0 0 #A38B39 inset, -2px 0 0 #FEDD71 inset;
        background: -webkit-linear-gradient(top left, #FECE34, #D8A605);
        background: -moz-linear-gradient(top left, #FECE34, #D8A605);
        background: linear-gradient(top left, #FECE34, #D8A605);
    }

    .green.rarrow:before {
        background: #64C878;
    }

    .green.rarrow:after {
        box-shadow: 0 1px 0 #B9ECC4 inset, -1px 0 0 #6C9F76 inset, -2px 0 0 #B9ECC4 inset;
        background: -webkit-linear-gradient(top left, #90DFA2, #84D494);
        background: -moz-linear-gradient(top left, #90DFA2, #84D494);
        background: linear-gradient(top left, #90DFA2, #84D494);
    }

    .blue.rarrow:before {
        background: #1E7DB9;
    }

    .blue.rarrow:after {
        box-shadow: 0 1px 0 #8FCAEE inset, -1px 0 0 #497897 inset, -2px 0 0 #8FCAEE inset;
        background: -webkit-linear-gradient(top left, #42A4E0, #2E88C0);
        background: -moz-linear-gradient(top left, #42A4E0, #2E88C0);
        background: linear-gradient(top left, #42A4E0, #2E88C0);
    }

    .larrow:before {
        top: 6px;
        left: -13px;
        width: 27px;
        height: 27px;
        clip: rect(2px 26px auto auto);
    }

    .larrow:after {
        top: 6px;
        left: -12px;
        clip: rect(2px 26px auto auto);
    }

    .gray.larrow:before {
        background: #d6dbe0;
    }

    .gray.larrow:after {
        box-shadow: 0 -1px 0 #b7babd inset, 1px 0 0 #fff inset;
        background: -webkit-linear-gradient(top left, #f2f3f7, #e4e8ec);
        background: -moz-linear-gradient(top left, #f2f3f7, #e4e8ec);
        background: linear-gradient(top left, #f2f3f7, #e4e8ec);
    }

    .black.larrow:before {
        background: #333;
    }

    .black.larrow:after {
        box-shadow: 0 -1px 0 #3d3d3d inset, 0 -2px 0 #8B8B8B inset, 1px 0 0 #8B8B8B inset;
        background: -webkit-linear-gradient(top left, #656565, #4C4C4C);
        background: -moz-linear-gradient(top left, #656565, #4C4C4C);
        background: linear-gradient(top left, #656565, #4C4C4C);
    }

    .red.larrow:before {
        background: #B42323;
    }

    .red.larrow:after {
        box-shadow: 0 -1px 0 #954B4B inset, 0 -2px 0 #E99494 inset, 1px 0 0 #E99494 inset;
        background: -webkit-linear-gradient(top left, #D53939, #B92929);
        background: -moz-linear-gradient(top left, #D53939, #B92929);
        background: linear-gradient(top left, #D53939, #B92929);
    }

    .yellow.larrow:before {
        background: #D2A000;
    }

    .yellow.larrow:after {
        box-shadow: 0 -1px 0 #A38B39 inset, 0 -2px 0 #FEDD71 inset, 1px 0 0 #FEDD71 inset;
        background: -webkit-linear-gradient(top left, #FECE34, #D8A605);
        background: -moz-linear-gradient(top left, #FECE34, #D8A605);
        background: linear-gradient(top left, #FECE34, #D8A605);
    }

    .green.larrow:before {
        background: #64C878;
    }

    .green.larrow:after {
        box-shadow: 0 -1px 0 #6C9F76 inset, 0 -2px 0 #B9ECC4 inset, 1px 0 0 #B9ECC4 inset;
        background: -webkit-linear-gradient(top left, #90DFA2, #84D494);
        background: -moz-linear-gradient(top left, #90DFA2, #84D494);
        background: linear-gradient(top left, #90DFA2, #84D494);
    }

    .blue.larrow:before {
        background: #1E7DB9;
    }

    .blue.larrow:after {
        box-shadow: 0 -1px 0 #497897 inset, 0 -2px 0 #8FCAEE inset, 1px 0 0 #8FCAEE inset;
        background: -webkit-linear-gradient(top left, #42A4E0, #2E88C0);
        background: -moz-linear-gradient(top left, #42A4E0, #2E88C0);
        background: linear-gradient(top left, #42A4E0, #2E88C0);
    }

    .gray:hover {
        background: -webkit-linear-gradient(top, #fefefe, #ebeced);
        background: -moz-linear-gradient(top, #f2f3f7, #ebeced);
        background: linear-gradient(top, #f2f3f7, #ebeced);
    }

    .black:hover {
        background: -webkit-linear-gradient(top, #818181, #575757);
        background: -moz-linear-gradient(top, #818181, #575757);
        background: linear-gradient(top, #818181, #575757);
    }

    .red:hover {
        background: -webkit-linear-gradient(top, #eb6f6f, #c83c3c);
        background: -moz-linear-gradient(top, #eb6f6f, #c83c3c);
        background: linear-gradient(top, #eb6f6f, #c83c3c);
    }

    .yellow:hover {
        background: -webkit-linear-gradient(top, #ffd859, #e3bb38);
        background: -moz-linear-gradient(top, #ffd859, #e3bb38);
        background: linear-gradient(top, #ffd859, #e3bb38);
    }

    .green:hover {
        background: -webkit-linear-gradient(top, #aaebb9, #82d392);
        background: -moz-linear-gradient(top, #aaebb9, #82d392);
        background: linear-gradient(top, #aaebb9, #82d392);
    }

    .blue:hover {
        background: -webkit-linear-gradient(top, #70bfef, #4097ce);
        background: -moz-linear-gradient(top, #70bfef, #4097ce);
        background: linear-gradient(top, #70bfef, #4097ce);
    }

    .gray:active {
        top: 1px;
        box-shadow: 0 1px 3px #a8abae inset, 0 3px 0 #fff;
        background: -webkit-linear-gradient(top, #e4e8ec, #e4e8ec);
        background: -moz-linear-gradient(top, #e4e8ec, #e4e8ec);
        background: linear-gradient(top, #e4e8ec, #e4e8ec);
    }

    .black:active {
        top: 1px;
        box-shadow: 0 1px 3px #111 inset, 0 3px 0 #fff;
        background: -webkit-linear-gradient(top, #424242, #575757);
        background: -moz-linear-gradient(top, #424242, #575757);
        background: linear-gradient(top, #424242, #575757);
    }

    .red:active {
        top: 1px;
        box-shadow: 0 1px 3px #5b0505 inset, 0 3px 0 #fff;
        background: -webkit-linear-gradient(top, #b11a1a, #bf2626);
        background: -moz-linear-gradient(top, #b11a1a, #bf2626);
        background: linear-gradient(top, #b11a1a, #bf2626);
    }

    .yellow:active {
        top: 1px;
        box-shadow: 0 1px 3px #816b1f inset, 0 3px 0 #fff;
        background: -webkit-linear-gradient(top, #d3a203, #dba907);
        background: -moz-linear-gradient(top, #d3a203, #dba907);
        background: linear-gradient(top, #d3a203, #dba907);
    }

    .green:active {
        top: 1px;
        box-shadow: 0 1px 3px #4d7254 inset, 0 3px 0 #fff;
        background: -webkit-linear-gradient(top, #5eac6e, #72b37e);
        background: -moz-linear-gradient(top, #5eac6e, #72b37e);
        background: linear-gradient(top, #5eac6e, #72b37e);
    }

    .blue:active {
        top: 1px;
        box-shadow: 0 1px 3px #114566 inset, 0 3px 0 #fff;
        background: -webkit-linear-gradient(top, #1a71a8, #1976b1);
        background: -moz-linear-gradient(top, #1a71a8, #1976b1);
        background: linear-gradient(top, #1a71a8, #1976b1);
    }

    .gray.side:hover:after {
        background: -webkit-linear-gradient(right, #e7ebee, #f8f8f8 60%);
        background: -moz-linear-gradient(right, #e7ebee, #f8f8f8 60%);
        background: linear-gradient(right, #e7ebee, #f8f8f8 60%);
    }

    .black.side:hover:after {
        background: -webkit-linear-gradient(right, #555, #6f6f6f 60%);
        background: -moz-linear-gradient(right, #555, #6f6f6f 60%);
        background: linear-gradient(right, #555, #6f6f6f 60%);
    }

    .red.side:hover:after {
        background: -webkit-linear-gradient(right, #c43333, #dc4949 60%);
        background: -moz-linear-gradient(right, #c43333, #dc4949 60%);
        background: linear-gradient(right, #c43333, #dc4949 60%);
    }

    .yellow.side:hover:after {
        background: -webkit-linear-gradient(right, #e1b21a, #fbc71d 60%);
        background: -moz-linear-gradient(right, #e1b21a, #fbc71d 60%);
        background: linear-gradient(right, #e1b21a, #fbc71d 60%);
    }

    .green.side:hover:after {
        background: -webkit-linear-gradient(right, #85da95, #94e0a5 60%);
        background: -moz-linear-gradient(right, #85da95, #94e0a5 60%);
        background: linear-gradient(right, #85da95, #94e0a5 60%);
    }

    .blue.side:hover:after {
        background: -webkit-linear-gradient(right, #338fc8, #56b2eb 60%);
        background: -moz-linear-gradient(right, #338fc8, #56b2eb 60%);
        background: linear-gradient(right, #338fc8, #56b2eb 60%);
    }

    .gray.side:active:after {
        top: 4px;
        border-top: 1px solid #9fa6ab;
        box-shadow: -1px 0 1px #a8abae inset;
        background: -webkit-linear-gradient(right, #e4e8ec, #e4e8ec 60%);
        background: -moz-linear-gradient(right, #e4e8ec, #e4e8ec 60%);
        background: linear-gradient(right, #e4e8ec, #e4e8ec 60%);
    }

    .black.side:active:after {
        box-shadow: -1px 0 1px #111 inset;
        background: -webkit-linear-gradient(right, #414040, #4d4c4c 60%);
        background: -moz-linear-gradient(right, #414040, #4d4c4c 60%);
        background: linear-gradient(right, #414040, #4d4c4c 60%);
    }

    .red.side:active:after {
        box-shadow: -1px 0 1px #4b0707 inset;
        background: -webkit-linear-gradient(right, #b11a1a, #b11a1a 60%);
        background: -moz-linear-gradient(right, #b11a1a, #b11a1a 60%);
        background: linear-gradient(right, #b11a1a, #b11a1a 60%);
    }

    .yellow.side:active:after {
        box-shadow: -1px 0 1px #816b1f inset;
        background: -webkit-linear-gradient(right, #d3a203, #dba907 60%);
        background: -moz-linear-gradient(right, #d3a203, #dba907 60%);
        background: linear-gradient(right, #d3a203, #dba907 60%);
    }

    .green.side:active:after {
        box-shadow: -1px 0 1px #33663d inset;
        background: -webkit-linear-gradient(right, #63a870, #72b37e 60%);
        background: -moz-linear-gradient(right, #63a870, #72b37e 60%);
        background: linear-gradient(right, #63a870, #72b37e 60%);
    }

    .blue.side:active:after {
        box-shadow: -1px 0 1px #114566 inset;
        background: -webkit-linear-gradient(right, #1a71a8, #1976b1 60%);
        background: -moz-linear-gradient(right, #1a71a8, #1976b1 60%);
        background: linear-gradient(right, #1a71a8, #1976b1 60%);
    }

    .gray.rarrow:hover:after,
    .gray.rarrow:hover:after {
        background: -webkit-linear-gradient(top left, #fefefe, #ebeced);
        background: -moz-linear-gradient(top left, #fefefe, #ebeced);
        background: linear-gradient(top left, #fefefe, #ebeced);
    }

    .black.rarrow:hover:after,
    .black.larrow:hover:after {
        background: -webkit-linear-gradient(top left, #818181, #575757);
        background: -moz-linear-gradient(top left, #818181, #575757);
        background: linear-gradient(top left, #818181, #575757);
    }

    .red.rarrow:hover:after,
    .red.larrow:hover:after {
        background: -webkit-linear-gradient(top left, #eb6f6f, #c83c3c);
        background: -moz-linear-gradient(top left, #eb6f6f, #c83c3c);
        background: linear-gradient(top left, #eb6f6f, #c83c3c);
    }

    .yellow.rarrow:hover:after,
    .yellow.larrow:hover:after {
        background: -webkit-linear-gradient(top left, #ffd859, #e3bb38);
        background: -moz-linear-gradient(top left, #ffd859, #e3bb38);
        background: linear-gradient(top left, #ffd859, #e3bb38);
    }

    .green.rarrow:hover:after,
    .green.larrow:hover:after {
        background: -webkit-linear-gradient(top left, #aaebb9, #82d392);
        background: -moz-linear-gradient(top left, #aaebb9, #82d392);
        background: linear-gradient(top left, #aaebb9, #82d392);
    }

    .blue.rarrow:hover:after,
    .blue.larrow:hover:after {
        background: -webkit-linear-gradient(top left, #70bfef, #4097ce);
        background: -moz-linear-gradient(top left, #70bfef, #4097ce);
        background: linear-gradient(top left, #70bfef, #4097ce);
    }

    .gray.rarrow:active:after,
    .gray.larrow:active:after {
        background: -webkit-linear-gradient(top left, #e4e8ec, #e4e8ec);
        background: -moz-linear-gradient(top left, #e4e8ec, #e4e8ec);
        background: linear-gradient(top left, #e4e8ec, #e4e8ec);
    }

    .black.rarrow:active:after,
    .black.larrow:active:after {
        background: -webkit-linear-gradient(top left, #424242, #575757);
        background: -moz-linear-gradient(top left, #424242, #575757);
        background: linear-gradient(top left, #424242, #575757);
    }

    .red.rarrow:active:after,
    .red.larrow:active:after {
        background: -webkit-linear-gradient(top left, #b11a1a, #bf2626);
        background: -moz-linear-gradient(top left, #b11a1a, #bf2626);
        background: linear-gradient(top left, #b11a1a, #bf2626);
    }

    .yellow.rarrow:active:after,
    .yellow.larrow:active:after {
        background: -webkit-linear-gradient(top left, #d3a203, #dba907);
        background: -moz-linear-gradient(top left, #d3a203, #dba907);
        background: linear-gradient(top left, #d3a203, #dba907);
    }

    .green.rarrow:active:after,
    .green.larrow:active:after {
        background: -webkit-linear-gradient(top left, #63a870, #72b37e);
        background: -moz-linear-gradient(top left, #63a870, #72b37e);
        background: linear-gradient(top left, #63a870, #72b37e);
    }

    .blue.rarrow:active:after,
    .blue.larrow:active:after {
        background: -webkit-linear-gradient(top left, #1a71a8, #1976b1);
        background: -moz-linear-gradient(top left, #1a71a8, #1976b1);
        background: linear-gradient(top left, #1a71a8, #1976b1);
    }

    .gray.rarrow:active:after {
        box-shadow: 0 1px 0 #b7babd inset, -1px 0 0 #b7babd inset;
    }

    .gray.larrow:active:after {
        box-shadow: 0 -1px 0 #b7babd inset, 1px 0 0 #b7babd inset;
    }

    .black.rarrow:active:after {
        box-shadow: 0 1px 0 #333 inset, -1px 0 0 #333 inset;
    }

    .black.larrow:active:after {
        box-shadow: 0 -1px 0 #333 inset, 1px 0 0 #333 inset;
    }

    .red.rarrow:active:after {
        box-shadow: 0 1px 0 #640909 inset, -1px 0 0 #640909 inset;
    }

    .red.larrow:active:after {
        box-shadow: 0 -1px 0 #640909 inset, 1px 0 0 #640909 inset;
    }

    .yellow.rarrow:active:after {
        box-shadow: 0 1px 0 #816b1f inset, -1px 0 0 #816b1f inset;
    }

    .yellow.larrow:active:after {
        box-shadow: 0 -1px 0 #816b1f inset, 1px 0 0 #816b1f inset;
    }

    .green.rarrow:active:after {
        box-shadow: 0 1px 0 #4d7254 inset, -1px 0 0 #4d7254 inset;
    }

    .green.larrow:active:after {
        box-shadow: 0 -1px 0 #4d7254 inset, 1px 0 0 #4d7254 inset;
    }

    .blue.rarrow:active:after {
        box-shadow: 0 1px 0 #114566 inset, -1px 0 0 #114566 inset;
    }

    .blue.larrow:active:after {
        box-shadow: 0 -1px 0 #114566 inset, 1px 0 0 #114566 inset;
    }
</style>

<script type="text/javascript">
    /*
     *图片验证
     */
    function photoCheck(obj){
        var ff = $("#file").val();
        if(ff == null || ff == ""){
            alert("请选择文件");
            return;
        }else if(!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(ff)){
            alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
            return;
        }
        photo_flag = true;
        //设置限制图像的大小为10MB，这里你可以自己设置
        var fSize = 1024 * 1024 *10;
        //限制图片宽高
        var fHeight = 3000;
        var fWidth = 3000;

        var fileType;
        var fileSize;
        var filePath;
        var img = new Image();
        //显示图片
        if (obj.files) {
            //用来把文件读入内存，并且读取文件中的数据，要注意的是只有 Firefox 3.6+ 和 Chrome 6.0+ 实现了 FileReader 接口。
            var reader = new FileReader();
            //获取文件的对像
            var thisFile = obj.files[0];
            //获取上传文件的类型，一般来说，如果类型是image/jpeg,.jpg,.gif等等图片格式的话就是合理的
            fileType = thisFile.type;
            //获取当前上传的文件的大小
            fileSize=thisFile.size;
            /*readAsDataURL：该方法将文件读取为一段以 data: 开头的字符串，这段字符串的实质就是 Data URI，Data URI是一种将小
            文件直接嵌入文档的方案。这里的小文件通常是指图像与 html 等格式的文件*/
            reader.readAsDataURL(thisFile);
            //文件读取成功完成时触发
            reader.onloadend = function(event){
                // 图像的路径
                img.src = event.target.result;
                // 图片加载完毕后
                img.onload = function(event) {
                    //效验图片规格
                    specification(img);
                    filePath = this.src;
                    if(photo_flag){
                        //设置图片为当前上传的图片路径
                        $("#imgShow").attr("src", filePath);
                    }else{
                        //否则设置默认的图片
                        $("#imgShow").attr("src", "default.jpg");
                    }
                }
            }

        }else{// 如果是ie浏览器

            //选择当前全部文本内容
            obj.select();
            /*
                为选择的内容创建一个Range对象，在.text转换成文本
                什么是Range？https://my.oschina.net/122612475/blog/286302
                所谓"Range"，是指HTML文档中任意一段内容。一个Range的起始点和结束点位置任意，甚至起始点和结束点可
                以是一样的（也就是空Range）。最常见的Range是用户文本选择范围(user text selection)。当用户选择了
                页面上的某一段文字后，你就可以把这个选择转为Range。当然，你也可以直接用程序定义Range
             */
            var path = document.selection.createRange().text;
            img.src = path;
            //效验图片规格
            specification(img);
            //取出文件后缀
            var fileType = path.substring(path.length-4,path.length);
            if(img.readyState == "complete") {
                //图片加载完毕,获取图片的大小
                fileSize = img.fileSize;
            }else{
                //当的该对象的 load state 改变时，会触发此事件
                img.onreadystatechange = function(){
                    // 当图片load完毕
                    if(img.readyState=='complete'){
                        fileSize = img.fileSize;
                        if(fileSize > fSize){
                            photo_flag = false;
                            $("#imgShow").attr("src", "default.jpg");
                            return;
                        }
                    }
                }
            }
            if (path) {
                filePath = path;
            }
        }

        //图片格式的判断
        if(
            fileType != ".jpg" && fileType != ".JPG" && fileType != "image/jpeg" &&
            fileType != ".png" && fileType != ".PNG" && fileType != "image/png"  &&
            fileType != ".gif" && fileType != ".GIF" && fileType != "image/gif"
        ){
            alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
            photo_flag = false;
            $("#imgShow").attr("src", "default.jpg");
            return;
        }
        //效验图片内存大小
        if(fileSize > fSize){
            alert("图片太大了！");
            photo_flag = false;
            $("#imgShow").attr("src", "default.jpg");
            return;
        }
        if(photo_flag){
            $("#imgShow").attr("src", filePath);
        } else {
            $("#imgShow").attr("src", "default.jpg");
        }
        // 匿名函数:效验图片规格
        var specification = function(image){
            if(image.width >= fWidth || image.height >= fHeight){
                alert("*提示：文件大小不对。您只能上传小于等于"+fWidth+"*"+fHeight+"尺寸的图片");
                $("#imgShow").attr("src", "default.jpg");
                return;
            }
        }
    }

</script>

<script type="text/javascript">
    function f1() {
        var tab1 = document.getElementById("tab1");
        var tab2 = document.getElementById("tab2");
        tab1.style.display = (tab1.style.display == "none" ? "" : "none");
        tab2.style.display = (tab2.style.display == "none" ? "" : "none");
    }
</script>


<style type="text/css">
    table.hovertable {
        font-family: verdana, arial, sans-serif;
        font-size: 11px;
        color: #333333;
        border-width: 1px;
        border-color: #999999;
        border-collapse: collapse;
    }

    table.hovertable th {
        background-color: #c3dde0;
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }

    table.hovertable tr {
        background-color: #d4e3e5;
    }

    table.hovertable td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }
</style>

<script>
    $(function () {
        $(".pimg").click(function () {

            var _this = $(this);//将当前的pimg元素作为_this传入函数
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
        });
    });

    //图片上传以及更新
    function updateIMG(button) {
        var name = document.getElementById('name').value;
        var formData = new FormData($('#form1')[0]); // FormData 对象
        $.ajax({
            type: 'POST',
            url: "updateImg/" + name,
            data: formData,
            contentType: false,
            processData: false,
            /* mimeType:"multipart/form-data",*/
            success: function (data) {
                if (data != null) {
                    button.type = "submit";
                    $('#from1').submit();
                } else {
                    alert("不能为空")
                    button.type = "submit";
                    $('#from1').submit();
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                button.type = "submit";
                $('#from1').submit();
            }
        })
    }
</script>