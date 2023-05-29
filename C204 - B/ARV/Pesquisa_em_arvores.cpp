#include <iostream>

using namespace std;

struct treenode
{
  int info;
  treenode *esq;
  treenode *dir;
};

typedef treenode *treenodeptr;

void tDestruir(treenodeptr &arvore)
{
  if (arvore != NULL)
  {
    tDestruir(arvore->esq);
    tDestruir(arvore->dir);
    delete arvore;
  }
  arvore = NULL;
}

void tInsere(treenodeptr &p, int x)
{
  if (p == NULL)
  {
    p = new treenode;
    p->info = x;
    p->esq = NULL;
    p->dir = NULL;
  }
  else if (x < p->info)
    tInsere(p->esq, x);
  else
    tInsere(p->dir, x);
}

treenodeptr tPesq(treenodeptr p, int x)
{
  if (p == NULL)
    return NULL;

  else if (x == p->info) // elemento encontrado na raiz
    return p;

  else if (x < p->info) // procura na subárvore esquerda
    return tPesq(p->esq, x);

  else
    return tPesq(p->dir, x); // pesquisa na subárvore direita
}

int main()
{

  treenodeptr arvore = NULL, result = NULL; // arvores
  int i, N, read, X;                        // variaveis

  cin >> N;

  for (i = 0; i <= N; i++)
  {
    cin >> read;
    tInsere(arvore, read);
  }

  cin >> X;
  result = tPesq(arvore, X);

  // saída
  if (result == NULL)
    cout << "Nao encontrado" << endl;

  else
    cout << result->info << endl;

  return 0;
}