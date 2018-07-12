package test.venda;

import dao.venda.VendedorDAO;
import to.venda.Vendedor;

/**
*
* @author Diego Munhoz
*/
public class VendedorInsert {
	public static void main(String[] args) {
		try {
			Vendedor vendedor = new Vendedor();
			vendedor.setNome("amanda".toUpperCase());
			vendedor.setTelefone("1111-3342");
			new VendedorDAO().saveOrUpdate(vendedor);
			System.out.println("vendedor salvo com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
