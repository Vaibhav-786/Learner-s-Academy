package com.xadmin.LA.LILO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

public class ControllerServlet4 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SubDAO subDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        subDAO = new SubDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertSub(request, response);
                break;
            case "/delete":
                deleteSub(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateSub(request, response);
                break;
            default:
                listSub(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listSub(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Sub> listSub = subDAO.listAllSubs();
        request.setAttribute("listSub", listSub);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SubList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("SubForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Sub existingSub = subDAO.getSub(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SubForm.jsp");
        request.setAttribute("sub", existingSub);
        dispatcher.forward(request, response);
 
    }
 
    private void insertSub(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String subname = request.getParameter("subname");
        String classname = request.getParameter("classname");
        
 
        Sub newSub = new Sub(subname, classname);
        subDAO.insertSub(newSub);
        response.sendRedirect("list");
    }
 
    private void updateSub(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String subname = request.getParameter("subname");
        String classname = request.getParameter("classname");
        
 
        Sub sub = new Sub(id, subname, classname);
        subDAO.updateSub(sub);
        response.sendRedirect("list");
    }
 
    private void deleteSub(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Sub sub = new Sub(id);
        subDAO.deleteSub(sub);
        response.sendRedirect("list");
 
    }
}