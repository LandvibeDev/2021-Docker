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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 코드를 작성하세요.
    	request.setCharacterEncoding("utf-8");
    	String name = request.getParameter("name");
    	String content = request.getParameter("content");
    	
    	GuestbookDao guestbookDao = new GuestbookDao();
    	Guestbook guestbook = new Guestbook(name, content);
    	guestbookDao.addGuestbook(guestbook);
    	response.sendRedirect("/guestbook/guestbooks");
    	
    	
    }

}
