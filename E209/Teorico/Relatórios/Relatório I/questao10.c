#include <avr/io.h>

int main(void) {
    // Configura os pinos PD0, PD1 e PD2 como entradas
    DDRD &= ~(1 << PD0);
    DDRD &= ~(1 << PD1);
    DDRD &= ~(1 << PD2);

    while (1) {
        // Verifica se os pinos estão configurados como entradas
        if (!(DDRD & ((1 << PD0) | (1 << PD1) | (1 << PD2)))) {
            // Todos estão configurados como entradas
        }
    }
    return 0;
}
