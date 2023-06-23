package Sistema_Escola;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BD.Conexao;


public class Principal {
	public static void main(String[] args) {
		
		try {
			Aluno aluno = new Aluno("Gabriel", "11117926907", "123", "teste@teste", "123");
			Aluno aluno2 = new Aluno("Lucas", "111.179.269-07", "1124213", "teste@teste", "123");
			
			String[] resultado = Conexao.Pesquisar("SELECT * FROM alunos", new String[] {"matricula", "nome"});
			
			for(int i = 0; i < resultado.length; i++) {
				System.out.println(resultado[i]);
			}
			
			
			
			System.out.println(aluno2.getCpf());
		} catch(Exception error) {
			System.out.print(error);
		}
		
		
		
	}
}
