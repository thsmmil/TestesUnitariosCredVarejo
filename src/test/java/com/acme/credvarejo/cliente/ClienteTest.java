package com.acme.credvarejo.cliente;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.*;

import com.acme.credvarejo.ado.cliente.InterfaceRepositorioCliente;
import com.acme.credvarejo.ado.cliente.RepositorioCliente;

public class ClienteTest {

	Cliente client;
	Cpf cpf;
	Date dt;
	ControladorCliente cc;
	InterfaceRepositorioCliente irc;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Before
	public void requirements() {
		cpf = new Cpf(1910000000);
		dt = new Date();
		client = new Cliente(cpf, "Thiago", 20, dt, 2000, 0);
		irc = new RepositorioCliente(0);
		cc = new ControladorCliente(irc);
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	public void NomeCompletoTest() {
		client.gravaNomeCompleto("Thiago Hortencio Silva");
		assertEquals(client.getNome(), "Thiago Hortencio Silva");
	}
	
	@Test
	public void PrimeiroNomeTest() {
		client.gravaNomeCompleto("Thiago Hortencio Silva");
		assertEquals(client.getPrimeiroNome(), "Thiago");
	}
	
	@Test
	public void SegundooNomeTest() {
		client.gravaNomeCompleto("Thiago Hortencio Silva");
		assertEquals(client.getSegundoNome(), "Hortencio");
	}
	
	@Test
	public void ValidarClientTest() {
		client.validar();
		assertEquals("Cliente Validado", outputStreamCaptor.toString()
			      .trim());
	}
	
	@Test
	public void IsNotValidoClientTest() {
		client.setSexo(4);;
		client.validar();
		assertEquals("Cliente invalido", outputStreamCaptor.toString()
			      .trim());
	}
	
	@Test
	public void DigitoTest() {
		
		assertEquals(cpf.calculaDigitoMod11("15", 3, 5, false), "2");
	}
	
	@Test
	public void isCpfTest() {
		assertEquals(cpf.isCPF("19100000000"), true);
	}
	
	@Test
	public void imprimeCpfTest() {
		assertEquals(cpf.imprimeCPF("19100000000"), "191.000.000-00");
	}
	
	@Test
	public void getNumeroTest() {
		assertEquals(cpf.getNumero(), 1910000000);
	}
	
	@Test
	public void setNumeroTest() {
		cpf.setNumero(1910000001);
		assertEquals(cpf.getNumero(), 1910000001);
	}
	
	@Test
	public void incluirValidoTest() {
		cc.incluir(client);
		cc.buscar(1910000000);
		assertEquals("Cliente Validado\r\n"
				+ "Cliente: Thiago", outputStreamCaptor.toString()
			      .trim());
	}
	@Test
	public void incluirIsNotValidoTest() {
		cc.incluir(null);
		assertEquals("Inv?lido!", outputStreamCaptor.toString()
			      .trim());
	}
	
	@Test
	public void alterarTest() {
		cc.incluir(client);
		cc.alterar(1910000000, "Thiago Hortencio");
		cc.buscar(1910000000);
		assertEquals("Cliente Validado\r\n"
				+ "Cliente: Thiago Hortencio", outputStreamCaptor.toString()
			      .trim());
	}
	
	@Test
	public void excluirTest() {
		cc.incluir(client);
		cc.excluir(1910000000);
		cc.buscar(1910000000);
		assertEquals("Cliente Validado\r\n"
				+"Inv?lido", outputStreamCaptor.toString()
			      .trim());
	}
	
	@Test
	public void buscarTodosTest() {
		cc.incluir(client);
		cc.buscarTodos();
		assertEquals("Cliente Validado\r\n"
				+ "Cliente: Thiago", outputStreamCaptor.toString()
			      .trim());
	}
	
}
