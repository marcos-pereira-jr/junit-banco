package br.com.cefet.banco.negocio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class BancoTest {

    @InjectMocks
    private Banco banco;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void calcularLimite_quandoSaldoForNulo_deveRetornarLimite() {

        Banco banco = new Banco();
        Conta conta = new ContaCorrente(1000);
        double limiteMaximo = banco.calcularLimiteMaximo(conta,0,100,10,100,1,1);
        Assert.assertEquals(limiteMaximo,100,1);

    }
}