package to.agendamento;

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
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true, name = "id_funcionario")
	private Long id;

	@Column(nullable = false, name = "nome")
	private String nome;

	@Column(nullable = true, name = "cpf")
	private String cpf;

	@Column(nullable = true, name = "telefone")
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
}
