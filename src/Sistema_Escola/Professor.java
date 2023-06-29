package Sistema_Escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BD.Conexao;

public class Professor extends Usuario_sem_definicao {
	private int codigo;
	private Curso curso;
	
	public Professor(String nomeCompleto, String cpf, String endereco, String email, String celular) throws Exception {
		super(nomeCompleto, cpf, endereco, email, celular);
	}
	
	public Professor(String nomeCompleto, String cpf, String endereco, String email, String celular, Curso curso) throws Exception {
		super(nomeCompleto, cpf, endereco, email, celular);
		this.curso = curso;
	}
	
	public Professor(int codigo, String nomeCompleto, String cpf, String endereco, String email, String celular, Curso curso) throws Exception {
		super(nomeCompleto, cpf, endereco, email, celular);
		this.curso = curso;
		this.codigo = codigo;
	}
	
	public Professor(int codigo, String nomeCompleto, String cpf, String endereco, String email, String celular) throws Exception {
		super(nomeCompleto, cpf, endereco, email, celular);
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public Curso getCurso() {
		return curso;
	}
	
	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
	public void salvarNoBanco(){
		String sql = "INSERT INTO professores (nome, cpf, endereco, email, celular, curso_id) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conexao = null;
		PreparedStatement pstm = null;
		
		try {
			conexao = Conexao.obterConexao();
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstm.setString(1, getNomeCompleto());
			pstm.setString(2, getCpf());
			pstm.setString(3, getEndereco());
			pstm.setString(4, getEmail());
			pstm.setString(5, getCelular());
			
			if(getCurso() != null) {
				pstm.setInt(6, getCurso().getId());
			}else {
				pstm.setString(6, null);
			}
			
			pstm.execute();
			
			int matricula = obterCodigo(pstm);
			this.setCodigo(matricula);
			
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
	
	private int obterCodigo(PreparedStatement pstm) throws SQLException {
		int codigo = 0;
	    ResultSet rs = pstm.getGeneratedKeys();
	    while (rs.next()) {
	        codigo = rs.getInt(1);
	    }
	    return codigo;
	}
	
	public static List<Professor> buscarTodos() throws Exception {
		try {
    		Connection con = Conexao.obterConexao();
			Statement stmt = con.createStatement();
		
			List<Professor> professores = new ArrayList<>();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Professores");
			
			while(rs.next()) {
				int codigo = rs.getInt("id");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String endereco = rs.getString("endereco");
				String email = rs.getString("email");
				String celular = rs.getString("celular");
				
				Professor professor = new Professor(codigo, nome, cpf, endereco, email, celular);
				professores.add(professor);
			}
						
			rs.close();
			stmt.close();
			con.close();
			
			return professores;
			
    	}catch(SQLException e){
    		e.printStackTrace();
    		return null;
    	}
	}
}
