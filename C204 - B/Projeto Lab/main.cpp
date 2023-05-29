#include <iostream> // Entrada e saï¿½da
#include <climits>  // INT_MAX

using namespace std;

#define V 100

int minDistance(int dist[], bool sptSet[], int size)
{

  int min = INT_MAX, min_index;

  for (int v = 0; v < size; v++)
    if (sptSet[v] == false && dist[v] <= min)
      min = dist[v], min_index = v;

  return min_index;
}

// Função que printa o caminho do resultado
void printPath(int parent[], int j)
{
  if (parent[j] == -1) // condição de parada da função recursiva
    return;

  printPath(parent, parent[j]);

  cout << "Cidade " << j << " ";
}

void printSolution(int dist[], int parent[], int src, int finalPoint, int size)
{
  cout << "O menor tempo para ir da cidade " << src << " para a cidade " << finalPoint << " é de: " << dist[finalPoint] << " minutos/horas/dias" << endl;
  cout << "Para gastar este tempo utilize a rota: " << endl;
  cout << endl
       << "Cidade " << src << " ";
  printPath(parent, finalPoint);
  cout << endl;
}

void dijkstra(int graph[V][V], int src, int finalPoint, int size)
{
  int dist[size]; // Vetor que armazenará o tempo que será gasto para ir de src para outra cidade

  bool sptSet[size]; // Vetor booleano que armazenará se o vertice u foi processado.

  // Vetor que armazenará a rota do menor caminho da arvore
  // Necessário para utilizar o metodo de print das rotas
  int parent[size];

  // Definindo que o caminho para o src é -1. (Necessário para a condição de parada da função printPath)
  parent[src] = -1;

  // Inicialização de dados de todos os pontos, criando valor do tempo das rotas no vetor dist
  for (int i = 0; i < size; i++)
    dist[i] = INT_MAX, sptSet[i] = false;

  // Definindo o valor da distancia de src para src como sendo zero.
  dist[src] = 0;

  // Encontrando o menor caminho a partir dos vertices
  for (int count = 0; count < size - 1; count++)
  {

    int u = minDistance(dist, sptSet, size);

    // Define que o vertice foi processado no vetor que armazena se o vertice foi processado.
    sptSet[u] = true;

    for (int v = 0; v < size; v++)

      // Avalia se o peso para ir de src para v por u é menor que o valor atual de dist[v]
      if (!sptSet[v] && graph[u][v] &&
          dist[u] + graph[u][v] < dist[v])
      {
        parent[v] = u;
        dist[v] = dist[u] + graph[u][v];
      }
  }

  // Printando o resultado
  printSolution(dist, parent, src, finalPoint, size);
}

int main()
{
  int custo[V][V]; // 100 -> numero maximo de cidades
                   // custo[i][j] = custo de ir da cidade 'i' para a cidade 'j'

  int cidade_inicial, cidade_final; // variaveis que armazenam as cidades de onde o usuário está e para onde ele vai.

  int n = 0; // variavel que armazena o valor total de rotas cadastradas pelo usuário

  cout << endl;
  cout << "Bem vindo a Planicie Driver !" << endl;
  cout << "Comece cadastrando as rotas disponiveis para o usuário:" << endl;

  int i = 0, j = 0, custoEspecifico = 0, read = 1; // variaveis auxiliares para leitura de dados.

  // Código para a entrada de dados.
  do
  {
    cout << endl;
    cout << "Digite o ID da cidade para definir uma rota: ";
    cin >> i;

    cout << "Digite o ID da cidade destino desta rota: ";
    cin >> j;

    cout << "Digite o custo de tempo casto para ir da cidade " << i << " até a cidade " << j << ": ";
    cin >> custoEspecifico;

    custo[i][j] = custoEspecifico; // setando que para ir de i -> j vale custoEspecifico
    custo[j][i] = custoEspecifico; // setando que para ir de j -> i vale custoEspecifico

    cout << endl
         << "Rota " << n << " cadastrada com sucesso." << endl;

    n++; // incrementando no valor total de rotas

    cout << "Deseja continuar cadastrando mais rotas? (0 = Não, 1 = Sim): ";
    cin >> read;

  } while (read); // Não para enquanto o usuário solicitar que existe mais rotas para serem cadastradas.

  cout << endl
       << "Todas as rotas foram cadastradas com sucesso." << endl;

  cout << "Digite a cidade em que você se encontra: ";

  cin >> cidade_inicial;
  cout << endl;

  cout << "Digite a cidade para a qual você quer ir: ";
  cin >> cidade_final;

  dijkstra(custo, cidade_inicial, cidade_final, n); // chamando a função para printar o resultado.

  return 0;
}
