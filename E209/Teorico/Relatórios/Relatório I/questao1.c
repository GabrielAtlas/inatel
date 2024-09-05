#include <avr/io.h>

int main(void) {
    // Configura o pino PD2 como saída
    DDRD |= (1 << PD2);

    // Acende o bit correspondente ao pino PD2
    PORTD |= (1 << PD2);

    while (1) {
        // Loop infinito
    }
    return 0;
}
