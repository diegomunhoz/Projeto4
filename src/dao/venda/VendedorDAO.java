package dao.venda;

import to.venda.Vendedor;
import dao.AbstractDAO;

/**
*
* @author Diego Munhoz
*/
public class VendedorDAO extends AbstractDAO {
	public Vendedor buscaPorId(Long id) throws Exception {
		return (Vendedor) super.buscaPorId(Vendedor.class, id);
	}

}
