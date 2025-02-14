package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;


public class CadastroController {
    // Puxando as variaveis do FXML para o controller
    @FXML
    private TextField TextID_Funcionario;

    @FXML
    private TextField TextID_Cliente;

    @FXML
    private TextField TextPlaca;

    @FXML
    private TextField TextForma_de_Pagamento;

    @FXML
    private TextField TextValor_Pagar;

    @FXML
    private DatePicker DateDataInicio;

    @FXML
    private DatePicker DateDataFinal;

    @FXML
    private Button BotaoConfirmarCadastro;

    @FXML
    private Button BotaoVoltar;

    private Stage window;
    private Scene scene;

    @FXML
    void voltarTelaPrincipal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml")); 
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}
