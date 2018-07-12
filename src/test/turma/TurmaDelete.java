package test.turma;

import to.turma.Turma;
import dao.turma.TurmaDAO;

/**
*
* @author Diego Munhoz
*/
public class TurmaDelete {
	public static void main(String[] args) {
		try {
			Turma turma = (Turma) new TurmaDAO().buscaPorId(2L);
			if (turma != null) {
				new TurmaDAO().delete(turma);
				System.out.println("turma deletada com sucesso".toUpperCase());
			} else {
				System.out.println("turma não encontrada".toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
