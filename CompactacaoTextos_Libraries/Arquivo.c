#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Estruturas.h"
#include "Arquivo.h"

int const TAM_MAX_PAL = 20;

/*******************ARQUIVO TEXTO***********************/
void LeArquivoTexto(char *arqTxt, TNODO ListaDePalavras, TREF TextoComoListaDePalavras)
{
    FILE *arq;
    arq = fopen(arqTxt, "rt");
    if (arq == NULL){ perror("Error"); return;}
    else printf("Lendo texto do arquivo \"%s\"\n",arqTxt);

    int result = 0;
    while(result != EOF)
    {
        char *s = (char*) malloc(sizeof(char)*50);
        result = fscanf(arq, "%s", s);
printf("LEU : %s\n", s);
        if((int)s[0] == 0) continue;
        else if(!ExisteDadoListaPalavras(s, ListaDePalavras)) ListaDePalavras = Insere(s, ListaDePalavras);

        TNODO *palavra = BuscaDadoListaPalavras(s, ListaDePalavras);
        TextoComoListaDePalavras = InsereRef(palavra, TextoComoListaDePalavras);
    }

Imprime(ListaDePalavras);
ImprimeTexto(TextoComoListaDePalavras);
    fclose(arq);
}

/*******************ARQUIVO SALVA BINÁRIO***********************/
void GravaArquivoBinario(TNODO* ListaDePalavras, TREF* TextoComoListaDePalavras)
{
    FILE *arq;
    int result;

    arq = fopen("ArqBinario.dat", "wb");
    if (arq == NULL) { printf("\nProblemas na CRIACAO do arquivo\n"); return; }

    int tamListaPal = TamanhoLista(ListaDePalavras);
    int tamTexto = TamanhoTexto(TextoComoListaDePalavras);
    int *ptrLista = &tamListaPal, *ptrTexto = &tamTexto;
    result = fwrite(ptrLista, sizeof(int), 1, arq);
    result += fwrite(ptrTexto, sizeof(int), 1, arq);
printf("GRAVAÇÃO\nGravou Tamanho Lista : %d\t Tamanho Texto : %d\n", tamListaPal,tamTexto);

    GravaBinarioLista(arq, ListaDePalavras);
    GravaBinarioTexto(arq, ListaDePalavras, TextoComoListaDePalavras);

    fclose(arq);
}

void GravaBinarioLista(FILE *arq, TNODO* ListaDePalavras)
{
    int result = 0, tamPal = 0;
    char fimPal = '@';
    char *ptrFimPal = &fimPal;
    TNODO *aux = ListaDePalavras;
    while(aux != NULL)
    {
printf("Gravou Palavra : %s\n", aux->pal);
        tamPal = strlen(aux->pal);
        result = fwrite(aux->pal, sizeof(char)*tamPal,1, arq);
printf("Gravou termino\n");
        result = fwrite(ptrFimPal, sizeof(char), 1, arq);
        aux = aux->prox;
    }
}

void GravaBinarioTexto(FILE *arq, TNODO* ListaDePalavras, TREF* TextoComoListaDePalavras)
{
    int result = 0, posPal = 0;
    int *ptrPosPal = &posPal;
    TREF *aux = TextoComoListaDePalavras;
    while(aux != NULL)
    {
        char *pal = aux->refPal->pal;
printf("Palavra atual : %s\n", aux->refPal->pal);
        posPal = posPalavra(pal, ListaDePalavras);
printf("Gravou Posicao : %d\n", posPal);
        result = fwrite(ptrPosPal, sizeof(int), 1, arq);
        aux = aux->prox;
    }

}

/*******************ARQUIVO LÊ BINÁRIO***********************/
void LeArquivoBinario(TNODO* ListaDePalavras, TREF* TextoComoListaDePalavras)
{
    FILE *arq;
    int result;
    arq = fopen("ArqBinario.dat", "rb");

    if (arq == NULL) { printf("Problemas na abertura do arquivo\n"); return;}

    int tamListaPal = 0, tamTexto = 0;
    result = fread (&tamListaPal, sizeof(int), 1, arq);
printf("\nLEITURA\nTam lista de palavras : %d\n", tamListaPal);
    result += fread (&tamTexto, sizeof(int), 1, arq);
printf("Tam texto : %d\n", tamTexto);

    LeBinarioListaPal(tamListaPal,arq, ListaDePalavras);
Imprime(ListaDePalavras);
    LeBinarioTexto(tamTexto,arq, ListaDePalavras, TextoComoListaDePalavras);
ImprimeTexto(TextoComoListaDePalavras);
    fclose(arq);
}

void LeBinarioListaPal(int tamListaPal, FILE *arq, TNODO* ListaDePalavras)
{
    CriaLista();
    while(tamListaPal > 0)
    {
        char* palAtual = (char*) malloc (sizeof(char)*TAM_MAX_PAL);
        char* aux = (char*) malloc (sizeof(char));
        fread(aux, sizeof(char),1,arq);
        while(strcmp(aux,"@") != 0){
printf("Pegou char : %s\n", aux);
            strcat(palAtual,aux);
            fread(aux, sizeof(char),1,arq);
printf("Concatenou em palAtual : %s\n", palAtual);
        }
        printf("Inseriu na lista : %s\n", palAtual);
        ListaDePalavras = Insere(palAtual, ListaDePalavras);
        palAtual = (char*) malloc (sizeof(char)*TAM_MAX_PAL);
        tamListaPal--;
    }
}

void LeBinarioTexto(int tamTexto, FILE *arq, TNODO* ListaDePalavras, TREF *TextoComoListaDePalavras)
{
printf("\nLeitura do texto\n");
    CriaTexto(TextoComoListaDePalavras);
    int i, result = 0;
    int posPalavraLista = 0;
    for(i=0; i<tamTexto; i++)
    {
        TNODO* pal = NULL;
        result = fread(&posPalavraLista, sizeof(int),1,arq);
        pal = BuscaDadoListaPalavrasPos(posPalavraLista, ListaDePalavras);
printf("Posicao: %d\nPalavra : %s\n", posPalavraLista, pal->pal);
        TextoComoListaDePalavras = InsereRef(pal, TextoComoListaDePalavras);
    }
}

