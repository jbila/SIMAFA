
package mz.co.mahs.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tbl_cliente")
public class Cliente extends Pessoa{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;
    private Utilizador utilizador;
    private String dataRegisto;
  //nome,genero,email,telefone,endereco
    
  public Cliente(){}

public int getIdCliente() {
	return idCliente;
}

public void setIdCliente(int idCliente) {
	this.idCliente = idCliente;
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

@Override
public String toString() {
	return getNome();
			}
  

}
