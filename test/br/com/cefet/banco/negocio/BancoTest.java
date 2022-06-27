package br.com.cefet.banco.negocio;

import br.com.cefet.banco.persistencia.bd.ClienteDAO;
import br.com.cefet.banco.persistencia.bd.ContaDAO;
import br.com.cefet.banco.persistencia.bd.FuncionarioDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.FieldSetter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class BancoTest {

    @InjectMocks
    private Banco banco;

    @Mock
    private FuncionarioDAO daoFuncionario;

    @Mock
    private ClienteDAO daoCliente;

    @Mock
    private ContaDAO daoConta;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void calcularSaldoTotal_quandoExitir3ContasCadastradasComSaldoDe1000_deveRetornar3000() throws NoSuchFieldException {
        List<Conta> contas = new ArrayList<>();

        banco.setContas(contas);

        Conta contaCorrente1 = new ContaCorrente(1000);
        Conta contaCorrente2 = new ContaCorrente(1000);
        Conta contaCorrente3 = new ContaCorrente(1000);

        banco.adicionarConta(contaCorrente1);
        banco.adicionarConta(contaCorrente2);
        banco.adicionarConta(contaCorrente3);

        Assert.assertEquals(3000,banco.calcularSaldoTotal(),0);

    }

    @Test
    public void calcularTotalDeGastos_quando3FuncionariosDeSalario10000DeveRetornar30000(){
        List<Funcionario> funcionarios = new ArrayList<>();

        banco.setFuncionarios(funcionarios);

        Diretor diretor =  new Diretor("Gerente","Avenida","192.169.90-22", "Contas",   "1234", "ddiretorD", 10000);
        Gerente gerente =  new Gerente("Gerente","Avenida","192.169.90-22", "Contas",   "1234", "ggerenteD", 10000);
        Caixa caixa =  new Caixa("Caixa","Avenida","192.169.90-22", "Contas",   "1234", "ccaixaD", 10000);

        banco.contratarFuncionario(diretor);
        banco.contratarFuncionario(gerente);
        banco.contratarFuncionario(caixa);

        Assert.assertEquals(30000,banco.calcularTotalDeGastos(),0);

    }

    @Test
    public void imprimeListaDeFuncionarios_quandoExistirUmFuncionario_devePrintarNoConsole(){
        List<Funcionario> funcionarios = new ArrayList<>();

        banco.setFuncionarios(funcionarios);

        Diretor diretor =  new Diretor("Gerente","Avenida","192.169.90-22", "Contas",   "1234", "ddiretorD", 10000);
        diretor.setEstado(EstadoFuncionario.EM_EXERCICIO);
        banco.contratarFuncionario(diretor);

        banco.imprimeListaDeFuncionarios();

        Assert.assertEquals("-----------------------\n" +
                "Nome: Gerente\n" +
                "Endereço: Avenida\n" +
                "CPF: 192.169.90-22\n" +
                "Departamento: Contas\n" +
                "Salário: 10000.0\n" +
                "Estado: Em exercício" +
                "\n",outputStreamCaptor.toString());

    }

    @Test
    public void imprimeRelatorioDeContas_quandoExistirUmaConta_devePrintaNumero(){
        List<Conta> contas = new ArrayList<>();

        banco.setContas(contas);

        Cliente cliente = new Cliente("Pedro Ferreira", "184.196.967-22", "Avenida Tancredo", "pferreira", "1234");
        cliente.setId(1);
        ContaCorrente contaCorrente = new ContaCorrente(1000.00);
        contaCorrente.setTitular(cliente);

        banco.adicionarConta(contaCorrente);

        banco.imprimeRelatorioDeContas();

        Assert.assertTrue(outputStreamCaptor.toString().contains("Numero"));

    }

    @Test
    public void atualizarContas_quandoAlteradaTaxaDoBancoPara2_deveAlterarSaldoDaContaPoupancaQueTem1000Para988(){
        List<Conta> contas = new ArrayList<>();
        banco.setContas(contas);

        Cliente cliente = new Cliente("Fernando", "184.196.967-22", "Avenida Tancredo", "FAlmeida", "1234");
        cliente.setId(2);
        ContaPoupanca contaPoupanca = new ContaPoupanca(1000.00);
        contaPoupanca.setTitular(cliente);

        banco.adicionarConta(contaPoupanca);

        banco.atualizarContas(2);

         Conta contaDepoisDeAtualizar = banco.getContas().stream()
                            .filter(c -> c.getTitular().getUsuario().equals("FAlmeida"))
                            .findFirst()
                            .get();

         Assert.assertEquals(contaDepoisDeAtualizar.getSaldo(),998,0);

    }

    @Test
    public void atualizarContas_quandoAlteradaTaxaDoBancoPara2_deveAlterarSaldoDaContaCorrenteQueTem1000Para996(){
        List<Conta> contas = new ArrayList<>();
        banco.setContas(contas);

        Cliente cliente = new Cliente("Fernando", "184.196.967-22", "Avenida Tancredo", "FAlmeida", "1234");
        cliente.setId(2);
        ContaCorrente contaCorrente = new ContaCorrente(1000.00);
        contaCorrente.setTitular(cliente);

        banco.adicionarConta(contaCorrente);

        banco.atualizarContas(2);

        Conta contaDepoisDeAtualizar = banco.getContas().stream()
                .filter(c -> c.getTitular().getUsuario().equals("FAlmeida"))
                .findFirst()
                .get();

        Assert.assertEquals(contaDepoisDeAtualizar.getSaldo(),996,0);

    }

}
