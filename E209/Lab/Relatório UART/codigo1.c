#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>
 
#define FOSC 16000000U
#define BAUD 9600
#define MYUBRR FOSC / 16 / BAUD - 1
 
#define LED_VERMELHO (1 << PD2) // LED vermelho no pino PD2
#define LED_VERDE (1 << PD3)    // LED verde no pino PD3
 
char msg_rx[2];
int pos_msg_rx = 0;
int tamanho_msg_rx = 1; // Espera por um caractere
 
void UART_Init(unsigned int ubrr);
void UART_Transmit(char *dados);
void controlLEDs(char comando);
 
int main(void)
{
    // Configura os pinos dos LEDs como saída
    DDRD |= LED_VERMELHO | LED_VERDE;
 
    UART_Init(MYUBRR);
    sei(); // Habilita interrupções globais
 
    UART_Transmit("Aguardando comandos:\n");
 
    // Loop principal
    while (1) {
        // Não é necessário um loop de espera, pois a ISR vai gerenciar os comandos
    }
}
 
ISR(USART_RX_vect)
{
    // Lê o valor recebido pela UART
    msg_rx[pos_msg_rx++] = UDR0;
    if (pos_msg_rx == tamanho_msg_rx) {
        controlLEDs(msg_rx[0]);
        pos_msg_rx = 0; // Reseta a posição após ler um comando
    }
}
 
void controlLEDs(char comando)
{
    switch (comando) {
        case '9':
            // Liga o LED vermelho e desliga o verde
            PORTD |= LED_VERMELHO;   // Liga o LED vermelho
            PORTD &= ~LED_VERDE;     // Desliga o LED verde
            UART_Transmit("LED vermelho ligado, LED verde desligado.\n");
            break;
        case '8':
            // Liga o LED verde e desliga o vermelho
            PORTD |= LED_VERDE;      // Liga o LED verde
            PORTD &= ~LED_VERMELHO;  // Desliga o LED vermelho
            UART_Transmit("LED verde ligado, LED vermelho desligado.\n");
            break;
        default:
            UART_Transmit("Comando desconhecido.\n");
            break;
    }
}
 
void UART_Transmit(char *dados)
{
    // Envia todos os caracteres do buffer dados até chegar um final de linha
    while (*dados != 0) {
        while (!(UCSR0A & (1 << UDRE0))); // Aguarda a transmissão acabar
        UDR0 = *dados; // Escreve o caractere no registro de transmissão
        dados++; // Passa para o próximo caractere do buffer dados
    }
}
 
void UART_Init(unsigned int ubrr)
{
    // Configura a baud rate
    UBRR0H = (unsigned char)(ubrr >> 8);
    UBRR0L = (unsigned char)ubrr;
    // Habilita a recepção, transmissão e interrupção na recepção
    UCSR0B = (1 << RXEN0) | (1 << TXEN0) | (1 << RXCIE0);
    // Configura o formato da mensagem: 8 bits de dados e 1 bit de stop
    UCSR0C = (1 << UCSZ01) | (1 << UCSZ00);
}
 