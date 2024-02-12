<%--
  Created by IntelliJ IDEA.
  User: Toji
  Date: 2024-02-08 오후 10:25
  Time: 오후 10:25
  https://github.com/lyckabc
--%>
<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="../view/css/style.css">
</head>

<body>
<h1>위치 히스토리 목록</h1>
<%@include file="header.jsp"%>
<p>
</p>
    <div class="mt-4">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>조회일자</th>
                <th>비고</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${selectAll}" var="history">
                <tr class ="Info">
                    <td><c:out value="${history.id}"/></td>
                    <td><c:out value="${history.lat}"/></td>
                    <td><c:out value="${history.lnt}"/></td>
                    <td><c:out value="${history.date}"/></td>
                    <td><button type ="button" class="button">삭제</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

<script>
    $(".button").click(function () {

            var checkBtn = $(this);
            var tr = checkBtn.parent().parent();
            var td = tr.children();

            var deleteIdnumber = td.eq(0).text();
            // console.log(no);

            $.ajax({
                type:"POST",
                url: "http://localhost:8080/removeOneData?deleteIdnumber=" + deleteIdnumber

            }).done(function (){
                location.reload();
            })
        }
    )
</script>
</body>
</html>
