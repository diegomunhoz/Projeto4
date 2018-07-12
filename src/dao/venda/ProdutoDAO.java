package dao.venda;

import to.venda.Produto;
import dao.AbstractDAO;

/**
*
* @author Diego Munhoz
*/
public class ProdutoDAO extends AbstractDAO {
	public Produto buscaPorId(Long id) throws Exception {
		return (Produto) super.buscaPorId(Produto.class, id);
	}
	
}
