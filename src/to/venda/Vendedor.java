package to.venda;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
*
* @author Diego Munhoz
*/
@Entity
@Table(name = "vendedores")
public class Vendedor {

	@Id
	@GeneratedValue
	@Column(name = "id_vendedor", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "cpf", nullable = true, unique = true)
	private String cpf;

	@Column(name = "telefone", nullable = true)
	private String telefone;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "vendedor")
	private Set<Venda> listaDeVendasEfetivadas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Set<Venda> getListaDeVendasEfetivadas() {
		return listaDeVendasEfetivadas;
	}

}
