package dao.turma;

import to.turma.Aluno;
import dao.AbstractDAO;

/**
*
* @author Diego Munhoz
*/
public class AlunoDAO extends AbstractDAO {

	public Aluno buscaPorId(Long id) throws Exception {
		return (Aluno) super.buscaPorId(Aluno.class, id);
	}
}
