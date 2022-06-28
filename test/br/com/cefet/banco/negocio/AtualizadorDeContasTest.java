package br.com.cefet.banco.negocio;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AtualizadorDeContasTest {

    @Test
    public void roda_quandoContaCorrenteTiver100DeSaldo_deveAtualizarSaldoPara100() {
        AtualizadorDeContas atualizadorDeContas = new AtualizadorDeContas(0.01);
        ContaCorrente contaCorrente = new ContaCorrente(100);
        atualizadorDeContas.roda(contaCorrente);
        Assert.assertEquals(99.98,atualizadorDeContas.getSaldoTotal(),0.);
    }

    @Test
    public void getSaldoTotal() {
    }

    @Test
    public void getSelic() {
    }
}