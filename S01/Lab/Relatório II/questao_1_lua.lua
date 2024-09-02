-- Função para calcular e exibir a tabuada de um número
function tabuada(numero)
    for i = 1, 10 do
        print(numero .. " x " .. i .. " = " .. (numero * i))
    end
end

-- Lê um número do usuário
io.write("Digite um número para calcular a tabuada: ")
local numero = tonumber(io.read())
print("\n")

-- Chama a função para calcular e exibir a tabuada
tabuada(numero)
