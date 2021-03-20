package mz.co.mahs.models;

import java.util.List;

public class Pedido {
	private int  idPedido;
	private Cliente cliente;
	private Utilizador utilizador;
	private  FormasDePagamento formasDepagamento;
	private boolean estado;
	private double valorPedido;
	private double descounto;
	private double valorComDesconto;
	private List<ItemsPedidos> items;
	private String dataRegisto;// VAI ARMAZENAR INFOMAÇÃO PARA VER A DATA QUE FOI FEITA A VENDA E A RESPECTIVA HORA

	public Pedido() {
		
	}
	
	public Pedido(int idPedido, Cliente cliente, Utilizador utilizador, String dataRegisto) {
		super();
		this.idPedido = idPedido;
		this.cliente = cliente;
		this.utilizador = utilizador;
		this.dataRegisto = dataRegisto;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public String getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(String dataRegisto) {
		this.dataRegisto = dataRegisto;
	}

	public FormasDePagamento getFormasDepagamento() {
		return formasDepagamento;
	}

	public void setFormasDepagamento(FormasDePagamento formasDepagamento) {
		this.formasDepagamento = formasDepagamento;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}

	public double getDescounto() {
		return descounto;
	}

	public void setDescounto(double descounto) {
		this.descounto = descounto;
	}

	public double getValorComDesconto() {
		return valorComDesconto;
	}

	public void setValorComDesconto(double valorComDesconto) {
		this.valorComDesconto = valorComDesconto;
	}

	public List<ItemsPedidos> getItems() {
		return items;
	}

	public void setItems(List<ItemsPedidos> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", cliente=" + cliente + ", utilizador=" + utilizador
				+ ", formasDepagamento=" + formasDepagamento + ", estado=" + estado + ", valorPedido=" + valorPedido
				+ ", descounto=" + descounto + ", valorComDesconto=" + valorComDesconto + ", items=" + items
				+ ", dataRegisto=" + dataRegisto + "]";
	}
	
	
	
}
