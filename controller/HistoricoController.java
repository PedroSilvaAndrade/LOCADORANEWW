package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.dao.LocacoesDAO;
import model.Locacoes;

import java.sql.SQLException;
import java.util.List;

public class HistoricoController {

    @FXML
    private Button BotaoVoltar;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TableView<Locacoes> tableView; // Tabela para exibir as locações
    
    @FXML
    private TableColumn<Locacoes, Integer> colIdLocacao;
    
    @FXML
    private TableColumn<Locacoes, Integer> colIdFuncionario;

    @FXML
    private TableColumn<Locacoes, Integer> colIdCliente;

    @FXML
    private TableColumn<Locacoes, String> colPlacaVeiculo;

    @FXML
    private TableColumn<Locacoes, String> colDataInicio;

    @FXML
    private TableColumn<Locacoes, String> colDataTermino;

    @FXML
    private TableColumn<Locacoes, String> colFormaPagamento;

    @FXML
    private TableColumn<Locacoes, Double> colValorPago;

    @FXML
    void voltarTelaPrincipal(ActionEvent event) {
        // Adicione a lógica para voltar para a tela principal
    }

    @FXML
    void acaoPesquisar(ActionEvent event) {
        int idLocacao = Integer.parseInt(txtPesquisa.getText()); // Obtém o ID da locação do campo de texto
        LocacoesDAO locacoesDAO = new LocacoesDAO();
        try {
            List<Locacoes> locacoes = locacoesDAO.buscarLocacoesPorId(idLocacao); // Chama o método para buscar locações

            // Limpa a tabela antes de adicionar novos itens
            tableView.getItems().clear();
            tableView.getItems().addAll(locacoes);

        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceções de SQL
        } catch (NumberFormatException e) {
            System.out.println("Por favor, insira um ID de locação válido.");
        }
    }
}