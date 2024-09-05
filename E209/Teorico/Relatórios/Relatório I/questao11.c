#include <avr/io.h>

int main(void) {
    // Configura o pino de entrada (ex: PD2)
    DDRD &= ~(1 << PD2);

    // Configura o pino de saída (ex: PD3)
    DDRD |= (1 << PD3);

    while (1) {
        // Alterna o estado do pino PD3 quando o botão em PD2 for pressionado
        if (PIND & (1 << PD2)) {
            PORTD ^= (1 << PD3);
        }
    }
    return 0;
}
