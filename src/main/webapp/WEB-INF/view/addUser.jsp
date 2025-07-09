<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<h2>회원가입</h2>

<form action="${pageContext.request.contextPath}/addUserAction" method="post">
    <table>
        <tr>
            <td>아이디</td>
            <td><input type="text" name="username" required></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="password" required></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">회원가입</button>
            </td>
        </tr>
    </table>
</form>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

</body>
</html>
