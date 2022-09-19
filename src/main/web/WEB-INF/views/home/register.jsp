<%--
  Created by IntelliJ IDEA.
  User: bma01
  Date: 2022-09-07
  Time: 오후 3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <link rel="stylesheet" href="/static/index.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.7.1.min.js"></script>
    <title>Register</title>
</head>
<body>
<h1>회원가입</h1>
<form action="applyRegister" method="post" onsubmit="return submitCheck()">

    <h3><label for="id">아이디</label></h3>
    <input type="text" name="id" id="id" aria-describedby="idMsg" maxlength="20" >
    <span id="idMsg" style="display: block;" aria-live="assertive" ></span>

    <h3><label for="pw">비밀번호</label></h3>
    <input type="password" name="pw" id="pw" aria-describedby="pwMsg" maxlength="16">
    <button type="button" id="viewPw" >비밀번호 보기</button>
    <span id="pwMsg" aria-live="assertive" style="display: block;"></span>

    <h3><label for="pwCheck">비밀번호 재확인</label></h3>
    <input type="password" name="pwCheck" id="pwCheck" aria-describedby="pwCheckMsg" maxlength="16" >
    <span id="pwCheckMsg" aria-live="assertive" style="display: block;"></span>

    <h3><label for="name">이름</label></h3>
    <input type="text" name="name" id="name">

    <h3><label for="birth">생년월일</label></h3>
    <input type="date" name="birth" id="birth" aria-label="생년월일(8자)" placeholder="생년월일(8자)" maxlength="8" >
    <span id="birthMsg" style="display: block;"></span>

    <h3><label for="gender">성별</label></h3>
    <select name="gender" id="gender">
        <option value="M">남자</option>
        <option value="F">여자</option>
        <option value="X">선택 안함</option>
    </select>

    <h3><label for="email">이메일</label></h3><p style="color: gray;">(선택 사항)</p>
    <input type="email" id="email" name="email" >

    <input type="submit" value="가입하기" id="submit" >
</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/index.js?ver=1"></script>
</html>
