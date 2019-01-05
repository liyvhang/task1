<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${students}" var="student">
    <div>
        <div class="show-blk">
            <img src="${student.img}" alt="" class="">
            <h4 class="mg-tb15 align-center">${student.position} ： ${student.name}</h4>
            <p>${student.intro}</p>
        </div>
    </div>
</c:forEach>
</body>
</html>
