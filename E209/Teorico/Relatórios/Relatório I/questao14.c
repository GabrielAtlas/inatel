#include <avr/io.h>

int main(void) {
    // Configura os botões com pull-up (PB1, PB2, PB3, PB4)
    DDRB &= ~((1 << PB1) | (1 << PB2) | (1 << PB3) | (1 << PB4));
    PORTB |= (1 << PB1) | (1 << PB2) | (1 << PB3) | (1 << PB4);

    // Configura o pino PD3 como saída para indicar a trava liberada
    DDRD |= (1 << PD3);

    while (1) {
        // Verifica se a sequência correta de
