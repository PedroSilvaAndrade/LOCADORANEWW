package model.dao;

import model.Locacoes;
import model.db.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocacoesDAO {
    public void cadastrarLocacao(Locacoes locacao) {
        String querySQL = "INSERT INTO Locacoes(id_funcionario, id_cliente, placa_veiculo, data_inicio, data_termino, forma_pagamento, valor_pago) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(querySQL)) {

            // Definindo os parâmetros da consulta
            stmt.setInt(1, locacao.getIdFuncionario());
            stmt.setInt(2, locacao.getIdCliente());
            stmt.setString(3, locacao.getPlacaVeiculo());
            stmt.setDate(4, java.sql.Date.valueOf(locacao.getDataInicio()));
            stmt.setDate(5, java.sql.Date.valueOf(locacao.getDataTermino()));
            stmt.setString(6, locacao.getFormaPagamento());
            stmt.setDouble(7, locacao.getValorPago());

            // Executando a atualização
            stmt.executeUpdate();
            System.out.println("Locação cadastrada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar locação: " + e.getMessage());
        }
    }
}
