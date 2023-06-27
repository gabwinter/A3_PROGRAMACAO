package Sistema_Escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BD.Conexao;

class Curso {
    private int id;
    private String nomeCurso;
    private int cargaHoraria;
    private String descricao;

    public Curso(String nomeCurso, int cargaHoraria, String descricao) {
        this.nomeCurso = nomeCurso;
        this.cargaHoraria = cargaHoraria;
        this.descricao = descricao;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public String getDescricao() {
        return descricao;
    }
    
    // Setters
    
    private void setId(int id) {
    	this.id = id;
    }
    
    public void setNomeCurso(String nomeCurso) {
    	this.nomeCurso = nomeCurso;
    }
    
    public void setCargaHoraria(int cargaHoraria) {
    	this.cargaHoraria = cargaHoraria;
    }
    
    public void setDescricao(String descricao) {
    	this.descricao = descricao;
    }
    
    public void salvarNoBanco(){
		String sql = "INSERT INTO cursos (nome, cargaHoraria, descricao) VALUES (?, ?, ?)";
		Connection conexao = null;
		PreparedStatement pstm = null;
		
		try {
			conexao = Conexao.obterConexao();
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstm.setString(1, getNomeCurso());
			pstm.setInt(2, getCargaHoraria());
			pstm.setString(3, getDescricao());
			
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