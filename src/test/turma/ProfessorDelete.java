package test.turma;

import to.turma.Professor;
import dao.turma.ProfessorDAO;

/**
*
* @author Diego Munhoz
*/
public class ProfessorDelete {
	public static void main(String[] args) {
		try {
			Professor professor = (Professor) new ProfessorDAO().buscaPorId(1L);
			if (professor != null) {
				new ProfessorDAO().delete(professor);
				System.out.println("professor deletado com sucesso".toUpperCase());
			} else {
				System.out.println("professor não encontrado".toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
