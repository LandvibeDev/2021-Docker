package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/guestbooks")
public class GuestbookListServlet extends HttpServlet {

    GuestbookDao dao = new GuestbookDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Guestbook> list = dao.getGuestbooks();

        request.setAttribute("list", list);
        request.getRequestDispatcher("/WEB-INF/guestbook/guestbooks.jsp").forward(request, response);

    }

}
