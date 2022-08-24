package sapo.funcao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;
import sapo.tarefa.Tarefa;
import sapo.tarefa.TarefaService;

public class FuncaoService {

	private PessoaService pessoaService;
	private TarefaService tarefaService;

	public FuncaoService(PessoaService pessoaService, TarefaService tarefaService) {
		this.pessoaService = pessoaService;
		this.tarefaService = tarefaService;
	}

	public void cadastrarAluno(String cpf, String nome, String matr, int periodo, String[] habilidades) {
		this.pessoaService.cadastrarPessoa(cpf, nome, habilidades, new Aluno(matr, periodo));
	}

	public void cadastrarProfessor(String cpf, String nome, String siape, String[] disciplinas, String[] habilidades) {
		this.pessoaService.cadastrarPessoa(cpf, nome, habilidades, new Professor(siape, disciplinas));
	}

	public void definirFuncaoProfessor(String cpf, String siape, String[] disciplinas) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		if (pessoa.getFuncao() != null && pessoa.getFuncao().getClass() == Professor.class) {
			throw new IllegalStateException("A função da pessoa já é de Professor");
		}
		pessoa.armazenaNivel(this.recuperaTarefas(cpf), this.pegarNivel(cpf));
		pessoa.setFuncao(new Professor(siape, disciplinas));
	}

	public void definirFuncaoAluno(String cpf, String matr, int periodo) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		if (pessoa.getFuncao() != null && pessoa.getFuncao().getClass() == Aluno.class) {
			throw new IllegalStateException("A função da pessoa já é de Aluno");
		}
		pessoa.armazenaNivel(this.recuperaTarefas(cpf), this.pegarNivel(cpf));
		pessoa.setFuncao(new Aluno(matr, periodo));
	}

	public void removerFuncao(String cpf) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		if (pessoa.getFuncao() == null) {
			throw new IllegalStateException("A pessoa já não possui função");
		}
		pessoa.armazenaNivel(this.recuperaTarefas(cpf), this.pegarNivel(cpf));
		pessoa.setFuncao(null);
	}

	public int pegarNivel(String cpf) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		int nivel = 0;
		if (pessoa.getFuncao() == null) {
			nivel += (this.tarefasAndamento(cpf).size() / 2) + this.tarefasConcluidas(cpf).size();
		} else if (pessoa.getFuncao().getClass() == Aluno.class) {
			nivel += (this.tarefasAndamento(cpf).size() / 2)
					+ (this.tarefasConcluidas(cpf).size() - this.tarefasHabilidades(cpf).size())
					+ Math.ceil(this.tarefasHabilidades(cpf).size() * 1.5);
		} else if (pessoa.getFuncao().getClass() == Professor.class) {
			nivel += this.tarefasAndamento(cpf).size() / 4 + this.tarefasHabilidadesDiciplinas(cpf).size();
		}
		return nivel + pessoa.getNivel();
	}

	public String[] listarPessoas() {
		return this.pessoaService.listarPessoas();
	}

	private Set<Tarefa> recuperaTarefas(String cpf) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		Set<Tarefa> tarefasNaoAvaliadas = this.tarefaService.tarefasAssociadasPessoa(cpf);
		tarefasNaoAvaliadas.removeAll(pessoa.getTarefasAvaliadas());
		return tarefasNaoAvaliadas;
	}

	private Set<Tarefa> tarefasAndamento(String cpf) {
		Set<Tarefa> tarefasAndamento = new HashSet<>();
		for (Tarefa tarefa : this.recuperaTarefas(cpf)) {
			if (tarefa.getEstado() == false) {
				tarefasAndamento.add(tarefa);
			}
		}
		return tarefasAndamento;
	}

	private Set<Tarefa> tarefasConcluidas(String cpf) {
		Set<Tarefa> tarefasConcluidas = new HashSet<>();
		for (Tarefa tarefa : this.recuperaTarefas(cpf)) {
			if (tarefa.getEstado() == true) {
				tarefasConcluidas.add(tarefa);
			}
		}
		return tarefasConcluidas;
	}

	private Set<Tarefa> tarefasHabilidades(String cpf) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		Set<Tarefa> tarefasHabilidades = new HashSet<>();
		for (Tarefa tarefa : this.tarefasConcluidas(cpf)) {
			for (String habilidadeTarefa : tarefa.getHabilidades()) {
				boolean bate = false;
				for (String habilidadePessoa : pessoa.getHabilidades()) {
					if (habilidadeTarefa.equals(habilidadePessoa)) {
						bate = true;
						break;
					}
				}
				if (bate == true) {
					tarefasHabilidades.add(tarefa);
				}
				break;
			}
		}
		return tarefasHabilidades;
	}

	private Set<Tarefa> tarefasHabilidadesDiciplinas(String cpf) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		Set<Tarefa> tarefasHabilidades = new HashSet<>();
		Set<String> habilidadesDisciplina = new HashSet<>(Arrays.asList(pessoa.getHabilidades()));
		habilidadesDisciplina.addAll(Arrays.asList(pessoa.getDisciplinas()));
		for (Tarefa tarefa : this.tarefasConcluidas(cpf)) {
			for (String habilidadeTarefa : tarefa.getHabilidades()) {
				boolean bate = false;
				for (String habilidadeDisciplina : habilidadesDisciplina) {
					if (habilidadeTarefa.equals(habilidadeDisciplina)) {
						bate = true;
						break;
					}
				}
				if (bate == true) {
					tarefasHabilidades.add(tarefa);
				}
				break;
			}
		}
		return tarefasHabilidades;
	}
}
