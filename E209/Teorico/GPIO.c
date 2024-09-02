#include <stdint.h>
#include <avr/io.h>
#include <util/delay.h>

#define set_bit(Y, bit_x) (Y |= (1 << bit_x))
#define clr_bit(Y, bit_x) (Y &= ~(1 << bit_x))
#define cpl_bit(Y, bit_x) (Y ^= (1 << bit_x))
#define tst_bit(Y, bit_x) (Y & (1 << bit_x))

int main() {
    int DDRD = 0, PD2 = 0, PD3 = 0, PD4 = 0, PD5 = 0;

    set_bit(DDRD, PD2); // SENSOR MOTOR ABERTO (MA)
    set_bit(DDRD, PD3); // SENSOR MOTOR FECHADO (MF)

    clr_bit(DDRD, PD4);
    clr_bit(DDRD, PD5);

    set_bit(PORTD, PD4);
    set_bit(PORTD, PD5);

    while (1) {
        uint8_t sensor_abertura = tst_bit(PIND, PD4);
        uint8_t sensor_fechamento = tst_bit(PIND, PD5);

        if (sensor_abertura == 0) {
            set_bit(PORTD, PD2); // Ativa o bit em 1
        } else if (sensor_fechamento == 0) {
            set_bit(PORTD, PD3); // Aciona motor para fechar o portão
        } else {
            clr_bit(PORTD, PD2);
            clr_bit(PORTD, PD3);
        }

        _delay_ms(100); // Pequena pausa para evitar leituras muito rápidas
    }
}
