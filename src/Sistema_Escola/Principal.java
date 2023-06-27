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
			//Professor professor = new Professor("Matematica", "Gabriel", "11117926907", "123", "teste@teste", "123");
			//Aluno aluno2 = new Aluno("Lucas", "111.179.269-10", "1124213", "teste@teste", "123");
			Sala sala = new Sala("Sala da galera", "C135", 30);
			sala.salvarNoBanco();
			
			//System.out.println(aluno2.getCpf());
		} catch(Exception error) {
			System.out.print(error);
		}
		
		
		
	}
}
