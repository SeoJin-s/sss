<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원정보 수정</title>
</head>
<body>
    <h1>회원정보 수정</h1>
    
    <form action="/user/update" method="post">
        <input type="hidden" name="username" value="${user.username}" />
        
        <label>비밀번호: </label>
        <input type="password" name="password" required /><br><br>
        
        <button type="submit">수정하기</button>
    </form>
</body>
</html>
