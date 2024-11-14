#include <avr/io.h>
#include <util/delay.h>

#define LED_BAIXO PD2
#define LED_MEDIO PD3
#define LED_ALTO PD4

void configurar_adc() {
    ADMUX = (1 << REFS0);  // AVcc como referência (5V), leitura no ADC0
    ADCSRA = (1 << ADEN)   // Habilita o ADC
           | (1 << ADPS2)  // Prescaler de 16 para uma frequência de 125kHz
           | (1 << ADPS1);
}

uint16_t ler_adc() {
    ADCSRA |= (1 << ADSC);
    while (ADCSRA & (1 << ADSC));
    return ADC;
}

void iniciar_configuracoes() {
    configurar_adc();
    DDRD |= (1 << LED_BAIXO) | (1 << LED_MEDIO) | (1 << LED_ALTO);
}

void verificar_umidade() {
    uint16_t leitura_adc = ler_adc();
    uint8_t percentual_umidade = (leitura_adc * 100) / 1023;

    if (percentual_umidade > 80) {
        PORTD |= (1 << LED_ALTO);       // Liga o LED Verde (ALTO)
        PORTD &= ~((1 << LED_MEDIO) | (1 << LED_BAIXO)); // Desliga os outros LEDs
    } else if (percentual_umidade > 50) {
        PORTD |= (1 << LED_MEDIO);      // Liga o LED Amarelo (MEDIO)
        PORTD &= ~((1 << LED_ALTO) | (1 << LED_BAIXO));  // Desliga os outros LEDs
    } else {
        PORTD |= (1 << LED_BAIXO);      // Liga o LED Vermelho (BAIXO)
        PORTD &= ~((1 << LED_ALTO) | (1 << LED_MEDIO));  // Desliga os outros LEDs
    }

    _delay_ms(500);  // Pequeno atraso para estabilidade
}

int main() {
    iniciar_configuracoes();
    while (1) {
        verificar_umidade();
    }
}
