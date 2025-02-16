package storage;

import classes.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteStorage {

    // Instância única da classe (Singleton)
    private static final ClienteStorage instance = new ClienteStorage();

    // Lista de clientes
    private final List<Cliente> clientes;

    // Construtor privado para evitar instanciação externa
    private ClienteStorage() {
        clientes = new ArrayList<>();
    }

    // Método para obter a instância única da classe
    public static ClienteStorage getInstance() {
        return instance;
    }

    // Método para verificar se um ID já existe
    private boolean idExiste(int id) {
        return clientes.stream().anyMatch(cliente -> cliente.getId() == id);
    }

    // Método para verificar se um CNH já existe
    private boolean cnhExiste(String cnh) {
        return clientes.stream().anyMatch(cliente -> cliente.getCnh().equals(cnh));
    }

    // Método para adicionar um cliente
    public void adicionarCliente(Cliente cliente) throws IllegalArgumentException {
        // Verifica se o ID já existe
        if (idExiste(cliente.getId())) {
            throw new IllegalArgumentException("Já existe um cliente com o ID " + cliente.getId() + ".");
        }

        // Verifica se o CNH já existe
        if (cnhExiste(cliente.getCnh())) {
            throw new IllegalArgumentException("Já existe um cliente com o CNH " + cliente.getCnh() + ".");
        }

        // Adiciona o cliente à lista
        clientes.add(cliente);
    }

    // Método para obter a lista de clientes
    public List<Cliente> getClientes() {
        return clientes;
    }
}