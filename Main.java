import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
  public static void main(String[] args) {
    // Executamos a chamada da GUI
    launch(args);
  }



   /* 
   *  Nome da Classe: Start
   *  Objetivo: Definir como as paginas vao funcionar e qual sera a primeira a abrir
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      // Colocando FXML em uma variavel que consiguimos converter para scene 
      Parent home = FXMLLoader.load(getClass().getResource("view/TelaPrincipal.fxml"));

      // Estamos trocado o stage que vamos apresentar para o FXML
      primaryStage.setScene(new Scene(home, 800, 495));

      // Apresentamos o stage com o FXML
      primaryStage.setResizable(false);
      primaryStage.show();
      
      // Garante que o programa sera encerrado
      primaryStage.setOnCloseRequest(e -> {System.exit(0);}); 
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}