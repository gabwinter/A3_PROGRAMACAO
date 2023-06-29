package Sistema_Escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BD.Conexao;

public class Aluno_Turma {
	private int id;
	private Aluno aluno;
	private Turma turma;
	
	Aluno_Turma(Aluno aluno, Turma turma){
		this.aluno = aluno;
		this.turma = turma;
	}
	
	//Getters
	
	public Aluno getAluno() {
		return this.aluno;
	}
	
	public Turma getTurma() {
		return this.turma;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public void salvarNoBanco(){
		String sql = "INSERT INTO alunos_turmas (aluno_matricula, turma_id) VALUES (?, ?)";
		Connection conexao = null;
		PreparedStatement pstm = null;
		
		try {
			conexao = Conexao.obterConexao();
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstm.setInt(1, getAluno().getMatricula());
			pstm.setInt(2, getTurma().getId());
			
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
}
