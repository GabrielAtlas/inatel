#include <avr/io.h>

int main(void) {
    // Configura o pino de entrada (ex: PD2)
    DDRD &= ~(1 << PD2);

    // Configura os pinos de saída (ex: PD3 e PD4)
    DDRD |= (1 << PD3) | (1 << PD4);

    while (1) {
        // Alterna os LEDs PD3 e PD4 a cada clique do botão em PD2
        if (PIND & (1 << PD2)) {
            PORTD ^= (1 << PD3);
            PORTD ^= (1 << PD4);
        }
    }
    return 0;
}
