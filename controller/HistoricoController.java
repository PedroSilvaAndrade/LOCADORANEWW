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
    private Button btnPesquisar;
    
    private LocacoesDAO locacoesDAO;

    private Stage window;
    private Scene scene;
    
    @FXML
public void initialize() {
    // Inicializar o DAO
    locacoesDAO = new LocacoesDAO();
    
    // Verificar e configurar as colunas da tabela
    if (colIdLocacao != null) {
        colIdLocacao.setCellValueFactory(new PropertyValueFactory<>("idLocacao"));
    }
    
    if (colIdCliente != null) {
        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
    }
    
    if (colIdFuncionario != null) {
        colIdFuncionario.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
    }
    
    if (colPlacaVeiculo != null) {
        colPlacaVeiculo.setCellValueFactory(new PropertyValueFactory<>("placaVeiculo"));
    }
    
    if (colDataInicio != null) {
        colDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
    }
    
    if (colDataTermino != null) {
        colDataTermino.setCellValueFactory(new PropertyValueFactory<>("dataTermino"));
    }
    
    if (colFormaPagamento != null) {
        colFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("formaPagamento"));
    }
    
    if (colValorPago != null) {
        colValorPago.setCellValueFactory(new PropertyValueFactory<>("valorPago"));
    }
}

    @FXML
    void voltarTelaPrincipal(ActionEvent event) throws IOException{
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