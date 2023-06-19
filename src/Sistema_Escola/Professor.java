package Sistema_Escola;

public class Professor extends Usuario_sem_definicao {
	private float codigoProfessor;
	private String materia;
	
	public Professor(float codigoProfessor,String materia, String matricula, String nomeCompleto, String cpf, String endereco, String email, String celular) {
		super(matricula, nomeCompleto, cpf, endereco, email, celular);
		this.codigoProfessor = codigoProfessor;
		this.materia = materia;
	}
	
	public float getCodigoProfessor() {
		return codigoProfessor;
	}
	public String materia() {
		return materia; 
	}
}
