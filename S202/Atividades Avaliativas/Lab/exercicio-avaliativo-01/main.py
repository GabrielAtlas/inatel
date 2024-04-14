from database import Database
from motoristaModel import MotoristaDAO
from cli import MotoristaCLI

db = Database(database="db_ex_av_lab_1", collection="motoristas")
motoristaDAO = MotoristaDAO(db)


personCLI = MotoristaCLI(motoristaDAO)
personCLI.run()
