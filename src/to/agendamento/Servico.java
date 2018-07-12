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
@Table(name = "servicos")
public class Servico {

	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true, name = "id_servico")
	private Long id;

	@Column(nullable = false, unique = true, name = "nome")
	private String nome;

	@Column(unique = true, name = "descricao")
	private String decricao;

	@Column(nullable = false, name = "tempo_necessario")
	private Integer tempoNecessario;

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

	public String getDecricao() {
		return decricao;
	}

	public void setDecricao(String decricao) {
		this.decricao = decricao;
	}

	public Integer getTempoNecessario() {
		return tempoNecessario;
	}

	public void setTempoNecessario(Integer tempoNecessario) {
		this.tempoNecessario = tempoNecessario;
	}
}
