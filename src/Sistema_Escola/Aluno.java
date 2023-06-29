package Sistema_Escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BD.Conexao;

class Aluno extends Usuario_sem_definicao {
	
	private List<Turma> turmas;

	public Aluno(String nomeCompleto, String cpf, String endereco, String email, String celular, List<Turma> turmas) throws Exception {
		super(nomeCompleto, cpf, endereco, email, celular);
		this.turmas = turmas;
	}
	
	public Aluno(int matricula, String nomeCompleto, String cpf, String endereco, String email, String celular) throws Exception {
		super(nomeCompleto, cpf, endereco, email, celular);
		this.matricula = matricula;
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
			
			for(Turma turma : turmas) {
				Aluno_Turma aluno_turma = new Aluno_Turma(this, turma);
				aluno_turma.salvarNoBanco();
			}
			
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
	
	public static List<Aluno> buscarTodos() throws Exception {
		try {
    		Connection con = Conexao.obterConexao();
			Statement stmt = con.createStatement();
		
			List<Aluno> alunos = new ArrayList<>();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Alunos");
			
			while(rs.next()) {
				int matricula = rs.getInt("matricula");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String endereco = rs.getString("endereco");
				String email = rs.getString("email");
				String celular = rs.getString("celular");
				
				Aluno aluno = new Aluno(matricula, nome, cpf, endereco, email, celular);
				alunos.add(aluno);
			}
						
			rs.close();
			stmt.close();
			con.close();
			
			return alunos;
			
    	}catch(SQLException e){
    		e.printStackTrace();
    		return null;
    	}
	}
}
