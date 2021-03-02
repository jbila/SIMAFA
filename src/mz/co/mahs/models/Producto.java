
package mz.co.mahs.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tbl_producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idProducto")
	private int idProducto;
	private String nome;//nome,descricao,qty,precofinal,precoFornecedor,validade,categoria,fornecedor,utilizador,dataRegisto
    private String descricao;
	private int quantidade;
	private double precoFinal;
	private double precoFornecedor;
	private String validade;
	private String dataRegisto;
	/*==========================*/
	@Column(name="idCategoria")
	private Categoria categoria;
	@Column(name="idUtilizador")
    private Utilizador utilizador;
	@Column(name="idFornecedor")
    private Fornecedor fornecedor;
    
    public  Producto() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoFinal() {
		return precoFinal;
	}

	public void setPrecoFinal(double precoFinal) {
		this.precoFinal = precoFinal;
	}

	public double getPrecoFornecedor() {
		return precoFornecedor;
	}

	public void setPrecoFornecedor(double precoFornecedor) {
		this.precoFornecedor = precoFornecedor;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(String dataRegisto) {
		this.dataRegisto = dataRegisto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
    
    
  
}
