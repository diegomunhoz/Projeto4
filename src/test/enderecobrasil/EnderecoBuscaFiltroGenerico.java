package test.enderecobrasil;

import java.util.List;

import to.enderecobrasil.Cidade;
import to.enderecobrasil.Endereco;
import to.enderecobrasil.Estado;
import dao.enderecobrasil.EnderecoDAO;

/**
*
* @author Diego Munhoz
*/
public class EnderecoBuscaFiltroGenerico {
	public static void main(String[] args) {
		try {
			Endereco endereco = new Endereco();
			endereco.setCidade(new Cidade());
			endereco.getCidade().setEstado(new Estado());
			endereco.getCidade().getEstado().setSigla("sp".toUpperCase());
			endereco.getCidade().setNome("ourinhos".toUpperCase());
			endereco.setLogradouro("vini".toUpperCase());
			endereco.setBairro("jardim".toUpperCase());

			
			List<Endereco> enderecos = new EnderecoDAO().filtroGenerico(endereco);
			if (enderecos == null || enderecos.size() == 0) {
				System.out.println("endereço não encontrados".toUpperCase());
			}
			for (Endereco e : enderecos) {
				System.out.println("\n--------------------------------------------------------------------------");
				System.out.println("Estado.....: " + e.getCidade().getEstado().getNome() + " - "
						+ e.getCidade().getEstado().getSigla());
				System.out.println("Cidade.....: " + e.getCidade().getNome());
				System.out.println("Bairro.....: " + e.getBairro());
				System.out.println("Logradouro.: " + e.getLogradouro());
				System.out.println("Complemento: " + e.getComplemento());
				System.out.println("CEP........: " + e.getCep());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
