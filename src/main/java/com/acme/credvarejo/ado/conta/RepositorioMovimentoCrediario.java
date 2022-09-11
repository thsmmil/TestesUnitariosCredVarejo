package com.acme.credvarejo.ado.conta;


import com.acme.credvarejo.conta.MovimentoCrediarioCredito;
import com.acme.credvarejo.conta.MovimentoCrediarioDebito;

public class RepositorioMovimentoCrediario implements InterfaceRepositorioMovimentoCrediario{
	private MovimentoCrediarioCredito[] movimentacaoCredito;
	private MovimentoCrediarioDebito[] movimentacaoDebito;
	private int indiceC;
	private int indiceD;
	
	public RepositorioMovimentoCrediario() {
		movimentacaoCredito = new MovimentoCrediarioCredito[150];
		movimentacaoDebito = new MovimentoCrediarioDebito[150];
		indiceC = 0;
		indiceD = 0;
	}
	
	public void incluirCredito(MovimentoCrediarioCredito c) {
		movimentacaoCredito[indiceC] = c;
		indiceC++;
	}
	
	public void incluirDebito(MovimentoCrediarioDebito d) {
		movimentacaoDebito[indiceD] = d;
		indiceD++;
	}
	
	public void buscarTodos() {
		System.out.println("Cr�dito: ");
		for(int i = 0; i < indiceC; i++) {
			System.out.println("Nome: "+movimentacaoCredito[i].getContaCrediario().getCliente().getNome()+
							   "Valor da Transa��o: "+movimentacaoCredito[i].getValorTransacao()+
							   "Saldo devido: "+movimentacaoCredito[i].getContaCrediario().getSaldoDevido()+
							   "Hora da transa��o: "+movimentacaoCredito[i].getDataHoraDaOperacao()+
							   "Est� ativa: "+movimentacaoCredito[i].getContaCrediario().isAtiva()+"");
		}
		System.out.println("D�bito: ");
		for(int i = 0; i < indiceD; i++) {
			System.out.println("Nome: "+movimentacaoDebito[i].getContaCrediario().getCliente().getNome()+
							   "Valor da Transa��o: "+movimentacaoDebito[i].getValorTransacao()+
							   "Saldo devido: "+movimentacaoDebito[i].getContaCrediario().getSaldoDevido()+
							   "Hora da transa��o: "+movimentacaoDebito[i].getDataHoraDaOperacao()+
							   "Est� ativa: "+movimentacaoDebito[i].getContaCrediario().isAtiva()+"");
		}
	}
}
