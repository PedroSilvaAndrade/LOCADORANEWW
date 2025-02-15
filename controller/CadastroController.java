package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.dao.CadastroDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CadastroController {

    @FXML
    private ChoiceBox<String> cbFuncionarioID;

    @FXML
    private ChoiceBox<String> cbClienteID;

    @FXML
    private ChoiceBox<String> cbPlacaVeiculo;

    @FXML
    private ChoiceBox<String> cbFormaPagamento;

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

    @FXML
    private Label lblStatus;

    @FXML
    private Label idLocacao;

    private Stage window;
    private Scene scene;
    private List<String> dadosFormulario = new ArrayList<>();

    @FXML
    private void initialize() {
        // Define o valor padrão do lblStatus como "Disponível"
        lblStatus.setText("Disponível");
        // Gera e define um ID de locação aleatório
        setRandomIdLocacao();

        // Inicializa as ChoiceBox com as opções
        cbFuncionarioID.getItems().addAll("Pedro, ID 1", "Rafael, ID 2", "Davi, ID 3");
        cbClienteID.getItems().addAll("ID 1", "ID 2", "ID 3");
        cbFormaPagamento.getItems().addAll("Cartão", "Dinheiro", "Transferência", "PIX");
        cbPlacaVeiculo.getItems().addAll("ABC1D23", "DEF4G56", "GHI7H89", "JKL0I12", "MNO3P45");
    }

    private void setRandomIdLocacao() {
        Random random = new Random();
        int idAleatorio = random.nextInt(1000) + 1; // Gera um número aleatório de 1 a 1000
        idLocacao.setText(String.valueOf(idAleatorio)); // Exibe o ID aleatório no Label
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
    void salvarDados(ActionEvent event) {
        try {
            // Coletar e converter dados dos ChoiceBox
            String funcionarioSelecionado = cbFuncionarioID.getValue();
            String clienteSelecionado = cbClienteID.getValue();
            String placaSelecionada = cbPlacaVeiculo.getValue();
            String formaPagamentoSelecionada = cbFormaPagamento.getValue();

            // Extrair IDs dos valores selecionados
            int idFuncionario = Integer.parseInt(funcionarioSelecionado.split(", ID ")[1]);
            int idCliente = Integer.parseInt(clienteSelecionado.split("ID ")[1]);
            String placa = placaSelecionada;
            String formaPagamento = formaPagamentoSelecionada;

            // Coletar outros dados
            double valorPagar = Double.parseDouble(TextValor_Pagar.getText());
            LocalDate dataInicio = DateDataInicio.getValue();
            LocalDate dataFinal = DateDataFinal.getValue();
            int idLocacaoValue = Integer.parseInt(idLocacao.getText());

            // Validar campos
            if (dataInicio == null || dataFinal == null) {
                System.out.println("Preencha as datas corretamente!");
                return;
            }

            // Verificar se a forma de pagamento é válida
            List<String> formasPagamentosPermitidas = Arrays.asList("Cartão", "Dinheiro", "Transferência", "PIX");
            if (!formasPagamentosPermitidas.contains(formaPagamento)) {
                System.out.println("Forma de pagamento inválida! Valores permitidos: " + formasPagamentosPermitidas);
                return;
            }

            // Verificar disponibilidade do veículo
            CadastroDAO dao = new CadastroDAO();
            boolean veiculoDisponivel = dao.verificarDisponibilidadeVeiculo(placa, dataInicio, dataFinal);

            if (!veiculoDisponivel) {
                System.out.println("Veículo indisponível no período selecionado!");
                lblStatus.setText("Indisponível");
                return; // Cancela a locação
            }

            // Definir o status como "Disponivel" por padrão
            String status = "Disponivel"; // Valor padrão

            // Adicionar ao ArrayList (como String)
            dadosFormulario.add(String.valueOf(idLocacaoValue));
            dadosFormulario.add(String.valueOf(idFuncionario));
            dadosFormulario.add(String.valueOf(idCliente));
            dadosFormulario.add(placa);
            dadosFormulario.add(formaPagamento);
            dadosFormulario.add(String.valueOf(valorPagar));
            dadosFormulario.add(dataInicio.toString());
            dadosFormulario.add(dataFinal.toString());
            dadosFormulario.add(status); // Adiciona o status como String

            // Salvar no banco
            dao.inserirCadastro(
                idLocacaoValue,
                idFuncionario,
                idCliente,
                placa,
                formaPagamento,
                valorPagar,
                dataInicio.toString(),
                dataFinal.toString(),
                status // Passa o status
            );

            System.out.println("Dados salvos!");

        } catch (NumberFormatException e) {
            System.out.println("Erro: IDs ou valor devem ser números!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }
}