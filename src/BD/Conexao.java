package BD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conexao {
	private static final String URL = "jdbc:mysql://localhost:3306/a3?serverTimezone=America/Sao_Paulo";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection obterConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String[] Pesquisar(String query, String[] colunas){
    	try {
    		Connection con = obterConexao();
			Statement stmt = con.createStatement();
		
			String[] resultado = new String[colunas.length];
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				for(int i = 0; i < colunas.length; i++) {	
					resultado[i] = rs.getString(colunas[i]);
				}
				
			}
						
			rs.close();
			stmt.close();
			con.close();
			
			return resultado;
			
    	}catch(SQLException e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public static void Inserir(String sql) {
    	try {
    		Connection con = obterConexao();
    		PreparedStatement ps = con.prepareStatement(sql);
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    
}
