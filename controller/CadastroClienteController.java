package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.CadastroClienteDAO;

public class CadastroClienteController {

    @FXML
    private Button BotaoVoltar;

    @FXML
    private TextField TextID_Cliente;

    @FXML
    private TextField TextNome_Cliente;

    @FXML
    private TextField TextIdade_Cliente;

    @FXML
    private TextField TextCNH_Cliente;

    @FXML
    private ChoiceBox<String> cbSexoCliente;

    @FXML
    private Button BotaoConfirmarCadastroCliente;

    private Stage window;
    private Scene scene;

    // ArrayList para armazenar os dados do cliente
    private ArrayList<String> dadosCliente = new ArrayList<>();

    @FXML
    void initialize() {
        // Inicializa o ChoiceBox com as opções de sexo
        cbSexoCliente.getItems().addAll("M", "F");
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
    void salvarDadosCliente(ActionEvent event) {
        CadastroClienteDAO cadastroClienteDAO = new CadastroClienteDAO(); // Instanciando a classe DAO

        try {
            // Coleta dos dados
            int idCliente = Integer.parseInt(TextID_Cliente.getText());
            String nomeCliente = TextNome_Cliente.getText();
            int idadeCliente = Integer.parseInt(TextIdade_Cliente.getText());
            String sexoCliente = cbSexoCliente.getValue(); // Coleta o valor do ChoiceBox
            String cnhCliente = TextCNH_Cliente.getText();

            // Validação dos dados
            if (nomeCliente.isEmpty() || sexoCliente == null || cnhCliente.isEmpty()) {
                System.out.println("Todos os campos devem ser preenchidos!");
                return;
            }

            // Adiciona os dados ao ArrayList
            dadosCliente.add(String.valueOf(idCliente));
            dadosCliente.add(nomeCliente);
            dadosCliente.add(String.valueOf(idadeCliente));
            dadosCliente.add(sexoCliente);
            dadosCliente.add(cnhCliente);

            // Chamar o método DAO para inserir no banco de dados
            cadastroClienteDAO.inserirCliente(idCliente, nomeCliente, idadeCliente, sexoCliente, cnhCliente);

            System.out.println("Dados do cliente salvos com sucesso: " + dadosCliente);

        } catch (NumberFormatException e) {
            System.out.println("Erro: ID e idade devem ser números!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }
}