package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Locacoes;
import model.dao.LocacoesDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class HistoricoController {

    @FXML
    private Button BotaoVoltar;

    @FXML
    private TextField txtIDPesquisa;

    @FXML
    private TableView<Locacoes> tbTabela;

    @FXML
    private TableColumn<Locacoes, Integer> colIdLocacao;

    @FXML
    private TableColumn<Locacoes, Integer> colIdCliente;

    @FXML
    private TableColumn<Locacoes, Integer> colIdFuncionario;

    @FXML
    private TableColumn<Locacoes, String> colPlacaVeiculo;

    @FXML
    private TableColumn<Locacoes, LocalDate> colDataInicio;

    @FXML
    private TableColumn<Locacoes, LocalDate> colDataTermino;

    @FXML
    private TableColumn<Locacoes, String> colFormaPagamento;

    @FXML
    private TableColumn<Locacoes, Double> colValorPago;

    @FXML
    private Button btnDelLocacao;

    @FXML
    private Button btnPesquisar;

    private LocacoesDAO locacoesDAO;

    private Stage window;
    private Scene scene;

    @FXML
    void acaoDelLocacao(ActionEvent event) {
        // Obter a locação selecionada na tabela
        Locacoes locacaoSelecionada = tbTabela.getSelectionModel().getSelectedItem();

        if (locacaoSelecionada == null) {
            System.out.println("Nenhuma locação selecionada!");
            return;
        }

        // Deletar a locação do banco de dados
        boolean deletado = locacoesDAO.deletarLocacao(locacaoSelecionada.getIdLocacao());

        if (deletado) {
            System.out.println("Locação deletada com sucesso!");

            // Remover a locação da tabela
            tbTabela.getItems().remove(locacaoSelecionada);
        } else {
            System.out.println("Erro ao deletar locação!");
        }
    }

    @FXML
    public void initialize() {
        // Inicializar o DAO
        locacoesDAO = new LocacoesDAO();

        // Configurar as colunas da tabela
        colIdLocacao.setCellValueFactory(new PropertyValueFactory<>("idLocacao"));
        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colIdFuncionario.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
        colPlacaVeiculo.setCellValueFactory(new PropertyValueFactory<>("placaVeiculo"));
        colDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        colDataTermino.setCellValueFactory(new PropertyValueFactory<>("dataTermino"));
        colFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("formaPagamento"));
        colValorPago.setCellValueFactory(new PropertyValueFactory<>("valorPago"));

        // Carregar todas as locações ao inicializar
        carregarTodasLocacoes();
    }

    private void carregarTodasLocacoes() {
        // Buscar todas as locações do banco de dados
        List<Locacoes> locacoes = locacoesDAO.buscarTodasLocacoes();

        // Adicionar as locações à tabela
        ObservableList<Locacoes> listaLocacoes = FXCollections.observableArrayList(locacoes);
        tbTabela.setItems(listaLocacoes);
    }

    @FXML
    void voltarTelaPrincipal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @FXML
    void acaoPesquisar(ActionEvent event) {
        try {
            // Obter o ID da locação digitado
            String idText = txtIDPesquisa.getText().trim();
            if (idText.isEmpty()) {
                System.out.println("Por favor, digite um ID de locação");
                return;
            }

            int idLocacao = Integer.parseInt(idText);

            // Buscar a locação no banco de dados
            Locacoes locacao = locacoesDAO.buscarLocacaoPorId(idLocacao);

            // Limpar a tabela
            tbTabela.getItems().clear();

            if (locacao != null) {
                // Adicionar a locação encontrada à tabela
                ObservableList<Locacoes> listaLocacoes = FXCollections.observableArrayList(locacao);
                tbTabela.setItems(listaLocacoes);
            } else {
                System.out.println("Locação não encontrada");
            }

        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite um número válido para o ID");
        }
    }
}