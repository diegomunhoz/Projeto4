package to.pagamento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
*
* @author Diego Munhoz
*/
@Entity
@Table(name = "compras")
public class Compra {

	@Id
	@GeneratedValue
	@Column(name = "id_compra", nullable = false, unique = true)
	private Long id;

	@Column(name = "data_compra", nullable = false)
	private Date data;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "valor_total", nullable = false)
	private Double valorTotal;

	@Column(name = "status_do_pagamento", nullable = false)
	private String statusPagamento;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "compra")
	@Cascade( { org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private Set<FormaPagamento> formasDePagamentos;

	public Compra() {
		formasDePagamentos = new HashSet<FormaPagamento>();
		data = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Set<FormaPagamento> getFormasDePagamentos() {
		return formasDePagamentos;
	}

	public void addFormaPagamentoDinheiro(Double valor, Date dataVencimento) throws Exception {
		if (valor == null || valor.equals(0)) {
			throw new Exception(" valor de pagamento zerado, verifique o valor ".toUpperCase());
		}
		if (!validaValorMaximoPagamento(valor)) {
			throw new Exception(" valor a ser pago maior que o valor total da venda, verifique a(s) parcela(s) "
					.toUpperCase());
		}
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setDinheiroToTrue(this, valor, dataVencimento);
		formaPagamento.setStatusPagamentoToPendente();
		this.formasDePagamentos.add(formaPagamento);
	}

	public void addFormaPagamentoCartaoDebito(Double valor, Date dataVencimento) throws Exception {
		if (valor == null || valor.equals(0)) {
			throw new Exception(" valor de pagamento zerado, verifique o valor ".toUpperCase());
		}
		if (!validaValorMaximoPagamento(valor)) {
			throw new Exception(" valor a ser pago maior que o valor total da venda, verifique a(s) parcela(s) "
					.toUpperCase());
		}

		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setDinheiroToTrue(this, valor, dataVencimento);
		formaPagamento.setStatusPagamentoToPendente();
		this.formasDePagamentos.add(formaPagamento);
	}

	public void addFormaPagamentoCartaoCredito(Double valor, Integer numeroDeParcelas, Date primeiraParcela)
			throws Exception {
		if (numeroDeParcelas == null || numeroDeParcelas.equals(0)) {
			throw new Exception(" numero parcelas do pagamento zerado, verifique as parcelas ".toUpperCase());
		}
		if (valor == null || valor.equals(0)) {
			throw new Exception(" valor de pagamento zerado, verifique o valor ".toUpperCase());
		}
		if (!validaValorMaximoPagamento(valor)) {
			throw new Exception(" valor a ser pago maior que o valor total da venda, verifique a(s) parcela(s) "
					.toUpperCase());
		}
		if (primeiraParcela == null) {
			primeiraParcela = new Date();
		}

		Double valorDaParcela = valor / numeroDeParcelas;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(primeiraParcela);

		Integer parcela = 1;
		for (FormaPagamento fp : this.formasDePagamentos) {
			if (fp.getCartaoCredito()) {
				if (fp.getNumeroParcela() >= parcela) {
					parcela++;
				}
			}
		}

		for (Integer i = 0; i < numeroDeParcelas; i++) {
			FormaPagamento formaPagamento = new FormaPagamento();
			formaPagamento.setCartaoCreditoToTrue(this, valorDaParcela, parcela, calendar.getTime());
			formaPagamento.setStatusPagamentoToPendente();
			this.formasDePagamentos.add(formaPagamento);
			calendar.add(Calendar.MONTH, 1);
			parcela++;
		}
	}

	private Boolean validaValorMaximoPagamento(Double valor) {

		Double valorDeParcelas = valor;
		for (FormaPagamento fp : formasDePagamentos) {
			valorDeParcelas += fp.getValor();
		}
		if (valorDeParcelas <= this.valorTotal) {
			return true;
		} else {
			return false;
		}
	}

	public Double somaParcelasCadastradas() {
		Double valorDeParcelas = 0.0;
		for (FormaPagamento fp : formasDePagamentos) {
			valorDeParcelas += fp.getValor();
		}
		return valorDeParcelas;
	}

	/**
	 * Se passar o idParcela o metodo paga a parcela pelo id se não ele paga
	 * pela parcela passada
	 * 
	 * @param idParcela
	 * @param pagamento
	 */
	public void pagaParcela(Long idParcela, FormaPagamento pagamento) {
		if (idParcela != null && !idParcela.equals(0)) {
			for (FormaPagamento fp : this.formasDePagamentos) {
				if (fp.getId().equals(idParcela)) {
					fp.setStatusPagamentoToPago();
					return;
				}
			}
		} else {
			pagamento.setStatusPagamentoToPago();
		}
	}

	/**
	 * Se passar o idParcela o metodo deleta pelo id se não ele deleta pela
	 * parcela passada
	 * 
	 * @param idParcela
	 * @param pagamento
	 */
	public void removeParcela(Long idParcela, FormaPagamento pagamento) {
		if (idParcela != null && !idParcela.equals(0)) {
			for (FormaPagamento fp : this.formasDePagamentos) {
				if (fp.getId().equals(idParcela)) {
					this.formasDePagamentos.remove(fp);
					return;
				}
			}
		} else {
			this.formasDePagamentos.remove(pagamento);
		}
	}

	public List<FormaPagamento> getParcelasPendentes() {
		List<FormaPagamento> list = new ArrayList<FormaPagamento>();
		for (FormaPagamento fp : this.formasDePagamentos) {
			if (fp.isPendenteStatusPagamento()) {
				list.add(fp);
			}
		}
		return list;
	}

	public List<FormaPagamento> getParcelasPagas() {
		List<FormaPagamento> list = new ArrayList<FormaPagamento>();
		for (FormaPagamento fp : this.formasDePagamentos) {
			if (!fp.isPendenteStatusPagamento()) {
				list.add(fp);
			}
		}
		return list;
	}

	public Boolean isPendenteStatusPagamento() {
		return (this.statusPagamento.equals("PENDENTE") ? true : false);
	}

	public void setStatusPagamentoToPago() {
		this.statusPagamento = "PAGO";
	}

	public void setStatusPagamentoToPendente() {
		this.statusPagamento = "PENDENTE";
	}

	public String getStatusPagamento() {
		return statusPagamento;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getObservacao() {
		return observacao;
	}

}
