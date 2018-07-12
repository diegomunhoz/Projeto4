package to.venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
*
* @author Diego Munhoz
*/
@Entity
@Table(name = "vendas")
public class Venda {

	@Id
	@GeneratedValue
	@Column(name = "id_venda", nullable = false, unique = true)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_vendedor", nullable = false)
	private Vendedor vendedor;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@Column(name = "dt_venda", nullable = false)
	private Date dataDaVenda;

	@Column(name = "vl_total", nullable = false)
	private Double valorTotal;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "venda")
	@Cascade( { org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private Set<ItemDaVenda> itensDaVenda;

	public Venda() {
		itensDaVenda = new HashSet<ItemDaVenda>();
		dataDaVenda = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataDaVenda() {
		return dataDaVenda;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public Set<ItemDaVenda> getItensDaVenda() {
		return itensDaVenda;
	}

	public void setItensDaVenda(Set<ItemDaVenda> itensDaVenda) {
		this.itensDaVenda = itensDaVenda;
	}

	public void addItensNaVenda(Produto produto, Integer quantidade) {

		// se o produto ja existir atualiza a quantidade
		for (ItemDaVenda i : this.itensDaVenda) {
			if ((i.getProduto().getId() != null) && (i.getProduto().getId().equals(produto.getId()))) {
				i.setQuantidade(i.getQuantidade() + quantidade);
				totalizarVenda();
				return;
			}
		}

		// adiciona um novo produto
		ItemDaVenda item = new ItemDaVenda();
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		item.setValorUnitario(produto.getValor());
		item.setVenda(this);
		itensDaVenda.add(item);
		totalizarVenda();
	}

	public void removeItensNaVenda(Produto produto) {
		for (ItemDaVenda i : this.itensDaVenda) {
			if ((i.getProduto().getId() != null) && (i.getProduto().getId().equals(produto.getId()))) {
				itensDaVenda.remove(i);
				totalizarVenda();
				return;
			}
		}
	}

	private void totalizarVenda() {
		valorTotal = 0.0;
		for (ItemDaVenda item : this.itensDaVenda) {
			valorTotal += ((item.getValorUnitario()) * (item.getQuantidade()));
		}
	}

	public List<ItemDaVenda> getItensDaVendaList() {
		List<ItemDaVenda> lista = new ArrayList<ItemDaVenda>();
		for (ItemDaVenda i : this.itensDaVenda) {
			lista.add(i);
		}
		return lista;
	}

}
