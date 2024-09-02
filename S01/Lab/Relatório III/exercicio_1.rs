use std::io;

fn main() {
    loop {
        // Solicita o primeiro número ao usuário
        println!("Digite o primeiro número:");
        let mut num1 = String::new();
        io::stdin().read_line(&mut num1).expect("Falha ao ler a entrada.");
        let num1: f64 = match num1.trim().parse() {
            Ok(n) => n,
            Err(_) => {
                println!("Por favor, insira um número válido.");
                continue;
            }
        };

        // Solicita o segundo número ao usuário
        println!("Digite o segundo número:");
        let mut num2 = String::new();
        io::stdin().read_line(&mut num2).expect("Falha ao ler a entrada.");
        let num2: f64 = match num2.trim().parse() {
            Ok(n) => n,
            Err(_) => {
                println!("Por favor, insira um número válido.");
                continue;
            }
        };

        // Solicita ao usuário que escolha a operação
        println!("Escolha uma operação: (1) Multiplicar ou (2) Somar:");
        let mut operacao = String::new();
        io::stdin().read_line(&mut operacao).expect("Falha ao ler a entrada.");
        let operacao: u32 = match operacao.trim().parse() {
            Ok(n) => n,
            Err(_) => {
                println!("Por favor, insira uma opção válida.");
                continue;
            }
        };

        // Executa a operação escolhida
        match operacao {
            1 => println!("Resultado: {}", num1 * num2),
            2 => println!("Resultado: {}", num1 + num2),
            _ => println!("Opção inválida. Por favor, escolha 1 para multiplicar ou 2 para somar."),
        }

        // Pergunta se o usuário deseja continuar
        println!("Deseja realizar outra operação? (s/n):");
        let mut continuar = String::new();
        io::stdin().read_line(&mut continuar).expect("Falha ao ler a entrada.");
        if continuar.trim().eq_ignore_ascii_case("n") {
            break;
        }
    }
}
