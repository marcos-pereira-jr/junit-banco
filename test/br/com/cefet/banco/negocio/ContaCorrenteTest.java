package br.com.cefet.banco.negocio;

import br.com.cefet.banco.exceptions.DepositoInvalidoException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContaCorrenteTest {

    @Test
    public void depositar_quandoAdicionado200aUmaContaComSaldo100_deveFicarComSaldoDe300() throws DepositoInvalidoException {
        ContaCorrente contaCorrente = new ContaCorrente(100);

        contaCorrente.depositar(200);
        Assert.assertEquals(300,contaCorrente.getSaldo(),00.01);
    }

    //public void sacar_quando
}