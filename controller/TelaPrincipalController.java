package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import storage.LocacaoStorage;
import classes.Locacao;

import java.io.IOException;
import java.util.List;

public class TelaPrincipalController {

    @FXML
    private Button BotaoCadastro;

    @FXML
    private Label lblMediaPIX;

    @FXML
    private Label lblValRafael;

    @FXML
    private Label lblMediaDeb;

    @FXML
    private Label lblMediaCred;

    @FXML
    private Label lblValDavi;

    @FXML
    private Label lblValPedro;

    @FXML
    private Label lblMediaDin;

    @FXML
    private Button BotaoCadastroCliente;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnHistorico;

    private Stage stage; // Janela atual
    private Scene scene; // Cena atual

    @FXML
    void initialize() {
        // Atualiza os valores médios dos funcionários ao inicializar a tela
        atualizarMediasFuncionarios();

        // Atualiza as porcentagens das formas de pagamento ao inicializar a tela
        atualizarPorcentagensFormasPagamento();
    }

    // Método para calcular a média das locações de um funcionário
    private double calcularMediaPorFuncionario(int idFuncionario) {
        List<Locacao> locacoes = LocacaoStorage.getInstance().getLocacoes();
        double soma = 0;
        int count = 0;

        for (Locacao locacao : locacoes) {
            if (locacao.getIdFuncionario() == idFuncionario) {
                soma += locacao.getValorPagar();
                count++;
            }
        }

        // Retorna a média se houver locações, caso contrário, retorna 0
        return count > 0 ? soma / count : 0;
    }

    // Método para atualizar os labels com as médias dos funcionários
    private void atualizarMediasFuncionarios() {
        // Média para Pedro (ID 1)
        double mediaPedro = calcularMediaPorFuncionario(1);
        lblValPedro.setText(String.format("R$ %.2f", mediaPedro));

        // Média para Rafael (ID 2)
        double mediaRafael = calcularMediaPorFuncionario(2);
        lblValRafael.setText(String.format("R$ %.2f", mediaRafael));

        // Média para Davi (ID 3)
        double mediaDavi = calcularMediaPorFuncionario(3);
        lblValDavi.setText(String.format("R$ %.2f", mediaDavi));
    }

    // Método para calcular a porcentagem de cada forma de pagamento
    private void atualizarPorcentagensFormasPagamento() {
        List<Locacao> locacoes = LocacaoStorage.getInstance().getLocacoes();
        int totalLocacoes = locacoes.size();

        if (totalLocacoes == 0) {
            // Se não houver locações, define todas as porcentagens como 0%
            lblMediaCred.setText("0%");
            lblMediaDeb.setText("0%");
            lblMediaPIX.setText("0%");
            lblMediaDin.setText("0%");
            return;
        }

        // Contadores para cada forma de pagamento
        int countCredito = 0;
        int countDebito = 0;
        int countPIX = 0;
        int countDinheiro = 0;

        for (Locacao locacao : locacoes) {
            switch (locacao.getFormaPagamento()) {
                case "Cartão de Crédito":
                    countCredito++;
                    break;
                case "Cartão de Débito":
                    countDebito++;
                    break;
                case "PIX":
                    countPIX++;
                    break;
                case "Dinheiro":
                    countDinheiro++;
                    break;
            }
        }

        // Calcula as porcentagens
        double porcentagemCredito = (double) countCredito / totalLocacoes * 100;
        double porcentagemDebito = (double) countDebito / totalLocacoes * 100;
        double porcentagemPIX = (double) countPIX / totalLocacoes * 100;
        double porcentagemDinheiro = (double) countDinheiro / totalLocacoes * 100;

        // Atualiza os labels com as porcentagens formatadas
        lblMediaCred.setText(String.format("%.1f%%", porcentagemCredito));
        lblMediaDeb.setText(String.format("%.1f%%", porcentagemDebito));
        lblMediaPIX.setText(String.format("%.1f%%", porcentagemPIX));
        lblMediaDin.setText(String.format("%.1f%%", porcentagemDinheiro));
    }

    @FXML
    void irParaCadastro(ActionEvent event) {
        try {
            // Carrega o arquivo FXML da tela de cadastro
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaCadastro.fxml"));

            // Obtém a janela atual (stage) a partir do evento
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Cria uma nova cena com o conteúdo carregado do FXML
            scene = new Scene(root);

            // Define a nova cena na janela atual
            stage.setScene(scene);

            // Exibe a nova cena
            stage.show();
        } catch (IOException e) {
            System.out.println("Erro ao carregar a tela de cadastro: " + e.getMessage());
        }
    }

    @FXML
    void irParaHistorico(ActionEvent event) {
        try {
            // Carrega o arquivo FXML da tela de histórico
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaHistorico.fxml"));

            // Obtém a janela atual (stage) a partir do evento
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Cria uma nova cena com o conteúdo carregado do FXML
            scene = new Scene(root);

            // Define a nova cena na janela atual
            stage.setScene(scene);

            // Exibe a nova cena
            stage.show();
        } catch (IOException e) {
            System.out.println("Erro ao carregar a tela de histórico: " + e.getMessage());
        }
    }

    @FXML
    void irParaCadastroCliente(ActionEvent event) {
        try {
            // Carrega o arquivo FXML da tela de cadastro de cliente
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaCadastroCliente.fxml"));

            // Obtém a janela atual (stage) a partir do evento
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Cria uma nova cena com o conteúdo carregado do FXML
            scene = new Scene(root);

            // Define a nova cena na janela atual
            stage.setScene(scene);

            // Exibe a nova cena
            stage.show();
        } catch (IOException e) {
            System.out.println("Erro ao carregar a tela de cadastro de cliente: " + e.getMessage());
        }
    }

    @FXML
    void sair(ActionEvent event) {
        // Obtém a janela atual (stage) a partir do evento
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Fecha a janela (sai do aplicativo)
        stage.close();
    }
}