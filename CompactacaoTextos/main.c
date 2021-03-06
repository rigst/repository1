#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//xxd -b ArqBinario.dat

/****STRUCTS******/
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

int const TAM_MAX_PAL = 20;

/****LISTA DE PALAVRAS******/
void CriaLista();
TNODO *Insere(char *palavra);
TNODO *BuscaDadoListaPalavras(char *dado);
TNODO *BuscaDadoListaPalavrasPos(int pos);
int posPalavra(char *dado);
int ExisteDadoListaPalavras(char *dado);
int TamanhoLista();
void Imprime();
/**********TEXTO**********/
void CriaTexto();
TREF *InsereRef(TNODO *palavra);
void ImprimeTexto();
int TamanhoTexto();
/**********ARQUIVO*******/
void LeArquivoTexto(char *arqTxt);
void GravaArquivoBinario();
void GravaBinarioLista(FILE *arq);
void GravaBinarioTexto(FILE *arq);

/*****************LISTA DE PALAVRAS**********************/
void CriaLista()
{
   ListaDePalavras = NULL;
}

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

TNODO *BuscaDadoListaPalavrasPos(int pos)
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

int posPalavra(char *dado)
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
    printf("--- fim da lista ---\n");
}

/*********************TEXTO****************************/
void CriaTexto()
{
   TextoComoListaDePalavras = NULL;
}

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
    printf("\n--- fim do texto ---\n");
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

/*******************ARQUIVO TEXTO***********************/
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

/*******************ARQUIVO SALVA BINÁRIO***********************/
void GravaArquivoBinario()
{
    FILE *arq;
    int result;

    arq = fopen("ArqBinario.dat", "wb");
    if (arq == NULL) { printf("\nProblemas na CRIACAO do arquivo\n"); return; }

    int tamListaPal = TamanhoLista(), tamTexto = TamanhoTexto();
    int *ptrLista = &tamListaPal, *ptrTexto = &tamTexto;
    result = fwrite(ptrLista, sizeof(int), 1, arq);
    result += fwrite(ptrTexto, sizeof(int), 1, arq);
printf("GRAVAÇÃO\nGravou Tamanho Lista : %d\t Tamanho Texto : %d\n", tamListaPal,tamTexto);

    GravaBinarioLista(arq);
    GravaBinarioTexto(arq);

    fclose(arq);
}

void GravaBinarioLista(FILE *arq)
{
    int tamPal = 0;
    char fimPal = '@';
    char *ptrFimPal = &fimPal;
    TNODO *aux = ListaDePalavras;
    while(aux != NULL)
    {
printf("Gravou Palavra : %s\n", aux->pal);
        tamPal = strlen(aux->pal);
        fwrite(aux->pal, sizeof(char)*tamPal,1, arq);
printf("Gravou termino\n");
        fwrite(ptrFimPal, sizeof(char), 1, arq);
        aux = aux->prox;
    }
}

void GravaBinarioTexto(FILE *arq)
{
    int posPal = 0;
    int *ptrPosPal = &posPal;
    TREF *aux = TextoComoListaDePalavras;
    while(aux != NULL)
    {
        char *pal = aux->refPal->pal;
printf("Palavra atual : %s\n", aux->refPal->pal);
        posPal = posPalavra(pal);
printf("Gravou Posicao : %d\n", posPal);
        fwrite(ptrPosPal, sizeof(int), 1, arq);
        aux = aux->prox;
    }

}

/************************MAIN*********************************/
int main()
{
    printf("COMPACTAÇÃO DO TEXTO\n");
    printf("Digite o nome do arquivo contendo o texto: ");
    char *arqTxt = (char*) malloc (sizeof(char)*50);
    scanf("%s",arqTxt);

    LeArquivoTexto(arqTxt);
    printf("\nLISTA DE PALAVRAS:\n");
    Imprime(ListaDePalavras);
    printf("Tamanho Lista: %d\n", TamanhoLista());
    printf("\nTEXTO:\n");
    ImprimeTexto(TextoComoListaDePalavras);
    printf("Tamanho Texto: %d\n\n", TamanhoTexto());


    GravaArquivoBinario();

    printf("FIM!");
    getchar();
    return 0;
}
