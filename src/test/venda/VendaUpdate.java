package test.venda;

import to.venda.Produto;
import to.venda.Venda;
import dao.venda.ProdutoDAO;
import dao.venda.VendaDAO;

/**
*
* @author Diego Munhoz
*/
public class VendaUpdate {
	public static void main(String[] args) {
		try {
			VendaDAO dao = new VendaDAO();
			Venda venda = (Venda) dao.buscaPorId(1L);
			if (venda == null) {
				System.out.println("venda não encontrada".toUpperCase());
				return;
			}
			Produto produtoAlterar = (Produto) new ProdutoDAO().buscaPorId(2L);
			venda.removeItensNaVenda(produtoAlterar);
			dao.saveOrUpdate(venda);
			System.out.println("venda alterado com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
