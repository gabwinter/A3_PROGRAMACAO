package Sistema_Escola;


public class Principal {
	public static void main(String[] args) {
		Aluno aluno = new Aluno("1234", "Gabriel", "123", "123", "123", "123");
		Aluno aluno2 = new Aluno("12323", "Lucas", "123213214", "1124213", "432", "123");
		System.out.println(aluno2.getCpf());
	}
}
