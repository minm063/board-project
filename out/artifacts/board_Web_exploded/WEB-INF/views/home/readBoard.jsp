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
<link rel="stylesheet" href="/static/index.css">

<head>
    <title>read</title>
</head>
<body>
<c:forEach items="${boardRead}" var="boardRead">
    <label for="boardRead" class="boardFooter">
        <input type="text" value=${boardRead.title} readonly>
        <input type="text" value=${boardRead.boardName} readOnly>
        <textarea cols="30" rows="10" id="boardRead" readonly>${boardRead.content}</textarea>
        <c:if test="${not empty boardRead.fileName}">
<%--            <a href="http://localhost:8080/home/fileDownload" id="download">${boardRead.fileName}</a>--%>
            <a href="/home/fileDownload?fileName=${boardRead.fileName}" >${boardRead.fileName}</a>
        </c:if>
    </label>
    <c:set var="name" value="${boardRead.boardName}"/>
</c:forEach>
<a href="${pageContext.request.contextPath}/home/updateBoard?boardNo=${boardNo}" id="update">
    <button>수정</button>
</a>
<a href="${pageContext.request.contextPath}/home/deleteBoard?boardNo=${boardNo}" id="delete">
    <button>삭제</button>
</a>
<a href="${pageContext.request.contextPath}/home">
    <button>목록</button>
</a>
</body>
<script>
    // user : 현재 접속해있는 사용자
    // name : 글 작성자
    <c:if test="${name != user}">
    document.getElementById('delete').style.display = "none";
    document.getElementById('update').style.display = "none";
    </c:if>
</script>
</html>
