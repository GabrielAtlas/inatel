#include <Arduino.h>
 
#define u8 unsigned char
#define u16 unsigned int
 
#define pwm_out (1 << PD6)
 
void ADC_init(void) {  // Inicialização do ADC
  // Configurando Vref para VCC = 5V
  ADMUX = (1 << REFS0);  // Referência de tensão VCC
  // ADC ativado e preescaler de 128
  ADCSRA = (1 << ADEN) | (1 << ADPS2) | (1 << ADPS1) | (1 << ADPS0);  // Habilita o ADC
}
 
int ADC_read(u8 ch) {  // Faz a leitura
  char i;
  int ADC_temp = 0;             // ADC temporário para manipular leitura
  int ADC_read = 0;             // ADC_read
  ch &= 0x07;                   // Zera os 3 primeiros bits e mantém o resto
  ADMUX = (ADMUX & 0xF8) | ch;  // Seleciona o canal
 
  ADCSRA |= (1 << ADSC);  // Inicia a conversão
  while (!(ADCSRA & (1 << ADIF)))
    ;  // Aguarda a conversão do sinal
 
  for (i = 0; i < 8; i++) {  // Fazendo a conversão 8 vezes para maior precisão
    ADCSRA |= (1 << ADSC);   // Nova conversão
    while (!(ADCSRA & (1 << ADIF)))
      ;                       // Aguarda a conversão do sinal
    ADC_temp = ADCL;          // Lê o registro ADCL
    ADC_temp += (ADCH << 8);  // Lê o registro ADCH
    ADC_read += ADC_temp;     // Acumula o resultado (8 amostras) para média
  }
  ADC_read /= 8;    // Média das 8 amostras
  return ADC_read;  // Retorna a leitura média
}
 
int main() {
  Serial.begin(9600);
  ADC_init();
 
  DDRD |= pwm_out;
  PORTD &= ~pwm_out;
 
  TCCR0A |= (1 << WGM01) | (1 << WGM00) | (1 << COM0A1);
  TCCR0B = (1 << CS00);
 
  u16 leitura;
 
  for (;;) {
    leitura = ADC_read(0);
 
    int pwmValue = (leitura * 255.) / 1023.0;
    OCR0A = pwmValue;
 
    Serial.print("Leitura: ");
    Serial.print(leitura);
    Serial.print(" -> PWM: ");
    Serial.println(pwmValue);
 
    delay(50);
  }
}
 