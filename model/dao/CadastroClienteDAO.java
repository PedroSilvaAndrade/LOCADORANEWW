package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadastroClienteDAO {
    // URL do banco de dados, usuário e senha
    private static final String URL = "jdbc:postgresql://localhost:5432/Trabalho";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    // Método para buscar todos os clientes
    public List<String> buscarTodosClientes() {
        List<String> clientes = new ArrayList<>();
        String sql = "SELECT id_cliente, nome FROM clientes";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Iterar sobre o ResultSet e adicionar os clientes à lista
            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String nomeCliente = rs.getString("nome");
                clientes.add(nomeCliente + ", ID " + idCliente); // Formato: "Nome, ID X"
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    // Método para inserir cadastro do cliente
    public void inserirCliente(int idCliente, String nomeCliente, int idadeCliente, 
                               String sexoCliente, String cnhCliente) {
        String sql = "INSERT INTO clientes (id_cliente, nome, idade, " +
                     "sexo, numero_cnh) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Setar parâmetros
            pstmt.setInt(1, idCliente); // Adiciona o idCliente como int
            pstmt.setString(2, nomeCliente);
            pstmt.setInt(3, idadeCliente);
            pstmt.setString(4, sexoCliente);
            pstmt.setString(5, cnhCliente);

            pstmt.executeUpdate();
            System.out.println("Cadastro do cliente inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}