package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class TelaInicialController {

    @FXML
    private Label lblTitulo;

    @FXML
    private MenuBar mnbSelecaoFunc;

    @FXML
    private MenuItem mniSelecFunc1;

    @FXML
    private MenuItem mniSelecFunc2;

    @FXML
    private Menu mnSelecFunc;

    @FXML
    private MenuItem mniSelecFunc3;

    @FXML
    public void irParaTelaPrincipal() {
        try {
            // Carrega o arquivo FXML da TelaPrincipal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaPrincipal.fxml"));
            Parent root = loader.load();

            // Obtém a cena atual a partir do MenuItem
            Scene scene = mniSelecFunc1.getParentPopup().getOwnerWindow().getScene();

            // Define a nova cena na janela atual
            scene.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a TelaPrincipal.fxml");
        }
    }
}