package sapo.tarefa;

import java.util.Objects;

import sapo.ValidadorPadrao;

public class ValidadorTarefa extends ValidadorPadrao {
	public void validacao(String nome, String[] habilidades) {
		this.validacaoNome(nome);
		this.validacaoHabilidades(habilidades);
	}
	public void validacao(String idAtividade, String nome, String[] habilidades, String[] idTarefas) {
		this.validacaoNome(nome);
		this.validacaoHabilidades(habilidades);
		this.validacaoIdAtividade(idAtividade);
	}
	
	public void validacao(String idTarefaGerencial, String idTarefa) {
		this.validacaoIdTarefaGerencial(idTarefaGerencial);
		this.validacaoIdTarefa(idTarefa);
	}
	
	public void validacaoIdTarefaGerencial(String idTarefaGerencial) {
		Objects.requireNonNull(idTarefaGerencial, "O id da Tarefa Gerencial não pode ser nulo");
		if (idTarefaGerencial.isBlank()) {
			throw new IllegalArgumentException("O id da Tarefa Gerencial não pode ser vazio");
		}
	}
	
	public void validacaoIdTarefa(String idTarefa) {
		Objects.requireNonNull(idTarefa, "O id da Tarefa não pode ser nulo");
		if (idTarefa.isBlank()) {
			throw new IllegalArgumentException("O id da Tarefa não pode ser vazio");
		}
	}
	
	public void validacaoIdAtividade(String idAtividade) {
		Objects.requireNonNull(idAtividade, "O id da Atividade não pode ser nulo");
		if (idAtividade.isBlank()) {
			throw new IllegalArgumentException("O id da Atividade não pode ser vazio");
		}
	}
	
	
	public void validacaoidTarefas(String[] idTarefas) {
		for (String id : idTarefas) {
			Objects.requireNonNull(id, "Não pode id nulo!");
			if (id.isBlank()) {
				throw new IllegalArgumentException("Não pode id vazio!");
			}
		}
	}
}
