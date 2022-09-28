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
    <script>
        // function dateCheck(reg) {
        //     let dateNow = new Date();
        //     if ((dateNow - reg) / (1000 * 60 * 60 * 24) < 1) {
        //         // new!
        //         console.log("new!");
        //     }
        // }

    </script>
</head>
<body>

<h2>게시판</h2>
<c:if test="${not empty user}">
    <h3>접속중 : ${user}</h3>
</c:if>

<form action="" method="post" id="submitPage" name="submitPage">
    <label>
        <select name="norm" id="norm">
            <option value="boardName" selected>작성자</option>
            <option value="title">title</option>
        </select>
        <input type="text" name="searchInput" id="searchInput">
        <button type="submit" id="search" onclick="search()">검색</button>
        <button onclick="resetSearch()">초기화</button>
    </label>
    <br>
    <br>
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
            <tr onclick="location.href='home/readBoard?boardNo=${boardList.boardNo}&boardName=${boardList.boardName}'"
                style="cursor: hand">
                <c:set var="now" value="<%=new java.util.Date()%>" />
                <fmt:parseNumber value="${now.time/(1000*60*60*24)}" var="today"/>
                <fmt:parseNumber value="${boardList.regdate.time/(1000*60*60*24)}" var="reg"/>

                <td>${boardList.ROWNUM}
                    <c:if test="${today - reg le 1}">
                        (NEW)
                    </c:if>
                </td>
                <td>${boardList.boardName}</td>
                <td>${boardList.title}</td>
                <td>${boardList.regdate}</td>
                <td>${boardList.hits}</td>
                <script>
                    dateCheck(${boardList.regdate});
                </script>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--    <input type="hidden" id="pageNo" >--%>
    <div id="page" style="text-align: center;">
        <c:if test="${page.pageNo != 0 and page.startPageNo!=0}">
            <!-- 글 목록이 지정해놓은 페이지 단 수보다 많다면 -->
            <c:if test="${page.pageNo > page.pageBlock}">
                <a href="javascript:movePage(${page.firstPageNo})" style="text-decoration: none;"></a>
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
    <%--    <c:set var="dateNow" value="new Date()" />--%>
    <%--    <c:set var="regDate" value="${boardList.get(3)}"/>--%>
    <%--    <c:if test="${(dateNow-regDate)/ (1000 * 60 * 60 * 24)<1}">--%>
    <%--    <span style="color: red;">new!</span>--%>
    <%--    </c:if>--%>


    function movePage(val) {
        let pageHref = location.href;
        pageHref = pageHref.split("\\?")[0];

        pageHref += "?pageNo=" + val;
        $('#submitPage').load(pageHref + " #submitPage");
    }

    function search() {
        // pageNo = 1으로 설정
        // pageSubmit
        let pageHref = location.href;
        pageHref = pageHref.split("\\?")[0];
        $('#submitPage').load(pageHref + " #submitPage");

        //     jQuery("input[name=searchFiled]").val(jQuery("#searchS").val());
        // }
        // var searchValue = jQuery("#searchI").val();
        // jQuery("input[name=searchValue]").val(searchValue);
        //
        // jQuery("input[name=pageNo]").val("1");
        // jQuery("form[name=frm]").attr("method", "post");
        // jQuery("form[name=frm]").attr("action","").submit();
    }

    function resetSearch() {
        document.querySelector('#searchInput').value = null;
    }

    // document.querySelector('#search').addEventListener('click', (e) => {
    //     let norm = document.querySelector('#norm');
    //     $.ajax({
    //         url: './home/search',
    //         type: 'post',
    //         data: {norm: norm.value, searchInput: $('#searchInput').val()},
    //         success: function () {
    //             console.log("검색 성공");
    //         },
    //         error: function () {
    //             alert('검색 실패');
    //         }
    //     })
    // });
</script>
</html>