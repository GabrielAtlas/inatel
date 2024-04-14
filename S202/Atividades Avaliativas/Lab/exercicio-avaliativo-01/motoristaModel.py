from pymongo import MongoClient
from bson.objectid import ObjectId

class MotoristaDAO:
    def __init__(self, database):
        self.db = database

    def create_motorista(self, nome: str, nota:int):
        try:
            res = self.db.collection.insert_one({"nome": nome, "nota": nota, "corridas": []})
            print(f"Motorista criado: {res.inserted_id}")
            return res.inserted_id
        except Exception as e:
            print(f"An error occurred while creating Motorista: {e}")
            return None

    def add_corrida(self, motorista_id: str, corrida_data: dict):
        try:
            res = self.db.collection.update_one(
                {"_id": ObjectId(motorista_id)},
                {"$push": {"corridas": corrida_data}}
            )
            print(f"Corrida adicionada ao motorista: {res.modified_count} document(s) modified")
            return res.modified_count
        except Exception as e:
            print(f"An error occurred while adding corrida: {e}")
            return None

    def read_motorista_by_id(self, id: str):
        try:
            res = self.db.collection.find_one({"_id": ObjectId(id)})
            print(f"Motorista encontrado: {res}")
            return res
        except Exception as e:
            print(f"An error occurred while reading person: {e}")
            return None

    def update_motorista(self, id: str, nota: int, corridas: []):
        try:
            res = self.db.collection.update_one({"_id": ObjectId(id)}, {"$set": {"nota": nota, "corridas": corridas}})
            print(f"Motorista atualizado: {res.modified_count} document(s) modified")
            return res.modified_count
        except Exception as e:
            print(f"An error occurred while updating person: {e}")
            return None

    def delete_motorista(self, id: str):
        try:
            res = self.db.collection.delete_one({"_id": ObjectId(id)})
            print(f"Person deleted: {res.deleted_count} document(s) deleted")
            return res.deleted_count
        except Exception as e:
            print(f"An error occurred while deleting person: {e}")
            return None