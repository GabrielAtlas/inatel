DIM N AS INTEGER
DIM F AS INTEGER
DIM I AS INTEGER

PRINT "Gerar numero aleatorio e calcular o fatorial"
RANDOMIZE TIMER
N = INT(RND * 10) + 1
PRINT "Numero gerado: "; N

F = 1
FOR I = 1 TO N
    F = F * I
NEXT I

PRINT "O fatorial de "; N; " eh "; F
END
