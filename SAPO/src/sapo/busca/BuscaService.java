package sapo.busca;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import sapo.atividade.AtividadeService;
import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;

public class BuscaService {
	PessoaService pessoaService;
	AtividadeService atividadeService;


	public BuscaService(PessoaService pessoaService, AtividadeService atividadeService) {
		this.pessoaService = pessoaService;
		this.atividadeService = atividadeService;
	}

	
	public List<Pessoa> buscaPessoas(BuscaPessoa buscaDesejada) {
		
		Set<Pessoa> resultadosBusca = buscaDesejada.busca(pessoaService);

		List<Pessoa> listaResultados = new ArrayList<>();

		for (Pessoa pessoa : resultadosBusca) {
			listaResultados.add(pessoa);
		}
		
		//ORDENAÇÃO
		
		return listaResultados;
	}


	public List<String> buscaAtividades(BuscaAtividade buscaDesejada) {
		Set<String> resultadosBusca = buscaDesejada.busca(atividadeService);

		List<String> listaResultados = new ArrayList<>();

		for (String representacao : resultadosBusca) {
			listaResultados.add(representacao);
		}
		
		//ORDENAÇÃO
		
		return listaResultados;
	}
}
