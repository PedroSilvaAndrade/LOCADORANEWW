package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane; // Importar TabPane
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class TelaPrincipalController {

    // Componentes da primeira versão
    @FXML
    private Label lblCNH;

    @FXML
    private TextField txtDataTer;

    @FXML
    private TextField txtDataIn;

    @FXML
    private TextField txtNome;

    @FXML
    private Tab tabCadastro; // Tab de Cadastro

    @FXML
    private Label lblSexo;

    @FXML
    private Label lblDataTer;

    @FXML
    private Label lblDataIn;

    @FXML
    private TextField txtIdade;

    @FXML
    private Button btnHistorico;

    @FXML
    private TextField txtCNH;

    @FXML
    private Label lblIdade;

    @FXML
    private Tab tabInicio;

    @FXML
    private Tab tabHistorico;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtSexo;

    @FXML
    private Button btnCadastramento;

    @FXML
    private Label lblNome;

    @FXML
    private TabPane tabPane; // Adicione esta linha para o TabPane

    // Métodos da primeira versão
    @FXML
    void irParaCadastro(ActionEvent event) {
        // Lógica para ir para a aba de cadastro
        if (tabPane != null && tabCadastro != null) {
            tabPane.getSelectionModel().select(tabCadastro); // Seleciona a aba de cadastro
            System.out.println("Ir para Cadastro");
        } else {
            System.out.println("tabPane ou tabCadastro é null");
        }
    }

    @FXML
    void irParaTelaInicial(ActionEvent event) {
        try {
            // Carregar a nova tela
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaInicial.fxml")); // Ajuste o caminho
            Stage stage = (Stage) btnVoltar.getScene().getWindow(); // Obtém a janela atual
            stage.setScene(new Scene(root)); // Define a nova cena
            stage.show(); // Mostra a nova cena
        } catch (Exception e) {
            e.printStackTrace(); // Imprime a pilha de erros se algo der errado
        }
    }

    // Método de inicialização
    @FXML
    public void initialize() {
        // Adiciona o evento de ação ao botão btnVoltar
        btnVoltar.setOnAction(this::irParaTelaInicial);

        // Adiciona o evento de ação ao botão btnCadastramento
        btnCadastramento.setOnAction(this::irParaCadastro);
    }
}