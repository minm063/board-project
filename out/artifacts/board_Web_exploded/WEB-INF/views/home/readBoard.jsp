<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bma01
  Date: 2022-09-05
  Time: 오후 6:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>read</title>
</head>
<body>
<c:forEach items="${boardRead}" var="boardRead">
    <h1>${boardRead.title}</h1>
    <p>${boardRead.content}</p>
    <c:set var="name" value="${boardRead.boardName}"/>
</c:forEach>
<a href="${pageContext.request.contextPath}/home/updateBoard?boardNo=${boardNo}" id="update">수정</a>
<a href="${pageContext.request.contextPath}/home/deleteBoard?boardNo=${boardNo}" id="delete">삭제</a>
<a href="${pageContext.request.contextPath}/home" class="btn">목록</a>
</body>
<script>
    // user : 현재 접속해있는 사용자
    // name : 글 작성자
    <%--console.log("${name}");--%>
    <%--console.log(typeof ${user});--%>
    <c:if test="${name != user}">
    document.getElementById('delete').style.display = "none";
    document.getElementById('update').style.display = "none";
    </c:if>
</script>
</html>
