using System;

// Classe base Cachorro
class Cachorro
{
    public string Nome { get; set; }
    public int Idade { get; set; }

    public Cachorro(string nome, int idade)
    {
        Nome = nome;
        Idade = idade;
    }

    public void MostrarNome()
    {
        Console.WriteLine($"O nome do cachorro é: {Nome}");
    }

    public virtual void MostrarIdade()
    {
        Console.WriteLine($"A idade do cachorro é: {Idade}");
    }
}

// Classe CachorroGrande que herda de Cachorro
class CachorroGrande : Cachorro
{
    private string tamanho;

    public CachorroGrande(string nome, int idade, string tamanho)
        : base(nome, idade)
    {
        this.tamanho = tamanho;
    }

    public override void MostrarIdade()
    {
        Console.WriteLine($"A idade do cachorro grande é: {Idade} anos e o tamanho é {tamanho}");
    }
}

// Classe CachorroPequeno que herda de Cachorro
class CachorroPequeno : Cachorro
{
    public CachorroPequeno(string nome, int idade)
        : base(nome, idade)
    {
    }

    public override void MostrarIdade()
    {
        Console.WriteLine($"A idade do cachorro pequeno é: {Idade} anos.");
    }
}

class Program
{
    static void Main(string[] args)
    {
        // Criando objetos das classes
        Cachorro cachorro = new Cachorro("Rex", 5);
        CachorroPequeno cachorroPequeno = new CachorroPequeno("Bolinha", 2);
        CachorroGrande cachorroGrande = new CachorroGrande("Thor", 8, "Grande");

        // Imprimindo métodos e atributos dos objetos
        cachorro.MostrarNome();
        cachorro.MostrarIdade();

        cachorroPequeno.MostrarNome();
        cachorroPequeno.MostrarIdade();

        cachorroGrande.MostrarNome();
        cachorroGrande.MostrarIdade();
    }
}
