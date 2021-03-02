package mz.co.mahs.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mz.co.mahs.conection.Conexao;
import mz.co.mahs.models.Categoria;
import mz.co.mahs.models.Fornecedor;
import mz.co.mahs.models.FornecedorProducto;
import mz.co.mahs.models.Producto;
import mz.co.mahs.models.Utilizador;

public class DaoProducto {


	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);
	private static final String INSERT = "INSERT INTO tbl_producto(nome,descricao,quantidade,precofinal,precoFornecedor,validade,idCategoria,idFornecedor,idUtilizador,dataRegisto) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String LIST = "SELECT p.idProducto,p.nome,p.descricao,p.quantidade,p.precoFinal,p.precoFornecedor,p.validade,p.dataRegisto FROM tbl_producto AS p";
	private static final String DELETE = "DELETE FROM tbl_producto WHERE idProducto=?";
	private static final String UPDATE = "UPDATE tbl_producto SET nome=? WHERE idProducto=?";

	private static Connection conn = null;
	// private static CallableStatement cs = null;
	private static ResultSet rs = null;
	private static PreparedStatement stmt;

  
  
  public  static boolean isRecorded(String nome,String data ) {
  	 boolean retorno = false;
		     final  String sql = "SELECT nome FROM tbl_producto WHERE nome ='"+nome+"'OR nome='"+data+"' ";
   
    
      try {
      	conn=Conexao.connect();
      	stmt=conn.prepareStatement(sql);
              rs = stmt.executeQuery();
              retorno = rs.next(); 
          
          	
         
         
      } catch (SQLException e) {
   
          alertErro.setHeaderText("Erro");
          alertErro.setContentText("Erro ao vertificar "+e);
          alertErro.showAndWait();
          
      }
     

      return retorno;
  }
  //--------------------------------------------------------------------------
  
  public static void add(Producto producto) {
   
          try {
        	  LocalDate localDate = LocalDate.now();
              String dataRegisto = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);

             
              conn = Conexao.connect();
              stmt = conn.prepareStatement(INSERT);
              //nome,descricao,qty,precofinal,precoFornecedor,validade,categoria,fornecedor,utilizador,dataRegisto
              stmt.setString(1, producto.getNome());
              stmt.setString(2, producto.getDescricao());
              stmt.setInt(3, producto.getQuantidade());
              stmt.setDouble(4, producto.getPrecoFinal());
              stmt.setDouble(5, producto.getPrecoFornecedor());
              stmt.setString(6, ""+producto.getValidade());
              stmt.setInt(7, producto.getCategoria().getIdCategoria());
              stmt.setInt(8, producto.getFornecedor().getIdFornecedor());
              stmt.setInt(9, producto.getUtilizador().getIdUtilizador());
              stmt.setString(10,dataRegisto);
              stmt.executeUpdate();
             
              alertInfo.setHeaderText("Information");
              alertInfo.setContentText("producto adicionado ");
              alertInfo.showAndWait();
             // 
          } catch (SQLException ex) {
          	alertInfo.setHeaderText("Informationo");
              alertInfo.setContentText(" "+ex);
              alertInfo.showAndWait();
          }
          finally {
        	  try {
				stmt.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
          }
     
  }
  //--------------------------------------------------------------------------
  public static void delete(Producto  producto) {
	
     try {
         conn = Conexao.connect();
         stmt = conn.prepareStatement(DELETE);
         stmt.setInt(1,producto.getIdProducto());
         stmt.execute();
         alertInfo.setHeaderText("Information");
         alertInfo.setContentText("producto Removida");
         alertInfo.showAndWait();
        

     } 
     catch (SQLException e) {
         
         alertErro.setHeaderText("Erro");
         alertErro.setContentText("Erro ao eliminar o  producto: "+e.getMessage());
         alertErro.showAndWait();
     }
     finally {
    	 try {
			stmt.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
     }
 }
//------------------------------------------------------------------------------
		public static  void update(Producto  producto) {
			try {

				conn = Conexao.connect();
				stmt = conn.prepareStatement(UPDATE);

				stmt.setString(1, producto.getNome());
				stmt.setString(2, producto.getDescricao());
				stmt.setInt(3, producto.getQuantidade());
				stmt.setDouble(4, producto.getPrecoFinal());
				stmt.setDouble(5, producto.getPrecoFornecedor());
				stmt.setString(6,producto.getValidade());
				stmt.setInt(7, producto.getCategoria().getIdCategoria());
				stmt.setInt(8, producto.getFornecedor().getIdFornecedor());
				stmt.setInt(9, producto.getUtilizador().getIdUtilizador());
				stmt.setInt(10,producto.getIdProducto());
				stmt.executeUpdate();

				alertInfo.setHeaderText("Information");
				alertInfo.setContentText("Updated ");
				alertInfo.showAndWait();
			}

			catch (SQLException ex) {
					alertErro.setHeaderText("Error");
		           alertErro.setContentText("Error Updating the the item "+ex.getMessage());
		           alertErro.showAndWait();
			}
			finally {
				try {
					stmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
	}
//------------------------------------------------------------------------------
	
	public static List<Producto> getAll() {
		List<Producto> productos = new ArrayList<>();
		try {
			conn = Conexao.connect();
			stmt = conn.prepareCall(LIST);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Producto producto = new Producto();//objecto principal
							producto.setIdProducto(rs.getInt("idProducto"));
							producto.setNome(rs.getString("nome"));
							producto.setQuantidade(rs.getInt("quantidade"));
							producto.setPrecoFinal(Double.parseDouble(rs.getString("precoFinal")));
							producto.setPrecoFornecedor(Double.parseDouble(rs.getString("precoFornecedor")));
							producto.setValidade(rs.getString("validade"));
							producto.setDescricao(rs.getString("descricao"));
							producto.setDataRegisto(rs.getString("dataRegisto"));
							
							
				Fornecedor fornecedor=new Fornecedor();//OBJECTO SECUNDARIO
				//fornecedor.setNome(rs.getString(6));
				
				Categoria categoria=new Categoria();//OBJECTO SECUNDARIO
				//categoria.setNome(rs.getString(7));
				
				Utilizador utilizador=new Utilizador(); //OBJECTO SECUNDARIO
				//utilizador.setUsername(rs.getString(8));
				
				
				
				producto.setUtilizador(utilizador);
				producto.setCategoria(categoria);
				producto.setFornecedor(fornecedor);
				
				productos.add(producto);
				
			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao listar  Producto  " + ex.getMessage());
			alertErro.showAndWait();
		}
		finally
		{
			try {
				rs.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
		}

		return productos;
  }
//-------------------------------------------------------------------------------------------
	public static  Integer ultimoIdProducto(){
      	int id=0;
   
     try{
         
         conn=Conexao.connect();
         stmt=conn.prepareStatement("SELECT MAX(idProducto) FROM tbl_producto");
         rs=stmt.executeQuery();
			if (rs.next()) {
				  id=(rs.getInt(1));
			}
     }
     catch(SQLException ex)
     {
  	   alertErro.setHeaderText("Erro");
  	   alertErro.setContentText("Erro ao Carregar o Id do tbl_Producto  "+ex.getMessage());
  	   alertErro.showAndWait();
     }
     finally {
    	 
    	 try {
				rs.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
     }
     
     
     
     return id;
  }
//---------------------------------------------------------------------------------------------------
	public static void addForeignKeys(FornecedorProducto fornecedorProducto) {
		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement("INSERT INTO tbl_fornecedorProducto(idProduto,idFornecedor)value(?,?)");
			stmt.setInt(1, fornecedorProducto.getProducto().getIdProducto());
			stmt.setInt(2, fornecedorProducto.getFornecedor().getIdFornecedor());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException ex) {
			alertInfo.setHeaderText("Information");
			alertInfo.setContentText(" " + ex);
			alertInfo.showAndWait();
		}
	}
//----------------------------------------------------------------------------------------------------
}
