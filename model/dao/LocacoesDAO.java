package model.dao;

import model.Locacoes;
import model.db.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocacoesDAO {

    // Método para deletar uma locação pelo ID
    public boolean deletarLocacao(int idLocacao) {
        String querySQL = "DELETE FROM locacoes WHERE id_locacao = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(querySQL)) {

            stmt.setInt(1, idLocacao);
            int rowsAffected = stmt.executeUpdate();

            // Retorna true se a locação foi deletada com sucesso
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar locação: " + e.getMessage());
            return false;
        }
    }

    // Método para buscar todas as locações
    public List<Locacoes> buscarTodasLocacoes() {
        List<Locacoes> locacoes = new ArrayList<>();
        String querySQL = "SELECT * FROM locacoes";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(querySQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Locacoes locacao = new Locacoes();
                locacao.setIdLocacao(rs.getInt("id_locacao"));
                locacao.setIdFuncionario(rs.getInt("id_funcionario"));
                locacao.setIdCliente(rs.getInt("id_cliente"));
                locacao.setPlacaVeiculo(rs.getString("placa_veiculo"));
                locacao.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                locacao.setDataTermino(rs.getDate("data_termino").toLocalDate());
                locacao.setFormaPagamento(rs.getString("forma_pagamento"));
                locacao.setValorPago(rs.getDouble("valor_pago"));
                locacoes.add(locacao);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar locações: " + e.getMessage());
        }

        return locacoes;
    }

    // Método para buscar uma locação por ID
    public Locacoes buscarLocacaoPorId(int idLocacao) {
        String querySQL = "SELECT * FROM locacoes WHERE id_locacao = ?";
        Locacoes locacao = null;

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(querySQL)) {

            stmt.setInt(1, idLocacao);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                locacao = new Locacoes();
                locacao.setIdLocacao(rs.getInt("id_locacao"));
                locacao.setIdFuncionario(rs.getInt("id_funcionario"));
                locacao.setIdCliente(rs.getInt("id_cliente"));
                locacao.setPlacaVeiculo(rs.getString("placa_veiculo"));
                locacao.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                locacao.setDataTermino(rs.getDate("data_termino").toLocalDate());
                locacao.setFormaPagamento(rs.getString("forma_pagamento"));
                locacao.setValorPago(rs.getDouble("valor_pago"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar locação por ID: " + e.getMessage());
        }

        return locacao;
    }
}