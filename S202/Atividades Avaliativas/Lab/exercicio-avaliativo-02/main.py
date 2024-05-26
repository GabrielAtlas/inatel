import argparse
from database import Database
from teacher_crud import TeacherCRUD

class CLI:
    def __init__(self):
        self.db = Database("SUA URI", "SEU USER AQUI", "SEU PASSWORD")
        self.teacher_crud = TeacherCRUD(self.db)

    def create_teacher(self, name, ano_nasc, cpf):
        self.teacher_crud.create(name, ano_nasc, cpf)

    def read_teacher(self, name):
        teacher = self.teacher_crud.read(name)
        print(teacher)

    def update_teacher(self, name, new_cpf):
        self.teacher_crud.update(name, new_cpf)

    def delete_teacher(self, name):
        self.teacher_crud.delete(name)

    def close(self):
        self.db.close()

def main():
    parser = argparse.ArgumentParser(description="Teacher CRUD CLI")
    parser.add_argument("action", choices=["create", "read", "update", "delete"], help="CRUD action")
    parser.add_argument("--name", help="Name of the teacher")
    parser.add_argument("--ano_nasc", type=int, help="Year of birth of the teacher")
    parser.add_argument("--cpf", help="CPF of the teacher")
    parser.add_argument("--new_cpf", help="New CPF for update")

    args = parser.parse_args()

    cli = CLI()

    if args.action == "create":
        if args.name and args.ano_nasc and args.cpf:
            cli.create_teacher(args.name, args.ano_nasc, args.cpf)
        else:
            print("Missing arguments for create action")
    elif args.action == "read":
        if args.name:
            cli.read_teacher(args.name)
        else:
            print("Missing arguments for read action")
    elif args.action == "update":
        if args.name and args.new_cpf:
            cli.update_teacher(args.name, args.new_cpf)
        else:
            print("Missing arguments for update action")
    elif args.action == "delete":
        if args.name:
            cli.delete_teacher(args.name)
        else:
            print("Missing arguments for delete action")

    cli.close()

if __name__ == "__main__":
    main()
