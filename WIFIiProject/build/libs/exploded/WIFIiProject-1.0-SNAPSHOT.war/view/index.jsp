<%--
  Created by IntelliJ IDEA.
  User: Toji 
  Date: 2024-02-08 오후 11:06  
  Time: 오후 11:06
  https://github.com/lyckabc 
--%>
<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>와이파이 정보 구하기</title>
	<link rel="stylesheet" type="text/css" href="../view/css/style.css">
</head>
<body>

<div class="container mt-3">
	<h1>와이파이 정보 구하기</h1>
	<%@include file="header.jsp"%>

	<!-- Form -->
	<form id="wifiForm" action="/LocationServlet" method="get" class="mt-4">
		<%
			String lat = request.getParameter("lat") == null ? "0.0" : request.getParameter("lat");
			String lnt = request.getParameter("lnt") == null ? "0.0" : request.getParameter("lnt");
		%>
		<span>
			<label for="latitude-input">LAT (위도):</label>
			<input type="text" id="latitude-input" name="latitude" value="<%=lat%>">
		</span>
		<span>
			<label for="longitude-input">LNT (경도):</label>
			<input type="text" id="longitude-input" name="longitude" value="<%=lnt%>">
		</span>
		<button type="button" class="btn btn-danger" onclick="getCurPosition()"><span>내 위치 가져오기</span></button>
		<button type="button" class="btn btn-info" onclick="getNearestWifi()"><span>근처 WIFI 정보 보기</span></button>
	</form>

	<!-- Data Display -->
	<div class="mt-4">
		<table>
			<thead>
			<tr>
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
			</thead>
			<tbody>
			<c:choose>
				<c:when test="${not empty searchList}">
					<c:forEach var="wifi" items="${searchList}">
						<tr>
							<td>${wifi.distance}</td>
							<td>${wifi.mgrNo}</td>
							<td>${wifi.wrdofc}</td>
							<td>${wifi.mainNm}</td>
							<td>${wifi.adres1}</td>
							<td>${wifi.adres2}</td>
							<td>${wifi.floor}</td>
							<td>${wifi.ty}</td>
							<td>${wifi.mby}</td>
							<td>${wifi.svcSe}</td>
							<td>${wifi.cmcwr}</td>
							<td>${wifi.year}</td>
							<td>${wifi.door}</td>
							<td>${wifi.remars3}</td>
							<td>${wifi.lat}</td>
							<td>${wifi.lnt}</td>
							<td>${wifi.dttm}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan='17'>위치 정보를 입력한 후에 조회해 주세요.</td>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</div>
</div>
<script>
	function getCurPosition() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function (pos) {
				var latitude = pos.coords.latitude;
				var longitude = pos.coords.longitude;

				document.getElementById('latitude-input').value = latitude;
				document.getElementById('longitude-input').value = longitude;
			});
		} else {
			alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
		}
	}

	function getNearestWifi() {
		let latitude = document.getElementById('latitude-input').value;
		let longitude = document.getElementById('longitude-input').value;

		if (latitude !== "" && longitude !== "") {
			fetch(`/LocationServlet?latitude-input=${latitude}&longitude-input=${longitude}`)
			.then(response => {
				if (!response.ok) {
					throw new Error('Network response was not ok');
				}
				return response.json(); // 이 부분은 서버가 JSON 응답을 반환한다고 가정
			})
			.then(data => {
				console.log(data); // 받은 데이터 처리, 예: 테이블에 데이터 표시
				// 여기서는 예제로 콘솔에 출력만 함
				// 실제 구현에서는 이 데이터를 DOM에 동적으로 추가하는 로직 필요
			})
			.catch((error) => {
				console.error('Error:', error);
				alert("오류가 발생했습니다. 다시 시도해주세요.");
			});
		} else {
			alert("위치 정보를 입력하신 후에 조회해주세요.")
		}
	}
</script>

</body>
</html>
