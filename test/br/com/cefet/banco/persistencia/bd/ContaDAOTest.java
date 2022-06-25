package br.com.cefet.banco.persistencia.bd;

import br.com.cefet.banco.negocio.Cliente;
import br.com.cefet.banco.negocio.Conta;
import br.com.cefet.banco.negocio.ContaCorrente;
import br.com.cefet.banco.negocio.Funcionario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.util.List;

public class ContaDAOTest {

    private ContaDAO contaDAO;


    @Before
    public void setUp() {
        this.contaDAO = new ContaDAO();
    }

    @Test
    public void adicionarConta_quandoEnviadoUmaContaCorrenteValida_deveSalvarNaBase() {
        Cliente cliente = new Cliente("Pedro Ferreira", "184.196.967-22", "Avenida Tancredo", "pferreira", "1234");
        cliente.setId(1);
        ContaCorrente contaCorrente = new ContaCorrente(1000.00);

        contaCorrente.setTitular(cliente);
        this.contaDAO.adicionaConta(contaCorrente);

        Conta conta = contaDAO.getContaDeCliente(cliente);
        Assert.assertNotNull(conta);

    }

    @Test
    public void adicionarConta_quandoVinculadoUmClienteQueNaoExiste_deveNaoAdicionar() {
        Cliente cliente = new Cliente("Inexistente", "184.196.967-22", "Avenida Tancredo", "cinexistente", "1234");
        cliente.setId(1000);
        ContaCorrente contaCorrente = new ContaCorrente(1000.00);

        contaCorrente.setTitular(cliente);
        this.contaDAO.adicionaConta(contaCorrente);

        Conta conta = this.contaDAO.getContaDeCliente(cliente);
        Assert.assertNull(conta);
    }

    @Test
    public void altera_quandoEnviadoUmaContaPoupancaValida_deveSalvarNaBase() {
        Cliente cliente = new Cliente("Pedro Ferreira", "184.196.967-22", "Avenida Tancredo", "pferreira", "1234");
        cliente.setId(1);
        Conta conta = contaDAO.getContaDeCliente(cliente);
        conta.setLimite(1000);
        contaDAO.altera(conta);
        Conta contaFromBD = contaDAO.getContaDeCliente(cliente);
        Assert.assertEquals(contaFromBD.getLimite(), conta.getLimite(), 0);

    }


    @Test
    public void remove_quandoUmCantaExistente_deveApagar() {
        Cliente cliente = new Cliente("Pedro Ferreira", "184.196.967-22", "Avenida Tancredo", "pferreira", "1234");
        cliente.setId(1);

        Conta conta = contaDAO.getContaDeCliente(cliente);
        contaDAO.remove(conta);
        Assert.assertNull(contaDAO.getConta(conta.getId()));

    }


    @Test
    public void getConta_quandoExistirUmaContaCorrente_deveRetonarNaoNulo() {
        Conta conta = contaDAO.getConta(1);
        Assert.assertNotNull(conta);
    }

    @Test
    public void getConta_quandoExistirUmaContaPoupanca_deveRetonarNaoNulo() {
        Conta conta = contaDAO.getConta(5);
        Assert.assertNotNull(conta);
    }

    @Test
    public void getContaDeCliente_quandoClienteTiverConta_deveObterAConta() {
        Cliente cliente = new Cliente("Pedro Ferreira", "184.196.967-22", "Avenida Tancredo", "pferreira", "1234");
        cliente.setId(4);
        Conta conta = contaDAO.getContaDeCliente(cliente);
        Assert.assertNotNull(conta);
    }

    @Test
    public void getListaContas_quandoTiverContasCadastradas_deveListaNaoVazia(){
        List<Conta> contas = contaDAO.getListaContas();
        Assert.assertTrue(contas.size() > 0);
    }
}