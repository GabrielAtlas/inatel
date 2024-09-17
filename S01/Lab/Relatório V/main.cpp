#include <iostream>
#include <string>

using namespace std;

// Classe Pessoa
class Pessoa {
protected:
    string nome;
    int idade;

public:
    Pessoa(string nome, int idade) : nome(nome), idade(idade) {}

    void imprimirNome() {
        cout << "O nome é: " << nome << endl;
    }

    virtual void imprimirIdade() {
        cout << "Idade da pessoa: " << idade << " anos" << endl;
    }
};

// Classe Professor herdando de Pessoa
class Professor : public Pessoa {
public:
    Professor(string nome, int idade) : Pessoa(nome, idade) {}

    void imprimirIdade() override {
        cout << "Idade do professor: " << idade << " anos" << endl;
    }
};

// Classe Aluno herdando de Pessoa
class Aluno : public Pessoa {
private:
    int matricula;

public:
    Aluno(string nome, int idade, int matricula) : Pessoa(nome, idade), matricula(matricula) {}

    void imprimirIdade() override {
        cout << "Idade do aluno: " << idade << " anos" << endl;
    }

    void imprimirMatricula() {
        cout << "Matrícula do aluno: " << matricula << endl;
    }
};

int main() {
    // Criando objetos de Aluno, Professor e Pessoa
    Aluno aluno("João", 20, 12345);
    Professor professor("Carlos", 45);
    Pessoa pessoa("Maria", 30);

    // Imprimindo métodos e atributos de cada objeto
    cout << "--- Aluno ---" << endl;
    aluno.imprimirNome();
    aluno.imprimirIdade();
    aluno.imprimirMatricula();

    cout << "\n--- Professor ---" << endl;
    professor.imprimirNome();
    professor.imprimirIdade();

    cout << "\n--- Pessoa ---" << endl;
    pessoa.imprimirNome();
    pessoa.imprimirIdade();

    return 0;
}
