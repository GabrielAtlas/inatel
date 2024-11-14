#include <avr/io.h>
#include <avr/interrupt.h>

#define BRANCO_LIMIAR 820   // ADC valor correspondente a >4V
#define PRETO_LIMIAR 716    // ADC valor correspondente a <3.5V

volatile uint8_t bits[8];   // Armazena até 8 bits da sequência
volatile uint8_t indice = 0;  // Índice para armazenar bits na sequência

void configurar_ADC() {
    ADMUX = (1 << REFS0);
    ADCSRA = (1 << ADEN) | (1 << ADPS2) | (1 << ADPS1); // Habilitar ADC com prescaler 64
}

uint16_t ler_ADC() {
    ADCSRA |= (1 << ADSC);                // Iniciar conversão ADC
    while (ADCSRA & (1 << ADSC));         // Esperar término da conversão
    return ADC;
}

void configurar_Timer1() {
    // Configurar Timer1 para gerar uma interrupção a cada 1 segundo (1 Hz)
    TCCR1A = 0;
    TCCR1B = (1 << WGM12) | (1 << CS12) | (1 << CS10);  // CTC com prescaler de 1024
    OCR1A = 15624;  // Valor de comparação para 1 Hz (com 16 MHz e prescaler de 1024)
    TIMSK1 = (1 << OCIE1A);  // Habilitar interrupção de comparação para Timer1
}

ISR(TIMER1_COMPA_vect) {
    uint16_t leitura_ADC = ler_ADC();  // Ler valor do sensor de luz

    if (leitura_ADC > BRANCO_LIMIAR) {       // Quadro Branco
        bits[indice++] = 1;
    } else if (leitura_ADC < PRETO_LIMIAR) { // Quadro Preto
        bits[indice++] = 0;
    } else {                                 // Leitura indeterminada
        indice = 0;                          // Reiniciar sequência
    }

    // Verificar se a sequência de 8 bits está completa
    if (indice >= 8) {
        for (uint8_t i = 0; i < 8; i++) {
            // Exibir ou processar a sequência (neste caso, imprimir bits no terminal)
            if (bits[i] == 1) {
                // Indicar quadro branco
                // Código de exibição ou sinalização
            } else {
                // Indicar quadro preto
                // Código de exibição ou sinalização
            }
        }
        indice = 0;  // Reiniciar para nova sequência
    }
}

int main() {
    configurar_ADC();     // Configurar ADC para leituras do sensor de luz
    configurar_Timer1();  // Configurar Timer para interrupção de 1 Hz
    sei();                // Habilitar interrupções globais
}
