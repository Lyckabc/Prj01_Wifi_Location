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

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="../view/css/style.css">
</head>
<body>
    <!-- Form -->
    <form id="wifiSearchForm" action="/searchWifi" method="get">
        LAT (위도): <input type="text" id="lat" name="lat" value="">
        LNT (경도): <input type="text" id="lnt" name="lnt" value="">
        <button type="button" id="btn_cur_position">내 위치 가져오기</button>
        <input type="submit" value="근처 WIFI 정보 보기">
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
            <%
                if (request.getAttribute("searchList") != null) {
                    java.util.List searchList = (java.util.List) request.getAttribute("searchList");
                    System.out.println("searchList contents:");
                    for (Object wifi : searchList) {
                        System.out.println(wifi.toString()); // 각 WifiVO 객체의 toString() 메소드가 정보를 적절히 출력하도록 구현되어 있어야 합니다.
                    }
                } else {
                    System.out.println("searchList is null or not set.");
                }
            %>
            <c:choose>
                <c:when test="${not empty searchList}">
                    <c:forEach var="wifi" items="${searchList}">
                        <tr>
                            <td><c:out value="${wifi.distance}"/></td>
                            <td><c:out value="${wifi.mgrNo}"/></td>
                            <td><c:out value="${wifi.wrdofc}"/></td>
                            <td><c:out value="${wifi.mainNm}"/></td>
                            <td><c:out value="${wifi.adres1}"/></td>
                            <td><c:out value="${wifi.adres2}"/></td>
                            <td><c:out value="${wifi.floor}"/></td>
                            <td><c:out value="${wifi.ty}"/></td>
                            <td><c:out value="${wifi.mby}"/></td>
                            <td><c:out value="${wifi.svcSe}"/></td>
                            <td><c:out value="${wifi.cmcwr}"/></td>
                            <td><c:out value="${wifi.year}"/></td>
                            <td><c:out value="${wifi.door}"/></td>
                            <td><c:out value="${wifi.remars3}"/></td>
                            <td><c:out value="${wifi.lat}"/></td>
                            <td><c:out value="${wifi.lnt}"/></td>
                            <td><c:out value="${wifi.dttm}"/></td>
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

    <script>
        let getCurPosition = document.getElementById("btn_cur_position");
        let getNearestWifi = document.getElementById("btn_nearest_wifi");

        let lat = null;
        let lnt = null;

        window.onload = () => {
            lat = document.getElementById("lat").value;
            lnt = document.getElementById("lnt").value;
        }

        getCurPosition.addEventListener("click", function () {
            if ('geolocation' in navigator) {
                navigator.geolocation.getCurrentPosition(function (position){
                    let latitude = position.coords.latitude;
                    let longitude = position.coords.longitude;
                    document.getElementById("lat").value = latitude;
                    document.getElementById("lnt").value = longitude;
                })
            } else{
                alert("위치 정보를 확인할 수 없으니 직접 입력해주시기 바랍니다.")
            }
        });

        document.getElementById("wifiSearchForm").addEventListener("submit", function (){
            let latitude = document.getElementById("lat").value;
            let longitude = document.getElementById("lnt").value;

            if (latitude !== "" || longitude !== "") {
                window.location.assign("http://localhost:8080?lat=" + latitude + "&lnt=" + longitude);
            } else {
                alert("위치 정보를 입력하신 후에 조회해주세요.")
            }
        })
    </script>


</body>
</html>
