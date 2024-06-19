package testepraticoiniflex;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Locale;

public class Principal {
	
	private ArrayList<Funcionario> listaFuncionarios;
	private HashMap<String, ArrayList<Funcionario>> mapaFuncoesFuncionarios;
	
	public static void main(String[] args) {
		
		Principal principal = new Principal();
		//Item 3.1
		principal.inserirFuncionarios();
		//Item 3.2
		principal.removerFuncionario("João");
		//Item 3.3
		principal.imprimirLista();
		//Item 3.4
		principal.alterarSalarioFuncionarios(10);
		//Item 3.5
		principal.agruparFuncionariosPorFuncao();
		//Item 3.6
		principal.imprimirMapa();
		//Item 3.7 e 3.8
		principal.imprimirAniversariantesPorMes(10);
		principal.imprimirAniversariantesPorMes(12);
		//Item 3.9
		principal.imprimirFuncionarioMaiorIdade();
		//Item 3.10
		principal.imprimirListaAlfabetica();
		//Item 3.11
		principal.imprimirTotalSalarios();
		//Item 3.12
		principal.imprimirQuantidadeSalarioMinimo();
	}
	
	public Principal() {
		listaFuncionarios = new ArrayList<Funcionario>();
		mapaFuncoesFuncionarios = new HashMap<>();
	}

	public void inserirFuncionarios() {
		listaFuncionarios.add(new Funcionario("Maria", LocalDate.parse("2000-10-18"), new BigDecimal("2009.44"), "Operador"));
		listaFuncionarios.add(new Funcionario("João", LocalDate.parse("1990-05-12"), new BigDecimal("2284.38"), "Operador"));
		listaFuncionarios.add(new Funcionario("Caio", LocalDate.parse("1961-05-02"), new BigDecimal("9836.14"), "Coordenador"));
		listaFuncionarios.add(new Funcionario("Miguel", LocalDate.parse("1988-10-14"), new BigDecimal("19119.88"), "Diretor"));
		listaFuncionarios.add(new Funcionario("Alice", LocalDate.parse("1995-01-05"), new BigDecimal("2234.68"), "Recepcionista"));
		listaFuncionarios.add(new Funcionario("Heitor", LocalDate.parse("1999-11-19"), new BigDecimal("1582.72"), "Operador"));
		listaFuncionarios.add(new Funcionario("Arthur", LocalDate.parse("1993-03-31"), new BigDecimal("4071.84"), "Contador"));
		listaFuncionarios.add(new Funcionario("Laura", LocalDate.parse("1994-07-08"), new BigDecimal("3017.45"), "Gerente"));
		listaFuncionarios.add(new Funcionario("Heloísa", LocalDate.parse("2003-05-24"), new BigDecimal("1606.85"), "Eletricista"));
		listaFuncionarios.add(new Funcionario("Helena", LocalDate.parse("1996-09-02"), new BigDecimal("2799.93"), "Gerente"));		
	}
	
	public void removerFuncionario(String nome) {
		ListIterator<Funcionario> iterador = listaFuncionarios.listIterator();
		while (iterador.hasNext()) {
			if (iterador.next().getNome().equals(nome)) {
				iterador.remove();
			}
		}
	}
	
	public void imprimirLista() {
		System.out.println("Lista de Todos os Funcionários");
		this.imprimirLista(listaFuncionarios);
	}
	
	public void imprimirLista(ArrayList<Funcionario> funcionarios) {
		ListIterator<Funcionario> iterador = funcionarios.listIterator();
		DateTimeFormatter dataFormatador = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		DecimalFormat decimalFormatador = (DecimalFormat)NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		String stringFormato = "%-15s";
		while (iterador.hasNext()) {
			Funcionario funcionario = iterador.next();
			System.out.println(String.format(stringFormato, funcionario.getNome())
				+ String.format(stringFormato, funcionario.getDataNascimento().format(dataFormatador))
				+ String.format(stringFormato, decimalFormatador.format(funcionario.getSalario()))
				+ funcionario.getFuncao());
		}
		System.out.println();
	}
	
	public void alterarSalarioFuncionarios(float percentual) {
		BigDecimal multiplicante = new BigDecimal(percentual / 100);
		for (Funcionario funcionario : listaFuncionarios) {
			funcionario.setSalario(funcionario.getSalario().add(funcionario.getSalario().multiply(multiplicante)));
		}
	}
	
	public void agruparFuncionariosPorFuncao() {
		ListIterator<Funcionario> iterador = listaFuncionarios.listIterator();
		while (iterador.hasNext()) {
			Funcionario funcionario = iterador.next();
			if (mapaFuncoesFuncionarios.containsKey(funcionario.getFuncao())) {
				mapaFuncoesFuncionarios.get(funcionario.getFuncao()).add(funcionario);
			} else {
				mapaFuncoesFuncionarios.put(funcionario.getFuncao(), new ArrayList<Funcionario>());
				mapaFuncoesFuncionarios.get(funcionario.getFuncao()).add(funcionario);
			}
		}
	}
	
	public void imprimirMapa() {
        for (String funcao : mapaFuncoesFuncionarios.keySet()) {
        	System.out.println("Lista de " + funcao);
        	imprimirLista(mapaFuncoesFuncionarios.get(funcao));
        }
	}
	
	public void imprimirAniversariantesPorMes(int mes) {
		ListIterator<Funcionario> iterador = listaFuncionarios.listIterator();
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		while (iterador.hasNext()) {
			Funcionario funcionario = iterador.next();
			if (funcionario.getDataNascimento().getMonthValue() == mes) {
				funcionarios.add(funcionario);
			}
		}
		System.out.println("Lista de Aniversariantes do Mês " + mes);
		imprimirLista(funcionarios);
	}
	
	public void imprimirFuncionarioMaiorIdade() {
		ListIterator<Funcionario> iterador = listaFuncionarios.listIterator();
		Funcionario funcionarioMaiorIdade = null;
		String stringFormato = "%-15s";
		while (iterador.hasNext()) {
			Funcionario funcionario = iterador.next();
			if (funcionarioMaiorIdade == null) {
				funcionarioMaiorIdade = funcionario;
			}
			if (funcionario.getDataNascimento().isBefore(funcionarioMaiorIdade.getDataNascimento())) {
				funcionarioMaiorIdade = funcionario;
			}
		}
		System.out.println("Funcionário com a Maior Idade");
		if (funcionarioMaiorIdade != null) {
			System.out.println(String.format(stringFormato, funcionarioMaiorIdade.getNome())
					+ ChronoUnit.YEARS.between(funcionarioMaiorIdade.getDataNascimento(), LocalDate.now()));
		}
		System.out.println();
	}
	
	public void imprimirListaAlfabetica() {
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>(listaFuncionarios);
		funcionarios.sort(Comparator.comparing(Funcionario::getNome));
		System.out.println("Lista Alfabética");
		imprimirLista(funcionarios);
	}
	
	public void imprimirTotalSalarios() {
		ListIterator<Funcionario> iterador = listaFuncionarios.listIterator();
		DecimalFormat decimalFormatador = (DecimalFormat)NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		BigDecimal totalSalarios = new BigDecimal(0);
		while (iterador.hasNext()) {
			totalSalarios = totalSalarios.add(iterador.next().getSalario());
		}
		System.out.println("Total dos Salários dos Funcionários");
		System.out.println(decimalFormatador.format(totalSalarios));
		System.out.println();
	}
	
	public void imprimirQuantidadeSalarioMinimo() {
		ListIterator<Funcionario> iterador = listaFuncionarios.listIterator();
		System.out.println("Quantidade de Salários Mínimos Ganhos por Funcionário");
		DecimalFormat decimalFormatador = (DecimalFormat)NumberFormat.getInstance(new Locale("pt", "BR"));
		BigDecimal salarioMinimo = new BigDecimal(1212);
		String stringFormato = "%-15s";
		while (iterador.hasNext()) {
			Funcionario funcionario = iterador.next();
			System.out.println(String.format(stringFormato, funcionario.getNome())
				+ decimalFormatador.format(funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_EVEN)));
					
		}
		System.out.println();
	}
	
}
