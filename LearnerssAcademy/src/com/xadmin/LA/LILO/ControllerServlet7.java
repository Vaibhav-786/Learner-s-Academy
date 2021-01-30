package com.xadmin.LA.LILO;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

public class ControllerServlet7 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RecordDAO recordDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        recordDAO = new RecordDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
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
                insertRecord(request, response);
                break;
            case "/delete":
                deleteRecord(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateRecord(request, response);
                break;
            default:
                listRecord(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listRecord(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Record> listRecord = recordDAO.listAllRecords();
        request.setAttribute("listRecord", listRecord);
        RequestDispatcher dispatcher = request.getRequestDispatcher("RecordList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("RecordForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Record existingRecord = recordDAO.getRecord(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("RecordForm.jsp");
        request.setAttribute("record", existingRecord);
        dispatcher.forward(request, response);
 
    }
 
    private void insertRecord(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String classname = request.getParameter("classname");
        String subjectname = request.getParameter("subjectname");
        String teachername = request.getParameter("teachername");
        String studentname = request.getParameter("studentname");
        
 
        Record newRecord = new Record(classname, subjectname, teachername, studentname);
        recordDAO.insertRecord(newRecord);
        response.sendRedirect("list");
    }
 
    private void updateRecord(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String classname = request.getParameter("classname");
        String subjectname = request.getParameter("subjectname");
        String teachername = request.getParameter("teachername");
        String studentname = request.getParameter("studentname");
        
 
        Record record = new Record(id, classname, subjectname, teachername, studentname);
        recordDAO.updateRecord(record);
        response.sendRedirect("list");
    }
 
    private void deleteRecord(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Record record = new Record(id);
        recordDAO.deleteRecord(record);
        response.sendRedirect("list");
 
    }
}