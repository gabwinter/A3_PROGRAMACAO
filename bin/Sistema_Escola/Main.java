package a3;

import java.util.ArrayList;
import java.util.List;

class Aluno {
    private String matricula;
    private String nomeCompleto;
    private String cpf;
    private String endereco;
    private String email;
    private String celular;

    public Aluno(String matricula, String nomeCompleto, String cpf, String endereco, String email, String celular) {
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

class Professor {
    private String cpf;
    private String nomeCompleto;
    private int codigoFuncionario;
    private String endereco;
    private String email;
    private String celular;

    public Professor(String cpf, String nomeCompleto, int codigoFuncionario, String endereco, String email, String celular) {
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.codigoFuncionario = codigoFuncionario;
        this.endereco = endereco;
        this.email = email;
        this.celular = celular;
    }

    // Getters

    public String getCpf() {
        return cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public int getCodigoFuncionario() {
        return codigoFuncionario;
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

class Curso {
    private String codigoCurso;
    private String nomeCurso;
    private int cargaHoraria;
    private String descricao;

    public Curso(String codigoCurso, String nomeCurso, int cargaHoraria, String descricao) {
        this.codigoCurso = codigoCurso;
        this.nomeCurso = nomeCurso;
        this.cargaHoraria = cargaHoraria;
        this.descricao = descricao;
    }

    // Getters

    public String getCodigoCurso() {
        return codigoCurso;
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
}

class Sala {
    private String nome;
    private String local;
    private int capacidadeTotal;

    public Sala(String nome, String local, int capacidadeTotal) {
        this.nome = nome;
        this.local = local;
        this.capacidadeTotal = capacidadeTotal;
    }

    // Getters

    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public int getCapacidadeTotal() {
        return capacidadeTotal;
    }
}

class Turma {
    private Curso curso;
    private Professor professor;
    private Sala sala;
    private String diaSemana;
    private List<Aluno> alunosMatriculados;

    public Turma(Curso curso, Professor professor, Sala sala, String diaSemana) {
        this.curso = curso;
        this.professor = professor;
        this.sala = sala;
        this.diaSemana = diaSemana;
        this.alunosMatriculados = new ArrayList<>();
    }

    public void matricularAluno(Aluno aluno) {
        alunosMatriculados.add(aluno);
    }

    // Getters

    public Curso getCurso() {
        return curso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Sala getSala() {
        return sala;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }
}

class SistemaControleCursos {
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Curso> cursos;
    private List<Sala> salas;
    private List<Turma> turmas;

    public SistemaControleCursos() {
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.salas = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    // Métodos de cadastro

    public void cadastrarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void cadastrarProfessor(Professor professor) {
        professores.add(professor);
    }

    public void cadastrarCurso(Curso curso) {
        cursos.add(curso);
    }

    public void cadastrarSala(Sala sala) {
        salas.add(sala);
    }

    public void cadastrarTurma(Turma turma) {
        turmas.add(turma);
    }

    // Método para listar os cursos ativos

    public void listarCursosAtivos() {
        for (Turma turma : turmas) {
            Curso curso = turma.getCurso();
            Professor professor = turma.getProfessor();
            Sala sala = turma.getSala();
            List<Aluno> alunosMatriculados = turma.getAlunosMatriculados();

            System.out.println("Curso: " + curso.getNomeCurso());
            System.out.println("Professor: " + professor.getNomeCompleto());
            System.out.println("Sala: " + sala.getNome());
            System.out.println("Alunos matriculados:");
            for (Aluno aluno : alunosMatriculados) {
                System.out.println("- " + aluno.getNomeCompleto());
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Criando instâncias de alunos, professores, cursos e salas
        Aluno aluno1 = new Aluno("123", "Fulano de Tal", "123456789", "Rua A", "fulano@example.com", "987654321");
        Aluno aluno2 = new Aluno("456", "Ciclano da Silva", "987654321", "Rua B", "ciclano@example.com", "123456789");

        Professor professor1 = new Professor("987654321", "Professor 1", 1001, "Rua X", "prof1@example.com", "999999999");
        Professor professor2 = new Professor("123456789", "Professor 2", 1002, "Rua Y", "prof2@example.com", "888888888");

        Curso curso1 = new Curso("C001", "Curso 1", 40, "Descrição do Curso 1");
        Curso curso2 = new Curso("C002", "Curso 2", 30, "Descrição do Curso 2");

        Sala sala1 = new Sala("Sala 1", "Local 1", 20);
        Sala sala2 = new Sala("Sala 2", "Local 2", 30);

        // Criando uma instância do sistema de controle de cursos
        SistemaControleCursos sistema = new SistemaControleCursos();

        // Cadastrando alunos, professores, cursos e salas no sistema
        sistema.cadastrarAluno(aluno1);
        sistema.cadastrarAluno(aluno2);

        sistema.cadastrarProfessor(professor1);
        sistema.cadastrarProfessor(professor2);

        sistema.cadastrarCurso(curso1);
        sistema.cadastrarCurso(curso2);

        sistema.cadastrarSala(sala1);
        sistema.cadastrarSala(sala2);

        // Criando uma turma e matriculando alunos
        Turma turma1 = new Turma(curso1, professor1, sala1, "Segunda-feira");
        turma1.matricularAluno(aluno1);
        turma1.matricularAluno(aluno2);

        // Cadastrando a turma no sistema
        sistema.cadastrarTurma(turma1);

        // Listando os cursos ativos
        sistema.listarCursosAtivos();
    }
}