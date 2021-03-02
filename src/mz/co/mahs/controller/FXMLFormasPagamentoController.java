package mz.co.mahs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLFormasPagamentoController {
	
	Alert alertInfo = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	Alert alertErro = new Alert(AlertType.ERROR);

    @FXML
    private TextField txtvalorApagar;

    @FXML
    private ComboBox<?> cboFormasPagaemto;

    @FXML
    private Label lblTrocos;

    @FXML
    private Label lblToalApagar;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    private Button btnCllient;

    @FXML
    private Button btnConfirmarPagemento;

    @FXML
    private void clientes(ActionEvent event) {
    	openAllClients();

    }
	
	 //--------------------------------------------------------------------------
    private void openAllClients(){
  	  Stage stage=new Stage();
     try {
         
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mz/co/mahs/views/FXMLAllClients.fxml"));
      Parent root = (Parent) fxmlLoader.load();
     
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
		stage.setScene(scene);
		stage.initStyle(StageStyle.DECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.centerOnScreen();
		 stage.show();
  } catch(Exception e) {
  	alertErro.setHeaderText("Erro");
  	alertErro.setContentText("Erro ao Carregar o Ficheiro "+e);
  	alertErro.showAndWait();
  }
  }

}
