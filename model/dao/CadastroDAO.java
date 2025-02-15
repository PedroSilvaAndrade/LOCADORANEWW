package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroDAO {
    // URL do banco de dados, usuário e senha
    private static final String URL = "jdbc:postgresql://localhost:5432/Trabalho";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    // Método atualizado para incluir o status
    public void inserirCadastro(int idLocacao, int id_Funcionario, int id_Cliente, 
                                String placa_veiculo, String forma_pagamento, double valor_pago, 
                                String data_inicio, String data_termino, String status) {
        String sql = "INSERT INTO locacoes (id_locacao, id_funcionario, id_cliente, " +
                     "placa_veiculo, forma_pagamento, valor_pago, data_inicio, data_termino, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Setar parâmetros
            pstmt.setInt(1, idLocacao); // Adiciona o idLocacao como int
            pstmt.setInt(2, id_Funcionario);
            pstmt.setInt(3, id_Cliente);
            pstmt.setString(4, placa_veiculo);
            pstmt.setString(5, forma_pagamento);
            pstmt.setDouble(6, valor_pago); // Adiciona o valor_pago como double
            pstmt.setDate(7, java.sql.Date.valueOf(data_inicio));
            pstmt.setDate(8, java.sql.Date.valueOf(data_termino));
            pstmt.setString(9, status); // Adiciona o status

            pstmt.executeUpdate();
            System.out.println("Cadastro inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}