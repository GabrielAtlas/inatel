#define OP_NORMAL 0
#define EMERGENCY_STOP 1

#define BELT (1<<PD6)
#define LED (1<<PB0)
#define BELT_INTERVAL 5000

//Global state variable
unsigned char state = OP_NORMAL;

int main ()
{
  DDRB |= LED;
  DDRD |= BELT;
  PORTB &= ~ LED
  PORTD &= ~ BELT;
	
  // EICRA |= (1<<ISC00) + (1<<ISC01);
  // EIMSK |= (1<<INT0);
  
  // Para interrupções do tipo PCINT
  PCICR |= (1 << PCIE2);
  PCMSK2 |= (1 << PD0);
  
  // 0 -> B = VERMELHO
  // 1 -> C = VERDE
  // 2 -> D = AZUL
  
  sei();
    
  for(;;)
  {
    switch(state)
    {
      case OP_NORMAL:
      	PORTD ^= BELT;
        _delay_ms(BELT_INTERVAL);
        break;
      
      case EMERGENCY_STOP:
        PORTB |= LED;
        PORTD |= BELT;
        break;
    }
  }

  return 0;
}

ISR(PCINT2_vect)
{
  PORTB |= LED;
  PORTD |= BELT;
  state = EMERGENCY_STOP;
}
