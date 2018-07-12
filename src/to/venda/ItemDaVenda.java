package to.venda;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
*
* @author Diego Munhoz
*/
@Entity
@Table(name = "itens_da_venda")
public class ItemDaVenda {

	@Id
	@GeneratedValue
	@Column(name = "id_item", nullable = false, unique = true)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_venda", nullable = false)
	private Venda venda;

	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;

	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;

	@Column(name = "vl_unit", nullable = false)
	private Double valorUnitario;

	public Double getSubTotal() {
		return (this.valorUnitario * this.quantidade);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}
