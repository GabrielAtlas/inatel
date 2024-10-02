# Exercicio 1 - Média de 3 números
puts "Digite o primeiro número:"
num1 = gets.chomp.to_f

puts "Digite o segundo número:"
num2 = gets.chomp.to_f

puts "Digite o terceiro número:"
num3 = gets.chomp.to_f

media = (num1 + num2 + num3) / 3

puts "A média dos três números é: #{media}"
