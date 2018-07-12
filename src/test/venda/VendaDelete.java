package test.venda;

import to.venda.Venda;
import dao.venda.VendaDAO;

/**
*
* @author Diego Munhoz
*/
public class VendaDelete {
	public static void main(String[] args) {
		try {
			Venda venda = (Venda) new VendaDAO().buscaPorId(3L);
			if (venda == null) {
				System.out.println("venda não encontrada".toUpperCase());
			}
			new VendaDAO().delete(venda);
			System.out.println("venda deletada com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
