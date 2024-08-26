/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.medilabsistemas.servlets;

import com.google.gson.Gson;
import com.mycompany.medilabsistemas.dao.ExameDAO;
import com.mycompany.medilabsistemas.model.Exame;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author panisset
 */
@WebServlet("/exames")
public class ExamesServlet extends HttpServlet {
    private ExameDAO exameDAO;

    @Override
    public void init() {
        exameDAO = new ExameDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            List<Exame> exames = exameDAO.getAllExames();
            Gson gson = new Gson();
            String json = gson.toJson(exames);
            out.write(json);
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
