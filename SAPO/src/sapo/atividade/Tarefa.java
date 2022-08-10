package sapo.atividade;

import java.util.HashMap;
import java.util.Map;

import sapo.pessoa.Pessoa;

public class Tarefa {
	private String nome;
	private String codigo;
	private int duracao;
	private String[] habilidadesRecomendadas;
	private String nomeAtividade;
	private Map<String, Pessoa> pessoasAssociadas;

	public Tarefa(String nome, String codigo, String[] habilidades, String nomeAtividade) {
		this.nome = nome;
		this.codigo = codigo;
		this.habilidadesRecomendadas = habilidades;
		this.nomeAtividade = nomeAtividade;
		this.pessoasAssociadas = new HashMap<>();
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
		this.pessoasAssociadas.put(pessoa.getCpf(), pessoa);
	}
	
	public void removePessoa(String cpf) {
		this.pessoasAssociadas.remove(cpf);
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
		for (String cpf : this.pessoasAssociadas.keySet()) {
			retorno += this.pessoasAssociadas.get(cpf).getNome() + " – " + this.pessoasAssociadas.get(cpf).getCpf();
		}

		return retorno;

	}
}
