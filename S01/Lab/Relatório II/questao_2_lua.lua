-- Define o tamanho da tabela
local tamanho = 100

-- Cria uma tabela e preenche com valores aleatórios
local tabela = {}
for i = 1, tamanho do
    tabela[i] = math.random(1, 100) -- Valores aleatórios entre 1 e 100
end

-- Conta o número de valores pares
local contagem_pares = 0
for i = 1, tamanho do
    if tabela[i] % 2 == 0 then
        contagem_pares = contagem_pares + 1
    end
end

-- Exibe a contagem de números pares
print("Número de valores pares na tabela: " .. contagem_pares)
