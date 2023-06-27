package Sistema_Escola;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BD.Conexao;

import java.util.Scanner;



public class Principal {
	public static void main(String[] args) {
		
		
		
		try {
			
			Scanner leitor = new Scanner(System.in);
			
			int resposta = exibeMenuPrincipal(leitor);
			limpaTela();
			
			if(resposta == 1) {
				Sala sala = exibeMenuCadastroSala(leitor);
				sala.salvarNoBanco();
			}
			
			
			leitor.close();
			
			
			
			
		} catch(Exception error) {
			System.out.print("alo");
			System.out.print(error);
		}
	}
	
	
	
	private static int exibeMenuPrincipal(Scanner leitor) {
		System.out.println("1 - Cadastrar salas");
		System.out.println("2 - Cadastrar cursos");
		System.out.println("3 - Cadastrar professores");
		System.out.println("4 - Cadastrar turmas");
		System.out.println("5 - Cadastrar alunos");
		
		System.out.println("\n");
		System.out.println("Selecione: ");
		int resposta = leitor.nextInt();
		
		if(resposta < 1 || resposta > 5) {
			limpaTela();
			resposta = exibeMenuPrincipal(leitor);
		}
		
		return resposta;
	}
	
	private static Sala exibeMenuCadastroSala(Scanner leitor) {
		System.out.println("Cadastro de Salas\n");
		
		try {
			System.out.print("nome: ");
			String nome = leitor.next();
			System.out.println("local: ");
			String local = leitor.next();
			System.out.println("capacidade total: ");
			int capacidadeTotal = leitor.nextInt();
			
			Sala sala = new Sala(nome, local, capacidadeTotal);
			return sala;
		}
		catch(Exception error) {
			System.out.println("Ocorreu um erro: " + error + "\n\n");
			return exibeMenuCadastroSala(leitor);
		}
	}
	
	private static void limpaTela() {
	    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=\n\n");
	}
}
