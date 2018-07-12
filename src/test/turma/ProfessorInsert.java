package test.turma;

import to.turma.Professor;
import dao.turma.ProfessorDAO;

/**
*
* @author Diego Munhoz
*/
public class ProfessorInsert {
	public static void main(String[] args) {
		try {
			Professor professor = new Professor();
			professor.setNome("mariana".toUpperCase());
			professor.setTelefone("1111-1111");
			new ProfessorDAO().saveOrUpdate(professor);
			System.out.println("professor salvo com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			Professor professor = new Professor();
			professor.setNome("cecilia".toUpperCase());
			professor.setTelefone("2222-2222");
			new ProfessorDAO().saveOrUpdate(professor);
			System.out.println("professor salvo com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
