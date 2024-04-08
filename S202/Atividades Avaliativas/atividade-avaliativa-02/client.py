from neo4j import GraphDatabase

class Neo4jClient:
    def __init__(self, uri, user, password):
        self._uri = uri
        self._user = user
        self._password = password
        self._driver = GraphDatabase.driver(self._uri, auth=(self._user, self._password))

    def close(self):
        self._driver.close()

    def query(self, query, parameters=None):
        with self._driver.session() as session:
            result = session.run(query, parameters)
            return result.data()

def engenheiros_familia(client):
    query = """
    MATCH (p:Pessoa:Engenheiro)
    RETURN p.nome
    """
    result = client.query(query)
    return [record['p.nome'] for record in result]

def pais_de(client, pessoa):
    query = """
    MATCH (p:Pessoa {nome: $nome})-[:PAI_DE|MÃE_DE]->(filho)
    RETURN filho.nome
    """
    result = client.query(query, {'nome': pessoa})
    return [record['filho.nome'] for record in result]

def namorados(client, pessoa):
    query = """
    MATCH (p:Pessoa {nome: $nome})-[:NAMORADO_DE]->(parceiro)
    RETURN parceiro.nome
    """
    result = client.query(query, {'nome': pessoa})
    return [record['parceiro.nome'] for record in result]

uri = "bolt://localhost:7687"
user = "neo4j"
password = ""

neo4j_client = Neo4jClient(uri, user, password)

print("Engenheiros na família:", engenheiros_familia(neo4j_client))
print("Pais de Ana:", pais_de(neo4j_client, 'Ana'))
print("Namorados de Pedro:", namorados(neo4j_client, 'Pedro'))

neo4j_client.close()
