package model.dao;

import model.Locacoes;
import model.db.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LocacoesDAO {

    // ... Método cadastrarLocacao existente ...

    public Locacoes buscarLocacaoPorId(int idLocacao) {
        String querySQL = "SELECT * FROM Locacoes WHERE id_locacao = ?";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(querySQL)) {
            
            stmt.setInt(1, idLocacao);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Locacoes locacao = new Locacoes();
                    locacao.setIdLocacao(rs.getInt("id_locacao"));
                    locacao.setIdFuncionario(rs.getInt("id_funcionario"));
                    locacao.setIdCliente(rs.getInt("id_cliente"));
                    locacao.setPlacaVeiculo(rs.getString("placa_veiculo"));
                    
                    // Converter java.sql.Date para LocalDate
                    java.sql.Date dataInicio = rs.getDate("data_inicio");
                    java.sql.Date dataTermino = rs.getDate("data_termino");
                    locacao.setDataInicio(dataInicio.toLocalDate());
                    locacao.setDataTermino(dataTermino.toLocalDate());
                    
                    locacao.setFormaPagamento(rs.getString("forma_pagamento"));
                    locacao.setValorPago(rs.getDouble("valor_pago"));
                    
                    return locacao;
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar locação: " + e.getMessage());
        }
        
        return null; // Retorna null se não encontrar
    }
}