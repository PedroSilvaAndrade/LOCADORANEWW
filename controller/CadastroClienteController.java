package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import storage.ClienteStorage;
import classes.Cliente;
import java.io.IOException;

public class CadastroClienteController {

    @FXML
    private Button BotaoVoltar;

    @FXML
    private TextField TextID_Cliente;

    @FXML
    private Label lblAvisoCNH;

    @FXML
    private Label lblAvisoID;

    @FXML
    private TextField TextNome_Cliente;

    @FXML
    private ChoiceBox<String> cbSexoCliente; // Alterado para ChoiceBox<String>

    @FXML
    private TextField TextIdade_Cliente;

    @FXML
    private TextField TextCNH_Cliente;

    @FXML
    private Button BotaoConfirmarCadastroCliente;

    @FXML
    void initialize() {
        // Inicializa o ChoiceBox de sexo com as opções
        ObservableList<String> opcoesSexo = FXCollections.observableArrayList(
            "Masculino", "Feminino", "Outro"
        );
        cbSexoCliente.setItems(opcoesSexo);

        // Limpa as mensagens de erro ao inicializar
        lblAvisoID.setText("");
        lblAvisoCNH.setText("");
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
    void salvarDadosCliente(ActionEvent event) {
        // Limpa as mensagens de erro antes de verificar
        lblAvisoID.setText("");
        lblAvisoCNH.setText("");

        try {
            // Obter os dados dos campos de texto e ChoiceBox
            int id = Integer.parseInt(TextID_Cliente.getText()); // Converte o texto para inteiro
            String nome = TextNome_Cliente.getText();
            String sexo = cbSexoCliente.getValue(); // Obtém o valor selecionado no ChoiceBox
            int idade = Integer.parseInt(TextIdade_Cliente.getText()); // Converte o texto para inteiro
            String cnh = TextCNH_Cliente.getText();

            // Verifica se o ID já existe
            if (ClienteStorage.getInstance().idExiste(id)) {
                lblAvisoID.setText("Erro: Já existe um cliente com este ID.");
                return; // Interrompe o salvamento
            }

            // Verifica se o CNH já existe
            if (ClienteStorage.getInstance().cnhExiste(cnh)) {
                lblAvisoCNH.setText("Erro: Já existe um cliente com este CNH.");
                return; // Interrompe o salvamento
            }

            // Criar um objeto Cliente
            Cliente cliente = new Cliente(id, nome, sexo, idade, cnh);

            // Armazenar o cliente na ClienteStorage
            ClienteStorage.getInstance().adicionarCliente(cliente);

            // Limpar os campos após salvar
            TextID_Cliente.clear();
            TextNome_Cliente.clear();
            cbSexoCliente.getSelectionModel().clearSelection();
            TextIdade_Cliente.clear();
            TextCNH_Cliente.clear();

            System.out.println("Cliente salvo: " + cliente);
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID ou idade inválidos. Certifique-se de que são números.");
        }
    }
}