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
                    <td><button onclick="historyDelete(event, ${history.id})">삭제</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

<script>
    function historyDelete(event, idCur) {
        event.preventDefault(); // 폼 제출 방지
        if (confirm("데이터를 삭제하시겠습니까?")) {
            fetch(`/removeOneData`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `Hid=${idCur}` // 클릭한 항목의 id만 전송
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    if (data.success) {
                        console.log('History item deleted successfully');
                        location.reload(); // 페이지 새로고침
                    } else {
                        alert("데이터 삭제에 실패했습니다.");
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert("데이터 삭제 중 오류가 발생했습니다.");
                });
        }
    }
</script>
</body>
</html>
