/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.medilabsistemas.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author panisset
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Random;
import java.sql.PreparedStatement;
import java.sql.Date;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5433/";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
                
                // Verificar se o banco de dados existe
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT 1 FROM pg_database WHERE datname = 'medilab'");
                
                if (!rs.next()) {
                    // O banco de dados não existe, então crie-o
                    stmt.execute("CREATE DATABASE medilab");
                    System.out.println("Banco de dados 'medilab' criado com sucesso!");
                    
                    // Conceder privilégios ao usuário
                    stmt.execute("GRANT ALL PRIVILEGES ON DATABASE medilab TO postgres");
                    System.out.println("Privilégios concedidos ao banco de dados 'medilab'!");
                }
                
                // Fechar a conexão temporária
                conn.close();

                // Conectar ao banco de dados criado
                connection = DriverManager.getConnection(URL + "medilab", USER, PASSWORD);
                System.out.println("Conexão com o banco de dados 'medilab' estabelecida com sucesso!");

                // Criar tabelas se não existirem
                createTables(connection);
                populateTables(connection);

            } catch (ClassNotFoundException | SQLException e) {
                throw new SQLException("Erro ao conectar ao PostgreSQL: " + e.getMessage());
            }
        }
        return connection;
    }
    private static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        stmt.execute("CREATE TABLE IF NOT EXISTS Paciente (" +
                "patientID SERIAL PRIMARY KEY, " +
                "nome VARCHAR(100), " +
                "sexo CHAR(1)" +
                ")");

        stmt.execute("CREATE TABLE IF NOT EXISTS Exame (" +
                "exameID SERIAL PRIMARY KEY,  " + 
                "numAcesso VARCHAR(50) UNIQUE, " +
                "patientID INT REFERENCES Paciente(patientID), " +
                "data DATE, " +
                "modalidade VARCHAR(50), " +
                "tipoExame VARCHAR(100), " +
                "estado INT, " +
                "medSol VARCHAR(100), " +
                "laudo CHAR(1), " +
                "especial CHAR(1), " +
                "urgente CHAR(1), " +
                "restaurado CHAR(1), " +
                "visita VARCHAR(100), " +
                "numero VARCHAR(50)" +
                ")");
    }

    private static void populateTables(Connection conn) throws SQLException {
        Random random = new Random();

        // Popular tabela Paciente
        for (int i = 0; i < 10; i++) {
            String nome = "Paciente " + i;
            char sexo = random.nextBoolean() ? 'M' : 'F';

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Paciente (nome, sexo) VALUES (?, ?)");
            pstmt.setString(1, nome);
            pstmt.setString(2, String.valueOf(sexo));
            pstmt.executeUpdate();
        }

        // Popular tabela Exame
        for (int i = 0; i < 20; i++) {
            String numAcesso = "Exame_" + i;
            int patientID = random.nextInt(10) + 1; // Assumindo que temos 10 pacientes
            Date data = new java.sql.Date(System.currentTimeMillis());
            String modalidade = "Modalidade " + i;
            String tipoExame = "Tipo Exame " + i;
            int estado = random.nextInt(2);
            String medSol = "Medico Sol " + i;
            char laudo = random.nextBoolean() ? 'S' : 'N';
            char especial = random.nextBoolean() ? 'S' : 'N';
            char urgente = random.nextBoolean() ? 'S' : 'N';
            char restaurado = random.nextBoolean() ? 'S' : 'N';
            String visita = "Visita " + i;
            String numero = "Numero " + i;

            // Verificar se o numAcesso já existe
            PreparedStatement checkStmt = conn.prepareStatement("SELECT 1 FROM Exame WHERE numAcesso = ?");
            checkStmt.setString(1, numAcesso);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Exame (numAcesso, patientID, data, modalidade, tipoExame, estado, medSol, laudo, especial, urgente, restaurado, visita, numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, numAcesso);
                pstmt.setInt(2, patientID);
                pstmt.setDate(3, data);
                pstmt.setString(4, modalidade);
                pstmt.setString(5, tipoExame);
                pstmt.setInt(6, estado);
                pstmt.setString(7, medSol);
                pstmt.setString(8, String.valueOf(laudo));
                pstmt.setString(9, String.valueOf(especial));
                pstmt.setString(10, String.valueOf(urgente));
                pstmt.setString(11, String.valueOf(restaurado));
                pstmt.setString(12, visita);
                pstmt.setString(13, numero);
                pstmt.executeUpdate();
            } else {
                System.out.println("Exame com numAcesso " + numAcesso + " já existe.");
            }
        }
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
