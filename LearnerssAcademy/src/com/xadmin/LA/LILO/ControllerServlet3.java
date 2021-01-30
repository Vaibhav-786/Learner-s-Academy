package com.xadmin.LA.LILO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class ControllerServlet3 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClasDAO clasDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        clasDAO = new ClasDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
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
                insertClas(request, response);
                break;
            case "/delete":
                deleteClas(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateClas(request, response);
                break;
            default:
                listClas(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listClas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Clas> listClas = clasDAO.listAllClasses();
        request.setAttribute("listClas", listClas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClassList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClassForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Clas existingClas = clasDAO.getClas(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClassForm.jsp");
        request.setAttribute("clas", existingClas);
        dispatcher.forward(request, response);
 
    }
 
    private void insertClas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String clasname = request.getParameter("classname");
        
 
        Clas newClas = new Clas(clasname);
        clasDAO.insertClas(newClas);
        response.sendRedirect("list");
    }
 
    private void updateClas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String clasname = request.getParameter("classname");
        
 
        Clas clas = new Clas(id, clasname);
        clasDAO.updateClas(clas);
        response.sendRedirect("list");
    }
 
    private void deleteClas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Clas clas = new Clas(id);
        clasDAO.deleteClas(clas);
        response.sendRedirect("list");
 
    }
}