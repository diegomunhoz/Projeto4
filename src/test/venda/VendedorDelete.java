package test.venda;

import dao.venda.VendedorDAO;
import to.venda.Vendedor;

/**
*
* @author Diego Munhoz
*/
public class VendedorDelete {
	public static void main(String[] args) {
		try {
			VendedorDAO dao = new VendedorDAO();
			Vendedor vendedor = (Vendedor) dao.buscaPorId(1L);
			if (vendedor != null) {
				dao.delete(vendedor);
				System.out.println("vendedor deletado com sucesso".toUpperCase());
			} else {
				System.out.println("vendedor não encontrado".toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
