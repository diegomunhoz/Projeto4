package test.turma;

import to.turma.Professor;
import dao.turma.ProfessorDAO;

/**
*
* @author Diego Munhoz
*/
public class ProfessorUpdate {
	public static void main(String[] args) {
		try {
			ProfessorDAO dao = new ProfessorDAO();
			Professor professor = (Professor) dao.buscaPorId(1L);
			if (professor != null) {
				professor.setTelefone("2222-222");
				dao.saveOrUpdate(professor);
				System.out.println("professor alterado com sucesso".toUpperCase());
			} else {
				System.out.println("professor não encontrado".toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
