package test.turma;

import to.turma.Aluno;
import dao.turma.AlunoDAO;

/**
*
* @author Diego Munhoz
*/
public class AlunoDelete {
	public static void main(String[] args) {
		try {
			Aluno aluno = (Aluno) new AlunoDAO().buscaPorId(1L);
			if (aluno != null) {
				new AlunoDAO().delete(aluno);
				System.out.println("aluno deletado com sucesso".toUpperCase());
			} else {
				System.out.println("aluno não encontrado".toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
