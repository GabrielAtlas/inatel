#include <iostream>
#include <iomanip>
#include <list>

using namespace std;

struct treenode
{
  int info;
  treenode *esq;
  treenode *dir;
};

typedef treenode *treenodeptr;

treenodeptr tMenor(treenodeptr &raiz)
{
  treenodeptr t;
  t = raiz;
  if (t->esq == NULL) // encontrou o menor valor
  {
    raiz = raiz->dir;
    return t;
  }
  else // continua a busca na sub ́arvore esquerda
    return tMenor(raiz->esq);
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

int tRemove(treenodeptr &raiz, int x)
{
  treenodeptr p;

  if (raiz == NULL) //  ́arvore vazia
    return 1;

  if (x == raiz->info)
  {
    p = raiz;
    if (raiz->esq == NULL) // a raiz n~ao tem filho esquerdo
      raiz = raiz->dir;
    else if (raiz->dir == NULL) // a raiz n~ao tem filho direito
      raiz = raiz->esq;
    else // a raiz tem ambos os filhos
    {
      p = tMenor(raiz->dir);
      raiz->info = p->info;
    }
    cout << p->info << " ";
    delete p;
    return 0;
  }
  else if (x < raiz->info)
    return tRemove(raiz->esq, x);
  else
    return tRemove(raiz->dir, x);
}

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

int main(int argc, char const *argv[])
{

  int x = 0;                 // variavel auxiliar para leitura de dados
  treenodeptr arvore = NULL; // armazena os numeros
  cin >> x;
  while (x != 0)
  {
    cin >> x;
    if (x != 0)
      tInsere(arvore, x);
  }

  int z = 0;

  cin >> z;

  int a = tRemove(arvore, z);

  cout << a;

  tDestruir(arvore);
}
