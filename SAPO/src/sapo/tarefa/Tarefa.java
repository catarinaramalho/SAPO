package sapo.tarefa;

import java.util.ArrayList;
import java.util.List;

import sapo.pessoa.Pessoa;

public class Tarefa {
	private String nome;
	private String codigo;
	private int duracao;
	private String[] habilidadesRecomendadas;
	private String nomeAtividade;
	private List<Pessoa> pessoasAssociadas;

	public Tarefa(String nome, String codigo, int duracao, String[] habilidades, String nomeAtividade,
			ArrayList<Pessoa> pessoas) {
		this.nome = nome;
		this.codigo = codigo;
		this.duracao = duracao;
		this.habilidadesRecomendadas = habilidades;
		this.nomeAtividade = nomeAtividade;
		this.pessoasAssociadas = pessoas;
	}

	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	public void setHabilidades(String[] novasHabilidades) {
		this.habilidadesRecomendadas = novasHabilidades;
	}

	public void acrescentarHoras(int horas) {
		this.duracao += horas;
	}

	public void removeHoras(int horas) {
		this.duracao -= horas;
	}
	
	public void associaPessoa(Pessoa pessoa) {
		pessoasAssociadas.add(pessoa);
	}

	@Override
	public String toString() {
		String retorno = "";
		retorno += this.nome + " - " + this.codigo + "\n";
		retorno += "- " + this.nomeAtividade + "\n";

		for (int i = 0; i < this.habilidadesRecomendadas.length; i++) {
			if (i != (this.habilidadesRecomendadas.length - 1)) {
				retorno += this.habilidadesRecomendadas[i] + ", ";
			} else {
				retorno += this.habilidadesRecomendadas[i];
			}
		}

		retorno += "\n";
		retorno += "(" + this.duracao + " hora(s) executada(s))\n===\nEquipe:\n";
		for (Pessoa pessoa : this.pessoasAssociadas) {
			retorno += pessoa.getNome() + " – " + pessoa.getCpf() + "\n";
		}

		return retorno;

	}
}
