MATCH(n) return n // Retorna todos os nodes criados

1. **MATCH:**
   ```cypher
   MATCH (p:Person)
   RETURN p.name, p.age
   ```
   Este comando irá encontrar todos os nós de tipo "Person" e retornar seus nomes e idades.

2. **CREATE:**
   ```cypher
   CREATE (p:Person {name: 'Alice', age: 30})
   ```
   Este comando irá criar um novo nó de tipo "Person" com o nome "Alice" e a idade de 30 anos.

3. **MERGE:**
   ```cypher
   MERGE (p:Person {name: 'Bob'})
   ON CREATE SET p.created = timestamp()
   ```
   Este comando irá criar um novo nó de tipo "Person" com o nome "Bob" se ele ainda não existir e definirá a propriedade "created" como o carimbo de data/hora atual.

4. **DELETE:**
   ```cypher
   MATCH (p:Person {name: 'Alice'})
   DELETE p
   ```
   Este comando irá excluir o nó de tipo "Person" com o nome "Alice" do grafo.

5. **RETURN:**
   ```cypher
   MATCH (p:Person)
   RETURN p.name, p.age
   ```
   Este comando irá retornar os nomes e idades de todos os nós de tipo "Person" no grafo.

6. **WHERE:**
   ```cypher
   MATCH (p:Person)
   WHERE p.age > 25
   RETURN p.name, p.age
   ```
   Este comando irá retornar os nomes e idades de todos os nós de tipo "Person" com idade superior a 25 anos.

7. **SET:**
   ```cypher
   MATCH (p:Person {name: 'Alice'})
   SET p.age = 31
   ```
   Este comando irá atualizar a idade do nó de tipo "Person" com o nome "Alice" para 31 anos.

8. **UNWIND:**
   ```cypher
   UNWIND [1, 2, 3, 4, 5] AS number
   RETURN number * 2 AS doubled
   ```
   Este comando irá desdobrar a lista [1, 2, 3, 4, 5] em linhas individuais e retornar cada número multiplicado por 2.

9. **ORDER BY:**
   ```cypher
   MATCH (p:Person)
   RETURN p.name, p.age
   ORDER BY p.age DESC
   ```
   Este comando irá retornar os nomes e idades de todos os nós de tipo "Person" no grafo, ordenados por idade em ordem decrescente.

10. **LIMIT:**
    ```cypher
    MATCH (p:Person)
    RETURN p.name, p.age
    LIMIT 5
    ```
    Este comando irá retornar os nomes e idades dos primeiros 5 nós de tipo "Person" no grafo.

11. **CREATE INDEX:**
    ```cypher
    CREATE INDEX ON :Person(name)
    ```
    Este comando irá criar um índice na propriedade "name" dos nós de tipo "Person" para acelerar as consultas que usam essa propriedade.

12. **FOREACH:**
    ```cypher
    FOREACH (name IN ['Alice', 'Bob', 'Charlie'] |
      CREATE (:Person {name: name})
    )
    ```
    Este comando irá criar nós de tipo "Person" com os nomes "Alice", "Bob" e "Charlie" em uma única transação.


Para criar um relacionamento entre dois nós no Neo4j usando Cypher, você pode usar o comando CREATE. Aqui está um exemplo de como você pode fazer isso:

MATCH (a:Node {id: 1}), (b:Node {id: 2})
CREATE (a)-[:RELATIONSHIP_TYPE]->(b)
Neste exemplo:

MATCH (a:Node {id: 1}) encontra o primeiro nó com o label "Node" e a propriedade "id" igual a 1.
(b:Node {id: 2}) encontra o segundo nó com o label "Node" e a propriedade "id" igual a 2.
CREATE (a)-[:RELATIONSHIP_TYPE]->(b) cria um relacionamento do nó 'a' para o nó 'b' com o tipo "RELATIONSHIP_TYPE".
Certifique-se de substituir "Node" pelo label dos seus nós e "RELATIONSHIP_TYPE" pelo tipo de relacionamento que deseja criar. 
Além disso, ajuste as propriedades e valores conforme necessário para encontrar os nós específicos com os quais deseja criar o relacionamento.






