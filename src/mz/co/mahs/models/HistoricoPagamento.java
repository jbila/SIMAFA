package mz.co.mahs.models;

import java.util.Date;

public class HistoricoPagamento {
	private int id;
	private ItemVenda turma;
	private Cliente formando;
	private Utilizador utilizador;
	private Date dataRegisto;

	public HistoricoPagamento() {
	}
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public ItemVenda getTurma() {
		return turma;
	}


	public void setTurma(ItemVenda turma) {
		this.turma = turma;
	}


	public Cliente getFormando() {
		return formando;
	}


	public void setFormando(Cliente formando) {
		this.formando = formando;
	}


	public Utilizador getUtilizador() {
		return utilizador;
	}


	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}


	public Date getDataRegisto() {
		return dataRegisto;
	}


	public void setDataRegisto(Date dataRegisto) {
		this.dataRegisto = dataRegisto;
	}


	@Override
	public String toString() {

		return null;
	}

}
