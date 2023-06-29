package Sistema_Escola;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Program {
	public static void main(String[] args) {		
		execute();		
	}
	
	private static void execute() {
		try {
			Scanner scanner = new Scanner(System.in);
			
			int response = displayMainMenu(scanner);
			limpaTela();
			
			if(response == 1) {
				Sala sala = displayRoomRegistrationMenu(scanner);
				sala.salvarNoBanco();	
				limpaTela();
				execute();
			}
			if(response == 2) {
				Curso curso = displayCourseRegistrationMenu(scanner);
				curso.salvarNoBanco();
				limpaTela();
				execute();
			}
			if(response == 3) {
				Professor professor = displayTeacherRegistrationMenu(scanner);
				professor.salvarNoBanco();	
				limpaTela();
				execute();
			}
			if(response == 4) {
				Turma turma = displayClassRegistrationMenu(scanner);
				turma.salvarNoBanco();	
				limpaTela();
				execute();
			}
			if(response == 5) {
				Aluno aluno = displayStudentRegistrationMenu(scanner);
				aluno.salvarNoBanco();	
				limpaTela();
				execute();
			}
			
			
			scanner.close();
		} catch(Exception error) {
			System.out.print("\n\nOcorreu um erro: " + error.getMessage() + "\n\n");
			execute();
		}	
	}

	
	private static int displayMainMenu(Scanner leitor) {
		System.out.print("--- Menu Principal ---\n\n");
		
		System.out.println("1 - Cadastrar salas");
		System.out.println("2 - Cadastrar cursos");
		System.out.println("3 - Cadastrar professores");
		System.out.println("4 - Cadastrar turmas");
		System.out.println("5 - Cadastrar alunos");
		
		
		
		System.out.print("\n");
		System.out.print("Escolha uma opção (1,2,3,4,5): ");
		int resposta = Integer.parseInt(leitor.nextLine());
		
		if(resposta < 1 || resposta > 5) {
			limpaTela();
			resposta = displayMainMenu(leitor);
		}
		
		return resposta;
	}
	
	private static Sala displayRoomRegistrationMenu(Scanner leitor) {
		System.out.println("--- Cadastro de Salas ---\n");
		
		try {
			System.out.print("nome: ");
			String nome = leitor.nextLine();
			System.out.print("local: ");
			String local = leitor.nextLine();
			System.out.print("capacidade total: ");
			int capacidadeTotal = leitor.nextInt();
			
			Sala sala = new Sala(nome, local, capacidadeTotal);
			
			System.out.print("\n- - - - - - - - - - - - - - -\n");
			System.out.print("Sala " + sala.getNome() + " cadastrada!");
			System.out.print("\n- - - - - - - - - - - - - - -\n");
			return sala;
		}
		catch(Exception error) {
			System.out.println("Ocorreu um erro: " + error + "\n\n");
			return displayRoomRegistrationMenu(leitor);
		}
	}
	
	private static Curso displayCourseRegistrationMenu(Scanner leitor) {
		System.out.println("--- Cadastro de Cursos ---\n");
		
		try {
			System.out.print("nome: ");
			String nome = leitor.nextLine();
			System.out.print("carga horária: ");
			int cargaHoraria = Integer.parseInt(leitor.nextLine());
			System.out.print("descrição: ");
			String descricao = leitor.nextLine();
			
			Curso curso = new Curso(nome, cargaHoraria, descricao);
			System.out.print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
			System.out.print("Curso cadastrado com sucesso!");
			System.out.print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
			return curso;
		}
		catch(Exception error) {
			throw error;
		}
	}
	
	private static Professor displayTeacherRegistrationMenu(Scanner leitor) throws Exception {
		System.out.println("--- Cadastro de Professores ---\n");

		try {
			
			System.out.print("nome: ");
			String nome = leitor.nextLine();
			
			System.out.print("cpf: ");
			String cpf = leitor.nextLine();
			
			System.out.print("endereço: ");
			String endereco = leitor.nextLine();
			
			System.out.print("email: ");
			String email = leitor.nextLine();
			
			System.out.print("celular: ");
			String celular = leitor.nextLine();		
			
			System.out.print("\nSelecione os cursos que o professor \"" + nome + "\" ministra: \n\n");
			
			List<Curso> cursos = Curso.buscarTodos();
			Curso cursoDoProfessor = null;
			
			if(cursos.size() == 0) {
				throw new Exception("Não há cursos cadastrados. Cadastre os cursos antes.");
			}
			else {
				int indice = 1;
				for (Curso curso : cursos) {
		            System.out.println(indice + " - " + curso.getNomeCurso());
		            indice++;
		        }
				
				System.out.print("Resposta: ");
				String resposta = leitor.nextLine();
				
				int indiceDoCurso = (Integer.parseInt(resposta) - 1);
				cursoDoProfessor = cursos.get(indiceDoCurso);
			}
			
			
			if(cursoDoProfessor == null) throw new Exception("É necessário selecionar um curso.");
			
			Professor professor = new Professor(nome, cpf, endereco, email, celular, cursoDoProfessor);
			System.out.print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
			System.out.print("Professor cadastrado com sucesso!");
			System.out.print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
			return professor;
		}
		catch(Exception error) {
			throw new Exception(error);
		}
	}
	
	private static Turma displayClassRegistrationMenu(Scanner leitor) throws Exception {
		System.out.println("--- Cadastro de Turmas ---\n");
		
		try {
			System.out.print("Dia da semana que a turma terá aula: \n\n");
			System.out.print("1 - Domingo\n");
			System.out.print("2 - Segunda-feira\n");
			System.out.print("3 - Terça-feira\n");
			System.out.print("4 - Quarta-feira\n");
			System.out.print("5 - Quinta-feira\n");
			System.out.print("6 - Sexta-feira\n");
			System.out.print("7 - Sábado\n");
			String diaDaSemanaResposta = leitor.nextLine();
			String diaDaSemana = "Segunda";
			
			if(diaDaSemanaResposta.equals("1")) diaDaSemana = "Domingo";
			else if(diaDaSemanaResposta.equals("3")) diaDaSemana = "Terca";
			else if(diaDaSemanaResposta.equals("4")) diaDaSemana = "Quarta";
			else if(diaDaSemanaResposta.equals("5")) diaDaSemana = "Quinta";
			else if(diaDaSemanaResposta.equals("6")) diaDaSemana = "Sexta";
			else if(diaDaSemanaResposta.equals("7")) diaDaSemana = "Sabado";
			
			
			//Exibe os professores
			List<Professor> professores = Professor.buscarTodos();
			Professor professorDaTurma = null;
			
			if(professores == null || professores.size() == 0) {
				throw new Exception("Não há professores cadastrados. Cadastre os professores antes.");
			}
			else {
				System.out.print("\nSelecione o professor responsável pela turma\n");
				
				int indice = 1;
				for (Professor professor : professores) {
		            System.out.println(indice + " - " + professor.getNomeCompleto());
		            indice++;
		        }

				
				System.out.print("Resposta: ");
				String resposta = leitor.nextLine();
				
				int indiceDoProfessor = (Integer.parseInt(resposta) - 1);
				professorDaTurma = professores.get(indiceDoProfessor);
			}
			
			//Exibe as salas
			List<Sala> salas = Sala.buscarTodosSemTurma();
			Sala salaDaTurma = null;
			
			if(salas == null || salas.size() == 0) {
				throw new Exception("Não há salas cadastradas ou disponíveis. Cadastre novas salas antes.");
			}
			else {
				System.out.print("\nSelecione a sala da turma\n");
				
				int indice = 1;
				for (Sala sala : salas) {
		            System.out.println(indice + " - " + sala.getNome());
		            indice++;
		        }

				
				System.out.print("Resposta: ");
				String resposta = leitor.nextLine();
				
				int indiceDaSala = (Integer.parseInt(resposta) - 1);
				salaDaTurma = salas.get(indiceDaSala);
			}
			
			Turma turma = new Turma(professorDaTurma, salaDaTurma, diaDaSemana);
			System.out.print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
			System.out.print("Turma cadastrada com sucesso!");
			System.out.print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
			return turma;
			
		}
		catch(Exception error) {
			throw error;
		}
	}
	
	
	private static Aluno displayStudentRegistrationMenu(Scanner leitor) throws Exception {
		System.out.println("Cadastro de Alunos\n");

		try {
			
			System.out.print("nome: ");
			String nome = leitor.nextLine();
			
			System.out.print("cpf: ");
			String cpf = leitor.nextLine();
			
			System.out.print("endereço: ");
			String endereco = leitor.nextLine();
			
			System.out.print("email: ");
			String email = leitor.nextLine();
			
			System.out.print("celular: ");
			String celular = leitor.nextLine();		
			
			System.out.print("\nSelecione as turmas que o aluno \"" + nome + "\" faz parte: \n\n");
			
			List<Turma> turmas = Turma.buscarTodos();
			List<Turma> turmasDoAluno = new ArrayList();
			
			if(turmas == null || turmas.size() == 0){
				throw new Exception("Não há turmas cadastradas. Cadastre as turmas antes.");
			}
			else {
				System.out.print("\n   dia da semana | professor | sala\n");
				System.out.print("--------------------------------------\n\n");
				int indice = 1;
				for (Turma turma : turmas) {
		            System.out.println(indice + " - " + turma.getDiaSemana() + " | " + turma.getProfessor().getNomeCompleto() + " | " + turma.getSala().getNome());
		            indice++;
		        }
				
				System.out.print("\nPara selecionar mais de 1 turma digite da seguinte forma: 1 3 4 5\n");
				System.out.print("Resposta: ");
				String[] respostaTurmas = leitor.nextLine().split(" ");
				
				for(int i = 0; i < respostaTurmas.length; i++) {
					int indiceDaTurma = (Integer.parseInt(respostaTurmas[i]) - 1);
					Turma turmaSelecionada = turmas.get(indiceDaTurma);
					turmasDoAluno.add(turmaSelecionada);
				}
			}
			
			
			if(turmasDoAluno.size() == 0) throw new Exception("É necessário selecionar pelo menos 1 turma.");
			
			Aluno aluno = new Aluno(nome, cpf, endereco, email, celular, turmasDoAluno);
			System.out.print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
			System.out.print("Aluno cadastrado com sucesso!");
			System.out.print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
			return aluno;
		}
		catch(Exception error) {
			throw new Exception(error);
		}
	}
	private static void limpaTela() {
		System.out.print("\n\n");
		System.out.print("\033[H\033[2J");
	    System.out.flush();
	}
}
