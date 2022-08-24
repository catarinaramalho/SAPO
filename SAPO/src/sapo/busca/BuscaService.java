package sapo.busca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import sapo.atividade.AtividadeService;
import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;
import sapo.tarefa.TarefaService;

/**
 * Service para a lógica de negócio das buscas. Converte os conjuntos resultados
 * das buscas em arrays e faz a comunicação das buscas com o historico de busca.
 * 
 * @author Lucas Leones Costa Dos Santos
 *
 */
public class BuscaService {
	/**
	 * Service de pessoa responsável por resgatar uma pessoa nas operações de busca
	 * em que é necessário.
	 */
	PessoaService pessoaService;
	/**
	 * Service de atividades responsavel por recuperar uma atividade onde a tarefa
	 * esta armazenada.
	 */
	AtividadeService atividadeService;
	/**
	 * Service de tarefas necessário para realizar as operações em cima das tarefas
	 * buscadas.
	 */
	TarefaService tarefaService;
	/**
	 * Service de historico de buscas para registrar e recuperar buscas ja
	 * efetuadas.
	 */
	HistoricoBuscas historicoBuscas;

	/**
	 * Construtor padrão de BuscaService, que vai inciar todos os seu parametros de
	 * acordo com os atributos passados pelo controller.
	 * 
	 * @param pessoaService    Service que vai assumir o atributo de pessoaService
	 *                         desse BuscaService.
	 * @param atividadeService Service que vai assumir o atributo de
	 *                         atividadeService desse BuscaService.
	 * @param tarefaService    Service que vai assumir o atributo de tarefaService
	 *                         desse BuscaService.
	 */
	public BuscaService(PessoaService pessoaService, AtividadeService atividadeService, TarefaService tarefaService) {
		this.pessoaService = pessoaService;
		this.atividadeService = atividadeService;
		this.tarefaService = tarefaService;
		this.historicoBuscas = new HistoricoBuscas();
	}

	/**
	 * Metodo que passa por todas as pessoas cadastradas no sistema e retorna todas
	 * as pessoas que possuem o critério da busca no seu nome, nas suas habilidades
	 * ou no seu cpf.
	 * 
	 * @param criterioBusca Criterio da busca que sera comparado com os atributos da
	 *                      pessoa.
	 * @return Retorna um array com strings que representam as pessoas que possuem o
	 *         criterio da busca.
	 */
	public String[] buscaPessoas(BuscaPessoa buscaDesejada) {
		Set<Pessoa> resultadosBusca = buscaDesejada.busca(pessoaService);

		this.historicoBuscas.registaBusca(buscaDesejada.representaBusca());

		List<Pessoa> listaResultados = new ArrayList<>();

		for (Pessoa pessoa : resultadosBusca) {
			listaResultados.add(pessoa);
		}

		String[] arrayPessoas = new String[listaResultados.size()];

		for (int i = 0; i < arrayPessoas.length; i++) {
			arrayPessoas[i] = listaResultados.get(i).toString();
		}

		Arrays.sort(arrayPessoas);

		return arrayPessoas;
	}

	/**
	 * Metodo que passa por todas as atividades cadastradas no sistema e retorna
	 * todas as representações textuais das atividades que possuem o critério da
	 * busca no seu nome, no seu codigo ou na sua descrição.
	 * 
	 * @param criterioBusca Criterio da busca que sera comparado com os atributos da
	 *                      atividade.
	 * @return Retorna um array com as representações textuais das atividades que
	 *         possuem o criterio da busca.
	 */
	public String[] buscaAtividades(BuscaAtividade buscaDesejada) {
		Set<String> resultadosBusca = buscaDesejada.busca(atividadeService);

		this.historicoBuscas.registaBusca(buscaDesejada.representaBusca());

		List<String> listaResultados = new ArrayList<>();

		for (String representacao : resultadosBusca) {
			listaResultados.add(representacao);
		}

		String[] arrayAtividades = new String[listaResultados.size()];

		for (int i = 0; i < arrayAtividades.length; i++) {
			arrayAtividades[i] = listaResultados.get(i);
		}

		return arrayAtividades;
	}

	/**
	 * Metodo que passa por todas as tarefas cadastradas no sistema e retorna todas
	 * as representações textuais das tarefas que possuem o critério da busca no seu
	 * nome.
	 * 
	 * @param criterioBusca Criterio da busca que sera comparado com o nome da
	 *                      tarefa.
	 * @return Retorna um array com as representações textuais das tarefas que
	 *         possuem o criterio da busca.
	 */
	public String[] buscaTarefas(BuscaTarefa buscaDesejada) {
		Set<String> resultadosBusca = buscaDesejada.busca(this.tarefaService);

		this.historicoBuscas.registaBusca(buscaDesejada.representaBusca());

		List<String> listaResultados = new ArrayList<>();

		for (String representacao : resultadosBusca) {
			listaResultados.add(representacao);
		}

		String[] arrayTarefas = new String[listaResultados.size()];

		for (int i = 0; i < arrayTarefas.length; i++) {
			arrayTarefas[i] = listaResultados.get(i).toString();
		}

		return arrayTarefas;
	}

	/**
	 * Metodo que passa por todas as tarefas cadastradas em uma atividade em
	 * especifico e retorna todas as representações textuais das tarefas que possuem
	 * o critério da busca no seu nome.
	 * 
	 * @param criterioBusca Criterio da busca que sera comparado com o nome da
	 *                      tarefa.
	 * @param idAtividade   Id da atividade cadastrada no sistema onde a busca por
	 *                      tarefas deve acontecer.
	 * @return Retorna um array com as representações textuais das tarefas que
	 *         possuem o criterio da busca.
	 */
	public String[] buscaTarefas(BuscaTarefa buscaDesejada, String idAtividade) {
		Set<String> resultadosBusca = buscaDesejada.busca(this.tarefaService, idAtividade);

		this.historicoBuscas.registaBusca(buscaDesejada.representaBusca());

		List<String> listaResultados = new ArrayList<>();

		for (String representacao : resultadosBusca) {
			listaResultados.add(representacao);
		}

		String[] arrayTarefas = new String[listaResultados.size()];

		for (int i = 0; i < arrayTarefas.length; i++) {
			arrayTarefas[i] = listaResultados.get(i).toString();
		}

		return arrayTarefas;
	}

	/**
	 * Metodo que recupera uma pessoa e, analisando suas habilidades, lista as
	 * tarefas que são mais conpativeis com ela.
	 * 
	 * @param cpf Cpf da pessoa em que as habilidades serão o criterio para busca de
	 *            tarefas.
	 * @return Retorna um array com as representações textuais das tarefas mais
	 *         compativeis com o criterio da busca.
	 */
	public String[] sugerirTarefas(SugerirTarefa sugerirTarefa, String cpf) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);

		String[] resultado = sugerirTarefa.sugere(tarefaService, pessoa);

		this.historicoBuscas.registaBusca(sugerirTarefa.representaBusca());

		return resultado;
	}

	/**
	 * Metodo que acessa o historico de buscas do sistema e retorna os dados sobre a
	 * n busca mais recente.
	 * 
	 * @param nBuscas O numero de qual busca mais recente o metodo vai retornar.
	 * @return Retorna um array de strings onde o primeiro elemento é o tipo de
	 *         busca e os demais elementos são os resultados obtidos pela busca.
	 */
	public String[] buscasMaisRecentes(int nBuscas) {
		return this.historicoBuscas.buscasMaisRecentes(nBuscas);
	}

	/**
	 * Metodo que acessa o historico de buscas do sistema e retorna os dados sobre
	 * uma busca de indice especifico entre todas as buscas.
	 * 
	 * @param indexBusca O indice da busca que deve ser retornada.
	 * @return Retorna um array de strings onde o primeiro elemento é o tipo de
	 *         busca e os demais elementos são os resultados obtidos pela busca.
	 */
	public String[] exibirHistoricoBusca(int indexBusca) {
		return this.historicoBuscas.exibirHistóricoBusca(indexBusca);
	}

}
