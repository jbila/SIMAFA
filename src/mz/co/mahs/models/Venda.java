package mz.co.mahs.models;

import java.time.LocalDate;

public class Venda {
	private int  idVenda;
	private Cliente cliente;
	private Utilizador utilizador;
	private LocalDate dataRegisto;// VAI ARMAZENAR INFOMAÇÃO PARA VER A DATA QUE FOI FEITA A VENDA E A RESPECTIVA HORA
	
	public Venda() {}
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
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
	public LocalDate getDataRegisto() {
		return dataRegisto;
	}
	public void setDataRegisto(LocalDate dataRegisto) {
		this.dataRegisto = dataRegisto;
	}
	@Override
	public String toString() {
		return "Venda [idVenda=" + idVenda + ", cliente=" + cliente + ", utilizador=" + utilizador + ", dataRegisto="
				+ dataRegisto + ", getIdVenda()=" + getIdVenda() + ", getCliente()=" + getCliente()
				+ ", getUtilizador()=" + getUtilizador() + ", getDataRegisto()=" + getDataRegisto() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
