package to.enderecobrasil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*
* @author Diego Munhoz
*/
@Entity
@Table(name = "estados")
public class Estado {

	@Id
	@GeneratedValue
	@Column(name = "id_estado", unique = true, nullable = false)
	private Long id;

	@Column(name = "nome", unique = true)
	private String nome;

	@Column(name = "uf", nullable = false, unique = true, length = 2)
	private String sigla;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
