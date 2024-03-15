import threading
import time
import random
from pymongo import MongoClient
from pymongo.errors import ServerSelectionTimeoutError
import matplotlib.pyplot as plt
from datetime import datetime, timedelta

try:
    client = MongoClient('localhost', 27023, serverSelectionTimeoutMS=100)
    client.server_info()
except ServerSelectionTimeoutError:
    print("O servidor do MongoDB não está em execução.")
    exit()

db = client.sensores
collection = db.sensores

if not db.list_collection_names() or "sensores" not in db.list_collection_names():
    db.create_collection("sensores")


def inserir_dados(nome_sensor, valor, alarmado):
    try:
        collection.update_one(
            {"nomeSensor": nome_sensor},
            {
                "$set": {"sensorAlarmado": alarmado},
                "$push": {"valoresSensor": valor}
            },
            upsert=True
        )
        print(f"Dados do sensor {nome_sensor} inseridos no banco de dados.")
    except Exception as e:
        print(f"Erro ao inserir dados do sensor {nome_sensor} no banco de dados:", e)


def verificar_alarme(nome_sensor, valor):
    if valor > 38:
        print(f"Atenção! Temperatura muito alta! Verificar Sensor {nome_sensor}!")
        return True
    return False


def ler_temperatura(nome_sensor):
    while True:
        temperatura = random.uniform(30, 40)
        inserir_dados(nome_sensor, temperatura, verificar_alarme(nome_sensor, temperatura))
        print(f"{nome_sensor}: {temperatura} C°")
        if verificar_alarme(nome_sensor, temperatura):
            break
        time.sleep(5)  # Tempo entre as leituras


def obter_dados_ultima_hora(nome_sensor):
    end_time = datetime.now()
    start_time = end_time - timedelta(hours=1)
    result = collection.find({"nomeSensor": nome_sensor}, {"valoresSensor": 1})
    valores = []
    for entry in result:
        if "valoresSensor" in entry:
            valores = entry["valoresSensor"]
    return range(len(valores)), valores

def plotar_grafico(nome_sensor, tempos, valores):
    plt.plot(tempos, valores, label=nome_sensor)
    plt.xlabel('Tempo')
    plt.ylabel('Temperatura (°C)')
    plt.title('Série Temporal de Temperatura')
    plt.legend()
    plt.show()

# Criar threads para simular sensores
sensores = ["Temp1", "Temp2", "Temp3"]

threads = []
for sensor in sensores:
    t = threading.Thread(target=ler_temperatura, args=(sensor,))
    threads.append(t)
    t.start()

for thread in threads:
    thread.join()

for sensor in sensores:
    tempos, valores = obter_dados_ultima_hora(sensor)
    if valores:
        plotar_grafico(sensor, tempos, valores)
    else:
        print(f"Não há dados disponíveis para o sensor {sensor} na última hora.")