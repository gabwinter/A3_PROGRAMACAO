package Sistema_Escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BD.Conexao;

class Aluno extends Usuario_sem_definicao {

	public Aluno(String nomeCompleto, String cpf, String endereco, String email, String celular) throws Exception {
		super(nomeCompleto, cpf, endereco, email, celular);
	}
	
	private int matricula;
	
	public int getMatricula() {
		return this.matricula;
	}
	
	private void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public void salvarNoBanco(){
		String sql = "INSERT INTO alunos (nome, cpf, endereco, email, celular) VALUES (?, ?, ?, ?, ?)";
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
			
			int matricula = obterMatricula(pstm);
			this.setMatricula(matricula);
			
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
	
	private int obterMatricula(PreparedStatement pstm) throws SQLException {
		int matricula = 0;
	    ResultSet rs = pstm.getGeneratedKeys();
	    while (rs.next()) {
	        matricula = rs.getInt(1);
	    }
	    return matricula;
				
	}
}
