package Sistema_Escola;

import java.util.ArrayList;
import java.util.List;

class Aluno extends Usuario_sem_definicao {

	public Aluno(String nomeCompleto, String cpf, String endereco, String email, String celular) throws Exception {
		super(nomeCompleto, cpf, endereco, email, celular);
	}
	
	public void cadastrarNoBanco(){
		
	}
}
