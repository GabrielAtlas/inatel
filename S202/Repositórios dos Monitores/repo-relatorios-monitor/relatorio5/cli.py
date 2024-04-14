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


class PersonCLI(SimpleCLI):
    def __init__(self, person_model):
        super().__init__()
        self.person_model = person_model
        self.add_command("create", self.create_person)
        self.add_command("read", self.read_person)
        self.add_command("update", self.update_person)
        self.add_command("delete", self.delete_person)

    def create_person(self):
        name = input("Enter the name: ")
        age = int(input("Enter the age: "))
        self.person_model.create_person(name, age)

    def read_person(self):
        id = input("Enter the id: ")
        person = self.person_model.read_person_by_id(id)
        if person:
            print(f"Name: {person['name']}")
            print(f"Age: {person['age']}")

    def update_person(self):
        id = input("Enter the id: ")
        name = input("Enter the new name: ")
        age = int(input("Enter the new age: "))
        self.person_model.update_person(id, name, age)

    def delete_person(self):
        id = input("Enter the id: ")
        self.person_model.delete_person(id)
        
    def run(self):
        print("Welcome to the person CLI!")
        print("Available commands: create, read, update, delete, quit")
        super().run()
        
