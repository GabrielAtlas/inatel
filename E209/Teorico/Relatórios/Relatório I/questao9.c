#include <avr/io.h>

int main(void) {
    // Configura os pinos PD4, PD5 e PD6 como saídas
    DDRD |= (1 << PD4) | (1 << PD5) | (1 << PD6);

    while (1) {
        // Loop infinito
    }
    return 0;
}
