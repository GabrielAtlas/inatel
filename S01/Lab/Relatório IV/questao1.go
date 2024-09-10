package main

import (
	"fmt"
	"math"
)

func main() {
	var a, b, c float64

	// Entrada de dados
	fmt.Println("Digite os coeficientes a, b e c da equação (ax^2 + bx + c = 0):")
	fmt.Print("a: ")
	fmt.Scan(&a)
	fmt.Print("b: ")
	fmt.Scan(&b)
	fmt.Print("c: ")
	fmt.Scan(&c)

	delta := b*b - 4*a*c

	if delta < 0 {
		fmt.Println("A equação não possui raízes reais.")
	} else {
		// Calculando as raízes
		x1 := (-b + math.Sqrt(delta)) / (2 * a)
		x2 := (-b - math.Sqrt(delta)) / (2 * a)

		fmt.Printf("As raízes da equação são: x1 = %.2f, x2 = %.2f\n", x1, x2)
	}
}
