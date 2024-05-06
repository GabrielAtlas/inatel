from neo4j import GraphDatabase

class Neo4jGameManager:
    def __init__(self, uri, user, password):
        self._driver = GraphDatabase.driver(uri, auth=(user, password))

    def close(self):
        self._driver.close()

    def create_player(self, player_name):
        with self._driver.session() as session:
            session.write_transaction(self._create_player, player_name)

    @staticmethod
    def _create_player(tx, player_name):
        tx.run("CREATE (:Player {name: $name})", name=player_name)

    def get_players(self):
        with self._driver.session() as session:
            return session.read_transaction(self._get_players)

    @staticmethod
    def _get_players(tx):
        result = tx.run("MATCH (p:Player) RETURN p.name AS player_name")
        return [record["player_name"] for record in result]

    def delete_player(self, player_name):
        with self._driver.session() as session:
            session.write_transaction(self._delete_player, player_name)

    @staticmethod
    def _delete_player(tx, player_name):
        tx.run("MATCH (p:Player {name: $name}) DETACH DELETE p", name=player_name)

    def create_match(self, players, result):
        with self._driver.session() as session:
            session.write_transaction(self._create_match, players, result)

    @staticmethod
    def _create_match(tx, players, result):
        query = (
            "CREATE (m:Match {result: $result}) "
            "WITH m "
            "UNWIND $players AS player_name "
            "MATCH (p:Player {name: player_name}) "
            "CREATE (p)-[:PARTICIPATED_IN]->(m)"
        )
        tx.run(query, players=players, result=result)

    def get_player_matches(self, player_name):
        with self._driver.session() as session:
            return session.read_transaction(self._get_player_matches, player_name)

    @staticmethod
    def _get_player_matches(tx, player_name):
        query = (
            "MATCH (p:Player {name: $name})-[:PARTICIPATED_IN]->(m:Match) "
            "RETURN m.result AS result"
        )
        result = tx.run(query, name=player_name)
        return [record["result"] for record in result]


# Exemplo de uso:
uri = "bolt://localhost:7687"
user = "neo4j"
password = "your_password"

manager = Neo4jGameManager(uri, user, password)

# Criar jogadores
manager.create_player("Player 1")
manager.create_player("Player 2")
manager.create_player("Player 3")

# Obter lista de jogadores
players = manager.get_players()
print("Lista de jogadores:", players)

# Criar partida
players_in_match = ["Player 1", "Player 2", "Player 3"]
result = {"Player 1": 10, "Player 2": 15, "Player 3": 12}
manager.create_match(players_in_match, result)

# Obter histórico de partidas de um jogador
player_name = "Player 1"
player_matches = manager.get_player_matches(player_name)
print(f"Histórico de partidas de {player_name}:", player_matches)

manager.close()
