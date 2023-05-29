#include <iostream>

using namespace std;

struct Point
{
  float x, y;
};

struct Line
{
  Point a, b;
};

bool pointIsOnLine(float m, int c, float x, float y)
{

  if (y == ((m * x) + c))
    return true;

  return false;
}

// is BC inline with AC or visa-versa
bool inLine(Point A, Point B, Point C)
{
  // if AC is vertical
  if (A.x == C.x)
    return B.x == C.x;
  // if AC is horizontal
  if (A.y == C.y)
    return B.y == C.y;
  // match the gradients
  return (A.x - C.x) * (A.y - C.y) == (C.x - B.x) * (C.y - B.y);
}

int main()
{
  int N;

  cin >> N;

  Line caminho[N - 1];

  for (int i = 0; i < N; i++)
  {
    float pointX, pointY;

    cin >> pointX >> pointY;

    Point point = {pointX, pointY};

    caminho[i].a = point;

    if (i != 0)
    {
      caminho[i - 1].b = point;
    }
  }

  float carX, carY;

  cin >> carX >> carY;

  int c = 0;

  bool foundAny = false;
  for (int j = 0; j < N - 1; j++)
  {
    Line line = caminho[j];
    Point carPoint = {carX, carY};
    // float m = (line.b.y - line.a.y) / (line.b.x - line.a.x);
    if (inLine(line.a, line.b, carPoint))
    {
      foundAny = true;
    }
  }

  if (foundAny)
  {
    cout << "O carro esta no caminho" << endl;
  }
  else
  {
    cout << "O carro esta fora do caminho" << endl;
  }
}