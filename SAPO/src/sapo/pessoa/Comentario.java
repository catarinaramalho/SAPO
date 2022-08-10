package sapo.pessoa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comentario {
	private Pessoa autor;
	private String comentario;
	private Date data;

	public Comentario(Pessoa autorCpf, String comentario) {
		this.autor = autorCpf;
		this.comentario = comentario;
		this.data = new Date();
	}

	@Override
	public String toString() {
		return this.comentario + " (" + this.autor.getNome() + ")";
	}

	public String formataData() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(this.data);
	}

}
