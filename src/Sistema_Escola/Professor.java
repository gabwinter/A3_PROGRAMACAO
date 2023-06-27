package Sistema_Escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BD.Conexao;

public class Professor extends Usuario_sem_definicao {
	private int codigo;
	private String materia;
	
	public Professor(String materia, String nomeCompleto, String cpf, String endereco, String email, String celular) throws Exception {
		super(nomeCompleto, cpf, endereco, email, celular);
		this.materia = materia;
	}
	
	public float getCodigo() {
		return codigo;
	}
	
	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String materia() {
		return materia; 
	}
	
	public void salvarNoBanco(){
		String sql = "INSERT INTO professores (nome, cpf, endereco, email, celular) VALUES (?, ?, ?, ?, ?)";
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
}
