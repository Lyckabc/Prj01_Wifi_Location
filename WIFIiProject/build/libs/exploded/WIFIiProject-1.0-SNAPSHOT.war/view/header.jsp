<%--
  Created by IntelliJ IDEA.
  User: Toji 
  Date: 2024-02-08 오후 10:03  
  Time: 오후 10:03
  https://github.com/lyckabc 
--%>
<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<!-- Actions -->
    <a href="http://localhost:8080/view/index.jsp">홈</a>
    <a href="#">|</a>
    <a href="/HomeServlet" class="btn btn-warnning" role=button">위치 히스토리 목록</a>
    <a href="#">|</a>
    <a href="/ApiWifiController" class="btn btn-success" role=button">Open API 와이파이 정보 가져오기</a>
    <a href="#">|</a>
    <a href="http://localhost:8080/view/bookmarkList.jsp">북마크 보기</a>
    <a href="#">|</a>
    <a href="http://localhost:8080/view/bookmarkGroup.jsp">북마크 그룹 관리</a>
<br>
</body>
</html>
