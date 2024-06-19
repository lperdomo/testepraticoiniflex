package testepraticoiniflex;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
	private String funcao;

    public BigDecimal getSalario() {
        return this.salario;
    }
    
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }   
    
    public String getFuncao() {
        return this.funcao;
    }
    
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }   
   
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
    	super(nome, dataNascimento);
        this.setSalario(salario);
    	this.setFuncao(funcao);
    }
    
}
