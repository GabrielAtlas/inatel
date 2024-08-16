DIM A AS SINGLE
DIM B AS SINGLE
DIM C AS SINGLE
DIM D AS SINGLE
DIM X1 AS SINGLE
DIM X2 AS SINGLE

PRINT "Resolucao de equacoes do segundo grau"
PRINT "Digite os coeficientes a, b, c"
INPUT A, B, C

D = B*B - 4*A*C

IF D > 0 THEN
    X1 = (-B + SQR(D)) / (2*A)
    X2 = (-B - SQR(D)) / (2*A)
    PRINT "As raizes sao: "; X1; " e "; X2
ELSEIF D = 0 THEN
    X1 = -B / (2*A)
    PRINT "A raiz dupla eh: "; X1
ELSE
    PRINT "A equacao nao tem solucoes reais"
END IF

END
