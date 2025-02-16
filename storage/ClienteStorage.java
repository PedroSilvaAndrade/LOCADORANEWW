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
    public boolean idExiste(int id) {
        return clientes.stream().anyMatch(cliente -> cliente.getId() == id);
    }

    // Método para verificar se um CNH já existe
    public boolean cnhExiste(String cnh) {
        return clientes.stream().anyMatch(cliente -> cliente.getCnh().equals(cnh));
    }

    // Método para adicionar um cliente
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    // Método para obter a lista de clientes
    public List<Cliente> getClientes() {
        return clientes;
    }
}