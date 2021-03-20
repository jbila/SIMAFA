package mz.co.mahs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mz.co.mahs.dao.DaoCliente;
import mz.co.mahs.dao.DaoFormasDePagamento;
import mz.co.mahs.dao.DaoItemsPedidos;
import mz.co.mahs.dao.DaoPedido;
import mz.co.mahs.dao.DaoProducto;
import mz.co.mahs.models.Categoria;
import mz.co.mahs.models.Cliente;
import mz.co.mahs.models.FormasDePagamento;
import mz.co.mahs.models.ItemsPedidos;
import mz.co.mahs.models.Pedido;
import mz.co.mahs.models.Producto;
import mz.co.mahs.models.Utilizador;

public class FXMLPedidoController {
	/**
	 * Esta variavel armazena o codigo da venda ou pedido para posterior ser gravado
	 * na tabela items da venda ou items do pedido
	 */
	int idPedido = 0;
	public static String idCliente = "0", nomeCliente = "0";
	List<ItemsPedidos> data = new ArrayList<>();
	double total = 0, subTotal = 0, diminuir=0;

	Alert alertInfo = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	Alert alertErro = new Alert(AlertType.ERROR);
	@FXML
	private AnchorPane anchorPaneVendas;
	@FXML
	private Pane paneProducto;
	
	/**
	 * Esta tabela e usada para exibir informacao de producto para ser selecionada
	 * no acto de adicionar items
	 */
	@FXML
	private TableView<Producto> tblProducto;
	@FXML
	private TableColumn<Producto, Integer> colIdProducto;
	@FXML
	private TableColumn<Producto, String> colCodigoProducto;
	@FXML
	private TableColumn<Producto, String> colNomeProducto;
	@FXML
	private TableColumn<Producto, Categoria> colCategoria;
	@FXML
	private TableColumn<Producto, Integer> colQuantidade;
	@FXML
	private TableColumn<Producto, Double> colPrecoProducto;
	@FXML
	private TableColumn<Producto, String> colDescricao;
	/** ------------------------------------------------------ */
	@FXML
	private Pane paneCarrinha;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtPreco;
	@FXML
	private TextField txtQty;
	@FXML
	private TextField txtSutotal;
	@FXML 
	private TextField txtTrocos;
	@FXML
	private TextField txtProcurar;/**usada para pesquisar dentro da tabela*/

	/** SEGUNDA TABELA items */
	@FXML
	private TableView<ItemsPedidos> tblItems;
	@FXML
	private TableColumn<ItemsPedidos, Integer> colId;
	@FXML
	private TableColumn<ItemsPedidos, String> colItemDescricao;
	@FXML
	private TableColumn<ItemsPedidos, Producto> colNome;
	@FXML
	private TableColumn<ItemsPedidos, Double> colPreco;
	@FXML
	private TableColumn<ItemsPedidos, Double> colSubTotal;
	@FXML
	private TableColumn<ItemsPedidos, Integer> colQty;
	@FXML
	private ComboBox<Producto> cboProducto;
	List<Producto> listProducto = new ArrayList<>();
	@FXML
	private ComboBox<FormasDePagamento> cboFormaDePagamento;
	List<FormasDePagamento> listFormasDePagamento = new ArrayList<>();
	@FXML
	Button btnAdicionar, btnCancelar;
	@FXML
	private Button btnRemover;
	@FXML
	private Label lblTotal;
	@FXML
	private Button btnFinalizar;
	@FXML
	private Button btnCliente;
	@FXML
	private Pane topVendasPane;
	/** Estes campossao usados para exibir informacao do cliente */
	@FXML
	private ComboBox<Cliente> cboCliente;
	List<Cliente> listCliente = new ArrayList<>();

	/** Este é o primeiro método que é carregado assim
	 * que o formulário de pedido ou venda é invocado
	 * ou em outras palavras é o primeiro evento que roda 
	 * na aplicação
	 * --------------------------------------------------------- */
	@FXML
	private void initialize() {
		showInfo();
		txtId.setVisible(false);// este e id de producto
		fillCliente();
		fillFormasDePagamento();
	}

	@FXML
	private void finalizar(ActionEvent event) {
		addPedido();
		addItmes();
	}

	@FXML
	public void handleMouseClickAction(MouseEvent event) {
		Producto producto = tblProducto.getSelectionModel().getSelectedItem();
		txtId.setText("" + producto.getIdProducto());
		txtNome.setText("" + producto.getNome());
		txtPreco.setText("" + producto.getPrecoFinal());
	}

	//
	@FXML
	private void addItems(ActionEvent event) {
		addRow();
		total = (total + subTotal);
		lblTotal.setText("" + total);
		txtQty.setText("0");
		txtNome.clear();	
		txtSutotal.clear();
		txtPreco.clear();
		
		
	}

	@FXML
	private void removeItems(ActionEvent event) {
		deleteRow();

	}

	@FXML
	private void productoList(ActionEvent event) {

	}

	/** Este evento ocorre quando a quantidade e intruduzida */
	@FXML
	private void calcularSubtotal(KeyEvent event) {
		if (txtQty.getText().equalsIgnoreCase("")) {
			txtSutotal.setText("0.0");
		} else {
			subTotal = (Integer.parseInt(txtQty.getText()) * (Double.parseDouble(txtPreco.getText())));
			txtSutotal.setText("" + subTotal);
		}

	}

	// --------------------------------------------------------------------------
	private void openFormasPagamento() {
		Stage stage = new Stage();
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getResource("/mz/co/mahs/views/FXMLFormasPagamento.fxml"));
			Parent root = (Parent) fxmlLoader.load();

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
			stage.setScene(scene);
			stage.initStyle(StageStyle.DECORATED);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.centerOnScreen();
			anchorPaneVendas.setCenterShape(true);// teste
			stage.show();
		} catch (Exception e) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Carregar o Ficheiro " + e);
			alertErro.showAndWait();
		}
	}

	/** Este metodo adiciona producto que estao na base de dados */
	public void showInfo() {
		List<Producto> list = DaoProducto.getAll();

		final ObservableList<Producto> obserList = FXCollections.observableArrayList(list);
		colIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
		colCodigoProducto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		colNomeProducto.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		colPrecoProducto.setCellValueFactory(new PropertyValueFactory<>("precoFinal"));
		tblProducto.setItems(obserList);
	}

	// -------------------------------------------------------------------------------
	@FXML
	private void clientes(ActionEvent event) {
		//openAllClients();
		openAllClientss();

	}

	// --------------------------------------------------------------------------
	private void openAllClients() {
		Stage stage = new Stage();
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
		} catch (Exception e) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Carregar o Ficheiro " + e);
			alertErro.showAndWait();
		}
	}
	//-------------------------------------------------------------------------
	private void openAllClientss() {
		Parent anchorPane = null;
		try {
			anchorPane = FXMLLoader.load(getClass().getResource("/mz/co/mahs/views/FXMLAllClients.fxml"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
         Scene scene = new Scene(anchorPane);
     			//scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());

         Stage stage = new Stage();
         stage.setScene(scene);
         stage.setResizable(false);
         stage.show();
	}

	private void fillCliente() {
		listCliente = DaoCliente.getAllCliente();
		for (Cliente cliente : listCliente)
			cboCliente.getItems().add(cliente);
	}

	// filFormasDePagamento
	private void fillFormasDePagamento() {
		listFormasDePagamento = DaoFormasDePagamento.getAll();
		for (FormasDePagamento formasDePagamento : listFormasDePagamento)
			cboFormaDePagamento.getItems().add(formasDePagamento);
	}

	/** Este metodo adiciona itens na tabela */
	private void addRow() {

		ItemsPedidos items = new ItemsPedidos();
		Producto producto = new Producto();
		producto.setNome(txtNome.getText());
		producto.setIdProducto(Integer.parseInt(txtId.getText()));

		items.setProducto(producto);
		items.setPrecoUnitario(Double.parseDouble(txtPreco.getText()));
		items.setQuantidade(Integer.parseInt(txtQty.getText()));
		items.setSubTotal(Double.parseDouble(txtPreco.getText()) * Integer.parseInt(txtQty.getText()));
		items.setIdp(Integer.parseInt(txtId.getText()));
		data.add(items);
		int row = data.size();

		tblItems.requestFocus();
		tblItems.getSelectionModel().select(row);
		tblItems.getFocusModel().focus(row);

		colId.setCellValueFactory(new PropertyValueFactory<>("idp"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("producto"));
		colQty.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		colPreco.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
		colSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
		tblItems.getItems().add(items);
		System.out.println(items);
	}

	private void deleteRow() {
		btnRemover.setOnAction(e -> {
		    ItemsPedidos selectedItem = tblItems.getSelectionModel().getSelectedItem();
		    int selecctedIndex= tblItems.getSelectionModel().getSelectedIndex();// seleciona o indice
		  
		    
		    for (int i = 0; i < tblItems.getItems().size(); i++) {
			ItemsPedidos itemsPedido = new ItemsPedidos();
			itemsPedido.setSubTotal(colSubTotal.getCellData(i));
			diminuir=colSubTotal.getCellData(i);
			//System.out.println("Diminuir"+diminuir);
			//total=total-t;
			//lblTotal.setText(Double.parseDouble(total-t));
			if(selecctedIndex>0)
			tblItems.getItems().remove(selectedItem);
			
		}
		    total=total-diminuir;
		    lblTotal.setText(""+total);
		     
		});
	}

	/**
	 * Este medido adicona o pedido ou a venda
	 */
	private void addPedido() {

		Pedido pedido = new Pedido();// OBJECTO PRINCIPAL
		Cliente cliente = new Cliente();// OBJECTO SECUNDARIO
		Cliente cli = (Cliente) cboCliente.getSelectionModel().getSelectedItem();
		final int idCliente = cli.getIdCliente();
		cliente.setIdCliente(idCliente);

		Utilizador utilizador = new Utilizador();// OBJECTO SECUNDARIO
		utilizador.setIdUtilizador(ControllerLogin.idUsuario);

		FormasDePagamento formasDepagamento = new FormasDePagamento();// OBJECTO SECUNDARIO
		FormasDePagamento formasPagamento = (FormasDePagamento) cboFormaDePagamento.getSelectionModel()
				.getSelectedItem();
		final int idFormasPagamento = formasPagamento.getId();
		formasDepagamento.setId(idFormasPagamento);

		pedido.setFormasDepagamento(formasDepagamento);
		pedido.setUtilizador(utilizador);
		pedido.setCliente(cliente);
		pedido.setEstado(true);
		pedido.setValorPedido(Double.parseDouble(lblTotal.getText()));
		idPedido = DaoPedido.add(pedido);
		System.out.println(idPedido);
	}

	/**
	 * Este medido adicona o items do pedido
	 */

	// addIttems na tabela
	private void addItmes() {
		List<ItemsPedidos> listItem = new ArrayList<>();
		tblItems.getSelectionModel().getSelectedItems();
		for (int i = 0; i < tblItems.getItems().size(); i++) {
			ItemsPedidos itemsPedido = new ItemsPedidos();
			Pedido pedido = new Pedido();
			pedido.setIdPedido(idPedido);
			Producto producto = new Producto();
			producto.setIdProducto(colId.getCellData(i));
			itemsPedido.setQuantidade(colQty.getCellData(i));
			itemsPedido.setPrecoUnitario(colPreco.getCellData(i));
			itemsPedido.setProducto(producto);
			itemsPedido.setPedido(pedido);
			listItem.add(itemsPedido);
			// System.out.println(colId.getCellData(i)+"<>"+colQty.getCellData(i)+"<>"+colPreco.getCellData(i));
		}
		DaoItemsPedidos.add(listItem);
		lblTotal.setText("0.0");
		limpaCampos();

	}

	/* procurador */
	@FXML
	private void procurador(KeyEvent event) {
		List<Producto> list = DaoProducto.searchAll(txtProcurar.getText());
		final ObservableList<Producto> obserList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colQty.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		tblProducto.setItems(obserList);
	}

	/* Este metodo limpa campos de e esvazia a tabela de itens */
	private void limpaCampos() {
		txtId.clear();
		txtNome.clear();
		txtPreco.clear();
		txtSutotal.clear();
		txtTrocos.clear();
		subTotal=0.0;
		total=0.0;
		tblItems.getItems().clear();
	}
	private void controlStock()
	{
		if(Integer.parseInt(txtQty.getText())>0) {
		}
	}
}
