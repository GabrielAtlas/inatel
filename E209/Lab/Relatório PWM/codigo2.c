// EX 1
 
#define pwm_out (1 << PD6) // Define o pino para PWM
#define BUTTON_PIN (1 << PD2) // Define o pino do botão
 
int main() {
    DDRD |= pwm_out;
    PORTD &= ~pwm_out;
 
    TCCR0A |= (1 << WGM01) | (1 << WGM00) | (1 << COM0A1);
    TCCR0B = (1 << CS00);
    OCR0A = 0;
 
    DDRD &= ~BUTTON_PIN;
    PORTD |= BUTTON_PIN;
 
    while (1) {
        if (!(PIND & BUTTON_PIN)) {
            OCR0A = 127;
        } else {
            OCR0A = 0;
        }
    }
}