package test.venda;

import spring.SpringUtil;
import to.venda.Venda;

/**
*
* @author Diego Munhoz
*/
public class VendaSpring {
	public static void main(String[] args) {
		try {
			Venda venda = SpringUtil.getVenda();
			venda.getCliente().setNome("joao".toUpperCase());
			System.out.println(venda.getCliente().getNome());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
