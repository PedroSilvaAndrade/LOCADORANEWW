package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;



public class TelaPrincipalController {
    // Puxando as variaveis do FXML para o controller
    @FXML
    private Button BotaoCadrasto;

    @FXML
    private Button btnHistorico;

    @FXML
    private Button BotaoCadastroCliente;

    @FXML
    private Button btnSair;

    @FXML
    void sair(ActionEvent event) {
        // Obtém a janela atual (stage) e fecha
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close(); // Fecha a aplicação
    }
    
    private Stage window;
    private Scene scene;

    @FXML
    void irParaCadastro(ActionEvent event) throws IOException {
        // Carrega o arquivo FXML da tela de cadastro
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaCadastro.fxml"));
    
        // Obtém a janela atual (stage) e configura a nova cena
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
    
        // Define a nova cena e exibe
        window.setScene(scene);
        window.show();
    }

    @FXML
    void irParaHistorico(ActionEvent event) throws IOException {
            // Carrega o arquivo FXML da tela de cadastro
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaHistorico.fxml"));
        
            // Obtém a janela atual (stage) e configura a nova cena
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
        
            // Define a nova cena e exibe
            window.setScene(scene);
            window.show();

    }

    @FXML
    void irParaCadastroCliente(ActionEvent event) throws IOException {
        // Carrega o arquivo FXML da tela de cadastro
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaCadastroCliente.fxml"));
    
        // Obtém a janela atual (stage) e configura a nova cena
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
    
        // Define a nova cena e exibe
        window.setScene(scene);
        window.show();
        
    }
}