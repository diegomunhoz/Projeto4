package to.pagamento;

import java.util.Date;

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
@Table(name = "formas_de_pagamentos")
public class FormaPagamento {

	@Id
	@GeneratedValue
	@Column(name = "id_forma_pagamento", nullable = false, unique = true)
	private Long id;

	@Column(name = "status_pagamento", nullable = false)
	private String statusPagamento;

	@ManyToOne
	@JoinColumn(name = "id_compra", nullable = false)
	private Compra compra;

	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento;

	@Column(name = "data_pagamento")
	private Date dataPagamento;

	@Column(name = "fp_ct_credito")
	private Boolean cartaoCredito;

	@Column(name = "fp_ct_debito")
	private Boolean cartaoDebito;

	@Column(name = "fp_dinheiro")
	private Boolean dinheiro;

	@Column(name = "valor", nullable = false)
	private Double valor;

	@Column(name = "num_parcela")
	private Integer numeroParcela;

	public FormaPagamento() {
		dataVencimento = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCartaoCreditoToTrue(Compra compra, Double valor, Integer numeroParcela, Date dataVencimento) {

		this.cartaoCredito = true;
		this.cartaoDebito = false;
		this.dinheiro = false;

		this.numeroParcela = numeroParcela;
		this.valor = valor;
		this.compra = compra;

		if (dataVencimento != null) {
			this.dataVencimento = dataVencimento;
		}
	}

	public void setCartaoCreditoToFause() {

		this.cartaoCredito = false;
		this.cartaoDebito = false;
		this.dinheiro = false;

		this.numeroParcela = 0;
		this.valor = null;
		this.compra = null;
	}

	public void setCartaoDebitoToTrue(Compra compra, Double valor, Date dataVencimento) {

		this.cartaoDebito = true;
		this.cartaoCredito = false;
		this.dinheiro = false;

		this.valor = valor;
		this.compra = compra;

		if (dataVencimento != null) {
			this.dataVencimento = dataVencimento;
		}
	}

	public void setCartaoDebitoToFalse() {

		this.cartaoDebito = false;
		this.cartaoCredito = false;
		this.dinheiro = false;

		this.valor = null;
		this.compra = null;

	}

	public void setDinheiroToTrue(Compra compra, Double valor, Date dataVencimento) {

		this.dinheiro = true;
		this.cartaoCredito = false;
		this.cartaoDebito = false;

		this.valor = valor;
		this.compra = compra;

		if (dataVencimento != null) {
			this.dataVencimento = dataVencimento;
		}
	}

	public void setDinheiroToFalse() {

		this.dinheiro = false;
		this.cartaoCredito = false;
		this.cartaoDebito = false;

		this.valor = null;
		this.compra = null;
	}

	public Double getValor() {
		return valor;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public Boolean getDinheiro() {
		return dinheiro;
	}

	public Boolean getCartaoDebito() {
		return cartaoDebito;
	}

	public Boolean getCartaoCredito() {
		return cartaoCredito;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setStatusPagamentoToPago() {
		this.statusPagamento = "PAGO";
		this.dataPagamento = new Date();
	}

	public void setStatusPagamentoToPendente() {
		this.statusPagamento = "PENDENTE";
	}

	public String getStatusPagamento() {
		return statusPagamento;
	}

	public Boolean isPendenteStatusPagamento() {
		return (this.statusPagamento.equals("PENDENTE") ? true : false);
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

}
