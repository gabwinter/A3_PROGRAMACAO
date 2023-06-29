package Sistema_Escola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BD.Conexao;

class Aluno extends Pessoa {
    private List<Turma> turmas;
    private int matricula;

    public Aluno(String nomeCompleto, String cpf, String endereco, String email, String celular, List<Turma> turmas) throws Exception {
        super(nomeCompleto, cpf, endereco, email, celular);
        this.turmas = turmas;
    }

    public Aluno(int matricula, String nomeCompleto, String cpf, String endereco, String email, String celular) throws Exception {
        super(nomeCompleto, cpf, endereco, email, celular);
        this.matricula = matricula;
    }

    // Getters e setters

    public int getMatricula() {
        return matricula;
    }

    private void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    // Método para salvar o aluno no banco de dados
    public void salvarNoBanco() {
        String sql = "INSERT INTO alunos (nome, cpf, endereco, email, celular) VALUES (?, ?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = Conexao.obterConexao();
            pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, getNomeCompleto());
            pstm.setString(2, getCpf());
            pstm.setString(3, getEndereco());
            pstm.setString(4, getEmail());
            pstm.setString(5, getCelular());

            pstm.execute();

            int matricula = obterMatricula(pstm);
            this.setMatricula(matricula);

            for (Turma turma : turmas) {
                Aluno_Turma aluno_turma = new Aluno_Turma(this, turma);
                aluno_turma.salvarNoBanco();
            }

        } catch (Exception error) {
            System.out.println(error);
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conexao != null) conexao.close();
            } catch (Exception error) {
                System.out.println(error);
            }
        }
    }

    // Método auxiliar para obter a matrícula gerada ao salvar o aluno no banco de dados
    private int obterMatricula(PreparedStatement pstm) throws SQLException {
        int matricula = 0;
        ResultSet rs = pstm.getGeneratedKeys();
        while (rs.next()) {
            matricula = rs.getInt(1);
        }
        return matricula;
    }

    // Método estático para buscar todos os alunos no banco de dados
    public static List<Aluno> buscarTodos() throws Exception {
        try {
            Connection con = Conexao.obterConexao();
            Statement stmt = con.createStatement();

            List<Aluno> alunos = new ArrayList<>();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Alunos");

            while (rs.next()) {
                int matricula = rs.getInt("matricula");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                String email = rs.getString("email");
                String celular = rs.getString("celular");

                Aluno aluno = new Aluno(matricula, nome, cpf, endereco, email, celular);
                alunos.add(aluno);
            }

            rs.close();
            stmt.close();
            con.close();

            return alunos;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
