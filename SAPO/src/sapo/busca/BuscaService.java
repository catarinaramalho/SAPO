package sapo.busca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import sapo.atividade.AtividadeService;
import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;
import sapo.tarefa.TarefaService;

public class BuscaService {
	PessoaService pessoaService;
	AtividadeService atividadeService;
	TarefaService tarefaService;
	HistoricoBuscas historicoBuscas;


	public BuscaService(PessoaService pessoaService, AtividadeService atividadeService) {
		this.pessoaService = pessoaService;
		this.atividadeService = atividadeService;
		this.historicoBuscas = new HistoricoBuscas();
	}

	
	public List<Pessoa> buscaPessoas(BuscaPessoa buscaDesejada) {
		Set<Pessoa> resultadosBusca = buscaDesejada.busca(pessoaService);

		this.historicoBuscas.registaBusca(buscaDesejada.representaBusca());
		
		List<Pessoa> listaResultados = new ArrayList<>();

		for (Pessoa pessoa : resultadosBusca) {
			listaResultados.add(pessoa);
		}
		
		//ORDENAÇÃO
		
		return listaResultados;
	}


	public List<String> buscaAtividades(BuscaAtividade buscaDesejada) {
		Set<String> resultadosBusca = buscaDesejada.busca(atividadeService);

		this.historicoBuscas.registaBusca(buscaDesejada.representaBusca());
		
		List<String> listaResultados = new ArrayList<>();

		for (String representacao : resultadosBusca) {
			listaResultados.add(representacao);
		}
		
		
		return listaResultados;
	}
	
	public List<String> buscaTarefas(BuscaTarefa buscaDesejada) {
		Set<String> resultadosBusca = buscaDesejada.busca(this.tarefaService);

		this.historicoBuscas.registaBusca(buscaDesejada.representaBusca());
		
		List<String> listaResultados = new ArrayList<>();

		for (String representacao : resultadosBusca) {
			listaResultados.add(representacao);
		}
		
		return listaResultados;
	}
	
	public List<String> buscaTarefas(BuscaTarefa buscaDesejada, String idAtividade) {
		Set<String> resultadosBusca = buscaDesejada.busca(this.tarefaService, idAtividade);

		this.historicoBuscas.registaBusca(buscaDesejada.representaBusca());
		
		List<String> listaResultados = new ArrayList<>();

		for (String representacao : resultadosBusca) {
			listaResultados.add(representacao);
		}
		
		return listaResultados;
	}


	public String[] sugerirTarefas(SugerirTarefa sugerirTarefa, String cpf) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		
		String[] resultado = sugerirTarefa.sugere(tarefaService, pessoa);
		
		this.historicoBuscas.registaBusca(sugerirTarefa.representaBusca());
		
		return resultado;
	}


	public String[] buscasMaisRecentes(int nBuscas) {
		return this.historicoBuscas.buscasMaisRecentes(nBuscas);
	}


	public String[] exibirHistóricoBusca(int indexBusca) {
		return this.historicoBuscas.exibirHistóricoBusca(indexBusca);
	}
	
	
}
