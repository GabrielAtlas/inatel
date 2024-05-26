class Query:
    def __init__(self, database):
        self.db = database

    def get_teacher_by_name(self, name):
        query = "MATCH (t:Teacher {name: $name}) RETURN t.ano_nasc AS ano_nasc, t.cpf AS cpf"
        parameters = {"name": name}
        return self.db.execute_query(query, parameters)

    def get_teachers_by_initial(self, initial):
        query = "MATCH (t:Teacher) WHERE t.name STARTS WITH $initial RETURN t.name AS name, t.cpf AS cpf"
        parameters = {"initial": initial}
        return self.db.execute_query(query, parameters)

    def get_all_cities(self):
        query = "MATCH (c:City) RETURN c.name AS name"
        return self.db.execute_query(query)

    def get_schools_by_number_range(self, min_number, max_number):
        query = "MATCH (s:School) WHERE s.number >= $min_number AND s.number <= $max_number RETURN s.name AS name, s.address AS address, s.number AS number"
        parameters = {"min_number": min_number, "max_number": max_number}
        return self.db.execute_query(query, parameters)

    ## QUESTÃO 2:

    def get_oldest_and_youngest_teacher_year(self):
        query = """
        MATCH (t:Teacher)
        RETURN MAX(t.ano_nasc) AS youngest, MIN(t.ano_nasc) AS oldest
        """
        return self.db.execute_query(query)

    def get_average_city_population(self):
        query = "MATCH (c:City) RETURN AVG(c.population) AS average_population"
        return self.db.execute_query(query)

    def get_city_by_cep(self, cep):
        query = """
        MATCH (c:City {cep: $cep})
        RETURN REPLACE(c.name, 'a', 'A') AS name
        """
        parameters = {"cep": cep}
        return self.db.execute_query(query, parameters)

    def get_third_letter_from_teacher_names(self):
        query = """
        MATCH (t:Teacher)
        RETURN SUBSTRING(t.name, 2, 1) AS third_letter
        """
        return self.db.execute_query(query)