import sqlite3
import json

class Database:
    def _init_(self, db_file="pokedex.db"):
        self.connection = sqlite3.connect(db_file)
        self.cursor = self.connection.cursor()

    def query(self, query):
        self.cursor.execute(query)
        return self.cursor.fetchall()

    def close(self):
        self.connection.close()

class Pokedex:
    def _init_(self, database: Database):
        self.database = database

    def query_1(self):
        result = self.database.query("SELECT * FROM Pokemon WHERE type='Fire'")
        self.write_a_json(result, "Query 1 executed")

    def query_2(self):
        result = self.database.query("SELECT * FROM Pokemon WHERE generation=1")
        self.write_a_json(result, "Query 2 executed")

    def query_3(self):
        result = self.database.query("SELECT * FROM Pokemon WHERE base_experience > 200")
        self.write_a_json(result, "Query 3 executed")

    def query_4(self):
        result = self.database.query("SELECT * FROM Pokemon WHERE legendary=True")
        self.write_a_json(result, "Query 4 executed")

    def query_5(self):
        result = self.database.query("SELECT * FROM Pokemon ORDER BY height DESC LIMIT 5")
        self.write_a_json(result, "Query 5 executed")

    def write_a_json(self, data, message):
        log_entry = {"message": message, "data": data}
        with open("logs.txt", "a") as log_file:
            log_file.write(json.dumps(log_entry) + "\n")


db_instance = Database()  
pokedex_instance = Pokedex(db_instance)

pokedex_instance.query_1()
pokedex_instance.query_2()

db_instance.close()