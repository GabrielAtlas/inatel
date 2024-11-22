#include <avr/io.h>
#include <avr/interrupt.h>
#include <stdlib.h>
#include <string.h>
#include <util/delay.h>
 
#define FOSC 16000000U  // Clock Speed
#define BAUD 9600
#define MYUBRR FOSC / 16 / BAUD - 1
#define botao (1 << PD4)
 
char msg_tx[20];
char msg_rx[32];
int pos_msg_rx = 0;
int tamanho_msg_rx = 3;
unsigned int x = 0, valor = 0;
char old_rx_hs[32];
// Prototipos das funcoes
void UART_Init(unsigned int ubrr);
void UART_Transmit(char *dados);
 
int main(void) {
  UART_Init(MYUBRR);
  sei();           // Habilita interrupções globais
  PORTD |= botao;  // Ativa o pull-up interno do botão
 
 
  UART_Transmit("Aperte o botao:\n");
 
  // Super-loop principal
  while (1) {
    if ((PIND & botao) == 0) {  // O botao foi pressionado?
      x++;
      UART_Transmit("num vezes botao press: ");
      itoa(x, msg_tx, 10);
      UART_Transmit(msg_tx);
      UART_Transmit("\n");
 
      _delay_ms(500);  // Aguarda um tempo para evitar o bounce
 
      // Verifica o comando "zer" para resetar o contador
        if ((msg_rx[0] == 'z') ) {
         // && (msg_rx[1] == 'e') && (msg_rx[2] == 'r') && (msg_rx[3] == 'a') && (msg_rx[4] == 'r')
          x = 0;  // Reseta o contador
          UART_Transmit("Contador zerado!\n");
           pos_msg_rx = 0;
          limpa_RX_buffer();  // Limpa o buffer após o comando
         
        }
    }
  }
}
 
ISR(USART_RX_vect)
{
 // Escreve o valor recebido pela UART na posição pos_msg_rx do buffer msg_rx
 msg_rx[pos_msg_rx++] = UDR0;
 if (pos_msg_rx == tamanho_msg_rx)
 pos_msg_rx = 0;
}
 
// Função para enviar dados via UART
void UART_Transmit(char *dados) {
  while (*dados != 0) {
    while (!(UCSR0A & (1 << UDRE0)))
      ;             // Aguarda a transmissão acabar
    UDR0 = *dados;  // Escreve o caractere no registro de transmissão
    dados++;
  }
}
 
// Configuração da UART
void UART_Init(unsigned int ubrr) {
  UBRR0H = (unsigned char)(ubrr >> 8);
  UBRR0L = (unsigned char)ubrr;
  UCSR0B = (1 << RXEN0) | (1 << TXEN0) | (1 << RXCIE0);  // Habilita RX, TX e interrupção RX
  UCSR0C = (1 << UCSZ01) | (1 << UCSZ00);                // Configura formato: 8 bits de dados, 1 bit de stop
}
void limpa_RX_buffer(void) {
  unsigned char dummy;
 
  // Enquanto houver dados no buffer
  while (UCSR0A & (1 << RXC0)) {
    // Lê o dado
    dummy = UDR0;
  }
 
  // Reseta o índice
  pos_msg_rx = 0;
 
  // Limpa todos os dados do buffer
  for (int i = 0; i < 32; i++) {
    old_rx_hs[i] = msg_rx[i];
    msg_rx[i] = 0;
  }
}