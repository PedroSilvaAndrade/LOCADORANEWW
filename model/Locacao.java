package model;

import java.time.LocalDate;

public class Locacao {
    // Atributos da classe
    private int idLocacao;
    private int idFuncionario;
    private int idCliente;
    private String placaVeiculo;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private String formaPagamento;
    private double valorPago;

    // Construtor
    public Locacao(int idLocacao, int idFuncionario, int idCliente, String placaVeiculo, 
                   LocalDate dataInicio, LocalDate dataTermino, String formaPagamento, 
                   double valorPago) {
        this.idLocacao = idLocacao;
        this.idFuncionario = idFuncionario;
        this.idCliente = idCliente;
        this.placaVeiculo = placaVeiculo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.formaPagamento = formaPagamento;
        this.valorPago = valorPago;
    }

    // Getters e Setters
    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    // Método para exibir informações da locação
    @Override
    public String toString() {
        return "Locacao{" +
                "idLocacao=" + idLocacao +
                ", idFuncionario=" + idFuncionario +
                ", idCliente=" + idCliente +
                ", placaVeiculo='" + placaVeiculo + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataTermino=" + dataTermino +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", valorPago=" + valorPago +
                '}';
    }
}