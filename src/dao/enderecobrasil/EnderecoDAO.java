package dao.enderecobrasil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import to.enderecobrasil.Endereco;
import util.HibernateUtil;
import dao.AbstractDAO;

/**
*
* @author Diego Munhoz
*/
public class EnderecoDAO extends AbstractDAO {

	public Endereco buscaPorId(Long id) throws Exception {
		return (Endereco) super.buscaPorId(Endereco.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Endereco> filtroGenerico(Endereco endereco) throws Exception {
		super.sessao = null;
		ArrayList<Endereco> lista = new ArrayList<Endereco>();
		try {
			super.sessao = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = sessao.createCriteria(Endereco.class);
			if (endereco != null) {

				if (endereco.getId() != null && endereco.getId() != 0) {
					criteria.add(Restrictions.eq("id", endereco.getId()));
				}

				if (endereco.getBairro() != null && !endereco.getBairro().isEmpty()) {
					criteria.add(Restrictions.ilike("bairro", "%" + endereco.getBairro() + "%"));
				}

				if (endereco.getCep() != null && !endereco.getCep().isEmpty()) {
					criteria.add(Restrictions.like("cep", endereco.getCep()));
				}

				if (endereco.getComplemento() != null && !endereco.getComplemento().isEmpty()) {
					criteria.add(Restrictions.ilike("complemento", "%" + endereco.getComplemento() + "%"));
				}

				if (endereco.getLogradouro() != null && !endereco.getLogradouro().isEmpty()) {
					criteria.add(Restrictions.ilike("logradouro", "%" + endereco.getLogradouro() + "%"));
				}

				if (endereco.getCidade() != null) {
					Criteria criterioCidade = criteria.createAlias("cidade", "c");
					if (endereco.getCidade().getId() != null && endereco.getCidade().getId() != 0) {
						criterioCidade.add(Restrictions.eq("c.id", endereco.getCidade().getId()));
					}

					if (endereco.getCidade().getNome() != null && !endereco.getCidade().getNome().isEmpty()) {
						criterioCidade.add(Restrictions.ilike("c.nome", "%" + endereco.getCidade().getNome() + "%"));
					}

					if (endereco.getCidade().getEstado() != null) {
						Criteria criterioEstado = criteria.createAlias("c.estado", "e");
						if (endereco.getCidade().getEstado().getId() != null
								&& endereco.getCidade().getEstado().getId() != 0) {
							criterioEstado.add(Restrictions.eq("e.id", endereco.getCidade().getEstado().getId()));
						}

						if (endereco.getCidade().getEstado().getNome() != null
								&& !endereco.getCidade().getEstado().getNome().isEmpty()) {
							criterioEstado.add(Restrictions.ilike("e.nome", "%"
									+ endereco.getCidade().getEstado().getNome() + "%"));
						}

						if (endereco.getCidade().getEstado().getSigla() != null
								&& !endereco.getCidade().getEstado().getSigla().isEmpty()) {
							criterioEstado.add(Restrictions.ilike("e.sigla", "%"
									+ endereco.getCidade().getEstado().getSigla() + "%"));
						}
					}
				}
			}
			criteria.addOrder(Order.asc("bairro"));

			lista = (ArrayList<Endereco>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		} catch (Exception e) {
			lista = null;
		} finally {
			sessao.close();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Endereco> paginar(Integer pagina, Integer qtdRegistros, Endereco endereco) {
		super.sessao = null;
		ArrayList<Endereco> lista = new ArrayList<Endereco>();
		try {
			super.sessao = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = sessao.createCriteria(Endereco.class);
			criteria.setFirstResult((pagina - 1) * qtdRegistros);
			criteria.setMaxResults(qtdRegistros);

			if (endereco != null) {

				if (endereco.getId() != null && endereco.getId() != 0) {
					criteria.add(Restrictions.eq("id", endereco.getId()));
				}

				if (endereco.getBairro() != null && !endereco.getBairro().isEmpty()) {
					criteria.add(Restrictions.ilike("bairro", "%" + endereco.getBairro() + "%"));
				}

				if (endereco.getCep() != null && !endereco.getCep().isEmpty()) {
					criteria.add(Restrictions.like("cep", endereco.getCep()));
				}

				if (endereco.getComplemento() != null && !endereco.getComplemento().isEmpty()) {
					criteria.add(Restrictions.ilike("complemento", "%" + endereco.getComplemento() + "%"));
				}

				if (endereco.getLogradouro() != null && !endereco.getLogradouro().isEmpty()) {
					criteria.add(Restrictions.ilike("logradouro", "%" + endereco.getLogradouro() + "%"));
				}

				if (endereco.getCidade() != null) {
					Criteria criterioCidade = criteria.createAlias("cidade", "c");
					if (endereco.getCidade().getId() != null && endereco.getCidade().getId() != 0) {
						criterioCidade.add(Restrictions.eq("c.id", endereco.getCidade().getId()));
					}

					if (endereco.getCidade().getNome() != null && !endereco.getCidade().getNome().isEmpty()) {
						criterioCidade.add(Restrictions.ilike("c.nome", "%" + endereco.getCidade().getNome() + "%"));
					}

					if (endereco.getCidade().getEstado() != null) {
						Criteria criterioEstado = criteria.createAlias("c.estado", "e");
						if (endereco.getCidade().getEstado().getId() != null
								&& endereco.getCidade().getEstado().getId() != 0) {
							criterioEstado.add(Restrictions.eq("e.id", endereco.getCidade().getEstado().getId()));
						}

						if (endereco.getCidade().getEstado().getNome() != null
								&& !endereco.getCidade().getEstado().getNome().isEmpty()) {
							criterioEstado.add(Restrictions.ilike("e.nome", "%"
									+ endereco.getCidade().getEstado().getNome() + "%"));
						}

						if (endereco.getCidade().getEstado().getSigla() != null
								&& !endereco.getCidade().getEstado().getSigla().isEmpty()) {
							criterioEstado.add(Restrictions.ilike("e.sigla", "%"
									+ endereco.getCidade().getEstado().getSigla() + "%"));
						}
					}
				}
			}
			criteria.addOrder(Order.asc("cidade"));
			criteria.addOrder(Order.asc("bairro"));
			criteria.addOrder(Order.asc("id"));

			lista = (ArrayList<Endereco>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		} catch (Exception e) {
			lista = null;
		} finally {
			sessao.close();
		}
		return lista;
	}
}
