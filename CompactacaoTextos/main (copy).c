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

TNODO *ListaDePalavras = NULL;
TREF *TextoComoListaDePalavras = NULL;

/*****************LISTA DE PALAVRAS**********************/
TNODO *Insere(char *palavra, TNODO *inicio) {
    TNODO *p;
    p = (TNODO*)malloc(sizeof (TNODO));
    p -> pal = palavra;
    if (inicio == NULL) {
        p -> prox = NULL;
        return p;
    }
    TNODO *ultima = inicio;
    while (ultima->prox != NULL) {
        ultima = ultima->prox;
    }
    ultima -> prox = p;
    return inicio;
}

void Imprime()
{
    TNODO *inicio = ListaDePalavras;
    TNODO *ptr;
  if (inicio == NULL)
  {
    printf("--- fim da lista ---\n\n");
    return;
  }
  ptr = inicio;
  while (ptr !=NULL) {
     printf("Palavra = %s\n",ptr->pal);
     ptr = ptr->prox;
  }
  printf("--- fim da lista ---\n\n");
}

/*********************TEXTO****************************/
TREF *InsereRef(TNODO *palavra, TREF *inicio) {
    TREF *p;
    p = (TREF*)malloc(sizeof (TREF));
    p -> refPal = palavra;
    if (inicio == NULL) {
        p -> prox = NULL;
        return p;
    }
    TREF *ultima = inicio;
    while (ultima->prox != NULL) {
        ultima = ultima->prox;
    }
    ultima -> prox = p;
    return inicio;
}

TREF *BuscaDadoTexto(TREF *inicio, char *dado)
{
    TREF *ptr;
  if (inicio == NULL)
  {
    return NULL;
  }
  ptr = inicio;
  while (ptr !=NULL) {
     if (strcmp(ptr->refPal->pal,dado) == 0)
            return (ptr);
     else ptr = ptr->prox;
  }
  return NULL;
}

int ExisteDadoTexto(TREF *inicio, char *dado)
{
  if(BuscaDadoTexto(inicio,dado) != NULL) return 1;
  return 0;
}

void ImprimeTexto()
{
    TREF *inicio = TextoComoListaDePalavras;
    TREF *ptr;
  if (inicio == NULL)
  {
    printf("--- fim do texto ---\n\n");
    return;
  }
  ptr = inicio;
  while (ptr !=NULL) {
     printf("Palavra = %s\n",ptr->refPal->pal);
     ptr = ptr->prox;
  }
  printf("--- fim do texto ---\n\n");
}

TREF* CriaTexto(TNODO *inicio)
{
    TREF *lista = NULL;
    TNODO *aux = inicio;
    while(aux != NULL)
    {
        if(!ExisteDadoTexto(lista,aux->pal))
            InsereRef(aux,lista);
        aux = aux->prox;
    }
    ImprimeTexto(lista);
    return lista;
}

/*******************ARQUIVOS***********************/
TNODO* LeArquivoTexto()
{
    TNODO *inicio = NULL;

    FILE *arq;
    arq = fopen("entrada.txt", "rt");
    if (arq == NULL){ perror("Error"); return inicio;}

    int i = 0;
   while(i != EOF)
   {
    char *s = (char*) malloc(sizeof(char)*50);
    i = fscanf(arq, "%s", s);
    printf("_%s",s);
    inicio = Insere(s, inicio);
   }
   printf("\n----acabou-----\n");
    fclose(arq);
    return inicio;
}
/************************MAIN*********************************/
int main()
{
    printf("Hello world!\n");

    ListaDePalavras = LeArquivoTexto();
    TextoComoListaDePalavras = CriaTexto(ListaDePalavras);

    Imprime(ListaDePalavras);
    ImprimeTexto(TextoComoListaDePalavras);

    getchar();
    return 0;
}
