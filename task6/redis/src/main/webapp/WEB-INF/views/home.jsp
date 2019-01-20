<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="placeholder"></div>
<div class="slider">
    <input type="radio" name="slider_radio" id="rd1" class="slider_radio " checked="checked">
    <input type="radio" name="slider_radio" id="rd2" class="slider_radio ">
    <input type="radio" name="slider_radio" id="rd3" class="slider_radio">
    <input type="radio" name="slider_radio" id="rd4" class="slider_radio ">
    <label for="rd1" class="lb1"></label>
    <label for="rd2" class="lb2"></label>
    <label for="rd3" class="lb3"></label>
    <label for="rd4" class="lb4"></label>
    <div class="page pg1">
        <img src="/out/img/lun.png" alt="">
    </div>
    <div class="page pg2">
        <img src="/out/img/lun1.png" alt="">
    </div>
    <div class="page pg3">
        <img src="/out/img/lun2.png" alt="">
    </div>
    <div class="page pg4">
        <img src="/out/img/lun3.png" alt="">
    </div>
</div>

<main>
    <div class="advantage">
        <div class="advantage-blk">
            <img src="/out/img/rocket.png" alt="">
            <h4 class="">高效</h4>
            <p>将五到七年的成长时间，缩短到一年到三年</p>
        </div>
        <div class="advantage-blk">
            <img src="/out/img/book.png" alt="">
            <h4 class="">规范</h4>
            <p>标准的实战教程，不会走弯路</p>
        </div>
        <div class="advantage-blk">
            <img src="/out/img/shape.png" alt="">
            <h4 class="">人脉</h4>
            <p>同班好友，同院学长，技术大师，入学就混入职脉圈，为以后铺平道路</p>
        </div>
        <div class="advantage-blk">
            <div class="adv-blk_nub">${total}</div>
            <span>修真院学员总人数</span>
            <div class="adv-blk_nub">${learning}</div>
            <span>学员已找到满意工作</span>
            <div class="adv-blk_nub">${graduate}</div>
            <span>累计在线学习人数</span>

        </div>
    </div>

    <h4 class="mg-tb30 align-center">如何学习</h4>
    <div class="how">
        <div class="how-line1">
            <div class="how-blk">
                <div class="circular">1</div>
                <p>匹配你现在的个人情况寻找合适的岗位</p>
            </div>
            <div class="how-blk">
                <div class="circular">2</div>
                <p>了解职业前景薪资待遇、竞争压力等</p>
            </div>
            <div class="how-blk">
                <div class="circular">3</div>
                <p>掌握行业内顶级技能</p>
            </div>
            <div class="how-blk">
                <div class="circular">4</div>
                <p>查看职业目标任务</p>
            </div>
        </div>
        <div class="how-line2">
            <div class="how-blk">
                <div class="circular">5</div>
                <p>参考学习资源，掌握技能点，逐个完成任务</p>
            </div>
            <div class="how-blk">
                <div class="circular">6</div>
                <p>假如班级，和小伙伴们互帮互助，一块学习</p>
            </div>
            <div class="how-blk">
                <div class="circular">7</div>
                <p>选择导师一路引导，加速自己成长</p>
            </div>
            <div class="how-blk">
                <div class="circular">8</div>
                <p>完成职业技能，升级业界大牛</p>
            </div>
        </div>
    </div>
    <h4 class="mg-tb30 align-center">优秀学员展示</h4>
    <div class="show">
        <c:forEach items="${students}" var="student">
        <div>
            <div class="show-blk">
                <img src="${student.img}" alt="" class="">
                <h4 class="mg-tb15 align-center">${student.position} ： ${student.name}</h4>
                <p>${student.intro}</p>
            </div>
        </div>
        </c:forEach>

        <div class="point">
            <input type="radio" name="oh" id="radio1" checked/>
            <input type="radio" name="oh" id="radio2">
            <input type="radio" name="oh" id="radio3">
            <input type="radio" name="oh" id="radio4">
            <label for="radio1"></label>
            <label for="radio2"></label>
            <label for="radio3"></label>
            <label for="radio4"></label>
        </div>
    </div>
    <h4 class="align-center mg-tb30">战略合作伙伴</h4>
    <div class="partner">
        <div class="bowl-img "><img src="/out/img/ali.jpg" alt=""></div>
        <div class="bowl-img "><img src="/out/img/jinsan.jpg" alt=""></div>
        <div class="bowl-img "><img src="/out/img/huanxin.jpg" alt=""></div>
        <div class="bowl-img "><img src="/out/img/ronglian.jpg" alt=""></div>
        <div class="bowl-img "><img src="/out/img/7niu.jpg" alt=""></div>
    </div>
</main>
<div class="link">
    <h4 class="mg-tb15 align-center">友情链接</h4>
    <div class="bowl-link">
        <ul class="">
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
        </ul >
        <ul class="">
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
            <li><a href="">手机软件</a></li>
        </ul >
    </div>
</div>


