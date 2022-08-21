package sapo.funcao;

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
		if (pessoa.getFuncao().getClass() == Professor.class) {
			throw new IllegalStateException("A função da pessoa já é de Professor");
		}
	}

	public void definirFuncaoAluno(String cpf, String matr, int periodo) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		if (pessoa.getFuncao().getClass() == Aluno.class) {
			throw new IllegalStateException("A função da pessoa já é de Aluno");
		}
	}

	public void removerFuncao(String cpf) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		if (pessoa.getFuncao() == null) {
			throw new IllegalStateException("A pessoa já não possui função");
		}
	}

	public int pegarNivel(String cpf) {
		Pessoa pessoa = this.pessoaService.recuperarPessoaOuFalhe(cpf);
		int nivel = 0;
		if (pessoa.getFuncao() == null || pessoa.getFuncao().getClass() == Aluno.class) {
			nivel += (this.tarefasAndamento(cpf) / 2) + this.tarefasConcluidas(cpf);
		}
		if (pessoa.getFuncao().getClass() == Aluno.class) {
			nivel += 0; // TODO
		} else if (pessoa.getFuncao().getClass() == Professor.class) {
			nivel += this.tarefasAndamento(cpf) / 4;
		}
		return nivel + pessoa.getNivel();
	}

	public String[] listarPessoas() {
		return this.pessoaService.listarPessoas();
	}

	private Set<Tarefa> recuperaTarefas(String cpf) {
		return this.tarefaService.tarefasAssociadasPessoa(cpf);
	}

	private int tarefasAndamento(String cpf) {
		Set<Tarefa> tarefasAndamento = new HashSet<>();
		for (Tarefa tarefa : this.recuperaTarefas(cpf)) {
			if (tarefa.getEstado() == false) {
				tarefasAndamento.add(tarefa);
			}
		}
		return tarefasAndamento.size();
	}

	private int tarefasConcluidas(String cpf) {
		return this.recuperaTarefas(cpf).size() - this.tarefasAndamento(cpf);
	}
}
