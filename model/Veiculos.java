package model;

public class Veiculos {
    private String placa;
    private String fabricante;
    private String modelo;
    private String cor;
    private int numero_matricula;
    private double tarifa;

    public Veiculos(String placa, String fabricante, String modelo, String cor, int numero_matricula, double tarifa) {
        this.placa = placa;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.cor = cor;
        this.numero_matricula = numero_matricula;
        this.tarifa = tarifa;
    }
}
