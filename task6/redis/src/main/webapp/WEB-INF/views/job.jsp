<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="title" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/tags" prefix="date"%>

<div class="placeholder"></div>

<main>
    <div class="main-nav mg-lr-10 ">
        <a href="">首页></a>
        <a href="">职业</a>
    </div>
    <div class="main-nav--job mg-lr-10">
        <span>方向：</span>
        <a href="">全部</a>
        <a href="#name1">前端开发</a>
        <a href="#name2">后端开发</a>
        <a href="#name3">移动开发</a>
        <a href="#name4">整站开发</a>
        <a href="#name4">运营维护</a>
    </div>
    <h4 class="mg-lr-10 pd-tb30 b-bt">
        <a href="" name="name1"></a>
        前端开发方向
    </h4>
    <div class="bowl-blk">
        <c:forEach items="${jobs}" var="job">
        <c:if test="${job.developmentDirection == '前端开发方向'}">
        <div class="main-blk">
            <div class="blk-line1">
                <img src="${job.img}" alt="" class="">
                <div>
                    <span>${job.professionName}</span>
                    <p>${job.intro}</p>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="blk-line2">
                <span><span>门槛</span>
                        <span>
                            <c:forEach begin="1" end="${job.threshold}">
                                <img src="/out/img/star.png" alt="">
                            </c:forEach>
                        </span> </span>
                <span><span>难易程度</span><span>
                        <span>
                            <c:forEach begin="1" end="${job.complexity}">
                                <img src="/out/img/star.png" alt="">
                            </c:forEach>
                        </span> </span> </span>
            </div>
            <div class="blk-line3">
                <span>成长周期 <span><span>${job.growthCycle}</span><span>年</span></span></span>
                <span>稀缺成度 <span><span>${job.scarcity}</span><span>家公司需要</span></span></span>
            </div>
            <div class="blk-line4">
                <div>薪资待遇</div>
                <div>
                    <span>
                        <span>0-1年</span>
                        <span>${job.salaryOne}</span>
                    </span>
                    <span>
                        <span>0-1年</span>
                        <span>${job.salaryTwo}</span>
                    </span>
                    <span>
                        <span>0-1年</span>
                        <span>${job.salaryFive}</span>
                    </span>
                </div>
            </div>
            <div class="blk-line5">
                有<span>${job.learners}</span>人正在学
            </div>
            <div class="blk-line6">
                提示：${job.hint}
                创建时间:
                <date:date value ="${job.createAt} "/>
                <br>

                修改时间:
                <date:date value ="${job.updateAt} "/>
                <br>
            </div>
            <div class="main-blk-hover">
                <div> >
                    <p>${job.intro}</p>
                </div>
            </div>
        </div>
        </c:if>
        </c:forEach>
    </div>

    <h4 class="mg-lr-10 pd-tb30 b-bt">
        <a href="" name="name2"></a>
        后端开发方向
    </h4>
            <div class="bowl-blk">
                <c:forEach items="${jobs}" var="job">
                    <c:if test="${job.developmentDirection == '后端开发方向'}">
                <div class="main-blk">
                    <div class="blk-line1">
                        <img src="${job.img}" alt="" class="">
                        <div>
                            <span>${job.professionName}</span>
                            <p>${job.intro}</p>
                            <div class="clear"></div>
                        </div>
                    </div>
                <div class="blk-line2">
                <span><span>门槛</span>
                        <span>
                            <c:forEach begin="1" end="${job.threshold}">
                                <img src="/out/img/star.png" alt="">
                            </c:forEach>
                        </span> </span>
                    <span><span>难易程度</span><span>
                        <span>
                            <c:forEach begin="1" end="${job.complexity}">
                                <img src="/out/img/star.png" alt="">
                            </c:forEach>
                        </span> </span> </span>
                </div>
                <div class="blk-line3">
                    <span>成长周期 <span><span>${job.growthCycle}</span><span>年</span></span></span>
                    <span>稀缺成度 <span><span>${job.scarcity}</span><span>家公司需要</span></span></span>
                </div>
                <div class="blk-line4">
                    <div>薪资待遇</div>
                    <div>
                    <span>
                        <span>0-1年</span>
                        <span>${job.salaryOne}</span>
                    </span>
                        <span>
                        <span>0-1年</span>
                        <span>${job.salaryTwo}</span>
                    </span>
                        <span>
                        <span>0-1年</span>
                        <span>${job.salaryFive}</span>
                    </span>
                    </div>
                </div>
                <div class="blk-line5">
                    有<span>${job.learners}</span>人正在学
                </div>
                <div class="blk-line6">
                    提示：${job.hint}
                    创建时间:
                    <date:date value ="${job.createAt} "/>
                    <br>

                    修改时间:
                    <date:date value ="${job.updateAt} "/>
                    <br>
                </div>
                <div class="main-blk-hover">
                    <div>
                        <span class="text-center">${job.professionName}</span>
                        <p>${job.intro}</p>
                    </div>
                </div>
            </div>
                </c:if>
                </c:forEach>
        </div>




    <h4 class="mg-lr-10 pd-tb30 b-bt">
        <a href="" name="name3"></a>
        移动开发
    </h4>
        <div class="bowl-blk">
            <c:forEach items="${jobs}" var="job">
                <c:if test="${job.developmentDirection == '移动开发'}">
            <div class="main-blk">
                <div class="blk-line1">
                    <img src="${job.img}" alt="" class="">
                    <div>
                        <span>${job.professionName}</span>
                        <p>${job.intro}</p>
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="blk-line2">
                <span><span>门槛</span>
                        <span>
                            <c:forEach begin="1" end="${job.threshold}">
                                <img src="/out/img/star.png" alt="">
                            </c:forEach>
                        </span> </span>
                    <span><span>难易程度</span><span>
                        <span>
                            <c:forEach begin="1" end="${job.complexity}">
                                <img src="/out/img/star.png" alt="">
                            </c:forEach>
                        </span> </span> </span>
                </div>
                <div class="blk-line3">
                    <span>成长周期 <span><span>${job.growthCycle}</span><span>年</span></span></span>
                    <span>稀缺成度 <span><span>${job.scarcity}</span><span>家公司需要</span></span></span>
                </div>
                <div class="blk-line4">
                    <div>薪资待遇</div>
                    <div>
                    <span>
                        <span>0-1年</span>
                        <span>${job.salaryOne}</span>
                    </span>
                        <span>
                        <span>0-1年</span>
                        <span>${job.salaryTwo}</span>
                    </span>
                        <span>
                        <span>0-1年</span>
                        <span>${job.salaryFive}</span>
                    </span>
                    </div>
                </div>
                <div class="blk-line5">
                    有<span>${job.learners}</span>人正在学
                </div>
                <div class="blk-line6">
                    提示：${job.hint} <br>
                    创建时间:
                    <date:date value ="${job.createAt} "/>
                    <br>

                    修改时间:
                    <date:date value ="${job.updateAt} "/>
                    <br>
                </div>
                <div class="main-blk-hover">
                    <div>
                        <span class="text-center">${job.professionName}</span>
                        <p>${job.intro}</p>
                    </div>
                </div>
            </div>
            </c:if>
            </c:forEach>
        </div>


    <h4 class="mg-lr-10 pd-tb30 b-bt">
        <a href="" name="name4"></a>
        整站开发
    </h4>
        <div class="bowl-blk">
            <c:forEach items="${jobs}" var="job">
                <c:if test="${job.developmentDirection == '整站开发'}">
            <div class="main-blk">
                <div class="blk-line1">
                    <img src="${job.img}" alt="" class="">
                    <div>
                        <span>${job.professionName}</span>
                        <p>${job.intro}</p>
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="blk-line2">
                <span><span>门槛</span>
                        <span>
                            <c:forEach begin="1" end="${job.threshold}">
                                <img src="/out/img/star.png" alt="">
                            </c:forEach>
                        </span> </span>
                    <span><span>难易程度</span><span>
                        <span>
                            <c:forEach begin="1" end="${job.complexity}">
                                <img src="/out/img/star.png" alt="">
                            </c:forEach>
                        </span> </span> </span>
                </div>
                <div class="blk-line3">
                    <span>成长周期 <span><span>${job.growthCycle}</span><span>年</span></span></span>
                    <span>稀缺成度 <span><span>${job.scarcity}</span><span>家公司需要</span></span></span>
                </div>
                <div class="blk-line4">
                    <div>薪资待遇</div>
                    <div>
                    <span>
                        <span>0-1年</span>
                        <span>${job.salaryOne}</span>
                    </span>
                        <span>
                        <span>0-1年</span>
                        <span>${job.salaryTwo}</span>
                    </span>
                        <span>
                        <span>0-1年</span>
                        <span>${job.salaryFive}</span>
                    </span>
                    </div>
                </div>
                <div class="blk-line5">
                    有<span>${job.learners}</span>人正在学
                </div>
                <div class="blk-line6">
                    提示：${job.hint} <br>
                    创建时间:
                    <date:date value ="${job.createAt} "/>
                    <br>

                    修改时间:
                    <date:date value ="${job.updateAt} "/>
                    <br>
                </div>
                <div class="main-blk-hover">
                    <div>
                        <span class="text-center">${job.professionName}</span>
                        <p>${job.intro}</p>
                    </div>
                </div>
            </div>
            </c:if>
            </c:forEach>
        </div>


    <h4 class="mg-lr-10 pd-tb30 b-bt">
        <a href="" name="name5"></a>
        运营维护
    </h4>
    <div class="bowl-blk">
        <c:forEach items="${jobs}" var="job">
        <c:if test="${job.developmentDirection == '运营维护'}">
        <div class="main-blk">
                <div class="blk-line1">
                    <img src="${job.img}" alt="" class="">
                    <div>
                        <span>${job.professionName}</span>
                        <p>${job.intro}</p>
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="blk-line2">
                <span><span>门槛</span>
                        <span>
                            <c:forEach begin="1" end="${job.threshold}">
                                <img src="/out/img/star.png" alt="">
                            </c:forEach>
                        </span> </span>
                    <span><span>难易程度</span><span>
                        <span>
                            <c:forEach begin="1" end="${job.complexity}">
                                <img src="/out/img/star.png" alt="">
                            </c:forEach>
                        </span> </span> </span>
                </div>
                <div class="blk-line3">
                    <span>成长周期 <span><span>${job.growthCycle}</span><span>年</span></span></span>
                    <span>稀缺成度 <span><span>${job.scarcity}</span><span>家公司需要</span></span></span>
                </div>
                <div class="blk-line4">
                    <div>薪资待遇</div>
                    <div>
                    <span>
                        <span>0-1年</span>
                        <span>${job.salaryOne}</span>
                    </span>
                        <span>
                        <span>0-1年</span>
                        <span>${job.salaryTwo}</span>
                    </span>
                        <span>
                        <span>0-1年</span>
                        <span>${job.salaryFive}</span>
                    </span>
                    </div>
                </div>
                <div class="blk-line5">
                    有<span>${job.learners}</span>人正在学
                </div>
                <div class="blk-line6">
                    提示：${job.hint} <br>
                    创建时间:
                    <date:date value ="${job.createAt} "/>
                    <br>

                    修改时间:
                    <date:date value ="${job.updateAt} "/>
                    <br>
                </div>
                <div class="main-blk-hover">
                    <div>
                        <span class="text-center">${job.professionName}</span>
                        <p>${job.intro}</p>
                    </div>
                </div>
            </div>
        </c:if>
        </c:forEach>
        </div>
</main>
