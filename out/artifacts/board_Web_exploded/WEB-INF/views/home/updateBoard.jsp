<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bma01
  Date: 2022-09-06
  Time: 오후 2:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<link rel="stylesheet" href="/static/index.css">
<html>
<head>
    <title>update</title>
</head>
<body>
<%--<form action="applyUpdate/${boardNo}" method="post">--%>
<%--    <input type="hidden" value="PUT" name="_method">--%>
<form action="${pageContext.request.contextPath}/applyUpdate?boardNo=${boardNo}" method="post">
    <div class="boardFooter">
        <h3>boardNumber ${boardNo}</h3>
        <c:forEach items="${boardList}" var="boardList">
            <label>
                <input name="title" type="text" value=${boardList.title} />
                <textarea name="content" cols="30" rows="10">${boardList.content}</textarea>
            </label>
        </c:forEach>
    </div>
    <div class="boardButton">
<%--        <a href="readBoard?boardNo=${boardNo}"><input type="submit" value="저장"></a>--%>
        <input type="submit" value="저장">
        <a href="${pageContext.request.contextPath}/home"><input type="button" value="취소"></a>
    </div>
</form>
<%--</form>--%>
</body>
</html>
