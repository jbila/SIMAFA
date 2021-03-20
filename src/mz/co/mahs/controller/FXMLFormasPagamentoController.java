package mz.co.mahs.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLFormasPagamentoController {

	Alert alertInfo = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	Alert alertErro = new Alert(AlertType.ERROR);

	@FXML
	private TextField txtvalorApagar;

	@FXML
	private ComboBox<String> cboFormasPagaemto;
	ObservableList<String> pagamento = FXCollections.observableArrayList("CASH", "MPESA", "MILLENNIUM BIM");

	@FXML
	private Label lblTrocos;

	@FXML
	private Label lblToalApagar;

	@FXML
	public static TextField txtId;

	@FXML
	public static TextField txtNome;
	@FXML
	public static TextField txtTelefone;
	@FXML
	private Button btnCllient;

	@FXML
	private Button btnConfirmarPagemento;

	@FXML
	private void initialize() {
		cboFormasPagaemto.setItems(pagamento);

	}

}
