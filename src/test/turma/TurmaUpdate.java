package test.turma;

import to.turma.Aluno;
import to.turma.Turma;
import dao.turma.AlunoDAO;
import dao.turma.TurmaDAO;

/**
*
* @author Diego Munhoz
*/
public class TurmaUpdate {
	public static void main(String[] args) {
		try {
			TurmaDAO dao = new TurmaDAO();
			Turma turma = (Turma) dao.buscaPorId(1L);
			if (turma != null) {
				turma.removeAlunos((Aluno) new AlunoDAO().buscaPorId(2L));
				dao.saveOrUpdate(turma);
				System.out.println("turma alterada com sucesso".toUpperCase());
			} else {
				System.out.println("turma não encontrada".toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
