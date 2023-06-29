package Sistema_Escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BD.Conexao;
import Sistema_Escola.Aluno;
import Sistema_Escola.Curso;
import Sistema_Escola.Professor;
import Sistema_Escola.Sala;

class Turma {
	private int id;
    private Professor professor;
    private Sala sala;
    private String diaSemana;

    public Turma(Professor professor, Sala sala, String diaSemana) {
        this.professor = professor;
        this.sala = sala;
        this.diaSemana = diaSemana;
    }
    
    public Turma(int id, Professor professor, Sala sala, String diaSemana) {
    	this.id = id;
        this.professor = professor;
        this.sala = sala;
        this.diaSemana = diaSemana;
    }


    public int getId() {
    	return this.id;
    }
    
    public Professor getProfessor() {
        return professor;
    }

    public Sala getSala() {
        return sala;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    private void setId(int id) {
    	this.id = id;
    }
    
    public void salvarNoBanco(){
		String sql = "INSERT INTO turmas (professor_id, sala_id, diaDaSemana) VALUES (?, ?, ?)";
		Connection conexao = null;
		PreparedStatement pstm = null;
		
		
		try {
			conexao = Conexao.obterConexao();
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstm.setInt(1, getProfessor().getCodigo());
			pstm.setInt(2, getSala().getId());
			pstm.setString(3, getDiaSemana());
			
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
    
    public static List<Turma> buscarTodos() throws Exception {
		try {
    		Connection con = Conexao.obterConexao();
			Statement stmt = con.createStatement();
		
			List<Turma> turmas = new ArrayList<>();
			
			ResultSet rs = stmt.executeQuery("SELECT \r\n"
					+ "turmas.id as turmasId,\r\n"
					+ "turmas.diaDaSemana as turmasDiaDaSemana,\r\n"
					+ "\r\n"
					+ "professores.id as professoresId,\r\n"
					+ "professores.nome as professoresNome,\r\n"
					+ "professores.cpf as professoresCpf,\r\n"
					+ "professores.endereco as professoresEndereco,\r\n"
					+ "professores.email as professoresEmail,\r\n"
					+ "professores.celular as professoresCelular,\r\n"
					+ "\r\n"
					+ "salas.id as salasId,\r\n"
					+ "salas.nome as salasNome,\r\n"
					+ "salas.local as salasLocal,\r\n"
					+ "salas.capacidadeTotal as salasCapacidadeTotal,\r\n"
					+ "\r\n"
					+ "cursos.id as cursosId,\r\n"
					+ "cursos.nome as cursosNome,\r\n"
					+ "cursos.cargaHoraria as cursosCargaHoraria,\r\n"
					+ "cursos.descricao as cursosDescricao\r\n"
					+ "\r\n"
					+ " FROM a3.Turmas as turmas\r\n"
					+ "LEFT JOIN a3.professores as professores on turmas.professor_id = professores.id\r\n"
					+ "LEFT JOIN a3.salas as salas on turmas.sala_id = salas.id\r\n"
					+ "LEFT JOIN a3.cursos as cursos on professores.curso_id = cursos.id;");
			
			while(rs.next()) {
				int id = rs.getInt("turmasId");
				String diaDaSemana = rs.getString("turmasDiaDaSemana");
				
				int salaId = rs.getInt("salasId");
				String salaNome = rs.getString("salasNome");
				String local = rs.getString("salasLocal");
				int capacidadeTotal = rs.getInt("salasCapacidadeTotal");
				
				Sala sala = new Sala(salaId, salaNome, local, capacidadeTotal);
				
				int cursoId = rs.getInt("cursosId");
				String cursoNome = rs.getString("cursosNome");
				int cursoCargaHoraria = rs.getInt("cursosCargaHoraria");
				String cursoDescricao = rs.getString("cursosDescricao");
				
				Curso curso = new Curso(cursoId, cursoNome, cursoCargaHoraria, cursoDescricao);
				
				
				int codigo = rs.getInt("professoresId");
				String nomeProfessor = rs.getString("professoresNome");
				String cpf = rs.getString("professoresCpf");
				String endereco = rs.getString("professoresEndereco");
				String email = rs.getString("professoresEmail");
				String celular = rs.getString("professoresCelular");
				
				Professor professor = new Professor(codigo, nomeProfessor, cpf, endereco, email, celular, curso);
				
				Turma turma = new Turma(id, professor, sala, diaDaSemana);
				turmas.add(turma);
				
			}
						
			rs.close();
			stmt.close();
			con.close();
			
			return turmas;
			
    	}catch(SQLException e){
    		e.printStackTrace();
    		return null;
    	}
	}
}

  
