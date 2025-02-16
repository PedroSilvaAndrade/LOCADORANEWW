package storage;

import java.util.HashMap;
import java.util.Map;

public class FuncionarioStorage {

    // Instância única da classe (Singleton)
    private static final FuncionarioStorage instance = new FuncionarioStorage();

    // Mapa para armazenar os funcionários (ID -> Nome)
    private final Map<Integer, String> funcionarios;

    // Construtor privado para evitar instanciação externa
    private FuncionarioStorage() {
        funcionarios = new HashMap<>();

        // Adiciona os funcionários padrão
        funcionarios.put(1, "Pedro, ID 1");
        funcionarios.put(2, "Rafael, ID 2");
        funcionarios.put(3, "Davi, ID 3");
    }

    // Método para obter a instância única da classe
    public static FuncionarioStorage getInstance() {
        return instance;
    }

    // Método para obter a lista de funcionários
    public Map<Integer, String> getFuncionarios() {
        return funcionarios;
    }

    // Método para obter o nome de um funcionário pelo ID
    public String getNomeFuncionario(int id) {
        return funcionarios.get(id);
    }
}