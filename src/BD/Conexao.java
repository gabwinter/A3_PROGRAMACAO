package BD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
	private static final String URL = "jdbc:mysql://localhost:3306/sistemamatricula?serverTimezone=America/Sao_Paulo";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection obterConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
