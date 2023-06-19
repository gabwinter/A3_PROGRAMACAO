package Sistema_Escola;
import java.util.ArrayList;
import java.util.List;

public class Usuario_sem_definicao {
// Declarando os atributos
	
	    private String matricula;
	    private String nomeCompleto;
	    private String cpf;
	    private String endereco;
	    private String email;
	    private String celular;

	    public Usuario_sem_definicao(String matricula, String nomeCompleto, String cpf, String endereco, String email, String celular) {
	        this.matricula = matricula;
	        this.nomeCompleto = nomeCompleto;
	        this.cpf = cpf;
	        this.endereco = endereco;
	        this.email = email;
	        this.celular = celular;
	    }

	    // Getters

	    public String getMatricula() {
	        return matricula;
	    }

	    public String getNomeCompleto() {
	        return nomeCompleto;
	    }

	    public String getCpf() {
	        return cpf;
	    }

	    public String getEndereco() {
	        return endereco;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getCelular() {
	        return celular;
	    }
	}

