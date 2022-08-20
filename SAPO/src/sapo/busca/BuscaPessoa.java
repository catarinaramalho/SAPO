package sapo.busca;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;

public class BuscaPessoa extends BuscaAbstract {
	private Set<Pessoa> resultado;
	
	public BuscaPessoa(String criterioBusca) {
		super(criterioBusca);
	}

	public Set<Pessoa> busca(PessoaService pessoaService) {
		this.resultado = pessoaService.busca(super.getCriterio());
		return this.resultado;
	}

	@Override
	public String[] representaBusca() {
		List<String> representacao = new ArrayList<>();
		representacao.add("PESSOA");
		for (Pessoa pessoa : this.resultado) {
			representacao.add(pessoa.toString());
		}

		String[] retorno = new String[representacao.size()];
		for (int i = 0; i < retorno.length; i++) {
			retorno[i] = representacao.get(i);
		}

		return retorno;
	}


	

}
