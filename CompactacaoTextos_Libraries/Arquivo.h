
/*******************ARQUIVO TEXTO***********************/
void LeArquivoTexto(char *arqTxt, TNODO* ListaDePalavras, TREF* TextoComoListaDePalavras);

/*******************ARQUIVO SALVA BINÁRIO***********************/
void GravaArquivoBinario();

void GravaBinarioLista(FILE *arq, TNODO* ListaDePalavras);

void GravaBinarioTexto(FILE *arq, TNODO* ListaDePalavras, TREF* TextoComoListaDePalavras);

/*******************ARQUIVO LÊ BINÁRIO***********************/
void LeArquivoBinario();

void LeBinarioListaPal(int tamListaPal, FILE *arq,  TNODO* ListaDePalavras);

void LeBinarioTexto(int tamTexto, FILE *arq, TNODO* ListaDePalavras, TREF *TextoComoListaDePalavras);



