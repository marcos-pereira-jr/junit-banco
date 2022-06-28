package br.com.cefet.banco.negocio;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EfetuaDepositoTest {

    @Test
    public void run_quandoUmaContaTiver1000Depositado10_deveTerSaldoDe1010() {
        ContaCorrente contaCorrente = new ContaCorrente(1000);
        EfetuaDeposito efetuaDeposito = new EfetuaDeposito(contaCorrente,10);

        efetuaDeposito.run();
        Assert.assertEquals(1010,efetuaDeposito.getConta().getSaldo(),00.01);
    }
}