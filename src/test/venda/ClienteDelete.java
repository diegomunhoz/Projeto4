package test.venda;

import dao.venda.ClienteDAO;
import to.venda.Cliente;

/**
*
* @author Diego Munhoz
*/
public class ClienteDelete {
	public static void main(String[] args) {
		try {
			ClienteDAO dao = new ClienteDAO();
			Cliente cliente = dao.buscaPorId(1L);
			if (cliente != null) {
				dao.delete(cliente);
				System.out.println("cliente deletado com sucesso".toUpperCase());
			} else {
				System.out.println("cliente não encontrado".toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
