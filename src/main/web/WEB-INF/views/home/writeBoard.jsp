<%--
  Created by IntelliJ IDEA.
  User: bma01
  Date: 2022-09-05
  Time: 오전 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" session="false" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<c:set var="path" value="${pageContext.request.requestURI}"/>--%>
<html>
<link rel="stylesheet" href="/static/index.css">
<head>
    <title>Write</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/home/applyInsert" method="post" enctype="multipart/form-data">
    <label class="boardFooter">
        <input class="title" type="text" name="title" placeholder="title">
        <input type="text" name="boardName" value="${user}" readonly>
        <textarea class="content" name="content" cols="50" rows="20" placeholder="input text"></textarea>
        <input type="file" name="uploadFile" multiple />
    </label>
    <input type="submit" value="등록">
<a href="${pageContext.request.contextPath}/home"><input type="button" value="취소"></a>
</form>
</body>
</html>
