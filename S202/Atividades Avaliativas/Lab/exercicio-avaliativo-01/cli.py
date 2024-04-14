class SimpleCLI:
    def __init__(self):
        self.commands = {}

    def add_command(self, name, function):
        self.commands[name] = function

    def run(self):
        while True:
            command = input("Enter a command: ")
            if command == "quit":
                print("Goodbye!")
                break
            elif command in self.commands:
                self.commands[command]()
            else:
                print("Invalid command. Try again.")


class MotoristaCLI(SimpleCLI):
    def __init__(self, motoristaDAO):
        super().__init__()
        self.motoristaDAO = motoristaDAO
        self.add_command("criarMotorista", self.create_motorista)
        self.add_command("addCorrida", self.add_corrida)
        self.add_command("consultarMotorista", self.read_motorista)
        self.add_command("deleteMotorista", self.delete_motorista)

    def create_motorista(self):
        nome = input("Digite o nome do motorista: ")
        nota = input("Digite a nota do motorista: ")
        self.motoristaDAO.create_motorista(nome, nota)

    def add_corrida(self):
        idMotorista = input("Digite o id do motorista: ")
        notaCorrida = input("Digite a nota da corrida: ")
        distanciaCorrida = input("Digite a distancia da corrida: ")
        valorCorrida = input("Digite o valor da corrida: ")
        passageiroNome = input("Digite o nome do passageiro: ")
        passageiroDocumento = input("Digite o documento do passageiro: ")

        corridaObj = {
            "nota": notaCorrida,
            "distancia": distanciaCorrida,
            "valor": valorCorrida,
            "passageiro": {
                "nome": passageiroNome,
                "documento": passageiroDocumento
            }
        }

        self.motoristaDAO.add_corrida(idMotorista, corridaObj)

    def read_motorista(self):
        id = input("Digite o ID do motorista: ")
        motorista = self.motoristaDAO.read_motorista_by_id(id)
        if motorista:
            print(f"Nome: {motorista['nome']}")
            print(f"Nota: {motorista['nota']}")
            print(f"Total de corridas: {len(motorista['corridas'])}\n")
            i = 1
            for corrida in motorista['corridas']:
                print(f" Corrida #{i}:")
                print(f"  Nota: {corrida['nota']}")
                print(f"  Distancia: {corrida['distancia']}")
                print(f"  Valor: {corrida['valor']}")
                print(f"  Nome do Passageiro: {corrida['passageiro']['nome']}")
                print(f"  Documento do Passageiro: {corrida['passageiro']['documento']}")
                i += 1

    def delete_motorista(self):
        id = input("Digite o ID do motorista: ")
        self.motoristaDAO.delete_person(id)

    def run(self):
        print("Welcome to the person CLI!")
        print("Available commands: criarMotorista, addCorrida, deleteMotorista, consultarMotorista, quit")
        super().run()

