#include <iostream>
#include <iomanip>

using namespace std;

struct treenode
{
  int info;
  treenode *esq;
  treenode *dir;
};

typedef treenode *treenodeptr;

int totalValues = 0;

float sumNodes(treenodeptr p, float initial)
{
  float result = initial;

  if (p == NULL)
  {
    return result;
  }

  totalValues++;

  result += p->info;
  result += sumNodes(p->esq, 0);
  result += sumNodes(p->dir, 0);
  return result;
}

float media(treenodeptr arvore)
{
  float totalSum = sumNodes(arvore, 0);
  float result = totalSum / totalValues;
  return result;
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

  treenodeptr arvore = NULL, result = NULL; // arvores

  int read = 0;

  do
  {
    cin >> read;

    if (read != -1)
    {
      tInsere(arvore, read);
      cout << "Valor " << read << " inserido na arvore de elementos com sucesso." << endl;
    }

  } while (read != -1);

  float mediaResult = media(arvore);

  cout << fixed << setprecision(2) << "Media: " << mediaResult << endl;

  tDestruir(arvore);

  return 0;
}
