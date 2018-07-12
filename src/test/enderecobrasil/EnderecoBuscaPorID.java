package test.enderecobrasil;

import to.enderecobrasil.Endereco;
import dao.enderecobrasil.EnderecoDAO;

/**
*
* @author Diego Munhoz
*/
public class EnderecoBuscaPorID {
	public static void main(String[] args) {
		try {
			Endereco endereco = new EnderecoDAO().buscaPorId(7523L);
			if (endereco == null) {
				System.out.println("endereço não encontrado".toUpperCase());
				return;
			}

			System.out.println("Estado.....: " + endereco.getCidade().getEstado().getNome() + " - "
					+ endereco.getCidade().getEstado().getSigla());
			System.out.println("Cidade.....: " + endereco.getCidade().getNome());
			System.out.println("Bairro.....: " + endereco.getBairro());
			System.out.println("Logradouro.: " + endereco.getLogradouro());
			System.out.println("Complemento: " + endereco.getComplemento());
			System.out.println("CEP........: " + endereco.getCep());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
