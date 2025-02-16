package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import storage.LocacaoStorage;

import java.io.IOException;

import classes.Locacao;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HistoricoController {

    @FXML
    private TableColumn<Locacao, Integer> colIdLocacao;

    @FXML
    private Button BotaoVoltar;

    @FXML
    private TableColumn<Locacao, String> colPlacaVeiculo;

    @FXML
    private TableColumn<Locacao, Double> colValorPago;

    @FXML
    private TextField txtIDPesquisa;

    @FXML
    private TableColumn<Locacao, Integer> colIdFuncionario;

    @FXML
    private TableColumn<Locacao, Integer> colIdCliente;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnDelLocacao;

    @FXML
    private TableView<Locacao> tbTabela;

    @FXML
    private TableColumn<Locacao, String> colDataTermino;

    @FXML
    private TableColumn<Locacao, String> colFormaPagamento;

    @FXML
    private TableColumn<Locacao, String> colDataInicio;

    @FXML
    void initialize() {
        // Configura as colunas da tabela
        colIdLocacao.setCellValueFactory(new PropertyValueFactory<>("idLocacao"));
        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colIdFuncionario.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
        colPlacaVeiculo.setCellValueFactory(new PropertyValueFactory<>("placaVeiculo"));
        colDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        colDataTermino.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));
        colFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("formaPagamento"));
        colValorPago.setCellValueFactory(new PropertyValueFactory<>("valorPagar"));

        // Carrega os dados na tabela
        carregarDadosTabela();
    }

    @FXML
    void voltarTelaPrincipal(ActionEvent event) {
        try {
            // Carrega o arquivo FXML da tela principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaPrincipal.fxml"));
            Parent root = loader.load();

            // Obtém o Stage atual a partir do botão clicado
            Stage stage = (Stage) BotaoVoltar.getScene().getWindow();

            // Define a nova cena no Stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a tela principal.");
        }
    }

    @FXML
    void acaoPesquisar(ActionEvent event) {
        String idPesquisa = txtIDPesquisa.getText().trim();
        if (idPesquisa.isEmpty()) {
            // Se o campo de pesquisa estiver vazio, carrega todos os dados
            carregarDadosTabela();
        } else {
            try {
                int id = Integer.parseInt(idPesquisa);
                // Filtra a locação pelo ID
                Locacao locacao = LocacaoStorage.getInstance().getLocacoes().stream()
                    .filter(l -> l.getIdLocacao() == id)
                    .findFirst()
                    .orElse(null);

                if (locacao != null) {
                    // Exibe a locação encontrada na tabela
                    ObservableList<Locacao> locacoes = FXCollections.observableArrayList();
                    locacoes.add(locacao);
                    tbTabela.setItems(locacoes);
                } else {
                    // Exibe uma mensagem se nenhuma locação for encontrada
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Pesquisa");
                    alert.setHeaderText(null);
                    alert.setContentText("Nenhuma locação encontrada com o ID informado.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                // Exibe uma mensagem de erro se o ID não for um número válido
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("O ID deve ser um número válido.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void acaoDelLocacao(ActionEvent event) {
        Locacao locacaoSelecionada = tbTabela.getSelectionModel().getSelectedItem();
        if (locacaoSelecionada != null) {
            // Remove a locação selecionada da lista
            LocacaoStorage.getInstance().getLocacoes().remove(locacaoSelecionada);
            // Recarrega os dados na tabela
            carregarDadosTabela();
        } else {
            // Exibe uma mensagem se nenhuma locação estiver selecionada
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Selecione uma locação para excluir.");
            alert.showAndWait();
        }
    }

    // Método para carregar os dados na tabela
    private void carregarDadosTabela() {
        ObservableList<Locacao> locacoes = FXCollections.observableArrayList(LocacaoStorage.getInstance().getLocacoes());
        tbTabela.setItems(locacoes);
    }
}