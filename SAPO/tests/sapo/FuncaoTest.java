package sapo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FuncaoTest {

	private Facade facade;

	@BeforeEach
	void setUp() {
		this.facade = new Facade();
	}

	@Test
	void testCadastrarAluno() {
		this.facade.cadastrarAluno("111.111.111-11", "Catarina Ramalho", "121110708", 2,
				new String[] { "Programação" });
		assertArrayEquals(new String[] { "Catarina Ramalho - 111.111.111-11" }, this.facade.listarPessoas());
	}

	@Test
	void testCadastrarAlunoCpfNulo() {
		try {
			this.facade.cadastrarAluno(null, "Catarina Ramalho", "121110708", 2, new String[] { "Programação" });
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarAlunoCpfVazio() {
		try {
			this.facade.cadastrarAluno("", "Catarina Ramalho", "121110708", 2, new String[] { "Programação" });
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testCadastrarAlunoNomeNulo() {
		try {
			this.facade.cadastrarAluno("111.111.111-11", null, "121110708", 2, new String[] { "Programação" });
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarAlunoNomeVazio() {
		try {
			this.facade.cadastrarAluno("111.111.111-11", "", "121110708", 2, new String[] { "Programação" });
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testCadastrarAlunoMatriculaNula() {
		try {
			this.facade.cadastrarAluno("111.111.111-11", "Catarina Ramalho", null, 2, new String[] { "Programação" });
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarAlunoMatriculaVazia() {
		try {
			this.facade.cadastrarAluno("111.111.111-11", "Catarina Ramalho", "", 2, new String[] { "Programação" });
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testCadastrarAlunoPeriodoMenorQue1() {
		try {
			this.facade.cadastrarAluno("111.111.111-11", "Catarina Ramalho", "121110708", 0,
					new String[] { "Programação" });
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testCadastrarAlunoHabilidadesNulas() {
		try {
			this.facade.cadastrarAluno("111.111.111-11", "Catarina Ramalho", "121110708", 2, null);
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarProfessor() {
		this.facade.cadastrarProfessor("111.111.111-11", "Matheus Gaudencio do Rêgo", "2291146-0",
				new String[] { "P2", "LP2" }, new String[] { "Programação" });
	}

	@Test
	void testCadastrarProfessorCpfNulo() {
		try {
			this.facade.cadastrarProfessor(null, "Matheus Gaudencio do Rêgo", "2291146-0", new String[] {},
					new String[] {});
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarProfessorCpfVazio() {
		try {
			this.facade.cadastrarProfessor("", "Matheus Gaudencio do Rêgo", "2291146-0", new String[] {},
					new String[] {});
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testCadastrarProfessorNomeNulo() {
		try {
			this.facade.cadastrarProfessor("111.111.111-11", null, "2291146-0", new String[] {}, new String[] {});
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarProfessorNomeVazio() {
		try {
			this.facade.cadastrarProfessor("111.111.111-11", "", "2291146-0", new String[] {}, new String[] {});
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testCadastrarProfessorSiapeNulo() {
		try {
			this.facade.cadastrarProfessor("111.111.111-11", "Matheus Gaudencio do Rêgo", null, new String[] {},
					new String[] {});
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarProfessorSiapeVazio() {
		try {
			this.facade.cadastrarProfessor("111.111.111-11", "Matheus Gaudencio do Rêgo", "", new String[] {},
					new String[] {});
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testCadastrarProfessorDiscipliasNula() {
		try {
			this.facade.cadastrarProfessor("111.111.111-11", "Matheus Gaudencio do Rêgo", "2291146-0", null,
					new String[] {});
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testCadastrarProfessorHabiliadesNula() {
		try {
			this.facade.cadastrarProfessor("111.111.111-11", "Matheus Gaudencio do Rêgo", "2291146-0", new String[] {},
					null);
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testDefinirFuncaoProfessor() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] {});
		this.facade.definirFuncaoProfessor("111.111.111-11", "2291146-0", new String[] { "P2", "LP2" });
	}

	@Test
	void testDefinirFuncaoProfessorJaSendoProfessor() {
		this.facade.cadastrarProfessor("111.111.111-11", "Matheus Gaudencio do Rêgo", "2291146-0", new String[] {},
				new String[] {});
		try {
			this.facade.definirFuncaoProfessor("111.111.111-11", "2291146-0", new String[] {});
		} catch (IllegalStateException ise) {

		}

	}

	@Test
	void testDefinirFuncaoAluno() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] {});
		this.facade.definirFuncaoAluno("111.111.111-11", "2291146-0", 2);
	}

	@Test
	void testDefinirFuncaoAlunoJaSendoAluno() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] {});
		this.facade.definirFuncaoAluno("111.111.111-11", "2291146-0", 2);
		try {
			this.facade.definirFuncaoAluno("111.111.111-11", "2291146-0", 2);
		} catch (IllegalStateException iee) {

		}
	}

	@Test
	void testRemoverFuncao() {
		this.facade.cadastrarProfessor("111.111.111-11", "Matheus Gaudencio do Rêgo", "2291146-0", new String[] {},
				new String[] {});
		this.facade.removerFuncao("111.111.111-11");
	}

	@Test
	void testPegarNivel() {
		this.facade.cadastrarPessoa("111.111.111-11", "Matheus Gaudencio do Rêgo", new String[] { "Web" });
		assertEquals(0, this.facade.pegarNivel("111.111.111-11"));
		this.facade.cadastrarAtividade("Atividade", "Descrição", "111.111.111-11");
		this.facade.cadastrarTarefa("TVD-0", "A", new String[] {});
		this.facade.cadastrarTarefa("TVD-0", "B", new String[] {});
		this.facade.cadastrarTarefa("TVD-0", "C", new String[] {});
		this.facade.associarPessoaTarefa("111.111.111-11", "TVD-0-0");
		this.facade.associarPessoaTarefa("111.111.111-11", "TVD-0-1");
		this.facade.associarPessoaTarefa("111.111.111-11", "TVD-0-2");
		assertEquals(1, this.facade.pegarNivel("111.111.111-11"));
		this.facade.concluirTarefa("TVD-0-0");
		assertEquals(2, this.facade.pegarNivel("111.111.111-11"));
		this.facade.definirFuncaoAluno("111.111.111-11", "121110123", 2);
		assertEquals(2, this.facade.pegarNivel("111.111.111-11"));
		this.facade.cadastrarTarefa("TVD-0", "D", new String[] {});
		this.facade.cadastrarTarefa("TVD-0", "E", new String[] {});
		this.facade.cadastrarTarefa("TVD-0", "F", new String[] { "Web" });
		this.facade.associarPessoaTarefa("111.111.111-11", "TVD-0-3");
		this.facade.associarPessoaTarefa("111.111.111-11", "TVD-0-4");
		this.facade.associarPessoaTarefa("111.111.111-11", "TVD-0-5");
		assertEquals(3, this.facade.pegarNivel("111.111.111-11"));
		this.facade.concluirTarefa("TVD-0-5");
		this.facade.pegarNivel("111.111.111-11");
		assertEquals(5, this.facade.pegarNivel("111.111.111-11"));
		this.facade.removerFuncao("111.111.111-11");
		assertEquals(5, this.facade.pegarNivel("111.111.111-11"));
	}

	@Test
	void testListarPessoas() {
		this.facade.cadastrarProfessor("111.111.111-11", "Matheus Gaudencio do Rêgo", "121323", new String[] {},
				new String[] {});
		this.facade.cadastrarAluno("111.111.111-12", "Catarina", "121110708", 2, new String[] {});
		assertArrayEquals(new String[] { "Catarina - 111.111.111-12", "Matheus Gaudencio do Rêgo - 111.111.111-11" },
				this.facade.listarPessoas());
	}

}
