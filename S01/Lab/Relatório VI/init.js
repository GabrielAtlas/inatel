class Animal {
  constructor(nome, idade, especie) {
    this.nome = nome;
    this.idade = idade;
    this.especie = especie;
  }

  printInfo() {
    console.log(`Nome: ${this.nome}, Idade: ${this.idade}, Espécie: ${this.especie}`);
  }
}

class Cachorro extends Animal {
  #raca;

  constructor(nome, idade, especie, raca) {
    super(nome, idade, especie);
    this.#raca = raca;
  }

  printInfo() {
    super.printInfo();
    console.log(`Raça: ${this.#raca}`);
  }
}

class Gato extends Animal {
  constructor(nome, idade, especie, cores) {
    super(nome, idade, especie);
    this.cores = cores;
  }

  printInfo() {
    super.printInfo();
    console.log(`Cores: ${this.cores.join(', ')}`);
  }
}

const animal = new Animal('Elefante', 10, 'Mamífero');
const cachorro = new Cachorro('Rex', 5, 'Cachorro', 'Pastor Alemão');
const gato = new Gato('Mimi', 2, 'Felino', ['Branco', 'Cinza']);

animal.printInfo();
console.log('------------------');
cachorro.printInfo();
console.log('------------------');
gato.printInfo();
