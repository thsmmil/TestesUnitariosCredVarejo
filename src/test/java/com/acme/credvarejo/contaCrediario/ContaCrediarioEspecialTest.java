package com.acme.credvarejo.contaCrediario;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.*;

import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;
import com.acme.credvarejo.conta.ContaCrediario;
import com.acme.credvarejo.conta.IdentificadorContaCrediario;

public class ContaCrediarioEspecialTest {
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	ContaCrediarioEspecial ccred;
	IdentificadorContaCrediario icc;
	Cpf cpf;
	Cliente client;
	
	@Before
	public void requirements() {
		icc = new IdentificadorContaCrediario((long) 13122018);
		cpf = new Cpf(1910000000);
		Date dt = new Date();
		client = new Cliente(cpf, "Thiago", 20, dt, 2000, 0);
		ccred = new ContaCrediarioEspecial(icc, client, 100.0, 200.0, 13, true, 0.01);
	}
	
	@Test
	public void efetuarPagamentoTest() {
		ccred.efetuarPagamento(50.0);
		assertEquals(50.0, ccred.getSaldoDevido(), 0.1);
	}
	
	@Test
	public void efetuarPagamentoDescontoTest() {
		ccred.efetuarPagamento(50.0, 0.5);
		assertEquals(49.5, ccred.getSaldoDevido(), 0.1);
	}
	
	@Test
	public void pontosAcumuladosTest() {
		
		assertEquals(0, ccred.getPontosAcumulados(), 0.1);
	}
	
	@Test
	public void percentualDescontoTest() {
		
		assertEquals(0, ccred.getPercentualDeDesconto(), 0.1);
	}
}
