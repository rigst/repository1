#include <stdio.h>
#include <stdlib.h>

/*************************LISTAS****************************/
typedef struct Temp{
    char *pal;
    struct Temp *prox;
}TNODO;

TNODO *ListaDePalavras = NULL;
TNODO *TextoComoListaDePalavras = NULL;

//insere no final da lista, recebe palavra e lista
TNODO *Insere(char *palavra, TNODO *inicio) {
    TNODO *p;
    p = malloc(sizeof (TNODO));
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

//recebe a lista e o dado, retorna uma referencia
TNODO *BuscaDado(TNODO *inicio, char *dado)
{
    TNODO *ptr;
  if (inicio == NULL)
  {
    return NULL;
  }
  ptr = inicio;
  while (ptr !=NULL) {
     if (ptr->pal == dado)
            return (ptr);
     else ptr = ptr->prox;
  }
  return NULL;
}

//recebe a lista e o dado, diz se existe
int ExisteDado(TNODO *inicio, char *dado)
{
  if(BuscaDado(inicio,dado) != NULL) return 1;
  return 0;
}

//recebe a lista
void Imprime(TNODO *inicio)
{
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

/*************************ARQUIVOS****************************/
void LeArquivoTexto(TNODO *inicio)
{
    FILE *arq;
    arq = fopen("entrada.txt", "rt");
    if (arq == NULL){ perror("Error"); return; }

    int i, result;
    char c;
   float x;
   while(result != EOF)
   {
    result = fscanf(arq, "%c", &c);
    printf("_%c",c);
   }
   printf("----acabou-----");
    fclose(arq);
}
/*
void LeArquivoBinario(TNODO *inicio)
{
  FILE *arq;
  int result;
  int i;

  // Abre um arquivo BINÁRIO para LEITURA
  arq = fopen("ArqTeste.dat", "rb");
  if (arq == NULL)  // Se houve erro na abertura
  {
     printf("Problemas na abertura do arquivo\n");
     return;
  }
  result = fread (&Vet[0], sizeof(double), 100, arq);
  printf("Nro de elementos lidos: %d\n", result);

  for (i=0; i<result; i++)
      printf("%lf\n", Vet[i]);

  fclose(arq);
}
*/
/*************************************************************/
int main()
{
    printf("Hello world!\n");
    LeArquivoTexto(ListaDePalavras);
    /*char *S = "ABCD";
    ListaDePalavras = Insere(S,ListaDePalavras);
    Imprime(ListaDePalavras);

    TextoComoListaDePalavras = Insere(ListaDePalavras->pal, TextoComoListaDePalavras);
    printf("TEXTO:");
    Imprime(TextoComoListaDePalavras);*/

    getchar();
    return 0;
}
