#include <avr/io.h>
#include <avr/interrupt.h>

int sensores[2];         // Vetor para armazenar leituras dos sensores (luz e gás)
uint8_t canal_atual = 0; // Variável para alternar entre os canais

void configurar_adc() {
    // Configura o ADC com referência em AVcc e canal ADC0 inicialmente
    ADMUX = (1 << REFS0);  // AVcc com capacitor externo no AREF
    ADCSRA = (1 << ADEN)   // Habilita o ADC
           | (1 << ADPS2)  // Prescaler de 16 para uma frequência de 125kHz
           | (1 << ADPS1)
           | (1 << ADIE);  // Habilita interrupção do ADC
}

void iniciar_conversao_adc() {
    // Inicia uma conversão ADC
    ADCSRA |= (1 << ADSC);  // Define o bit ADSC para iniciar a conversão
}

void configurar_timer() {
    // Configura o Timer1 para gerar interrupção a cada 333ms (3Hz)
    TCCR1A = 0;             // Timer1 no modo Normal
    TCCR1B = (1 << WGM12)   // CTC (Clear Timer on Compare Match)
           | (1 << CS12)    // Prescaler de 256
           | (1 << CS10);
    OCR1A = 62499;          // Valor de comparação para 3Hz (16MHz / (256 * 3Hz) - 1)
    TIMSK1 = (1 << OCIE1A); // Habilita interrupção de comparação do Timer1
}

ISR(TIMER1_COMPA_vect) {
    // Alterna o canal do ADC a cada interrupção do Timer
    canal_atual = (canal_atual == 0) ? 1 : 0;
    
    // Seleciona o canal ADC (0 para luz, 1 para gás)
    ADMUX = (ADMUX & 0xF0) | canal_atual;
    iniciar_conversao_adc(); // Inicia uma nova conversão ADC
}

ISR(ADC_vect) {
    // Armazena o resultado da conversão no vetor de sensores
    sensores[canal_atual] = ADC;
}

int main() {
    // Configurações iniciais
    configurar_adc();
    configurar_timer();
    sei(); // Habilita interrupções globais

    while (1) {
        // Loop principal (não precisa fazer nada, pois tudo é tratado pelas interrupções)
    }
}
