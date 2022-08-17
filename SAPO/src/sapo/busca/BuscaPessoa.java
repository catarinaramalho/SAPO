package sapo.busca;

import java.util.HashSet;
import java.util.Set;

import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;

public class BuscaPessoa extends BuscaAbstract {
	

	public BuscaPessoa(String criterioBusca) {
		super(criterioBusca);
	}

	@Override
	public Set<Pessoa> busca(PessoaService pessoaService) {
		Set<Pessoa> retorno = new HashSet<>();

		for (String criterio : super.getCriterio()) {
			for (Pessoa pessoaCorrespondente : pessoaService.busca(criterio)) {
				retorno.add(pessoaCorrespondente);
			}
		}
		return retorno;
	}

}
