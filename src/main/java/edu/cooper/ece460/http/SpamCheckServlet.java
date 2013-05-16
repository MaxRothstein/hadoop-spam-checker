package edu.cooper.ece460.http;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

// Should probably be in a jsp
public class SpamCheckServlet extends HttpServlet {
    PrintWriter out;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);

        out = resp.getWriter();
        out.println("<link rel='stylesheet' href='style.css' type='text/css'>");
        out.println("<h1>List of Potential Spammers</h1>");

        req.getRequestDispatcher("/authlog_analyse.html").include(req, resp); 

        out.println("<div style='float:left; padding-left:10px;'>");

        out.println("</div>");
    }
}

