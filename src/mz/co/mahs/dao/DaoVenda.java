package mz.co.mahs.dao;

import java.sql.CallableStatement;
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
import mz.co.mahs.models.Cliente;
import mz.co.mahs.models.Utilizador;
import mz.co.mahs.models.Venda;

public class DaoVenda {
	
	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);
	private static final String INSERT = "INSERT INTO tbl_venda(idCliente,idUtilizador,dataRegisto) VALUES(?,?,?)";
	private static final String LIST = "select * from tbl_venda order by id desc";
	private static final String DELETE = "{CALL ps_Delete_User(?)}";
	private static final String UPDATE = "UPDATE tbl_venda  ";
	
	private static Connection conn = null;
	private static PreparedStatement stmt;
	private static CallableStatement cs=null;
	private static ResultSet rs=null;
	
	
	 public static void add(Venda venda) {
		   
         try {
       	  LocalDate localDate = LocalDate.now();
             String dataRegisto = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);

            
             conn = Conexao.connect();
             stmt = conn.prepareStatement(INSERT);
             stmt.setInt(1, venda.getCliente().getIdCliente());
             stmt.setInt(2, venda.getUtilizador().getIdUtilizador());
             stmt.setString(3,dataRegisto);
             stmt.executeUpdate();
            
             alertInfo.setHeaderText("Information");
             alertInfo.setContentText("venda adicionada ");
             alertInfo.showAndWait();
            // 
         } catch (SQLException ex) {
         	alertInfo.setHeaderText("Information");
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
//-------------------------------------------------------------------
	
	  public static void delete(Venda venda) {
		
	     try {
	         conn = Conexao.connect();
	         stmt = conn.prepareStatement(DELETE);
	         stmt.setInt(1,venda.getIdVenda());
	         stmt.execute();
	         alertInfo.setHeaderText("Information");
	         alertInfo.setContentText("Venda Removida");
	         alertInfo.showAndWait();
	        

	     } 
	     catch (SQLException e) {
	         
	         alertErro.setHeaderText("Erro");
	         alertErro.setContentText("Erro ao eliminar a  Venda: "+e.getMessage());
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
			public static  void update(Venda  venda) {
				try {

					conn = Conexao.connect();
					stmt = conn.prepareStatement(UPDATE);

					stmt.setInt(1, venda.getCliente().getIdCliente());
		             stmt.setInt(2, venda.getUtilizador().getIdUtilizador());
					stmt.setInt(3,venda.getIdVenda());
					stmt.executeUpdate();

					alertInfo.setHeaderText("Information");
					alertInfo.setContentText("Updated ");
					alertInfo.showAndWait();
				}

				catch (SQLException ex) {
						alertErro.setHeaderText("Error");
			           alertErro.setContentText("Erro ao actualizar a venda: "+ex.getMessage());
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
		
		public static List<Venda> getAll() {
			List<Venda> vendas = new ArrayList<>();
			try {
				conn = Conexao.connect();
				stmt = conn.prepareCall(LIST);
				rs = stmt.executeQuery();
				while (rs.next()) {
				
					Venda venda = new Venda();//objecto principal
					Cliente cliente=new Cliente();//OBJECTO SECUNDARIO
					cliente.setNome(rs.getString(2));
					
					Utilizador utilizador=new Utilizador();
					utilizador.setUsername(rs.getString(3));
					
					venda.setIdVenda(rs.getInt(1));
					venda.setDataRegisto(LocalDate.parse(rs.getString(4)));
					
					venda.setCliente(cliente);
					venda.setUtilizador(utilizador);
				}

			} catch (SQLException ex) {
				alertErro.setHeaderText("Erro");
				alertErro.setContentText("Erro ao listar  Turma  " + ex.getMessage());
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

			return vendas;
	  }
//------------------------------------------------------------------------------------
		
		public static List<Venda> search(String nome) {
			List<Venda> vendas = new ArrayList<>();
			try {
				conn = Conexao.connect();
				stmt = conn.prepareCall(LIST);
				rs = stmt.executeQuery();
				while (rs.next()) {
				
					Venda venda = new Venda();//objecto principal
					Cliente cliente=new Cliente();//OBJECTO SECUNDARIO
					cliente.setNome(rs.getString(2));
					
					Utilizador utilizador=new Utilizador();
					utilizador.setUsername(rs.getString(3));
					
					venda.setIdVenda(rs.getInt(1));
					venda.setDataRegisto(LocalDate.parse(rs.getString(4)));
					
					venda.setCliente(cliente);
					venda.setUtilizador(utilizador);
				}

			} catch (SQLException ex) {
				alertErro.setHeaderText("Erro");
				alertErro.setContentText("Erro ao listar  Turma  " + ex.getMessage());
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

			return vendas;
	  }
//----------------------------------------------------------------------------------------
}
