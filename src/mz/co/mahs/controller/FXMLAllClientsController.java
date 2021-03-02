package mz.co.mahs.controller;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mz.co.mahs.dao.DaoCliente;
import mz.co.mahs.models.Cliente;
import mz.co.mahs.models.Utilizador;

public class FXMLAllClientsController implements Crud {

	@FXML
	private TextField txtProcurar;
//
	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtApelido;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtEndereco;

	@FXML
	private TextField txtTelefone;

	@FXML
	private TextField txtID;

	@FXML
	private ComboBox<String> cboSexo;
	ObservableList<String> sexo = FXCollections.observableArrayList("M", "F");
	//
	@FXML
	private TableView<Cliente> tblCliente;

	@FXML
	private TableColumn<Cliente, String> colId;

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
	private TableColumn<Cliente, LocalDate> colDataRegisto;

	@FXML
	private TableColumn<Cliente, Utilizador> colUtilizador;

	public void initialize(URL arg0, ResourceBundle arg1) {
		cboSexo.setItems(sexo);
		showInfo();

	}

	@FXML
	private void add(ActionEvent event) {
		acessoAdd();
		showInfo();
	}

	@FXML
	private void delete(ActionEvent event) {
		acessoDelete();
		showInfo();
	}

	@FXML
	private void handleMouseClickAction(MouseEvent event) {

	}

	@FXML
	private void update(ActionEvent event) {
		acessoUpdate();
		showInfo();
	}

	@Override
	public void acessoAdd() {
		Cliente cliente = new Cliente();
		Utilizador utilizador = new Utilizador();
		utilizador.setIdUtilizador(0);
		cliente.setNome(txtNome.getText());
		cliente.setApelido(txtApelido.getText());
		cliente.setApelido(txtApelido.getText());
		cliente.setEmail(txtEmail.getText());
		cliente.setEndereco(txtEndereco.getText());
		cliente.setTelefone(txtTelefone.getText());
		cliente.setGenero(cboSexo.getValue());
		cliente.setUtilizador(utilizador);
		DaoCliente.addCliente(cliente);
		limpar();
	}

	@Override
	public void acessoIsRecorded() {

	}

	@Override
	public void acessoUpdate() {
		Cliente cliente = new Cliente();
		Utilizador utilizador = new Utilizador();
		utilizador.setIdUtilizador(0);
		cliente.setNome(txtNome.getText());
		cliente.setApelido(txtApelido.getText());
		cliente.setApelido(txtApelido.getText());
		cliente.setEmail(txtEmail.getText());
		cliente.setEndereco(txtEndereco.getText());
		cliente.setTelefone(txtTelefone.getText());
		cliente.setGenero(cboSexo.getValue());
		cliente.setUtilizador(utilizador);
		cliente.setIdCliente(Integer.parseInt(txtID.getText()));
		DaoCliente.updateCliente(cliente);
		limpar();
	}

	@Override
	public void acessoDelete() {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(Integer.parseInt(txtID.getText()));
		DaoCliente.deleteCliente(cliente);
		limpar();
	}

	@Override
	public void limpar() {
		txtID.setText("");
		txtApelido.setText("");
		txtNome.setText("");
		txtTelefone.setText("");
		txtEndereco.setText("");
		txtEmail.setText("");
	}

	@Override
	public void showInfo() {

		List<Cliente> list = DaoCliente.getAllCliente();
		final ObservableList<Cliente> obserList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colEndereco.setCellValueFactory(new PropertyValueFactory<>("morada"));
		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		colUtilizador.setCellValueFactory(new PropertyValueFactory<>("utilizador"));
		tblCliente.setItems(obserList);

	}

}
