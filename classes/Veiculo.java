package classes;

public class Veiculo {
    private String placaVeiculo;
    private String fabricante;
    private String modelo;
    private String cor;
    private double tarifa;

    // Construtor
    public Veiculo(String placaVeiculo, String fabricante, String modelo, String cor, double tarifa) {
        this.placaVeiculo = placaVeiculo;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.cor = cor;
        this.tarifa = tarifa;
    }

    // Getters
    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public double getTarifa() {
        return tarifa;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placaVeiculo='" + placaVeiculo + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cor='" + cor + '\'' +
                ", tarifa=" + tarifa +
                '}';
    }
}