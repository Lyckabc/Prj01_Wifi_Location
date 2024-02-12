package controller;

import model.vo.HistoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/removeOneData")
public class HistoryRemoveOneDataController extends HttpServlet {

    private final HistoryDAO historyDAO = new HistoryDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        try {
            // 클라이언트에서 전송된 historyId 값을 받아옴
            String historyIdParam = request.getParameter("deleteIdnumber");
            System.out.println("Received historyId: " + request.getParameter("deleteIdnumber"));
            // 받아온 historyId 값을 서버 로그에 출력
            System.out.println("String historyId: " + historyIdParam);

            if (historyIdParam == null || historyIdParam.trim().isEmpty()) {
                out.println("{\"success\": false, \"message\": \"Missing or invalid historyId parameter\"}");
                return;
            }

            // 문자열 historyIdParam을 정수로 변환
            int historyId = Integer.parseInt(historyIdParam);

            // 데이터베이스에서 해당 ID에 해당하는 항목을 삭제하고 결과를 받음
            int result = historyDAO.removeOneData(historyId);

            if (result > 0) {
                out.println("{\"success\": true}");
            } else {
                out.println("{\"success\": false, \"message\": \"No record found to delete\"}");
            }
        } catch (NumberFormatException e) {
            out.println("{\"success\": false, \"message\": \"Invalid historyId format\"}");
        } finally {
            out.flush();
        }
    }
}