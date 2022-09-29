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
    <%--    <link rel="stylesheet" href="/static/index.css">--%>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.7.1.min.js"></script><meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/checkout/">

    <!-- Bootstrap core CSS -->
    <link href="/assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="sign-in/form-validation.css" rel="stylesheet">
    <title>Register</title>
</head>
<body class="bg-light">
<div class="container">
    <img class="d-block mx-auto mb-4" src="/assets/brand/bootstrap-solid.svg" alt="" width="42" height="42">
    <h2 class="py-5 text-center">회원가입</h2>
    <div class="row g-5" >
        <div class="col-md-7 col-lg-8" >
            <form action="applyRegister" method="post" onsubmit="return submitCheck()" >
                <div class="row g-3">
                    <div class="col-12">
                        <label for="id" class="form-label">아이디</label>
                        <input type="text" name="id" id="id" aria-describedby="idMsg" maxlength="20" class="form-control">
                        <span id="idMsg" style="display: block;" aria-live="assertive"></span>
                    </div>
                    <div class="col-12">
                        <label for="pw" class="form-label">비밀번호</label>
                        <input type="password" class="togglePw form-control" name="pw" id="pw" aria-describedby="pwMsg"
                               maxlength="16">
                        <button type="button" class="viewPw">비밀번호 보기</button>
                        <span id="pwMsg" aria-live="assertive" style="display: block;"></span>
                    </div>
                    <div class="col-12">
                        <label for="pwCheck" class="form-label">비밀번호 재확인</label>
                        <input type="password" class="togglePw form-control" name="pwCheck" id="pwCheck"
                               aria-describedby="pwCheckMsg"
                               maxlength="16">
                        <span id="pwCheckMsg" aria-live="assertive" style="display: block;"></span>
                    </div>
                    <div class="col-12">
                        <label for="name" class="form-label">이름</label>
                        <input type="text" name="name" id="name" class="form-control">
                    </div>
                    <div class="col-12">
                        <label for="birth" class="form-label">생년월일</label>
                        <input type="date" name="birth" id="birth" aria-label="생년월일(8자)" placeholder="생년월일(8자)"
                               maxlength="8" class="form-control">
                        <span id="birthMsg" style="display: block;"></span>
                    </div>
                    <div class="col-12">
                        <label for="gender" class="form-label">성별</label>
                        <select name="gender" id="gender" class="form-select">
                            <option value="M">남자</option>
                            <option value="F">여자</option>
                            <option value="X">선택 안함</option>
                        </select>
                    </div>
                    <div class="col-12">
                        <label for="email" class="form-label">이메일</label>
                        <input type="email" id="email" name="email" class="form-control">
                        <button id="auth" type="button">인증하기</button><br>
                        <div id="authCheck" style="display: none;">
                            <label for="authKey">
                                <input type="text" placeholder="인증번호 6자리를 입력하세요." id="authKey" name="authKey" maxlength="6">
                                <button type="button">확인</button>
                            </label>
                        </div>
                    </div>
                    <hr class="my-4">
                    <div>
                        <input class="btn btn-primary btn-lg" type="submit" value="가입하기" id="submit">
                        <a href="${pageContext.request.contextPath}/">
                            <button type="button" class="btn btn-primary btn-lg">취소</button>
                        </a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/index.js?ver=2"></script>
</html>
