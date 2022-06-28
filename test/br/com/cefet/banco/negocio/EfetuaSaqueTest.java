package br.com.cefet.banco.negocio;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EfetuaSaqueTest {

    @Test
    public void run_quandoContaCorrenteTiver100Sacado10_deveFicarComSaldoDe90() {
        ContaCorrente contaCorrente = new ContaCorrente(100);
        EfetuaSaque efetuaSaque = new EfetuaSaque(contaCorrente,10);

        efetuaSaque.run();
        Assert.assertEquals(90,efetuaSaque.getConta().getSaldo(),00.01);
    }
}