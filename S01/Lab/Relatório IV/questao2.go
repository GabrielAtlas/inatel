package main

import (
	"fmt"
	"math/rand"
	"time"
)

func factorial(n int) int {
	if n == 0 || n == 1 {
		return 1
	}
	return n * factorial(n-1)
}

func main() {

	rand.Seed(time.Now().UnixNano())


	randomNumber := rand.Intn(11)


	fact := factorial(randomNumber)

	// Exibir o resultado
	fmt.Printf("Número gerado: %d\n", randomNumber)
	fmt.Printf("Fatorial de %d é: %d\n", randomNumber, fact)
}
