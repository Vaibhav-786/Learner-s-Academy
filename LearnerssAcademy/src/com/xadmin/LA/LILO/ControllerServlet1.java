package com.xadmin.LA.LILO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class ControllerServlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SubjectDAO subjectDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        subjectDAO = new SubjectDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
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
                insertSubject(request, response);
                break;
            case "/delete":
                deleteSubject(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateSubject(request, response);
                break;
            default:
                listSubject(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listSubject(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Subject> listSubject = subjectDAO.listAllSubjects();
        request.setAttribute("listSubject", listSubject);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SubjectList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("SubjectForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Subject existingSubject = subjectDAO.getSubject(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SubjectForm.jsp");
        request.setAttribute("subject", existingSubject);
        dispatcher.forward(request, response);
 
    }
 
    private void insertSubject(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String subjectname = request.getParameter("subjectname");
        
 
        Subject newSubject = new Subject(subjectname);
        subjectDAO.insertSubject(newSubject);
        response.sendRedirect("list");
    }
 
    private void updateSubject(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String subjectname = request.getParameter("subjectname");
        
 
        Subject subject = new Subject(id, subjectname);
        subjectDAO.updateSubject(subject);
        response.sendRedirect("list");
    }
 
    private void deleteSubject(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Subject subject = new Subject(id);
        subjectDAO.deleteSubject(subject);
        response.sendRedirect("list");
 
    }
}