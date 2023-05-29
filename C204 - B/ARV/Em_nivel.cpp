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

void insere(list<treenodeptr> q, treenodeptr value)
{
  q.push_back(value);
}

void emNivel(treenodeptr t)
{
  treenodeptr n;
  list<treenodeptr> q;
  if (t != NULL)
  {
    insere(q, t);
    while (!q.empty())
    {
      n = *q.begin();
      q.pop_front();
      if (n->esq != NULL)
        insere(q, n->esq);
      if (n->dir != NULL)
        insere(q, n->dir);
      cout << n->info << endl;
    }
  }
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

int main(int argc, char const *argv[])
{

  treenodeptr arvore = NULL;

  int read = 0;

  while (read != -1)
  {
    cin >> read;
    if (read != -1)
    {
      tInsere(arvore, read);
    }
  }

  emNivel(arvore);

  tDestruir(arvore);
}