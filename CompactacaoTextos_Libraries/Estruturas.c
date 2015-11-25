#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Temp{
    char *pal;
    struct Temp *prox;
}TNODO;

typedef struct Temp2{
    TNODO *refPal;
    struct Temp2 *prox;
}TREF;

/*****************LISTA DE PALAVRAS**********************/
void CriaLista(TNODO *ListaDePalavras)
{
   ListaDePalavras = NULL;
}

TNODO *Insere(char *palavra,  TNODO* ListaDePalavras) {
    TNODO *p;
    p = (TNODO*)malloc(sizeof (TNODO));
    p -> pal = palavra;
    if (ListaDePalavras == NULL) {
        p -> prox = NULL;
        return p;
    }
    TNODO *ultima = ListaDePalavras;
    while (ultima->prox != NULL) {
        ultima = ultima->prox;
    }
    ultima -> prox = p;
    return ListaDePalavras;
}

TNODO *BuscaDadoListaPalavras(char *dado, TNODO* ListaDePalavras)
{
    TNODO *ptr = ListaDePalavras;
    if (ListaDePalavras == NULL) return NULL;

    while (ptr !=NULL)
    {
        if (strcmp(ptr->pal,dado) == 0)
            return (ptr);
        else ptr = ptr->prox;
    }
    return NULL;
}

TNODO *BuscaDadoListaPalavrasPos(int pos, TNODO* ListaDePalavras)
{
    TNODO *ptr = ListaDePalavras;
    if (ListaDePalavras == NULL) return NULL;
    while (ptr !=NULL)
    {
        if (pos == 0)
            return (ptr);
        else { pos--; ptr = ptr->prox;}
    }
    return NULL;
}

int posPalavra(char *dado, TNODO* ListaDePalavras)
{
    TNODO *ptr = ListaDePalavras;
    int cont = 0;
    while (ptr !=NULL)
    {
        if (strcmp(ptr->pal,dado) == 0)
            return cont;
        else
        {
            ptr = ptr->prox;
            cont++;
        }
    }
    return -1;
}

int ExisteDadoListaPalavras(char *dado, TNODO* ListaDePalavras)
{
  if(BuscaDadoListaPalavras(dado, ListaDePalavras) != NULL) return 1;
  return 0;
}

int TamanhoLista(ListaDePalavras)
{
    int cont = 0;
    TNODO *ptr = ListaDePalavras;
    while (ptr != NULL)
    {
        cont++;
        ptr = ptr->prox;
    }
    return cont;
}

void Imprime(TNODO* ListaDePalavras)
{
    TNODO *ptr = ListaDePalavras;
    while (ptr !=NULL) {
        printf("%s\n",ptr->pal);
        ptr = ptr->prox;
    }
    printf("--- fim da lista ---\n");
}

/*********************TEXTO****************************/
void CriaTexto(TREF *TextoComoListaDePalavras)
{
   TextoComoListaDePalavras = NULL;
}

TREF *InsereRef(TNODO *palavra, TREF *TextoComoListaDePalavras) {
    TREF *p;
    p = (TREF*)malloc(sizeof (TREF));
    p -> refPal = palavra;
    if (TextoComoListaDePalavras == NULL) {
        p -> prox = NULL;
        return p;
    }
    TREF *ultima = TextoComoListaDePalavras;
    while (ultima->prox != NULL) {
        ultima = ultima->prox;
    }
    ultima -> prox = p;
    return TextoComoListaDePalavras;
}

void ImprimeTexto(TREF *TextoComoListaDePalavras)
{
    TREF *ptr = TextoComoListaDePalavras;
    while (ptr !=NULL)
    {
        printf("%s\t",ptr->refPal->pal);
        ptr = ptr->prox;
    }
    printf("\n--- fim do texto ---\n");
}

int TamanhoTexto(TREF *TextoComoListaDePalavras)
{
    int cont = 0;
    TREF *ptr = TextoComoListaDePalavras;
    while (ptr !=NULL)
    {
        cont++;
        ptr = ptr->prox;
    }
    return cont;
}

