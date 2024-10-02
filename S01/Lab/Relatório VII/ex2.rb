# Classe base Carro
class Carro
  def descrever
    "Este é um carro comum."
  end
end

# Classe CarroEsportivo que herda de Carro
class CarroEsportivo < Carro
  def descrever
    "Este é um carro esportivo rápido."
  end
end

# Classe CarroSedan que herda de Carro
class CarroSedan < Carro
  def descrever
    "Este é um carro sedan confortável."
  end
end

# Criando instâncias das classes
carro_comum = Carro.new
carro_esportivo = CarroEsportivo.new
carro_sedan = CarroSedan.new

# Chamando o método descrever em cada objeto
puts carro_comum.descrever
puts carro_esportivo.descrever
puts carro_sedan.descrever
