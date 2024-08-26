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

public class Exame {
    private int exameID;
    private String numAcesso;
    private Paciente paciente;
    private String data;
    private String modalidade;
    private String tipoExame;
    private int estado;
    private String medSol;
    private char laudo;
    private char especial;
    private char urgente;
    private char restaurado;
    private String visita;
    private String numero;

    // Construtores
    public Exame() {}

    public Exame(int exameID, String numAcesso, Paciente paciente, String data, String modalidade, 
                 String tipoExame, int estado, String medSol, char laudo, char especial, 
                 char urgente, char restaurado, String visita, String numero) {
        this.exameID = exameID;
        this.numAcesso = numAcesso;
        this.paciente = paciente;
        this.data = data;
        this.modalidade = modalidade;
        this.tipoExame = tipoExame;
        this.estado = estado;
        this.medSol = medSol;
        this.laudo = laudo;
        this.especial = especial;
        this.urgente = urgente;
        this.restaurado = restaurado;
        this.visita = visita;
        this.numero = numero;
    }

    // Getters e Setters
    public int getExameID() {
        return exameID;
    }

    public void setExameID(int exameID) {
        this.exameID = exameID;
    }

    public String getNumAcesso() {
        return numAcesso;
    }

    public void setNumAcesso(String numAcesso) {
        this.numAcesso = numAcesso;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getData() {
        return data;
    }

    public void setData(Date data) {
            this.data = data.toString();
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(String tipoExame) {
        this.tipoExame = tipoExame;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMedSol() {
        return medSol;
    }

    public void setMedSol(String medSol) {
        this.medSol = medSol;
    }

    public char getLaudo() {
        return laudo;
    }

    public void setLaudo(char laudo) {
        this.laudo = laudo;
    }

    public char getEspecial() {
        return especial;
    }

    public void setEspecial(char especial) {
        this.especial = especial;
    }

    public char getUrgente() {
        return urgente;
    }

    public void setUrgente(char urgente) {
        this.urgente = urgente;
    }

    public char getRestaurado() {
        return restaurado;
    }

    public void setRestaurado(char restaurado) {
        this.restaurado = restaurado;
    }

    public String getVisita() {
        return visita;
    }

    public void setVisita(String visita) {
        this.visita = visita;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Exame{" +
                "exameID=" + exameID +
                ", numAcesso='" + numAcesso + '\'' +
                ", paciente=" + paciente +
                ", data=" + data +
                ", modalidade='" + modalidade + '\'' +
                ", tipoExame='" + tipoExame + '\'' +
                ", estado=" + estado +
                ", medSol='" + medSol + '\'' +
                ", laudo=" + laudo +
                ", especial=" + especial +
                ", urgente=" + urgente +
                ", restaurado=" + restaurado +
                ", visita='" + visita + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
