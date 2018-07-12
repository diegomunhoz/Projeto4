package test.pagamento;

import to.pagamento.*;
import dao.pagamento.CompraDAO;

/**
*
* @author Diego Munhoz
*/
public class CompraUpdate {
	public static void main(String[] args) {
		try {
			Compra compra = new CompraDAO().buscaPorId(1L);
			for (FormaPagamento fp : compra.getFormasDePagamentos()) {
				compra.pagaParcela(null, fp);
			}
			new CompraDAO().saveOrUpdate(compra);
			System.out.println(" compra atualizada com sucesso ".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
