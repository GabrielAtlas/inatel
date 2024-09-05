#include <avr/io.h>

int main(void) {
    // Configura o pino PD5 como entrada
    DDRD &= ~(1 << PD5);

    // Ativa o resistor de pull-up interno
    PORTD |= (1 << PD5);

    while (1) {
        // Verifica o estado do pino PD5
        if (PIND & (1 << PD5)) {
            // O pino está em nível alto
        }
    }
    return 0;
}
