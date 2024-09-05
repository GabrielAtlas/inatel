#include <avr/io.h>

int main(void) {
    // Configura os pinos PD6 e PD7 como saídas
    DDRD |= (1 << PD6) | (1 << PD7);

    // Apaga o bit correspondente ao pino PD6
    DDRD &= ~(1 << PD6);

    while (1) {
        // Loop infinito
    }
    return 0;
}
