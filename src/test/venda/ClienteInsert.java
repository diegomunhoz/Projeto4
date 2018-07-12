package test.venda;

import java.util.Date;

import to.venda.Cliente;
import util.ErroSQL;
import dao.venda.ClienteDAO;

/**
*
* @author Diego Munhoz
*/
public class ClienteInsert {
	public static void main(String[] args) {
		try {
			Cliente cliente = new Cliente();
			cliente.setNome("bianca alves".toUpperCase());
			cliente.setDataNascimento(new Date());
			cliente.setTelefone("4444-4444");
			cliente.setCpf("111.111.111-88");
			new ClienteDAO().saveOrUpdate(cliente);
			System.out.println("cliente salvo com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(ErroSQL.trataErroSQL(e));
		}
	}

}
