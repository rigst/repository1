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
TNODO *Insere(char *palavra) {
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

TNODO *BuscaDadoListaPalavras(char *dado)
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

int ExisteDadoListaPalavras(char *dado)
{
  if(BuscaDadoListaPalavras(dado) != NULL) return 1;
  return 0;
}

int TamanhoLista()
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

void Imprime()
{
    TNODO *ptr = ListaDePalavras;
    while (ptr !=NULL) {
        printf("%s\n",ptr->pal);
        ptr = ptr->prox;
    }
    printf("--- fim da lista ---\n\n");
}

/*********************TEXTO****************************/
TREF *InsereRef(TNODO *palavra) {
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

void ImprimeTexto()
{
    TREF *ptr = TextoComoListaDePalavras;
    while (ptr !=NULL)
    {
        printf("%s\t",ptr->refPal->pal);
        ptr = ptr->prox;
    }
    printf("\n--- fim do texto ---\n\n");
}

int TamanhoTexto()
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

/*******************ARQUIVOS***********************/
void LeArquivoTexto(char *arqTxt)
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

        if((int)s[0] == 0) continue;
        else if(!ExisteDadoListaPalavras(s)) ListaDePalavras = Insere(s);

        TNODO *palavra = BuscaDadoListaPalavras(s);
        TextoComoListaDePalavras = InsereRef(palavra);
    }
    fclose(arq);
}

void GravaArquivoBinario()
{
    FILE *arq;
    int result;

    arq = fopen("ArqBinario.dat", "wb");
    if (arq == NULL) { printf("Problemas na CRIACAO do arquivo\n"); return; }

    int tamListaPal = TamanhoLista();
    int tamTexto = TamanhoTexto();
    int *ptrLista = &tamListaPal;
    int *ptrTexto = &tamTexto;

    result = fwrite(&ptrLista, sizeof(int), 1, arq);
    result += fwrite(&ptrTexto, sizeof(int), 1, arq);

    printf("tamanho int : %d\n",sizeof(int));
    printf("Nro de elementos gravados: %d", result);
    fclose(arq);
}
/************************MAIN*********************************/
int main()
{
    printf("Digite o nome do arquivo contendo o texto: ");
    char *arqTxt = (char*) malloc (sizeof(char)*50);
    scanf("%s",arqTxt);

    LeArquivoTexto(arqTxt);
    printf("\nLISTA DE PALAVRAS:\n");
    Imprime(ListaDePalavras);
    printf("Tamanho : %d\n", TamanhoLista());
    printf("TEXTO:\n");
    ImprimeTexto(TextoComoListaDePalavras);
    printf("Tamanho : %d\n", TamanhoTexto());

    GravaArquivoBinario();
    getchar();
    return 0;
}
