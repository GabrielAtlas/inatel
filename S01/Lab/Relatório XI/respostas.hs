-- Exercício 1
main :: IO ()
main = do
    -- Cria a lista de 30 até 1
    let lista = [30,29..1]
    -- Multiplica cada elemento por 3
    let listaMultiplicada = map (*3) lista
    -- Inverte a lista
    let listaInvertida = reverse listaMultiplicada
    -- Mostra o último elemento da lista invertida
    let ultimoElemento = last listaInvertida
    print ultimoElemento


-- Função para calcular o fatorial de um número
fatorial :: Integer -> Integer
fatorial 0 = 1
fatorial n = product [1..n]

-- Exercício 2
calcular :: Integer -> Integer
calcular n
    | n > 0     = fatorial n
    | otherwise = n * 2

mainEx2 :: IO ()
mainEx2 = do
    print (calcular 5)  -- Exemplo de entrada maior que 0
    print (calcular (-4)) -- Exemplo de entrada menor ou igual a 0
