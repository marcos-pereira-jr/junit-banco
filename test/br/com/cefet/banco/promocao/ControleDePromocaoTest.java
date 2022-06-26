package br.com.cefet.banco.promocao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.AssertionFailedException;

import br.com.cefet.banco.exceptions.PromocaoFuncionarioException;
import br.com.cefet.banco.negocio.Caixa;
import br.com.cefet.banco.negocio.ControleDePromocao;
import br.com.cefet.banco.negocio.Diretor;
import br.com.cefet.banco.negocio.Funcionario;
import br.com.cefet.banco.negocio.Gerente;
import br.com.cefet.banco.persistencia.bd.FuncionarioDAO;

public class ControleDePromocaoTest {
	
	private ControleDePromocao service;
	
    @Before
    public void setUp() {
        this.service = new ControleDePromocao();
    }
	
	@Test
	public void gerente_promove_caixa_para_gerente() throws PromocaoFuncionarioException {
		Caixa promovido = new Caixa("Fernando","Avenida","192.169.90-22","Contas","2566","fcaixa",50000);
		promovido.setId(0);
		
		Diretor promovente = new Diretor("Cléber","Avenida","192.169.90-22","Contas","2566","fgerente",50000);
		promovente.setId(1);
		
		service.promoverFuncionario(promovente, promovido, 1);
		
		assertEquals(promovido.getCargo(), 1);
	}

	@Test
	public void diretor_promove_caixa_para_gerente() throws PromocaoFuncionarioException {
		Caixa promovido = new Caixa("Fernando","Avenida","192.169.90-22","Contas","2566","fcaixa",50000);
		promovido.setId(0);
		
		Diretor promovente = new Diretor("Cléber","Avenida","192.169.90-22","Contas","2566","fdiretor",50000);
		promovente.setId(1);
		
		service.promoverFuncionario(promovente, promovido, 1);
		
		assertEquals(promovido.getCargo(), 1);
	}
	
	@Test(expected = PromocaoFuncionarioException.class)
	public void funcionario_se_autopromove() throws PromocaoFuncionarioException {
		Caixa promovido = new Caixa("Fernando","Avenida","192.169.90-22","Contas","2566","fcaixa",50000);
		promovido.setId(0);
		
		Funcionario promovente = promovido;
		promovente.setId(1);
		
		service.promoverFuncionario(promovente, promovido, 1);

		assertEquals(promovido.getCargo(), 0);
	}
	
	@Test(expected = PromocaoFuncionarioException.class)
	public void funcionario_promove_funcionario_com_cargo_acima() throws PromocaoFuncionarioException {
		Gerente promovido = new Gerente("Fernando","Avenida","192.169.90-22","Contas","2566","fcaixa",50000);
		promovido.setId(0);
		
		Caixa promovente = new Caixa("Cléber","Avenida","192.169.90-22","Contas","2566","fgerente",50000);
		promovente.setId(1);
		
		service.promoverFuncionario(promovente, promovido, 2);

		assertEquals(promovido.getCargo(), 2);
	}
	
	@Test(expected = PromocaoFuncionarioException.class)
	public void funcionario_promove_para_cargo_acima_dele() throws PromocaoFuncionarioException {
		Caixa promovido = new Caixa("Fernando","Avenida","192.169.90-22","Contas","2566","fcaixa",50000);
		promovido.setId(0);
		
		Gerente promovente = new Gerente("Cléber","Avenida","192.169.90-22","Contas","2566","fgerente",50000);
		promovente.setId(1);
		
		service.promoverFuncionario(promovente, promovido, 3);

		assertEquals(promovido.getCargo(), 2);
	}
	
	

}
