#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*************************LISTAS****************************/
typedef struct Temp{
    char *pal;
    struct Temp *prox;
}TNODO;

TNODO *ListaDePalavras = NULL;
TNODO *TextoComoListaDePalavras = NULL;


TNODO *Insere(char *palavra, TNODO *inicio) {
    TNODO *p = NULL;
    p = (TNODO*) malloc(sizeof (TNODO));
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

TNODO *BuscaDado(TNODO *inicio, char *dado)
{
    TNODO *ptr = NULL;
  ptr = inicio;
  while (ptr != NULL) {
     if (strcmp(ptr->pal, dado)==0)
            return (ptr);
     else ptr = ptr->prox;
  }
  return NULL;
}

int ExisteDado(TNODO *inicio, char *dado)
{
  if(BuscaDado(inicio,dado) != NULL) return 1;
  return 0;
}

void Imprime(TNODO *inicio)
{
    TNODO *ptr = NULL;
  if (inicio == NULL)
  {
    printf("--- fim da lista ---\n\n");
    return;
  }
  ptr = inicio;
  while (ptr !=NULL) {
     printf("Palavra Lista = %s\n",ptr->pal);
     ptr = ptr->prox;
  }
  printf("--- fim da lista ---\n\n");
}

/*************************ARQUIVOS****************************/
void LeArquivoTexto(TNODO *inicio)
{
    FILE *arq = NULL;
    arq = fopen("entrada.txt", "rt");
    if (arq == NULL){ perror("Error"); return; }

    int result = 0;
    char c = ' ';
    char *s = NULL;

   while(result != EOF)
   {
        result = fscanf(arq, "%c", &c);
        printf("_%c_",c);

        if(c == ' ' || c == '\n'){
            printf("PALAVRA: %s\n", s);
            if(!ExisteDado(ListaDePalavras,s)) ListaDePalavras = Insere(s,ListaDePalavras);
            Imprime(ListaDePalavras);
            s = NULL;
        }else if(isalnum(c)){
            if(s == NULL) {
                    s = (char*) malloc (sizeof(char));
                    s[0] = c;
            }
            else strcat(s,&c);
        }
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


    char *S = "ABCD";
    ListaDePalavras = Insere(S,ListaDePalavras);
    S = "19283";
    ListaDePalavras = Insere(S,ListaDePalavras);
    S = "iosduroew";
    ListaDePalavras = Insere(S,ListaDePalavras);
    Imprime(ListaDePalavras);

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
