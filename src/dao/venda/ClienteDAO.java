package dao.venda;

import to.venda.Cliente;
import dao.AbstractDAO;

/**
*
* @author Diego Munhoz
*/
public class ClienteDAO extends AbstractDAO {

	public Cliente buscaPorId(Long id) throws Exception {
		return (Cliente) super.buscaPorId(Cliente.class, id);
	}
}
