package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import storage.VeiculosStorage;
import storage.FuncionarioStorage;
import storage.ClienteStorage;
import storage.LocacaoStorage;
import classes.Locacao;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CadastroController {

    @FXML
    private ChoiceBox<String> cbFormaPagamento;

    @FXML
    private Button BotaoVoltar;

    @FXML
    private DatePicker DateDataFinal;

    @FXML
    private Label lblValorReal;

    @FXML
    private DatePicker DateDataInicio;

    @FXML
    private ChoiceBox<Integer> cbClienteID;

    @FXML
    private TextField TextValor_Pagar;

    @FXML
    private ChoiceBox<String> cbFuncionarioID;

    @FXML
    private Button BotaoConfirmarCadastro;

    @FXML
    private ChoiceBox<String> cbPlacaVeiculo;

    @FXML
    private Label lblStatus;

    @FXML
    private Label idLocacao;

    private int idLocacaoGerado; // Variável para armazenar o ID gerado

    @FXML
    void initialize() {
        // Gera um número aleatório para o ID da locação
        idLocacaoGerado = gerarIdAleatorio();
        idLocacao.setText(String.valueOf(idLocacaoGerado));

        // Inicializa o ChoiceBox com as opções de forma de pagamento
        ObservableList<String> formasPagamento = FXCollections.observableArrayList(
            "Cartão de Crédito", "Dinheiro", "PIX", "Cartão de Débito"
        );
        cbFormaPagamento.setItems(formasPagamento);

        // Inicializa o ChoiceBox de funcionários
        FuncionarioStorage funcionarioStorage = FuncionarioStorage.getInstance();
        List<String> nomesFuncionarios = funcionarioStorage.getFuncionarios().values().stream()
            .collect(Collectors.toList());

        ObservableList<String> funcionariosObservableList = FXCollections.observableArrayList(nomesFuncionarios);
        cbFuncionarioID.setItems(funcionariosObservableList);

        // Configura um listener para o ChoiceBox de funcionários
        cbFuncionarioID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                atualizarPlacasVeiculos(newValue);
            }
        });

        // Inicializa o ChoiceBox de IDs de clientes
        ClienteStorage clienteStorage = ClienteStorage.getInstance();
        List<Integer> idsClientes = clienteStorage.getClientes().stream()
            .map(cliente -> cliente.getId())
            .collect(Collectors.toList());

        ObservableList<Integer> idsObservableList = FXCollections.observableArrayList(idsClientes);
        cbClienteID.setItems(idsObservableList);

        // Configura um listener para o ChoiceBox de placas de veículos
        cbPlacaVeiculo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                calcularValorReal(newValue);
                verificarDisponibilidadeVeiculo(); // Atualiza o status ao mudar a placa
            }
        });

        // Configura listeners para os DatePickers
        DateDataInicio.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && cbPlacaVeiculo.getValue() != null) {
                verificarDisponibilidadeVeiculo(); // Atualiza o status ao mudar a data de início
            }
        });

        DateDataFinal.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && cbPlacaVeiculo.getValue() != null) {
                verificarDisponibilidadeVeiculo(); // Atualiza o status ao mudar a data final
            }
        });

        // Adiciona um listener ao TextValor_Pagar para atualizar o lblValorReal
        TextValor_Pagar.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty() && cbPlacaVeiculo.getValue() != null) {
                calcularValorReal(cbPlacaVeiculo.getValue());
            } else {
                lblValorReal.setText("R$ 0.00");
            }
        });
    }

    // Método para atualizar as placas de veículos com base no funcionário selecionado
    private void atualizarPlacasVeiculos(String funcionario) {
        VeiculosStorage veiculosStorage = VeiculosStorage.getInstance();
        ObservableList<String> placasObservableList;

        switch (funcionario) {
            case "Pedro, ID 1":
                placasObservableList = FXCollections.observableArrayList("ABC-1234", "DEF-5678");
                break;
            case "Rafael, ID 2":
                placasObservableList = FXCollections.observableArrayList("GHI-9101", "JKL-1121");
                break;
            case "Davi, ID 3":
                placasObservableList = FXCollections.observableArrayList("MNO-3141");
                break;
            default:
                placasObservableList = FXCollections.observableArrayList();
                break;
        }

        cbPlacaVeiculo.setItems(placasObservableList);
    }

    // Método para calcular o valor real com base na placa do veículo
    private void calcularValorReal(String placaVeiculo) {
        try {
            double valorPagar = Double.parseDouble(TextValor_Pagar.getText());

            // Verifica se a placa do veículo é "ABC-1234" ou "DEF-5678" (5% de tarifa)
            if (placaVeiculo.equals("ABC-1234") || placaVeiculo.equals("DEF-5678")) {
                valorPagar *= 1.05; // Acrescenta 5%
            }
            // Verifica se a placa do veículo é "GHI-9101" ou "JKL-1121" (6% de tarifa)
            else if (placaVeiculo.equals("GHI-9101") || placaVeiculo.equals("JKL-1121")) {
                valorPagar *= 1.06; // Acrescenta 6%
            }
            // Verifica se a placa do veículo é "MNO-3141" (7% de tarifa)
            else if (placaVeiculo.equals("MNO-3141")) {
                valorPagar *= 1.07; // Acrescenta 7%
            }

            // Exibe o valor real na label
            lblValorReal.setText(String.format("R$ %.2f", valorPagar));
        } catch (NumberFormatException e) {
            // Exibe uma mensagem de erro se o valor não for um número válido
            lblValorReal.setText("Valor inválido");
        }
    }

    // Método para verificar a disponibilidade do veículo
    private void verificarDisponibilidadeVeiculo() {
        String placaVeiculo = cbPlacaVeiculo.getValue();
        LocalDate dataInicio = DateDataInicio.getValue();
        LocalDate dataFinal = DateDataFinal.getValue();

        if (placaVeiculo != null && dataInicio != null && dataFinal != null) {
            boolean disponivel = LocacaoStorage.getInstance().getLocacoes().stream()
                .noneMatch(locacao -> locacao.getPlacaVeiculo().equals(placaVeiculo)
                    && !dataInicio.isAfter(locacao.getDataFinal())
                    && !dataFinal.isBefore(locacao.getDataInicio()));

            if (disponivel) {
                lblStatus.setText("Disponível");
            } else {
                lblStatus.setText("Indisponível");
            }
        } else {
            lblStatus.setText(" ");
        }
    }

    // Método para gerar um ID aleatório entre 1 e 1000
    private int gerarIdAleatorio() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
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
            lblStatus.setText("Erro ao carregar a tela principal.");
        }
    }

    @FXML
    void salvarDados(ActionEvent event) {
        // Obter os dados dos campos de texto, ChoiceBox e DatePicker
        int idCliente = cbClienteID.getValue(); // Obtém o ID do cliente selecionado
        int idFuncionario = cbFuncionarioID.getSelectionModel().getSelectedIndex() + 1; // Obtém o ID do funcionário (assumindo que os IDs começam em 1)
        String placaVeiculo = cbPlacaVeiculo.getValue(); // Obtém a placa do veículo selecionado
        LocalDate dataInicio = DateDataInicio.getValue(); // Obtém a data de início
        LocalDate dataFinal = DateDataFinal.getValue(); // Obtém a data final
        double valorPagar = Double.parseDouble(TextValor_Pagar.getText()); // Converte o texto para double
        String formaPagamento = cbFormaPagamento.getValue(); // Obtém a forma de pagamento selecionada

        // Verifica se o veículo está disponível
        boolean disponivel = LocacaoStorage.getInstance().getLocacoes().stream()
            .noneMatch(locacao -> locacao.getPlacaVeiculo().equals(placaVeiculo)
                && !dataInicio.isAfter(locacao.getDataFinal())
                && !dataFinal.isBefore(locacao.getDataInicio()));

        if (!disponivel) {
            lblStatus.setText("Indisponível");
            return; // Não prossegue com o salvamento
        }

        // Verifica se a placa do veículo é "ABC-1234" ou "DEF-5678" (5% de tarifa)
        if (placaVeiculo.equals("ABC-1234") || placaVeiculo.equals("DEF-5678")) {
            valorPagar *= 1.05; // Acrescenta 5%
        }
        // Verifica se a placa do veículo é "GHI-9101" ou "JKL-1121" (6% de tarifa)
        else if (placaVeiculo.equals("GHI-9101") || placaVeiculo.equals("JKL-1121")) {
            valorPagar *= 1.06; // Acrescenta 6%
        }
        // Verifica se a placa do veículo é "MNO-3141" (7% de tarifa)
        else if (placaVeiculo.equals("MNO-3141")) {
            valorPagar *= 1.07; // Acrescenta 7%
        }

        // Criar um objeto Locacao com o ID gerado
        Locacao locacao = new Locacao(idLocacaoGerado, idCliente, idFuncionario, placaVeiculo, dataInicio, dataFinal, valorPagar, formaPagamento);

        // Armazenar a locação na LocacaoStorage
        LocacaoStorage.getInstance().adicionarLocacao(locacao);

        // Limpar os campos após salvar
        cbClienteID.getSelectionModel().clearSelection();
        cbFuncionarioID.getSelectionModel().clearSelection();
        cbPlacaVeiculo.getSelectionModel().clearSelection();
        DateDataInicio.setValue(null);
        DateDataFinal.setValue(null);
        TextValor_Pagar.clear();
        cbFormaPagamento.getSelectionModel().clearSelection();

        // Gera um novo ID aleatório para a próxima locação
        idLocacaoGerado = gerarIdAleatorio();
        idLocacao.setText(String.valueOf(idLocacaoGerado));

        System.out.println("Locação salva: " + locacao);
    }
}