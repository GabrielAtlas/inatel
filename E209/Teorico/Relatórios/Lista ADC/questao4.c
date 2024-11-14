#include <avr/io.h>
#include <avr/interrupt.h>

#define BUZZER_PIN PB1  // Definir o pino do buzzer

// Frequências das notas em Hz
#define DO_FREQ 264
#define RE_FREQ 300
#define MI_FREQ 330
#define FA_FREQ 352
#define SOL_FREQ 396
#define LA_FREQ 440
#define SI_FREQ 495

void configurar_ADC() {
    ADMUX = (1 << REFS0);               // Referência AVcc
    ADCSRA = (1 << ADEN) | (1 << ADPS2); // Habilitar ADC e prescaler de 16
}

uint16_t ler_ADC() {
    ADCSRA |= (1 << ADSC);               // Iniciar conversão
    while (ADCSRA & (1 << ADSC));        // Esperar fim da conversão
    return ADC;
}

void configurar_Timer1(uint16_t frequencia) {
    // Calcular OCR1A para a frequência desejada
    uint16_t ocr_value = (16000000 / (2 * 1024 * frequencia)) - 1;

    TCCR1A = (1 << COM1A0);            // Alternar OC1A no Compare Match
    TCCR1B = (1 << WGM12) | (1 << CS12) | (1 << CS10);  // CTC e prescaler de 1024
    OCR1A = ocr_value;
}

void definir_frequencia(uint16_t valor_ADC) {
    if (valor_ADC < 102) {               // Menor que 10%
        configurar_Timer1(DO_FREQ);       // Dó
    } else if (valor_ADC < 204) {         // 10% - 20%
        configurar_Timer1(RE_FREQ);       // Ré
    } else if (valor_ADC < 307) {         // 20% - 30%
        configurar_Timer1(MI_FREQ);       // Mi
    } else if (valor_ADC < 409) {         // 30% - 40%
        configurar_Timer1(FA_FREQ);       // Fá
    } else if (valor_ADC < 512) {         // 40% - 50%
        configurar_Timer1(SOL_FREQ);      // Sol
    } else if (valor_ADC < 614) {         // 50% - 60%
        configurar_Timer1(LA_FREQ);       // Lá
    } else {                              // 60% ou mais
        configurar_Timer1(SI_FREQ);       // Si
    }
}

int main() {
    DDRB |= (1 << BUZZER_PIN);            // Configurar pino do buzzer como saída
    configurar_ADC();                     // Configurar ADC

    while (1) {
        uint16_t leitura_ADC = ler_ADC(); // Ler valor do ADC
        definir_frequencia(leitura_ADC);  // Ajustar frequência do buzzer
    }
}
