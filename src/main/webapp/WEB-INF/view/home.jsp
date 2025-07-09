<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
    <h1>Home</h1>
    <hr>

    <c:choose>
        <c:when test="${empty loginUsername or loginUsername == 'anonymousUser'}">
            <a href="/addUser">회원가입</a>
            <a href="/login">로그인</a>
        </c:when>
        <c:otherwise>
            ${loginUsername}님 반갑습니다.
            <br>
            <a href="/logout">로그아웃</a> |
            <a href="/user/updateForm">회원정보수정</a> |
            <a href="/user/delete" onclick="return confirm('정말 탈퇴하시겠습니까?')">회원 탈퇴</a>
        </c:otherwise>
    </c:choose>
</body>
</html>
