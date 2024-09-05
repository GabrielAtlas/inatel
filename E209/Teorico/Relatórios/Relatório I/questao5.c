#include <avr/io.h>

int main(void) {
    // Configura o pino PD5 como saída
    DDRD |= (1 << PD5);

    // Inverte o estado do pino PD5
    PORTD ^= (1 << PD5);

    while (1) {
        // Loop infinito
    }
    return 0;
}
