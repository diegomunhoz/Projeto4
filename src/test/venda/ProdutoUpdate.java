package test.venda;

import to.venda.Produto;
import dao.venda.ProdutoDAO;

/**
*
* @author Diego Munhoz
*/
public class ProdutoUpdate {
	public static void main(String[] args) {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			Produto produto = (Produto) dao.buscaPorId(1L);
			if (produto != null) {
				produto.setValor(12.87);
				dao.saveOrUpdate(produto);
				System.out.println("produto alterado com sucesso".toUpperCase());
			} else {
				System.out.println("produto não encontrado".toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
