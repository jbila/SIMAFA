package mz.co.mahs.controller;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mz.co.mahs.dao.DaoCliente;
import mz.co.mahs.models.Cliente;
import mz.co.mahs.models.Utilizador;

public class FXMLAllClientsController {
	@FXML
	private TextField txtProcurar;

	@FXML
	private TableView<Cliente> tblCliente;

	@FXML
	private TableColumn<Cliente, Integer> colId;

	@FXML
	private TableColumn<Cliente, String> colNome;

	@FXML
	private TableColumn<Cliente, String> colApelido;

	@FXML
	private TableColumn<Cliente, String> colGenero;

	@FXML
	private TableColumn<Cliente, String> colEmail;

	@FXML
	private TableColumn<Cliente, String> colTelefone;

	@FXML
	private TableColumn<Cliente, String> colEndereco;

	@FXML
	private TableColumn<Cliente, String> colDataRegisto;

	@FXML
	private TableColumn<Cliente, Utilizador> colUtilizador;

	@FXML
	private void initialize() {
		showInfo();
	}

	@FXML
	private void handleMouseClickAction(MouseEvent event) {
		Stage stage = (Stage) this.txtProcurar.getScene().getWindow();

		try {

			Cliente cliente = tblCliente.getSelectionModel().getSelectedItem();
			String id=("" + cliente.getIdCliente());
			String nome=("" + cliente.getNome());
			String telefone=("" + cliente.getTelefone());
			FXMLPedidoController.idCliente=(id);
			FXMLPedidoController.nomeCliente=nome;
	    	//txtProcurar.setText("" + cliente.getNome());
			stage.close();
		} catch (java.lang.NullPointerException e) {
			JOptionPane.showMessageDialog(null, "EXCEPTION " + e.getMessage());
			//e.printStackTrace();
		}
	}

	public void showInfo() {
		List<Cliente> list = DaoCliente.getAllCliente();
		final ObservableList<Cliente> obserList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));
		colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		colUtilizador.setCellValueFactory(new PropertyValueFactory<>("utilizador"));
		colDataRegisto.setCellValueFactory(new PropertyValueFactory<>("dataRegisto"));
		tblCliente.setItems(obserList);

	}

}
