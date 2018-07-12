package test.venda;

import java.util.List;

import to.venda.Produto;
import dao.venda.VendaDAO;

/**
*
* @author Diego Munhoz
*/
public class VendaBuscaProdutoNaoVendido {
	public static void main(String[] args) {
		try {
			List<Produto> lista = new VendaDAO().buscaProdutosNaoVendidos(null);

			if (lista == null || lista.isEmpty()) {
				System.out.println(" produto já foi vendido".toUpperCase());
				return;
			}

			for (Produto produto : lista) {
				System.out.println(" Produto..: " + produto.getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
