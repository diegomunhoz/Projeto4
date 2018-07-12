package test.venda;

import to.venda.Cliente;
import dao.venda.ClienteDAO;

/**
*
* @author Diego Munhoz
*/
public class TestaMetodoGenericoPorCampo {
	public static void main(String[] args) {
		try {
			for (Object objeto : new ClienteDAO().filtroStringGenerico(Cliente.class, "AMANDA", "nome")) {
				Cliente cliente = (Cliente) objeto;
				System.out.println("Nome...: " + cliente.getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
