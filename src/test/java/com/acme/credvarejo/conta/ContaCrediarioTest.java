package com.acme.credvarejo.conta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.*;

import com.acme.credvarejo.ado.conta.InterfaceRepositorioContaCrediario;
import com.acme.credvarejo.ado.conta.InterfaceRepositorioMovimentoCrediario;
import com.acme.credvarejo.ado.conta.RepositorioContaCrediario;
import com.acme.credvarejo.ado.conta.RepositorioMovimentoCrediario;
import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;

public class ContaCrediarioTest {
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	ContaCrediario ccred;
	IdentificadorContaCrediario icc;
	Cliente client;
	Cpf cpf;
	ControladorContaCrediario ccc;
	InterfaceRepositorioContaCrediario ircc;
	ControladorMovimentoCrediario cmc;
	InterfaceRepositorioMovimentoCrediario irmc;
	
	
	@Before
	public void requirements() {
		icc = new IdentificadorContaCrediario((long) 13122018);
		cpf = new Cpf(1910000000);
		Date dt = new Date();
		client = new Cliente(cpf, "Thiago", 20, dt, 2000, 0);
		ccred = new ContaCrediario(icc, client, 100, 200, 13, true);
		ircc = new RepositorioContaCrediario();
		ccc = new ControladorContaCrediario(ircc);
		irmc = new RepositorioMovimentoCrediario();
		cmc = new ControladorMovimentoCrediario(irmc);
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	public void isValidoContaCrediario() {
		ccred.validar();
		assertEquals("Conta Validada", outputStreamCaptor.toString()
			      .trim());
	}
	
	@Test
	public void isNotValidoContaCrediario() {
		ccred.setSaldoDevido(-1);
		ccred.validar();
		assertEquals("Conta invalida", outputStreamCaptor.toString()
			      .trim());
	}
	
	@Test
	public void efetuarPagamentoTest() {
		ccred.efetuarPagamento(50);
		assertEquals(50, ccred.getSaldoDevido(), 0.1);
	}
	
	@Test
	public void efetuarCompraTest() {
		ccred.efetuarCompra(50);
		assertEquals(150, ccred.getSaldoDevido(), 0.1);
	}
	
	@Test
	public void desativarTest() {
		ccred.desativar();
		assertEquals(ccred.isAtiva(), false);
	}
	
	@Test
	public void reativarTest() {
		ccred.reativar();
		assertEquals(ccred.isAtiva(), true);
	}
	
	@Test
	public void IdentificadorContaTest() {
		assertEquals(ccred.getIdentificadorConta().getNumero(), icc.getNumero());
	}
	
	@Test
	public void calcularDigitoTest() {
		assertEquals(icc.calcularDigitoVerificador(), 7);
		
	}
	
	@Test
	public void verificarValidadeTest() {
		assertEquals(icc.verificarValidadeDigito(7), true);
		
	}
	
	@Test
	public void incluirTest() {
		ccc.inserir(client, 200.0, 13);
		assertNotEquals("Conta Validada", outputStreamCaptor.toString()
			      .trim());
	}
	@Test
	public void buscarTest() {
		ccc.inserir(client, 200.0, 13);
		ccc.buscar(icc);
		assertNotEquals(outputStreamCaptor.toString()
			      .trim(), "Inv?lido!");
	}
	
	@Test
	public void buscarNotValidTest() {
		ccc.inserir(client, 200.0, 13);
		ccc.buscar(icc);
		assertEquals(outputStreamCaptor.toString()
			      .trim(), "Conta invalida");
	}
	
	@Test
	public void alterarTest() {
		ircc.incluir(ccred);
		ircc.alterar(icc, 150);
		assertEquals(ircc.buscarPorChave(icc).getLimiteDeCredito(), ccred.getLimiteDeCredito(), 0.1);
	}
	
	@Test
	public void excluirTest() {
		ircc.excluir(icc);
		assertEquals(null, ircc.buscarPorChave(icc));
	}
	
	@Test
	public void buscarTodosControladorTest() {
	ccc.inserir(client, 200.0, 13);
		ccc.buscarTodos();
		assertEquals("Conta invalida\r\n"
				+ "Nome: ThiagoID Conta: 1910000000CPF: 1910000000Limite de Credito: 200.0", outputStreamCaptor.toString()
			      .trim());
	}
	
	@Test
	public void creditarNullTest() {
		ccc.inserir(client, 100.0, 13);
		ccc.creditar(null, 25, cmc);
		assertEquals("Conta invalida\r\n"
				+ "Inv?lido!", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void creditarTest() {
		ccc.inserir(client, 100.0, 13);
		icc.setNumero(1910000000);
		ccc.creditar(icc, 25, cmc);
		assertEquals("Conta invalida\r\n"
				+ "Movimento Validado", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void debitarTest() {
		ccc.inserir(client, 100.0, 13);
		icc.setNumero(1910000000);
		ccc.debitar(icc, 25, cmc);
		assertEquals("Conta invalida\r\n"
				+ "Movimento Validado", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void buscarControladorTest() {
		ccc.inserir(client, 100.0, 13);
		assertNotEquals("Inv?lido!", outputStreamCaptor.toString().trim());
	}
	
	
	@Test
	public void incluirCredito() {
		Date dt = new Date();
		MovimentoCrediarioCredito mcc = new MovimentoCrediarioCredito(ccred, 200, dt, 300);
		irmc.incluirCredito(mcc);
	}

	@Test
	public void incluirDebito() {
		Date dt = new Date();
		MovimentoCrediarioDebito mcd = new MovimentoCrediarioDebito(ccred, 200, dt, 300);
		irmc.incluirDebito(mcd);
	}
	
	@Test
	public void buscarTodosTest() {
		Date dt = new Date();
		MovimentoCrediarioCredito mcc = new MovimentoCrediarioCredito(ccred, 200, dt, 300);
		MovimentoCrediarioDebito mcd = new MovimentoCrediarioDebito(ccred, 200, dt, 300);
		irmc.incluirCredito(mcc);
		irmc.incluirDebito(mcd);
		irmc.buscarTodos();
		assertNotEquals("Cr?dito: \r\n"
				+ "D?bito:", outputStreamCaptor.toString().trim());
		
	}
}
