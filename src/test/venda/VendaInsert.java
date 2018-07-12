package test.venda;

import to.venda.Produto;
import to.venda.Venda;
import to.venda.Vendedor;
import util.ErroSQL;
import dao.venda.ClienteDAO;
import dao.venda.ProdutoDAO;
import dao.venda.VendaDAO;
import dao.venda.VendedorDAO;

/**
*
* @author Diego Munhoz
*/
public class VendaInsert {
	public static void main(String[] args) {
		try {
			Venda venda = new Venda();

			venda.setCliente(new ClienteDAO().buscaPorId(1L));
			if (venda.getCliente() == null) {
				System.out.println("cliente não encontrado processamento encerrado".toUpperCase());
				return;
			}
			venda.setVendedor((Vendedor) new VendedorDAO().buscaPorId(1L));
			if (venda.getVendedor() == null) {
				System.out.println("vendedor não encontrado processamento encerrado".toUpperCase());
				return;
			}

			Produto produto = (Produto) new ProdutoDAO().buscaPorId(1L);
			if (produto != null) {
				venda.addItensNaVenda(produto, 1);
			}

			produto = (Produto) new ProdutoDAO().buscaPorId(2L);
			if (produto != null) {
				venda.addItensNaVenda(produto, 1);
			}

			produto = (Produto) new ProdutoDAO().buscaPorId(3L);
			if (produto != null) {
				venda.addItensNaVenda(produto, 6);
			}

			produto = (Produto) new ProdutoDAO().buscaPorId(4L);
			if (produto != null) {
				venda.addItensNaVenda(produto, 1);
			}

			produto = (Produto) new ProdutoDAO().buscaPorId(2L);
			if (produto != null) {
				venda.addItensNaVenda(produto, 2);
			}

			new VendaDAO().saveOrUpdate(venda);
			System.out.println("venda salva com sucesso".toUpperCase());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(ErroSQL.trataErroSQL(e));
		}
	}
}
