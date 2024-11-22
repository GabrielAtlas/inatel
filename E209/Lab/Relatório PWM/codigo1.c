// EX 2
 
#define pwm_out (1 << PD6)
#define BUTTON_PIN (1 << PD2)
 
int brilho = 0;
 
void setup() {
    DDRD |= pwm_out;
    PORTD &= ~pwm_out;
 
    TCCR0A |= (1 << WGM01) | (1 << WGM00) | (1 << COM0A1);
    TCCR0B = (1 << CS00);
 
    DDRD &= ~BUTTON_PIN;
    PORTD |= BUTTON_PIN;
 
    EIMSK |= (1 << INT0);
    EICRA |= (1 << ISC01);
 
    while(1) {
      OCR0A = brilho;
      _delay_ms(100);
    }
}
 
ISR(INT0_vect) {
    if (brilho < 255) {
        brilho += 26;
    } else {
        brilho = 0;
    }
}