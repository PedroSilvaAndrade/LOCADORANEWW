package storage;

import classes.Veiculo;
import java.util.ArrayList;
import java.util.List;

public class VeiculosStorage {

    // Instância única da classe (Singleton)
    private static final VeiculosStorage instance = new VeiculosStorage();

    // Lista de veículos
    private final List<Veiculo> veiculos;

    // Construtor privado para evitar instanciação externa
    private VeiculosStorage() {
        veiculos = new ArrayList<>();

        // Adiciona os veículos padrão
        veiculos.add(new Veiculo("ABC-1234", "Toyota", "Corolla", "Prata", 0.05));
        veiculos.add(new Veiculo("DEF-5678", "Honda", "Civic", "Preto", 0.05));
        veiculos.add(new Veiculo("GHI-9101", "Ford", "Mustang", "Vermelho", 0.06));
        veiculos.add(new Veiculo("JKL-1121", "Chevrolet", "Onix", "Branco", 0.06));
        veiculos.add(new Veiculo("MNO-3141", "Volkswagen", "Golf", "Azul", 0.07));
    }

    // Método para obter a instância única da classe
    public static VeiculosStorage getInstance() {
        return instance;
    }

    // Método para obter a lista de veículos
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    // Método para buscar um veículo pela placa
    public Veiculo getVeiculoPorPlaca(String placaVeiculo) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlacaVeiculo().equals(placaVeiculo)) {
                return veiculo;
            }
        }
        return null; // Retorna null se não encontrar
    }
}