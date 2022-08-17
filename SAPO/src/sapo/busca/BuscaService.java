package sapo.busca;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;

public class BuscaService {
	PessoaService pessoaService;


	public BuscaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	//FALTAAAA COMPARAAAAR
	public List<Pessoa> busca(BuscaAbstract buscaDesejada) {
		
		Set<Pessoa> resultadosBusca = buscaDesejada.busca(pessoaService);

		List<Pessoa> listaResultados = new ArrayList<>();

		for (Pessoa pessoa : resultadosBusca) {
			listaResultados.add(pessoa);
		}
		
		return listaResultados;
	}
}
