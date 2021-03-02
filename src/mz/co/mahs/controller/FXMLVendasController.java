package mz.co.mahs.controller;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLVendasController {
	Alert alertInfo = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	Alert alertErro = new Alert(AlertType.ERROR);
	  @FXML
	    private AnchorPane anchorPaneVendas;
	 @FXML
	    private Pane paneProducto;

	    @FXML
	    private TextField txtProcurar;

	    @FXML
	    private TableView<?> tblProducto;

	    @FXML
	    private TableColumn<?, ?> colId;

	    @FXML
	    private TableColumn<?, ?> colNome;

	    @FXML
	    private TableColumn<?, ?> colCategoria;

	    @FXML
	    private TableColumn<?, ?> colQty;

	    @FXML
	    private TableColumn<?, ?> colPreco;

	    @FXML
	    private TableColumn<?, ?> colValidade;

	    @FXML
	    private Pane paneCarrinha;

	    @FXML
	    private TextField txtPorductoNome;

	    @FXML
	    private TextField txtPreco;

	    @FXML
	    private TextField txtQty;

	    @FXML
	    private TextField txtSutotal;

	    @FXML
	    private TableView<?> tblItems;

	    @FXML
	    private TableColumn<?, ?> calSubTotal;

	    @FXML
	    private Button btnAdicionar;

	    @FXML
	    private Button btnRemover;

	    @FXML
	    private Label lbValorComlIVA;

	    @FXML
	    private Label lblValorSemIVA;

	    @FXML
	    private Label lblIVA;

	    @FXML
	    private Button btnFinalizar;

	    @FXML
	    private Pane topVendasPane;

	    @FXML
	    private void finalizar(ActionEvent event) {
	    	openFormasPagamento();
	    	/*
	    	alertErro.setHeaderText("PRESSIONADO COM EXITO");
	      	alertErro.setContentText("ESTOU FUNCIONANDO! ");
	      	alertErro.showAndWait();
	    	*/
	    

	    }

	  //--------------------------------------------------------------------------
        private void openFormasPagamento(){
      	  Stage stage=new Stage();
         try {
             
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mz/co/mahs/views/FXMLFormasPagamento.fxml"));
          Parent root = (Parent) fxmlLoader.load();
         
  		Scene scene = new Scene(root);
  		scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
  		stage.setScene(scene);
  		stage.initStyle(StageStyle.DECORATED);
  		stage.initModality(Modality.APPLICATION_MODAL);
  		stage.centerOnScreen();
  		//stage.setWidth(800);
        //stage.setHeight(500);
  		//menuPane.setCenter(root);
  		anchorPaneVendas.setCenterShape(true);// teste
  		//menuPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
  		 stage.show();
      } catch(Exception e) {
      	alertErro.setHeaderText("Erro");
      	alertErro.setContentText("Erro ao Carregar o Ficheiro "+e);
      	alertErro.showAndWait();
      }
      }
}
