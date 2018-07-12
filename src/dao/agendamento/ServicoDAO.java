package dao.agendamento;

import to.agendamento.Servico;
import dao.AbstractDAO;

/**
*
* @author Diego Munhoz
*/
public class ServicoDAO extends AbstractDAO {

	public Servico buscaPorId(Long id) throws Exception {
		return (Servico) super.buscaPorId(Servico.class, id);
	}

}
