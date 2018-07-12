package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

/**
*
* @author Diego Munhoz
*/
public abstract class AbstractDAO {

	protected Session sessao = null;
	private Transaction transacao = null;

	/**
	 * 
	 * Este método recebe um objeto que referencia uma tabela no banco e salva
	 * se não existir nenhum registro equivalente, ou altera o registro
	 * equivalente ao objeto.
	 * 
	 * @param pObjeto
	 * @throws Exception
	 */
	public void saveOrUpdate(Object pObjeto) throws Exception {
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.saveOrUpdate(pObjeto);
			transacao.commit();
		} catch (Exception e) {
			transacao.rollback();
			throw e;
		} finally {
			sessao.close();
		}
	}

	/**
	 * Este Método recebe um objeto referente a uma tabela do banco, procura a
	 * tupla referente e exclui da base o registro.
	 * 
	 * @param pObjeto
	 * @throws Exception
	 */

	public void delete(Object pObjeto) throws Exception {
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(pObjeto);
			transacao.commit();
		} catch (Exception e) {
			transacao.rollback();
			throw e;
		} finally {
			sessao.close();
		}
	}

	/**
	 * Método genérico de busca por id.
	 * 
	 * @param classe
	 * @param id
	 * @return Objeto
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected Object buscaPorId(Class classe, Long id) throws Exception {
		Object obejto = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			obejto = sessao.get(classe, id);
		} catch (Exception e) {
			obejto = null;
			throw e;
		} finally {
			sessao.close();
		}
		return obejto;
	}

	/**
	 * Método responsável por fazer pesquisa em qualquer tabela em um campo
	 * String, trazendo todos os resultados que tenham o parâmetro passado.
	 * 
	 * @param classe
	 * @param stringDePesquisa
	 * @param nomeCampoTabela
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Object> filtroStringGenerico(Class classe, String stringDePesquisa, String nomeCampoTabela)
			throws Exception {
		sessao = null;
		ArrayList<Object> lista;
		try {

			sessao = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = sessao.createCriteria(classe);
			criteria.add(Restrictions.ilike(nomeCampoTabela, "%" + stringDePesquisa + "%"));
			lista = (ArrayList<Object>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		} catch (Exception e) {
			lista = null;
		} finally {
			sessao.close();
		}
		return lista;
	}

	/**
	 * Método resposável por trazer todos os dados da tabela, sem nenhum filtro.
	 * 
	 * @param classe
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAll(Class classe) throws Exception {
		sessao = null;
		ArrayList<Object> lista = new ArrayList<Object>();
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = sessao.createCriteria(classe);
			lista = (ArrayList<Object>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (Exception e) {
			lista = null;
		} finally {
			sessao.close();
		}
		return lista;
	}
}