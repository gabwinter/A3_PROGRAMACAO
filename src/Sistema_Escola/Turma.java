package Sistema_Escola;

import java.util.ArrayList;
import java.util.List;

import Sistema_Escola.Aluno;
import Sistema_Escola.Curso;
import Sistema_Escola.Professor;
import Sistema_Escola.Sala;

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

  
