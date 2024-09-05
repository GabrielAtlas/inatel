#include <avr/io.h>

int main(void) {
    // Configura 5 pinos de entrada no PortB
    DDRB &= ~((1 << PB0) | (1 << PB1) | (1 << PB2) | (1 << PB3) | (1 << PB4));

    // Configura LED1 como saída no PortD e LED2 como saída no PortC
    DDRD |= (1 << PD0); // LED1
    DDRC |= (1 << PC0); // LED2

    while (1) {
        // Lê as entradas e identifica o padrão
        uint8_t entradas = PINB & 0b00011111;

        if (entradas == 0b01010 || entradas == 0b10101) {
            PORTD |= (1 << PD0);  // Acende LED1
            PORTC &= ~(1 << PC0); // Apaga LED2
        } else if (entradas == 0b11111 || entradas == 0b00000) {
            PORTD &= ~(1 << PD0); // Apaga LED1
            PORTC |= (1 << PC0);  // Acende LED2
        } else {
            PORTD &= ~(1 << PD0); // Apaga LED1
            PORTC &= ~(1 << PC0); // Apaga LED2
        }
    }
    return 0;
}
