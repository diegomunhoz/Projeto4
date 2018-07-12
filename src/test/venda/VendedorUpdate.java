package test.venda;

import to.venda.Vendedor;
import dao.venda.VendedorDAO;

/**
*
* @author Diego Munhoz
*/
public class VendedorUpdate {
	public static void main(String[] args) {
		try {
			VendedorDAO dao = new VendedorDAO();
			Vendedor vendedor = (Vendedor) dao.buscaPorId(1L);
			if (vendedor != null) {
				vendedor.setTelefone("6666-5555");
				dao.saveOrUpdate(vendedor);
				System.out.println("vendedor alterado com sucesso".toUpperCase());
			} else {
				System.out.println("vendedor não encontrado".toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
