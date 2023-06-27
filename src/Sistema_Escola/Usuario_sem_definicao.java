package Sistema_Escola;
import java.util.ArrayList;
import java.util.List;

public class Usuario_sem_definicao {
	
	    private String nomeCompleto;
	    private String cpf;
	    private String endereco;
	    private String email;
	    private String celular;

	    public Usuario_sem_definicao(String nomeCompleto, String cpf, String endereco, String email, String celular) throws Exception {
	    	
	    	
	    	this.validateEmail(email);
	    	this.validateNome(nomeCompleto);
	    	
	    	//Regex extrair apenas os números do CPF (111.222.333-44 -> 11122233344)
	    	cpf = cpf.replaceAll("\\D+","");
	    	this.validateCpf(cpf);
	    	
	        this.nomeCompleto = nomeCompleto;
	        this.cpf = cpf;
	        this.endereco = endereco;
	        this.email = email;
	        this.celular = celular;
	    }

	    // Getters


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
	    
	    // Setters
	    
	    private void validateNome(String nome) throws Exception {
	    	if(nome.length() <= 2) {
	    		throw new Exception("O nome do usuário deve ter no mínimo 3 caracteres.");
	    	}
	    	if(nome.length() > 255) {
	    		throw new Exception("O nome do usuário deve ter no máximo 255 caracteres.");
	    	}
	    }
	    
	    private void validateCpf(String cpf) throws Exception {
	    	if(cpf.length() != 11) {
	    		throw new Exception("CPF inválido");
	    	}
	    }
	    
	    private void validateEmail(String email) throws Exception {
	    	if(email.length() > 320) {
	    		throw new Exception("Email não pode ultrapassar de 320 caracteres.");
	    	}
	    	if(email.length() < 3) {
	    		throw new Exception("Email deve ter 3 caracteres ou mais.");
	    	}
	    	if(!email.contains("@")) {
	    		throw new Exception("Email inválido.");
	    	}
	    }
	    
	    
	    
	}

