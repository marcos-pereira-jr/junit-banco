package br.com.cefet.banco.negocio;

import br.com.cefet.banco.exceptions.DepositoInvalidoException;
import br.com.cefet.banco.exceptions.SaldoInsuficienteException;
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

    @Test
    public void sacar_quandoSacar200DeUmaContaQueTemSaldo400_deveTerSaldoDe200() throws SaldoInsuficienteException {
        ContaCorrente contaCorrente = new ContaCorrente(400);
        contaCorrente.sacar(200);

        Assert.assertEquals(200,contaCorrente.getSaldo(),00.01);
    }

    @Test
    public void sacar_quandoTranferir10DeUmaContaComSaldoDe100ParaOutraQueTem100_deveAContaOrigemCom90ADeDestinoCom110(){
        ContaCorrente contaCorrenteOrigem  = new ContaCorrente(100);
        ContaCorrente contaCorrenteDestino = new ContaCorrente(100);

        contaCorrenteOrigem.transferir(contaCorrenteDestino,10);

        Assert.assertEquals(90,contaCorrenteOrigem.getSaldo(),00.01);
        Assert.assertEquals(110,contaCorrenteDestino.getSaldo(),00.01);
    }

    @Test
    public void compareTo_quandoExistirContasComSaldoIgual_deveRetornar0(){
        ContaCorrente contaCorrente1 = new ContaCorrente(100);
        ContaCorrente contaCorrente2= new ContaCorrente(100);


        Assert.assertEquals(0,contaCorrente1.compareTo(contaCorrente2),00.01);
    }

    @Test
    public void compareTo_quandoAConta1MaiorQueConta2_deveRetornarMenos1(){
        ContaCorrente contaCorrente1 = new ContaCorrente(110);
        ContaCorrente contaCorrente2= new ContaCorrente(100);


        Assert.assertEquals(-1,contaCorrente1.compareTo(contaCorrente2),00.01);
    }

    @Test
    public void compareTo_quandoAConta1MenorQueConta2_deveRetornarMenos1(){
        ContaCorrente contaCorrente1 = new ContaCorrente(100);
        ContaCorrente contaCorrente2= new ContaCorrente(110);


        Assert.assertEquals(-1,contaCorrente1.compareTo(contaCorrente2),00.01);
    }

    @Test
    public void getTipoStr_quandoForContaCorrente_deveRetornarContaCorrente(){
        ContaCorrente contaCorrente = new ContaCorrente(100);

        Assert.assertEquals("Conta corrente",contaCorrente.getTipoStr());
    }

}