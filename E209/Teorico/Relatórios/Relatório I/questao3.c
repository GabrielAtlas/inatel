#include <avr/io.h>

int main(void) {
    // Configura os pinos PD3 e PB4 como saídas
    DDRD |= (1 << PD3);
    DDRB |= (1 << PB4);

    // Alterna o estado dos pinos PD3 e PB4
    PORTD ^= (1 << PD3);
    PORTB ^= (1 << PB4);

    while (1) {
        // Loop infinito
    }
    return 0;
}
