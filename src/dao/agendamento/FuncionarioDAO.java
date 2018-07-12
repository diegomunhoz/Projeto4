package dao.agendamento;

import to.agendamento.Funcionario;
import dao.AbstractDAO;

/**
*
* @author Diego Munhoz
*/
public class FuncionarioDAO extends AbstractDAO {

	public Funcionario buscaPorId(Long id) throws Exception {
		return (Funcionario) super.buscaPorId(Funcionario.class, id);
	}

}
