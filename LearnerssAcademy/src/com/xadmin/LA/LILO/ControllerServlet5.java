package com.xadmin.LA.LILO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

public class ControllerServlet5 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TeaDAO teaDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        teaDAO = new TeaDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
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
                insertTea(request, response);
                break;
            case "/delete":
                deleteTea(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateTea(request, response);
                break;
            default:
                listTea(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listTea(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Tea> listTea = teaDAO.listAllTeas();
        request.setAttribute("listTea", listTea);
        RequestDispatcher dispatcher = request.getRequestDispatcher("TeaList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("TeaForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tea existingTea = teaDAO.getTea(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("TeaForm.jsp");
        request.setAttribute("tea", existingTea);
        dispatcher.forward(request, response);
 
    }
 
    private void insertTea(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String classname = request.getParameter("classname");
        String subjectname = request.getParameter("subjectname");
        String teachername = request.getParameter("teachername");
 
        Tea newTea = new Tea(classname, subjectname, teachername);
        teaDAO.insertTea(newTea);
        response.sendRedirect("list");
    }
 
    private void updateTea(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String classname = request.getParameter("classname");
        String subjectname = request.getParameter("subjectname");
        String teachername = request.getParameter("teachername");
 
        Tea tea = new Tea(id, classname, subjectname, teachername);
        teaDAO.updateTea(tea);
        response.sendRedirect("list");
    }
 
    private void deleteTea(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Tea tea = new Tea(id);
        teaDAO.deleteTea(tea);
        response.sendRedirect("list");
 
    }
}