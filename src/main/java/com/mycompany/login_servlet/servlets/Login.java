/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.login_servlet.servlets;

import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;

/**
 *
 * @author Smarts3
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String userId = request.getParameter("identification");
                String name = request.getParameter("userName");
                String psw = request.getParameter("pswrd");

          
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                 
                // Data Base Connection
                String mysqlUser = "root";
                String mysqlPsw = "";
                String dbUrl = "jdbc:mysql://localhost:3306/logintomcat";
                 
                // Declare connection variables
                Connection connection;
                Statement statement;
                ResultSet rSet;

                String query = "SELECT * FROM `user` WHERE `identification` = %s AND `userName` = %s `pswrd` = %s".formatted(userId, name, psw);
                
                try {
                    connection = DriverManager.getConnection(dbUrl,
                            mysqlUser, mysqlPsw);
                    System.out.println("Conexion establecida");
                    statement = connection.createStatement();
                    rSet = statement.executeQuery(query);
                    
                    if (rSet.next()) {
                        request.getSession().setAttribute("Connected", name);
                        response.sendRedirect("landing.jsp");
                    } else {
                        response.sendRedirect("index.html");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
