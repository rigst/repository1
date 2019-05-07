package sena;

import java.io.*;

public class Main {
	public static void main (String[] args) {
	
	int[] tab = new int[60];
	int[] numLinha;
	
	try{
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/Rodrigo/Downloads/numeros/num.txt"));
		
		while(br.ready()){

			String linha = br.readLine();
			numLinha = retornaNum(linha);
			
imprime(numLinha);
			for(int i=0; i<6; i++){
				tab[numLinha[i]-1]++;
			}
			

		}
imprimeTabela(tab);
		br.close();}
		catch(IOException ioe){ioe.printStackTrace();}
	
	}
	
	public static int[] retornaNum(String linha) {
		int[] numeros = new int[6];
		int pos = 0;
		String numS = "";
System.out.println("Linha: " + linha);
		for(int i = 0; i<linha.length(); i++) {
			
			char c = linha.charAt(i);
System.out.println("Char: " + c);
			if(c != '\t'){
				numS = numS + c;

System.out.println("colocou na string, resultado = " + numS);
				if(i == linha.length()-1) {
					int numI = Integer.parseInt(numS);
					numeros[pos] = numI;
					numS = "";
					pos++;
				}
			}
			else if(c == '\n'){break;}
			else{	
System.out.println("entrou no else");
				int numI = Integer.parseInt(numS);
				numeros[pos] = numI;
				numS = "";
				pos++;
			}
			
		}
		
		return numeros;
	}
	
	public static void imprime(int[] a) {
		for(int i=0; i<a.length; i++) {
			System.out.println(a[i]);
		}
	}
	
	public static void imprimeTabela(int[] a) {
int soma = 0;
		for(int i=0; i<a.length; i++) {
			System.out.println("Número: " + (i+1) + "\tContagem: " + a[i]);
soma += a[i];
		}
System.out.println("Soma : " + soma);
	}
		
}
