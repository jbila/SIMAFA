
package mz.co.mahs.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import mz.co.mahs.dao.DaoCategoria;
import mz.co.mahs.dao.DaoFornecedor;
import mz.co.mahs.dao.DaoProducto;
import mz.co.mahs.models.Categoria;
import mz.co.mahs.models.Fornecedor;
import mz.co.mahs.models.FornecedorProducto;
import mz.co.mahs.models.Producto;
import mz.co.mahs.models.Utilizador;

public class FXMLProductoController implements Initializable, Crud {
	int ultimoIdProducto=0;
	
	Alert alert = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);

	@FXML
	private AnchorPane rootProducto;
	
	//*********************************************
	 @FXML
	    private TableView<Producto> tblProducto;

	    @FXML
	    private TableColumn<Producto, Integer> colId;

	    @FXML
	    private TableColumn<Producto, String> colNome;

	    @FXML
	    private TableColumn<Producto, String> colDescricao;

	    @FXML
	    private TableColumn<Producto, Integer> colQty;

	    @FXML
	    private TableColumn<Producto, Double> colValorCompra;

	    @FXML
	    private TableColumn<Producto, Double> colValorVenda;

	    @FXML
	    private TableColumn<Producto,Categoria> colCategoria;

	    @FXML
	    private TableColumn<Producto,Fornecedor> colFornecedor;

	    @FXML
	    private TableColumn<Producto,String> colValidade;

	    @FXML
	    private TableColumn<Producto,LocalDate> colDataRegisto;

	    @FXML
	    private TableColumn<Producto,Utilizador> colUtilizador;

	//******************************************************

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtDescricao;

	@FXML
	private ComboBox<Categoria> cboCategoria;
    private List<Categoria> listCategoria=new ArrayList<>();
	@FXML
	private ComboBox<Fornecedor> cboFornecedor;
    private List<Fornecedor> listFornecedor=new ArrayList<>();


	@FXML
	private TextField txtQty;

	@FXML
	private TextField txtValorCompra;

	@FXML
	private TextField txtValorVenda;

	@FXML
	private DatePicker datePickerValidade;

	@FXML
	private TextField txtPesquisar;

	@FXML
	private ImageView imageProductPhoto;

	@FXML
	private HBox hBoxButton;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnUpdate;

	@FXML
	private Button btnDelete;

	// ---------------------------------------------------------------------------
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		showInfo();
		fillCategoria();
		fillFornecedor();
		btnDelete.setVisible(false);
		btnUpdate.setVisible(false);
	}

	// ---------------------------------------------------------------------------
	@FXML
	private void handleMouseClickAction(MouseEvent event) {
		Producto producto = tblProducto.getSelectionModel().getSelectedItem();
		txtId.setText("" + producto.getIdProducto());
		txtNome.setText("" +producto.getNome());
		txtQty.setText("" + producto.getQuantidade());
		txtDescricao.setText("" + producto.getDescricao());
		txtValorCompra.setText("" + producto.getPrecoFornecedor());
		txtValorVenda.setText("" + producto.getPrecoFinal());
		//datePickerValidade.setValue(LocalDate(producto.getValidade()));

		

	}

	

	@FXML
	private void add(ActionEvent event) {
		acessoAdd();
		showInfo();

	}

	@FXML
	private void delete(ActionEvent event) {

		if (txtId.getText().isEmpty()) {
			alert.setTitle("Warning");
			alert.setHeaderText("Validacao");
			alert.setContentText("selecione a linha a apagar");
			alert.showAndWait();
		} else {
			alertConfirm.setTitle("Confirmacao");
			alertConfirm.setHeaderText(" Dialogo de Confirmacao");
			alertConfirm.setContentText("Eliminar o Registo?");

			Optional<ButtonType> result = alertConfirm.showAndWait();
			if (result.get() == ButtonType.OK) {
				// ... user chose OK
				acessoDelete();
				limpar();
				showInfo();
			} else {
				// ... user chose CANCEL or closed the dialog
			}

		}

	}

	@FXML
	private void update(ActionEvent event) {
		acessoUpdate();
		showInfo();
	}

	@Override
	public void acessoAdd() {
		Producto producto = new Producto();
		Utilizador utilizador = new Utilizador();//OBJECTO SECUNDARIO
		utilizador.setIdUtilizador(ControllerLogin.idUsuario);

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setIdFornecedor(1);

		Categoria categoria = new Categoria();//OBJECTO SECUNDARIO
		Categoria cat=(Categoria) cboCategoria.getSelectionModel().getSelectedItem();
		final int idCategoria=cat.getIdCategoria();
		
		categoria.setIdCategoria(idCategoria);
		producto.setNome(txtNome.getText().toUpperCase());
		producto.setDescricao(txtDescricao.getText().toUpperCase());
		producto.setPrecoFornecedor(Double.parseDouble(txtValorCompra.getText()));
		producto.setPrecoFinal(Double.parseDouble(txtValorVenda.getText()));
		producto.setQuantidade(Integer.parseInt(txtQty.getText()));
		producto.setCategoria(categoria);
		producto.setFornecedor(fornecedor);
		producto.setUtilizador(utilizador);
		producto.setValidade(datePickerValidade.getValue().toString());
		saveIds();
		DaoProducto.add(producto);
		limpar();
	}
	/**
	 * Este metodo grava id do fornecedor e do producto na tabela FornecedorProducto
	 * porque o relacionamento de producto e fornecedor gere uma terceira tabela que vai conter 
	 * os ids
	 * */
	public void saveIds() {
		FornecedorProducto fornecedorProducto = new FornecedorProducto();

		Fornecedor fornecedor = new Fornecedor();// OBJECTO SECUNDARIO
		Fornecedor fornecedo = (Fornecedor) cboFornecedor.getSelectionModel().getSelectedItem();
		final int idFornecedor = fornecedo.getIdFornecedor();
		fornecedor.setIdFornecedor(idFornecedor);
		/**
		 * Este (ultimoIdProducto) traz o id do ultimo Producto registado e incrementa se o1
		 * para posterior ser gravado na tabela FornecedorProducto
		 * 
		 * */
		ultimoIdProducto=DaoProducto.ultimoIdProducto();
		System.out.println(ultimoIdProducto);
		Producto producto = new Producto();//OBJECTO SECUNDARIO
		producto.setIdProducto(ultimoIdProducto+1);

		fornecedorProducto.setFornecedor(fornecedor);
		fornecedorProducto.setProducto(producto);	
		DaoProducto.addForeignKeys(fornecedorProducto);
	}
	
	
	
	@Override
	public void acessoIsRecorded() {

	}

	@Override
	public void acessoUpdate() {
		Producto producto = new Producto();
		Utilizador utilizador = new Utilizador();
		utilizador.setIdUtilizador(ControllerLogin.idUsuario);

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setIdFornecedor(1);

		Categoria categoria = new Categoria();
		categoria.setIdCategoria(2);

		producto.setNome(txtNome.getText().toUpperCase());
		producto.setDescricao(txtDescricao.getText().toUpperCase());
		producto.setPrecoFornecedor(Double.parseDouble(txtValorCompra.getText()));
		producto.setPrecoFinal(Double.parseDouble(txtValorVenda.getText()));
		producto.setCategoria(categoria);
		producto.setFornecedor(fornecedor);
		producto.setUtilizador(utilizador);
		producto.setIdProducto(Integer.parseInt(txtId.getId()));
		DaoProducto.update(producto);
		limpar();
	}

	@Override
	public void acessoDelete() {
		Producto producto = new Producto();
		producto.setIdProducto(Integer.parseInt(txtId.getId()));
		DaoProducto.delete(producto);
		limpar();

	}

	@Override
	public void limpar() {
		// nome,descricao,qty,precofinal,precoFornecedor,validade,categoria,fornecedor,utilizador,dataRegisto
		txtId.setText("");
		txtDescricao.setText("");
		txtQty.setText("");
		txtValorCompra.setText("");
		txtValorVenda.setText("");
		datePickerValidade.dayCellFactoryProperty();
	}

	@Override
	public void showInfo() {
		List<Producto> list = DaoProducto.getAll();
		System.out.println(list);
		final ObservableList<Producto> obserList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colQty.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		colValorCompra.setCellValueFactory(new PropertyValueFactory<>("precoFornecedor"));
		colValorVenda.setCellValueFactory(new PropertyValueFactory<>("precoFinal"));
		colValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
		colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		colFornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
		colDataRegisto.setCellValueFactory(new PropertyValueFactory<>("dataRegisto"));
		colUtilizador.setCellValueFactory(new PropertyValueFactory<>("utilizador"));
		tblProducto.setItems(obserList);

	}
	//fill categoria
	  private void fillCategoria() {
			listCategoria=DaoCategoria.getAllCategoria();
			for (Categoria categoria : listCategoria) 
				cboCategoria.getItems().add(categoria);		
	  }
	//fill Fornecedor
	  private void fillFornecedor() {
			listFornecedor=DaoFornecedor.getAllFornecedor();
			for (Fornecedor fornecedor : listFornecedor) 
				cboFornecedor.getItems().add(fornecedor);		
	  }
}
