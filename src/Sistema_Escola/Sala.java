package Sistema_Escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BD.Conexao;

class Sala {
	private int id;
    private String nome;
    private String local;
    private int capacidadeTotal;

    public Sala(String nome, String local, int capacidadeTotal) {
        this.nome = nome;
        this.local = local;
        this.capacidadeTotal = capacidadeTotal;
    }
    
    public Sala(int id, String nome, String local, int capacidadeTotal) {
    	this.id = id;
        this.nome = nome;
        this.local = local;
        this.capacidadeTotal = capacidadeTotal;
    }

    // Getters

    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public int getCapacidadeTotal() {
        return capacidadeTotal;
    }
    
    public int getId() {
    	return this.id;
    }
    
    private void setId(int id) {
    	this.id = id;
    }
    
    public void salvarNoBanco(){
		String sql = "INSERT INTO salas (nome, local, capacidadeTotal) VALUES (?, ?, ?)";
		Connection conexao = null;
		PreparedStatement pstm = null;
		
		try {
			conexao = Conexao.obterConexao();
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstm.setString(1, getNome());
			pstm.setString(2, getLocal());
			pstm.setInt(3, getCapacidadeTotal());
			
			pstm.execute();
			
			int id = obterId(pstm);
			this.setId(id);
			
		}catch(Exception error) {
			System.out.println(error);
		}finally {
			try {
				if(pstm != null) pstm.close();
				
				if(conexao != null) conexao.close();
				
			}catch(Exception error) {
				System.out.println(error);
			}
		}
	}
    
    private int obterId(PreparedStatement pstm) throws SQLException {
		int id = 0;
	    ResultSet rs = pstm.getGeneratedKeys();
	    while (rs.next()) {
	    	id = rs.getInt(1);
	    }
	    return id;
				
	}
    
    public static List<Sala> buscarTodosSemTurma() throws Exception {
		try {
    		Connection con = Conexao.obterConexao();
			Statement stmt = con.createStatement();
		
			List<Sala> salas = new ArrayList<>();
			
			//Retorna apenas salas sem turmas
			ResultSet rs = stmt.executeQuery("SELECT salas.*, turmas.sala_id FROM a3.salas as salas\r\n"
					+ "LEFT JOIN a3.turmas as turmas ON turmas.sala_id = salas.id\r\n"
					+ "WHERE sala_id is null;");
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String local = rs.getString("local");
				int capacidadeTotal = rs.getInt("capacidadeTotal");
				
				Sala sala = new Sala(id, nome, local, capacidadeTotal);
				salas.add(sala);
			}
						
			rs.close();
			stmt.close();
			con.close();
			
			return salas;
			
    	}catch(SQLException e){
    		e.printStackTrace();
    		return null;
    	}
	}
}