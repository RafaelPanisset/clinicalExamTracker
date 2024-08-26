/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.medilabsistemas.model;

/**
 *
 * @author panisset
 */
import java.util.Date;

public class Paciente {
    private int patientID;
    private String nome;
    private char sexo;

    // Construtores
    public Paciente() {}

    public Paciente(int patientID, String nome, char sexo) {
        this.patientID = patientID;
        this.nome = nome;
        this.sexo = sexo;
    }

    // Getters e Setters
    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "patientID=" + patientID +
                ", nome='" + nome + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}