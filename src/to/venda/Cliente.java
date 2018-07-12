package to.venda;

import java.util.Date;
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
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue
	@Column(name = "id_cliente", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "cpf", nullable = true, unique = true, length = 14)
	private String cpf;

	@Column(name = "telefone", nullable = false, length = 20)
	private String telefone;

	@Column(name = "dt_nascimento", nullable = true)
	private Date dataNascimento;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
	private Set<Venda> listaDeComprasEfetivadas;

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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Set<Venda> getListaDeComprasEfetivadas() {
		return listaDeComprasEfetivadas;
	}

}
