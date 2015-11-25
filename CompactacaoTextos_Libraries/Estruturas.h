typedef struct Temp{
    char *pal;
    struct Temp *prox;
}TNODO;

typedef struct Temp2{
    TNODO *refPal;
    struct Temp2 *prox;
}TREF;

/*****************LISTA DE PALAVRAS**********************/
void CriaLista();
TNODO *Insere(char *palavra,  TNODO* ListaDePalavras);

TNODO *BuscaDadoListaPalavras(char *dado, TNODO* ListaDePalavras);
TNODO *BuscaDadoListaPalavrasPos(int pos, TNODO* ListaDePalavras);
int posPalavra(char *dado, TNODO* ListaDePalavras);
int ExisteDadoListaPalavras(char *dado, TNODO* ListaDePalavras);

int TamanhoLista();

/*********************TEXTO****************************/
void CriaTexto(TREF *TextoComoListaDePalavras);
TREF *InsereRef(TNODO *palavra, TREF *TextoComoListaDePalavras);
void ImprimeTexto(TREF *TextoComoListaDePalavras);

int TamanhoTexto(TREF *TextoComoListaDePalavras);

