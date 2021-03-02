package mz.co.mahs.models;

import java.time.LocalDate;

public class ItemVenda {
	private int idItemVenda;
	private Producto producto;
	private int qty;
	private  double subTotal;
	private Venda venda;
	private Utilizador utilizador;
	private LocalDate dataRegisto;
	
	
	public ItemVenda() {}
	

	
	public int getIdItemVenda() {
		return idItemVenda;
	}



	public void setIdItemVenda(int idItemVenda) {
		this.idItemVenda = idItemVenda;
	}



	public Producto getProducto() {
		return producto;
	}



	public void setProducto(Producto producto) {
		this.producto = producto;
	}



	public int getQty() {
		return qty;
	}



	public void setQty(int qty) {
		this.qty = qty;
	}



	public double getSubTotal() {
		return subTotal;
	}



	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}



	public Utilizador getUtilizador() {
		return utilizador;
	}



	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}



	public LocalDate getDataRegisto() {
		return dataRegisto;
	}



	public void setDataRegisto(LocalDate dataRegisto) {
		this.dataRegisto = dataRegisto;
	}



	public Venda getVenda() {
		return venda;
	}



	public void setVenda(Venda venda) {
		this.venda = venda;
	}



	@Override
	public String toString() {
		return null;
	}
	
	
	
	
}
