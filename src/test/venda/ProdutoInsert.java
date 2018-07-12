package test.venda;

import to.venda.Produto;
import dao.venda.ProdutoDAO;

/**
*
* @author Diego Munhoz
*/
public class ProdutoInsert {
	public static void main(String[] args) {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			Produto produto = new Produto();

			produto.setNome("arroz".toUpperCase());
			produto.setDescricao("arroz tipo a+".toUpperCase());
			produto.setValor(10.10);
			dao.saveOrUpdate(produto);
			System.out.println("produto.: ".toUpperCase() + produto.getNome() + " salvo com sucesso".toUpperCase());

			produto = new Produto();
			produto.setNome("prego".toUpperCase());
			produto.setDescricao("prego tipo <- ¬¬ ->".toUpperCase());
			produto.setValor(2.0);
			dao.saveOrUpdate(produto);
			System.out.println("produto.: ".toUpperCase() + produto.getNome() + " salvo com sucesso".toUpperCase());

			produto = new Produto();
			produto.setNome("banana".toUpperCase());
			produto.setDescricao("banana maça das geleiras do norte".toUpperCase());
			produto.setValor(100.13);
			dao.saveOrUpdate(produto);
			System.out.println("produto.: ".toUpperCase() + produto.getNome() + " salvo com sucesso".toUpperCase());

			produto = new Produto();
			produto.setNome("macacos".toUpperCase());
			produto.setDescricao("macacos das geleiras do sul".toUpperCase());
			produto.setValor(1023.47);
			dao.saveOrUpdate(produto);
			System.out.println("produto.: ".toUpperCase() + produto.getNome() + " salvo com sucesso".toUpperCase());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
