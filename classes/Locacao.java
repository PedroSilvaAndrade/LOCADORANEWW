package classes;

import java.time.LocalDate;

public class Locacao {
    private int idLocacao; // Novo atributo
    private int idCliente;
    private int idFuncionario;
    private String placaVeiculo;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private double valorPagar;
    private String formaPagamento;

    // Construtor
    public Locacao(int idLocacao, int idCliente, int idFuncionario, String placaVeiculo, LocalDate dataInicio, LocalDate dataFinal, double valorPagar, String formaPagamento) {
        this.idLocacao = idLocacao;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.placaVeiculo = placaVeiculo;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.valorPagar = valorPagar;
        this.formaPagamento = formaPagamento;
    }

    // Getters e Setters
    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
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

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return "Locacao{" +
                "idLocacao=" + idLocacao +
                ", idCliente=" + idCliente +
                ", idFuncionario=" + idFuncionario +
                ", placaVeiculo='" + placaVeiculo + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFinal=" + dataFinal +
                ", valorPagar=" + valorPagar +
                ", formaPagamento='" + formaPagamento + '\'' +
                '}';
    }
}