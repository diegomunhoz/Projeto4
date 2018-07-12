package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import to.venda.Cliente;
import to.venda.ItemDaVenda;
import to.venda.Produto;
import to.venda.Venda;
import to.venda.Vendedor;

/**
*
* @author Diego Munhoz
*/
public final class SpringUtil {

	private static final ApplicationContext contexto = new ClassPathXmlApplicationContext(
			"spring/applicationContextSpring.xml");

	public static Cliente getCliente() {
		return (Cliente) contexto.getBean("VendaCliente");
	}

	public static Vendedor getVendedor() {
		return (Vendedor) contexto.getBean("VendaVendedor");
	}

	public static Produto getProduto() {
		return (Produto) contexto.getBean("VendaProduto");
	}

	public static ItemDaVenda getItenDaVenda() {
		return (ItemDaVenda) contexto.getBean("VendaItemDaVenda");
	}

	public static Venda getVenda() {
		return (Venda) contexto.getBean("VendaVenda");
	}

}
