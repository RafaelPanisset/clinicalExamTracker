/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.medilabsistemas.dao;

import com.mycompany.medilabsistemas.model.Exame;
import com.mycompany.medilabsistemas.model.Paciente;
import com.mycompany.medilabsistemas.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author panisset
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExameDAO {
    public List<Exame> getAllExames() throws SQLException {
        List<Exame> exames = new ArrayList<>();
        String sql = "SELECT e.*, p.nome, p.sexo " +
                     "FROM exame e " +
                     "JOIN paciente p ON e.patientid = p.patientid;";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Exame exame = new Exame();
                Paciente paciente = new Paciente();
                
                // Preencher dados do exame
                exame.setExameID(rs.getInt("exameID"));
                exame.setNumAcesso(rs.getString("numAcesso"));
                exame.setData(rs.getDate("data"));
                exame.setModalidade(rs.getString("modalidade"));
                exame.setTipoExame(rs.getString("tipoExame"));
                exame.setEstado(rs.getInt("estado"));
                exame.setMedSol(rs.getString("medSol"));
                exame.setLaudo(rs.getString("laudo").charAt(0));
                exame.setEspecial(rs.getString("especial").charAt(0));
                exame.setUrgente(rs.getString("urgente").charAt(0));
                exame.setRestaurado(rs.getString("restaurado").charAt(0));
                exame.setVisita(rs.getString("visita"));
                exame.setNumero(rs.getString("numero"));
                
                // Preencher dados do paciente
                paciente.setPatientID(rs.getInt("patientID"));
                paciente.setNome(rs.getString("nome"));
                paciente.setSexo(rs.getString("sexo").charAt(0));
                
                // Associar o paciente ao exame
                exame.setPaciente(paciente);
                
                exames.add(exame);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar exames: " + e.getMessage());
            throw e;
        }
        
        return exames;
    }
}