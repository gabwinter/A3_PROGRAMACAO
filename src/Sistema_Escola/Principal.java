package Sistema_Escola;


public class Principal {
	public static void main(String[] args) {
		
		try {
			Aluno aluno = new Aluno("Gabriel", "11117926907", "123", "teste@teste", "123");
			Aluno aluno2 = new Aluno("Lucas", "111.179.269-07", "1124213", "teste@teste", "123");
			
			
			System.out.println(aluno2.getCpf());
		} catch(Exception error) {
			System.out.print(error);
		}
		
		
		
	}
}
