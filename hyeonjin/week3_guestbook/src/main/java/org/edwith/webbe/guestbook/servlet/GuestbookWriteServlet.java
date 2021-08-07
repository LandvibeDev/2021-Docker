package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guestbooks/write")
public class GuestbookWriteServlet extends HttpServlet {

    GuestbookDao dao = new GuestbookDao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String content = request.getParameter("content");

        System.out.print(name + content);
        Guestbook guestbook = new Guestbook(name, content);
        dao.addGuestbook(guestbook);
        response.sendRedirect("/guestbooks");

    }

}
