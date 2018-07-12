package test.venda;

import dao.venda.ProdutoDAO;
import to.venda.Produto;

/**
*
* @author Diego Munhoz
*/
public class ProdutoDelete {
	public static void main(String[] args) {
		try {
			Produto produto = (Produto) new ProdutoDAO().buscaPorId(1L);
			if (produto != null) {
				new ProdutoDAO().delete(produto);
				System.out.println("produto deletado com sucesso".toUpperCase());
			} else {
				System.out.println("produto não encontrado".toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
