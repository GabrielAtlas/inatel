#include <avr/io.h>

int main(void) {
    // Configura o pino PD2 como entrada
    DDRD &= ~(1 << PD2);

    // Configura o pino PD3 como saída
    DDRD |= (1 << PD3);

    while (1) {
        // Verifica o estado do pino PD2
        if (PIND & (1 << PD2)) {
            // Apaga o LED no pino PD3
            PORTD &= ~(1 << PD3);
        }
    }
    return 0;
}
