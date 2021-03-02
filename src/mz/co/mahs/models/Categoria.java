
package mz.co.mahs.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tbl_categoria")
public class Categoria{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCategoria")
    private int idCategoria;
    private String nome;
    private String descricao;
	@Column(name="idUtilizador")
    private Utilizador utilizador;
	
	
	
	

	public Categoria( String nome, String descricao) {	
		this.nome = nome;
		this.descricao = descricao;
		
	}

	public Categoria() {}
	
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}
	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return getNome();
	}   
}
