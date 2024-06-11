'''
Nessa atividade você deve usar seus conhecimentos sobre banco de dados chave-valor, mais especificamente sobre Redis para atender os requisitos pedidos.
Todos as questões tem um exemplo de caso de teste (com dados de entrada e saída esperada) para que você valide a sua solução.

Contexto: Imagine que você está desenvolvendo um banco de dados de cache para uma rede social. O objetivo do sistema é sempre exibir os posts mais recentes dessa rede social em ordem de interesse.
Para isso, são mantidas informações sobre os interesses dos usuários e sobre o tópico de cada postagem.
Esse sistema deve armazenar o perfil de cada usuário, contendo o nome, o e-mail e um token de sessão. Além, disso o sistema deve manter uma lista de interesses para cada usuário, ordenando pelo score de interesse.
Por fim, o sistema deve manter informações sobre os posts, como id, conteúdo, e-mail do autor e data e hora de publicação. É registrado também uma lista de palavras chave sobre o post. Apenas as informações dos posts mais recentes devem ser mantidos no banco de dados (posts com mais de 5 horas devem ser excluídos).
Considere que o interesse de um usuário por um post específico é a soma dos scores de cada interesse que está presente nas palavras chave desse post específico.

Questão 1)
Registe as informações dos seguintes usuários e realize a consutla para apresentar os dados.

Questão 2)
Registre uma lista de interesses para cada um dos usuários e realize a consutla para apresentar os dados.

Questão 3)
Resgistre as informações sobre os posts mais recentes e realize a consutla para apresentar os dados.

Questão 4)
Imagine que o usuário 3 acessa o seu feed. Realize uma consulta nos dados cadatrados par mostrar a lista dos posts mais interessantes para esse usuário.


Questão 5)
Imagine que será mantido também um lista de posts já vistos por um determinado usuário. Registre essa lista para cada um dos usuários e realize a consutla para apresentar os dados.

'''

import redis

redis_conn = redis.Redis(
    host="127.0.0.1", port=6379,
    username="default", # use your Redis user. More info https://redis.io/docs/latest/operate/oss_and_stack/management/security/acl/
    password="", # use your Redis password
    decode_responses=True
)

redis_conn.flushall();


# Questão 1
def questao_1(users):
    for user in users:
        user_key = f"user:{user['id']}"
        redis_conn.hset(user_key, mapping={"nome": user['nome'], "email": user['email']})

    stored_users = []
    for user in users:
        user_key = f"user:{user['id']}"
        stored_user = redis_conn.hgetall(user_key)
        stored_user['id'] = user['id']  # Ensure the 'id' is included in the result
        stored_users.append(stored_user)

    return stored_users


def test_questao_1():

    users = [
        {"id":'1', "nome":"Serafim Amarantes", "email":"samarantes@g.com"},
        {"id":'2', "nome":"Tamara Borges", "email":"tam_borges@g.com"},
        {"id":'3', "nome":"Ubiratã Carvalho", "email":"bira@g.com"},
        {"id":'4', "nome":"Valéria Damasco", "email":"valeria_damasco@g.com"}
    ]

    assert users == sorted(questao_1(users), key=lambda d: d['id'])


# Questão 2
def questao_2(interests):
    # Registrar os interesses para cada usuário no Redis
    for user_interest in interests:
        user_id = user_interest["usuaruo"]
        for interest in user_interest["interesses"]:
            for topic, score in interest.items():
                redis_conn.rpush(f"user:{user_id}:interests", f"{topic}:{score}")

    # Recuperar os interesses para cada usuário
    result = []
    for user_interest in interests:
        user_id = user_interest["usuaruo"]
        raw_interests = redis_conn.lrange(f"user:{user_id}:interests", 0, -1)
        user_interests = [(item.split(':')[0], float(item.split(':')[1])) for item in raw_interests]
        result.append(sorted(user_interests))  # Ordenar os interesses para garantir consistência

    return result


def test_questao_2():
    interests = [
        {"usuaruo": 1, "interesses": [{"futebol": 0.855}, {"pagode": 0.765}, {"engraçado": 0.732}, {"cerveja": 0.622}, {"estética": 0.519}]},
        {"usuaruo": 2, "interesses": [{"estética": 0.765}, {"jiujitsu": 0.921}, {"luta": 0.884}, {"academia": 0.541}, {"maquiagem": 0.658}]},
        {"usuaruo": 3, "interesses": [{"tecnologia": 0.999}, {"hardware": 0.865}, {"games": 0.745}, {"culinária": 0.658}, {"servers": 0.54}]},
        {"usuaruo": 4, "interesses": [{"neurociências": 0.865}, {"comportamento": 0.844}, {"skinner": 0.854}, {"laboratório": 0.354}, {"pesquisa": 0.428}]}
    ]

    output = [
        [('futebol', 0.855), ('pagode', 0.765), ('engraçado', 0.732), ('cerveja', 0.622), ('estética', 0.519)],
        [('estética', 0.765), ('jiujitsu', 0.921), ('luta', 0.884), ('academia', 0.541), ('maquiagem', 0.658)],
        [('tecnologia', 0.999), ('hardware', 0.865), ('games', 0.745), ('culinária', 0.658), ('servers', 0.54)],
        [('neurociências', 0.865), ('comportamento', 0.844), ('skinner', 0.854), ('laboratório', 0.354), ('pesquisa', 0.428)]
    ]

    for i in range(len(interests)):
        assert sorted(output[i]) == sorted(questao_2([interests[i]])[0])


# Questão 3
def questao_3(posts):
    for post in posts:
        post_key = f"post:{post['id']}"
        redis_conn.hset(post_key, mapping=post)

    stored_posts = []
    for post in posts:
        post_key = f"post:{post['id']}"
        stored_posts.append(redis_conn.hgetall(post_key))

    return stored_posts

def test_questao_3():

    posts = [
        {"id": '345', "autor":"news_fc@g.com", "data_hora": "2024-06-10 19:51:03", "conteudo": "Se liga nessa lista de jogadores que vão mudar de time no próximo mês!", "palavras_chave": "brasileirao, futebol, cartola, esporte" },
        {"id": '348', "autor":"gastro_pub@g.com", "data_hora": "2024-06-10 19:55:13", "conteudo": "Aprenda uma receita rápida de onion rings super crocantes.", "palavras_chave": "onion rings, receita, gastronomia, cerveja, culinária" },
        {"id": '349', "autor":"make_with_tina@g.com", "data_hora": "2024-06-10 19:56:44", "conteudo": "A dica de hoje envolve os novos delineadores da linha Rare Beauty", "palavras_chave": "maquiagem, estética, beleza, delineador" },
        {"id": '350', "autor":"samarantes@g.com", "data_hora": "2024-06-10 19:56:48", "conteudo": "Eu quando acho a chuteira que perdi na última pelada...", "palavras_chave": "pelada, futebol, cerveja, parceiros" },
        {"id": '351', "autor":"portal9@g.com", "data_hora": "2024-06-10 19:57:02", "conteudo": "No último mês pesquisadores testaram três novos medicamentos para ajudar aumentar o foco.", "palavras_chave": "neurociências, tecnologia, foco, medicamento" },
        {"id": '352', "autor":"meme_e_cia@g.com", "data_hora": "2024-06-10 19:58:33", "conteudo": "Você prefere compartilhar a nossa página agora ou daqui cinco minutos?", "palavras_chave": "entretenimento, engraçado, viral, meme" },
        {"id": '353', "autor":"rnd_hub@g.com", "data_hora": "2024-06-10 19:59:59", "conteudo": "A polêmica pesquisa de V. Damasco sobre ciência do comportamente acaba de ser publicada.", "palavras_chave": "comportamento, ciência, pesquisa, damasco" }
    ]

    assert posts == sorted(questao_3(posts), key=lambda d: d['id'])


# Questão 4
def questao_4(user_id):
    interest_key = f"user:{user_id}:interests"
    user_interests = redis_conn.lrange(interest_key, 0, -1)

    user_interests = [interest for interest in user_interests]

    posts = [redis_conn.hgetall(key) for key in redis_conn.keys("post:*")]

    def calculate_interest(post):
        keywords = post['palavras_chave'].split(", ")
        score = 0
        for interest in user_interests:
            keyword, value = interest.split(":")
            if keyword in keywords:
                score += float(value)
        return (score, int(post['id'])) if score != 0 else (0, -int(post['id']))

    posts_sorted = sorted(posts, key=calculate_interest, reverse=True)

    return [post['conteudo'] for post in posts_sorted]

def test_questao_4():

    user_id = 3

    output = [
        "No último mês pesquisadores testaram três novos medicamentos para ajudar aumentar o foco.",
        "Aprenda uma receita rápida de onion rings super crocantes.",
        "Se liga nessa lista de jogadores que vão mudar de time no próximo mês!",
        "A dica de hoje envolve os novos delineadores da linha Rare Beauty",
        "Eu quando acho a chuteira que perdi na última pelada...",
        "Você prefere compartilhar a nossa página agora ou daqui cinco minutos?",
        "A polêmica pesquisa de V. Damasco sobre ciência do comportamente acaba de ser publicada."
    ]

    assert output == questao_4(user_id)

# Questão 5
def questao_5(user_views, user_id):
    # Armazenar a lista de posts visualizados para cada usuário
    for view in user_views:
        usuario = view["usuario"]
        for post_id in view["visualizado"]:
            redis_conn.sadd(f"user:{usuario}:visualizados", post_id)

    # Recuperar todos os posts
    all_posts = redis_conn.keys("post:*")
    all_post_ids = {int(post.split(":")[1]) for post in all_posts}

    # Recuperar posts visualizados pelo usuário
    viewed_posts = redis_conn.smembers(f"user:{user_id}:visualizados")
    viewed_post_ids = {int(post_id) for post_id in viewed_posts}

    # Determinar os posts não visualizados
    not_viewed_post_ids = all_post_ids - viewed_post_ids

    # Recuperar os conteúdos dos posts não visualizados
    not_viewed_posts = []
    for post_id in not_viewed_post_ids:
        post = redis_conn.hgetall(f"post:{post_id}")
        not_viewed_posts.append(post["conteudo"])

    return not_viewed_posts

def test_questao_5():

    user_id = 3
    user_views = [
        {"usuario":1, "visualizado": [345,350,353]},
        {"usuario":2, "visualizado": [350,351]},
        {"usuario":3, "visualizado": [345,351,352,353]},
        {"usuario":4, "visualizado": []}
    ]

    output = [
        "Aprenda uma receita rápida de onion rings super crocantes.",
        "A dica de hoje envolve os novos delineadores da linha Rare Beauty",
        "Eu quando acho a chuteira que perdi na última pelada..."
    ]

    assert output == questao_5(user_views, user_id)
