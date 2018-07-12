package dao.turma;

import to.turma.Turma;
import dao.AbstractDAO;

/**
*
* @author Diego Munhoz
*/
public class TurmaDAO extends AbstractDAO {

	public Turma buscaPorId(Long id) throws Exception {
		return (Turma) super.buscaPorId(Turma.class, id);
	}
}
