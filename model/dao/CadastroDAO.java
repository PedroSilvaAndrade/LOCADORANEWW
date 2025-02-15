package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CadastroDAO {
    // URL do banco de dados, usuário e senha
    private static final String URL = "jdbc:postgresql://localhost:5432/Trabalho";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    // Método para verificar a disponibilidade do veículo
    public boolean verificarDisponibilidadeVeiculo(String placaVeiculo, LocalDate dataInicio, LocalDate dataTermino) {
        String sql = "SELECT COUNT(*) FROM locacoes WHERE placa_veiculo = ? AND " +
                     "((data_inicio BETWEEN ? AND ?) OR (data_termino BETWEEN ? AND ?))";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Setar parâmetros
            pstmt.setString(1, placaVeiculo);
            pstmt.setDate(2, java.sql.Date.valueOf(dataInicio));
            pstmt.setDate(3, java.sql.Date.valueOf(dataTermino));
            pstmt.setDate(4, java.sql.Date.valueOf(dataInicio));
            pstmt.setDate(5, java.sql.Date.valueOf(dataTermino));

            // Executar a consulta
            ResultSet rs = pstmt.executeQuery();

            // Verificar se há locações no período
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0; // Retorna true se o veículo estiver disponível
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Retorna false em caso de erro
    }

    // Método para inserir cadastro
    public void inserirCadastro(int idLocacao, int idFuncionario, int idCliente, 
                                String placaVeiculo, String formaPagamento, double valorPago, 
                                String dataInicio, String dataTermino, String status) {
        String sql = "INSERT INTO locacoes (id_locacao, id_funcionario, id_cliente, " +
                     "placa_veiculo, forma_pagamento, valor_pago, data_inicio, data_termino, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Setar parâmetros
            pstmt.setInt(1, idLocacao);
            pstmt.setInt(2, idFuncionario);
            pstmt.setInt(3, idCliente);
            pstmt.setString(4, placaVeiculo);
            pstmt.setString(5, formaPagamento);
            pstmt.setDouble(6, valorPago);
            pstmt.setDate(7, java.sql.Date.valueOf(dataInicio));
            pstmt.setDate(8, java.sql.Date.valueOf(dataTermino));
            pstmt.setString(9, status);

            pstmt.executeUpdate();
            System.out.println("Cadastro inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}