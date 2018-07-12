package to.turma;

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
@Table(name = "professores")
public class Professor {

	@Id
	@GeneratedValue
	@Column(name = "id_professor", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "telefone", nullable = true)
	private String telefone;

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
