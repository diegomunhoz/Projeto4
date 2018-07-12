package dao.turma;

import to.turma.Professor;
import dao.AbstractDAO;

/**
*
* @author Diego Munhoz
*/
public class ProfessorDAO extends AbstractDAO {
	public Professor buscaPorId(Long id) throws Exception {
		return (Professor) super.buscaPorId(Professor.class, id);
	}
}
