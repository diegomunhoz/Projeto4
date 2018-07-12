package test.turma;

import to.turma.Aluno;
import dao.turma.AlunoDAO;

/**
*
* @author Diego Munhoz
*/
public class AlunoUpdate {
	public static void main(String[] args) {
		try {
			AlunoDAO dao = new AlunoDAO();
			Aluno aluno = (Aluno) dao.buscaPorId(1L);
			if (aluno != null) {
				aluno.setTelefone("2222-2222");
				dao.saveOrUpdate(aluno);
				System.out.println("aluno alterado com sucesso".toUpperCase());
			} else {
				System.out.println("aluno não encontrado".toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
