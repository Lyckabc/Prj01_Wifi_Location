package controller;
import apidto.WifiDto;
import model.DAO;
import model.vo.HistoryDAO;
import model.vo.WifiVO;
import com.google.gson.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.List;

@WebServlet("/LocationHistoryController")
public class LocationHistoryController extends HttpServlet {

    private final DAO dao;    // 메모리에 한번에올려놓고 사용하려고
    private final HistoryDAO historyDAO;

    public LocationHistoryController(){
        this.dao = new DAO();
        this.historyDAO = new HistoryDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");
        System.out.println("lat: " + request.getParameter("lat") + ", lnt: " + request.getParameter("lnt"));
        if(lat != null && lnt != null) {
            try {
                double latitude = Double.parseDouble(lat);
                double longitude = Double.parseDouble(lnt);

                // 위치 정보를 데이터베이스에 저장하는 로직 (옵션)
                historyDAO.save(latitude, longitude);

                // lat와 lnt 기반으로 searchList 검색
                List<WifiVO> searchList = dao.getNearestWifiList(latitude, longitude);

                request.setAttribute("searchList", searchList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); // 또는 결과를 보여줄 다른 JSP 페이지
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 위도 또는 경도 형식입니다.");
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "요청 처리 중 오류 발생");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "위도와 경도는 필수입니다.");
        }
    }
}
