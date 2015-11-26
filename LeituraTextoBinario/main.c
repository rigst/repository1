#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//xxd -b ArqBinario.dat

/**********STRUCTS*******/
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
void LeArquivoBinario(char* nomeArq);
void LeBinarioListaPal(int tamListaPal, FILE *arq);
void LeBinarioTexto(int tamTexto, FILE *arq);


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

/*******************ARQUIVO LÊ BINÁRIO***********************/
void LeArquivoBinario(char* nomeArq)
{
    FILE *arq;
    int result;
    arq = fopen(nomeArq, "rb");

    if (arq == NULL) { printf("Problemas na abertura do arquivo\n"); return;}

    int tamListaPal = 0, tamTexto = 0;
    result = fread (&tamListaPal, sizeof(int), 1, arq);
printf("\nLEITURA\nTam lista de palavras : %d\n", tamListaPal);
    result += fread (&tamTexto, sizeof(int), 1, arq);
printf("Tam texto : %d\n", tamTexto);

    LeBinarioListaPal(tamListaPal,arq);
Imprime();
    LeBinarioTexto(tamTexto,arq);
ImprimeTexto();
    fclose(arq);
}

void LeBinarioListaPal(int tamListaPal, FILE *arq)
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
        ListaDePalavras = Insere(palAtual);
        palAtual = (char*) malloc (sizeof(char)*TAM_MAX_PAL);
        tamListaPal--;
    }
}

void LeBinarioTexto(int tamTexto, FILE *arq)
{
printf("\nLeitura do texto\n");
    CriaTexto();
    int i;
    int posPalavraLista = 0;
    for(i=0; i<tamTexto; i++)
    {
        TNODO* pal = NULL;
        fread(&posPalavraLista, sizeof(int),1,arq);
        pal = BuscaDadoListaPalavrasPos(posPalavraLista);
printf("Posicao: %d\nPalavra : %s\n", posPalavraLista, pal->pal);
        TextoComoListaDePalavras = InsereRef(pal);
    }
}

/************************MAIN*********************************/
int main()
{
    printf("LEITURA DO TEXTO COMPACTADO\n");
    printf("Digite o nome do arquivo binario contendo o texto: ");
    char *arqBin = (char*) malloc (sizeof(char)*50);
    scanf("%s",arqBin);

    LeArquivoBinario(arqBin);

    printf("\nLISTA DE PALAVRAS:\n");
    Imprime(ListaDePalavras);
    printf("Tamanho Lista: %d\n", TamanhoLista());
    printf("\nTEXTO:\n");
    ImprimeTexto(TextoComoListaDePalavras);
    printf("Tamanho Texto: %d\n\n", TamanhoTexto());

    printf("FIM!");
    getchar();
    return 0;
}
