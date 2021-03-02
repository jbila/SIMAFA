
package mz.co.mahs.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tbl_fornecedor")
public class Fornecedor extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFornecedor;
	private Utilizador  utilizador;
	private LocalDate dataRegisto;
	
	public Fornecedor() {
	}
	public Fornecedor(int idFornecedor, String nome,String apelido,String sexo, String email, String telefone, String endereco,
			Utilizador utilizador, LocalDate dataRegisto) {
		super(nome,apelido,sexo,email,telefone,endereco);
		this.idFornecedor = idFornecedor;
		this.utilizador = utilizador;
		this.dataRegisto = dataRegisto;
	}



	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
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
		return getNome();
	}

	
}
