#include <avr/io.h>
#include <stdio.h>

bool interrupted = false;

int main()
{
    DDRD |= (1 << PB1); // Configurando o botão para as portas que aceitam interrupções
    
    DDRB |= (1 << PB1) + (1 << PB2); // Porta da Esteira + Porta do LED
    
    EICRA |= (1 << ISC01); // Configurando Evento para acionar o ISR
    EICRA &= ~(1 << ISC00); // Reforçando que o ISC00 esteja desligado
    
    EIMSK |= (1 << INT0);
    
    sei(); // Função Obrigatória
    
    while(true) {
        if(interrupted) {
            // Liga o led
            PORTD |= (1 << PB2);
            printf("Aguardando reiniciar o sistema.");
            _delay_ms(2000);
            // Desliga o led
            PORTD &= ~(1 << PB2);
            return;
        }
        // Liga a esteira
        PORTD |= (1 << PB1);
        printf("Esteira rodando...");
        _delay_ms(5000);
        // Desliga a esteira
        PORTD &= ~(1 << PB1);
    }

    return 0;
}

ISR(INT0_vect) {
    interrupted = true;
    PORTD &= ~(1 << PB1);
}