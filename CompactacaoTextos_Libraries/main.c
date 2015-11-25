#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Estruturas.h"
#include "Arquivo.h"
//xxd -b ArqBinario.dat

TNODO* ListaDePalavras = NULL;
TREF* TextoComoListaDePalavras = NULL;

/************************MAIN*********************************/
int main()
{
    printf("Digite o nome do arquivo contendo o texto: ");
    char *arqTxt = (char*) malloc (sizeof(char)*50);
    scanf("%s",arqTxt);

    LeArquivoTexto(arqTxt, &ListaDePalavras, &TextoComoListaDePalavras);
    printf("\nLISTA DE PALAVRAS:\n");
    Imprime(ListaDePalavras);
    printf("Tamanho Lista: %d\n", TamanhoLista(ListaDePalavras));
    printf("\nTEXTO:\n");
    ImprimeTexto(TextoComoListaDePalavras);
    printf("Tamanho Texto: %d\n\n", TamanhoTexto(TextoComoListaDePalavras));

    GravaArquivoBinario();
    LeArquivoBinario();

    printf("FIM!");
    getchar();
    return 0;
}
