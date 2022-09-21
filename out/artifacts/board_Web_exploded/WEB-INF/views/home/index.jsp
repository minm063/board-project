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
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.7.1.min.js"></script>
    <title>home</title>
</head>
<body>

<h2>게시판</h2>
<c:if test="${not empty user}">
    <h3>접속중 : ${user}</h3>
</c:if>
<form action="" method="post" id="submitPage">
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
                <td>
                    <a href="home/readBoard?boardNo=${boardList.boardNo}&boardName=${boardList.boardName}">${boardList.title}</a>
                </td>
                <td>${boardList.regdate}</td>
                <td>${boardList.hits}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--    <input type="hidden" id="pageNo" >--%>
    <div id="page" style="text-align: center;">
        <c:if test="${page.pageNo != 0}">
            <!-- 글 목록이 지정해놓은 페이지 단 수보다 많다면 -->
            <c:if test="${page.pageNo > page.pageBlock}">
                <a href="javascript:movePage(${page.firstPageNo})" style="text-decoration: none;">[첫 페이지]</a>
            </c:if>
            <!-- 1페이지 이후 -->
            <c:if test="${page.pageNo != 1}">
                <a href="javascript:movePage(${page.prevPageNo})" style="text-decoration: none;">[이전]</a>
            </c:if>
            <span>
            <!-- 보고 있는 페이지를 bold 처리 -->
            <c:forEach var="i" begin="${page.startPageNo}" end="${page.endPageNo}" step="1">
                <c:choose>
                    <c:when test="${i eq page.pageNo}">
                        <a href="javascript:movePage(${i})" style="text-decoration: none;">
                            <span style="font-weight: bold;">${i}</span>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:movePage(${i})" style="text-decoration: none;">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </span>
            <c:if test="${page.pageNo != page.finalPageNo }">
                <a href="javascript:movePage(${page.nextPageNo})" style="text-decoration: none;">[다음]</a>
            </c:if>
            <c:if test="${page.endPageNo < page.finalPageNo }">
                <a href="javascript:movePage(${page.finalPageNo})" style="text-decoration: none;">[마지막 페이지]</a>
            </c:if>
        </c:if>
    </div>
<a href="home/writeBoard"><input type="button" value="글쓰기" class="btn"/></a>
<a href="/logout"><input type="button" value="로그아웃" id="logout"></a>
</form>
</body>
<script>
    function movePage(val) {
        // $('#pageNo').val(val); // #pageNo input hidden
        let pageHref = location.href;
        pageHref = pageHref.split("\\?")[0];

        pageHref += "?pageNo=" + val;
        $('#submitPage').load(pageHref + " #submitPage");
    }

</script>
</html>