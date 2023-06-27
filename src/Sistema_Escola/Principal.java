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
			
			run();
			
			
			
		} catch(Exception error) {
			System.out.print("alo");
			System.out.print(error);
		}
	}
	
	private static void run() {
		Scanner leitor = new Scanner(System.in);
		
		int resposta = exibeMenuPrincipal(leitor);
		limpaTela();
		
		if(resposta == 1) {
			Sala sala = exibeMenuCadastroSala(leitor);
			sala.salvarNoBanco();	
			limpaTela();
			run();
		}
		if(resposta == 2) {
			Curso curso = exibeMenuCadastroCurso(leitor);
			curso.salvarNoBanco();
			limpaTela();
			run();
		}
		if(resposta == 3) {
			Professor professor = exibeMenuCadastroProfessor(leitor);
			professor.salvarNoBanco();	
			limpaTela();
			run();
		}
		if(resposta == 4) {
			Sala sala = exibeMenuCadastroSala(leitor);
			sala.salvarNoBanco();	
			limpaTela();
			run();
		}
		if(resposta == 5) {
			Sala sala = exibeMenuCadastroSala(leitor);
			sala.salvarNoBanco();	
			limpaTela();
			run();
		}
		
		
		leitor.close();
		
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
	
	private static Curso exibeMenuCadastroCurso(Scanner leitor) {
		System.out.println("Cadastro de Cursos\n");
		
		try {
			System.out.print("nome: ");
			String nome = leitor.next();
			System.out.println("carga horária: ");
			int cargaHoraria = leitor.nextInt();
			System.out.println("descrição: ");
			String descricao = leitor.next();
			
			Curso curso = new Curso(nome, cargaHoraria, descricao);
			return curso;
		}
		catch(Exception error) {
			System.out.println("Ocorreu um erro: " + error + "\n\n");
			return exibeMenuCadastroCurso(leitor);
		}
	}
	
	private static Professor exibeMenuCadastroProfessor(Scanner leitor) {
		System.out.println("Cadastro de Professores\n");
		
		try {
			System.out.print("nome: ");
			String nome = leitor.next();
			System.out.println("materia: ");
			String materia = leitor.next();
			System.out.println("cpf: ");
			String cpf = leitor.next();
			System.out.println("endereço: ");
			String endereco = leitor.next();
			System.out.println("email: ");
			String email = leitor.next();
			System.out.println("celular: ");
			String celular = leitor.next();
			
			
			Professor professor = new Professor(materia, nome, cpf, endereco, email, celular);
			return professor;
		}
		catch(Exception error) {
			System.out.println("Ocorreu um erro: " + error + "\n\n");
			return exibeMenuCadastroProfessor(leitor);
		}
	}
	
	private static void limpaTela() {
	    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=\n\n");
	}
}
