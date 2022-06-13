package br.com.cefet.banco.negocio;

import br.com.cefet.banco.persistencia.bd.ClienteDAO;
import br.com.cefet.banco.persistencia.bd.ContaDAO;
import br.com.cefet.banco.persistencia.bd.FuncionarioDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class BancoTest {

    @InjectMocks
    private Banco banco;

    @Mock
    private FuncionarioDAO daoFuncionario;

    @Mock
    private ClienteDAO daoCliente;

    @Mock
    private ContaDAO daoConta;

    @Before
    public void init(){

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void calcularLimite_testar_caminhoBasico_caminho1() {
        // 1 - (I,1,2,18,0)
        double saldoNegativo = -100;
        double limit = 100;

        Conta conta = new ContaCorrente(1000);
        double limiteMaximo = banco.calcularLimiteMaximo(conta,saldoNegativo,limit,10,100,1,1);
        Assert.assertEquals(limiteMaximo,limit,1);
    }

    @Test
    public void calcularLimite_testar_caminhoBasico_caminho2(){
        // 2 -(I,1,3,4,6,8,9,15,17,18,0)
        double saldoPositivo = 10000;

        double limite = 1000;

        double gastoTotalBanco = 1000;
        double saldoTotalBanco = 3000;

        int numeroClientes = 6;
        int numeroFuncionario = 20;

        Conta conta = new ContaCorrente(1000);
        double limiteMaximo = banco.calcularLimiteMaximo(conta,saldoPositivo,limite,
                                                            gastoTotalBanco,saldoTotalBanco,
                                                            numeroClientes,numeroFuncionario);

        Assert.assertEquals(limiteMaximo,saldoTotalBanco,1);
    }

    @Test
    public void calcularLimite_testar_caminhoBasico_caminho3(){
        // 3 -(I,1,3,5,6,8,9,15,17,18,0)
        double saldoPositivo = 1000;

        double limite = 100;

        double gastoTotalBanco = 4000;
        double saldoTotalBanco = 3000;

        int numeroClientes = 6;
        int numeroFuncionario = 20;

        Conta conta = new ContaCorrente(1000);
        double limiteMaximo = banco.calcularLimiteMaximo(conta,saldoPositivo,limite,
                gastoTotalBanco,saldoTotalBanco,
                numeroClientes,numeroFuncionario);

        Assert.assertEquals(limiteMaximo,200,1);
    }

    @Test
    public void calcularLimite_testar_caminhoBasico_caminho4(){
        // 4 -(I,1,3,4,6,8,10,15,17,18,0)
        double saldoPositivo = 5000;

        double limite = 300;

        double gastoTotalBanco = 1000;
        double saldoTotalBanco = 3000;

        int numeroClientes = 6;
        int numeroFuncionario = 20;

        Conta conta = new ContaCorrente(1000);
        double limiteMaximo = banco.calcularLimiteMaximo(conta,saldoPositivo,limite,
                gastoTotalBanco,saldoTotalBanco,
                numeroClientes,numeroFuncionario);

        Assert.assertEquals(limiteMaximo,1500,1);
    }


}