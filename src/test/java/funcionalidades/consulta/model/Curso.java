package funcionalidades.consulta.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Curso {
	
	public String nome, quantidadeParcela, valorParcela;
	
	public Curso(String nome, String [] valores) {
		this.nome = nome;
		if(valores.length > 1) {
			this.quantidadeParcela = valores[0];
			this.valorParcela = valores[1];
		}else {
			this.valorParcela = valores[0];
		}
	}
	
	public String getValorFinal() {
		BigDecimal b2 =  new BigDecimal(this.valorParcela.replace(".", "").replace(",", "."));
		if(this.quantidadeParcela == null)
			return formatarValor(b2);
		BigDecimal b1 = new BigDecimal(this.quantidadeParcela);
		return formatarValor(b1.multiply(b2));
	}
	
	private String formatarValor(BigDecimal value) {
		Locale ptBr = new Locale("pt", "BR");
		NumberFormat formato = NumberFormat.getCurrencyInstance(ptBr);
		return formato.format(value);
	}

}
