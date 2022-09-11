package com.acme.credvarejo.cliente;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class CpfTeste {
	Cpf num;
	
	@Before
	public void requirements() {
		num = new Cpf(1910000000);
	}
	
	@Test
	public void DigitoTest() {
		
		assertEquals(num.calculaDigitoMod11("15", 3, 5, false), "2");
	}
	
	@Test
	public void isCpfTest() {
		assertEquals(num.isCPF("19100000000"), true);
	}
	
	@Test
	public void imprimeCpfTest() {
		assertEquals(num.imprimeCPF("19100000000"), "191.000.000-00");
	}
	
	@Test
	public void getNumeroTest() {
		assertEquals(num.getNumero(), 1910000000);
	}
	
	@Test
	public void setNumeroTest() {
		num.setNumero(1910000001);
		assertEquals(num.getNumero(), 1910000001);
	}
}
