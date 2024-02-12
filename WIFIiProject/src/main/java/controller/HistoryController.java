package controller;

import model.vo.HistoryDAO;
import model.vo.HistoryVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet("/HistoryList")
public class HistoryController extends HttpServlet{

    HistoryDAO historyDAO = new HistoryDAO();
    @Override
    protected void service(HttpServletRequest request , HttpServletResponse response) throws
            IOException, HTTPException, ServletException {
        /**
         * 1. 히스토리를에 대한걸 가져와서
         * 2. -> 히스토리에 대한정보를 넣자.
         */

        List<HistoryVO> list = historyDAO.HistoryselectAll();
        System.out.println(list);

        // jsp에서 사용
        request.setAttribute("selectAll",list);// 데이터담기

        /**
         * 포워드
         */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/history.jsp");// 보내야함
        requestDispatcher.forward(request,response);

    }
}
