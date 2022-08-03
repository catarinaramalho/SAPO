package sapo.pessoa;

import java.util.Date;

public class Comentario {
	private String autorCpf;
	private String comentario;
	private Date data;
	
	public Comentario(String autorCpf, String comentario) {
		this.autorCpf = autorCpf;
		this.comentario = comentario;
		this.data = new Date();
	}

	@Override
	public String toString() {
		return "Comentario [autorCpf=" + autorCpf + ", comentario=" + comentario + ", data=" + data + "]";
	}
	
	
	
	
}
