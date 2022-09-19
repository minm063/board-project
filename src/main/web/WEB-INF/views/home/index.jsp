<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: bma01
  Date: 2022-09-01
  Time: 오후 5:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<link rel="stylesheet" href="/static/index.css">
<html>
<head>
    <title>home</title>
</head>
<body>

<h2>게시판</h2>
<c:if test="${not empty user}">
    <h3>접속중 : ${user}</h3>
</c:if>
<table>
    <thead>
    <tr>
        <th>no</th>
        <th>작성자</th>
        <th>title</th>
        <th>regdate</th>
        <th>hits</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${boardList}" var="boardList">
        <tr>
            <td>${boardList.ROWNUM}</td>
            <td>${boardList.boardName}</td>
            <td><a href="home/readBoard?boardNo=${boardList.boardNo}">${boardList.title}</a></td>
            <td>${boardList.regdate}</td>
            <td>${boardList.hits}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="home/writeBoard"><input type="button" value="글쓰기" class="btn"/></a>
<a href="/logout"><input type="button" value="로그아웃" id="logout"></a>
</body>
</html>