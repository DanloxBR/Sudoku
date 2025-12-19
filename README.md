🧩 Sudoku em Java

Projeto de Sudoku desenvolvido em Java, com interface gráfica usando Swing, seguindo boas práticas de organização em camadas (model, service e ui).
O jogo permite interação completa do usuário, validação das jogadas, feedback visual por cores e registro de ações em log.

📌 Funcionalidades

🎮 Jogo de Sudoku totalmente funcional

🧠 Geração automática de tabuleiro válido

🔢 Inserção de números pelo usuário

✅ Validação das jogadas em tempo real

🎨 Feedback visual:

🟢 Verde → número correto

🔴 Vermelho → número incorreto

🟡 Amarelo → número possível (cabível)

📜 Painel de log com histórico das jogadas

🏁 Detecção automática de conclusão do jogo

🔒 Células fixas (não editáveis)

🏗️ Estrutura do Projeto
src/
 └── com.bringto.sudoku
      ├── model        # Entidades (Board, Space, CellStatus)
      ├── service      # Regras de negócio e validações
      └── ui
           ├── custom
           │     ├── panel   # Painéis (tabuleiro, log)
           │     └── input   # Componentes customizados (NumberText)
           └── screen        # Tela principal do jogo

🧠 Conceitos Utilizados

Programação Orientada a Objetos (POO)

Java Swing (UI Desktop)

Listeners e eventos

Separação de responsabilidades

Validação de regras de negócio

Uso de boolean[][], enums e coleções

Padrão de camadas (Model / Service / UI)

🚀 Como Executar o Projeto
Pré-requisitos

Java 8 ou superior

IDE (IntelliJ, Eclipse ou VS Code)

Passos
# Clone o repositório
git clone https://github.com/DanloxBR/Sudoku.git

# Abra o projeto na sua IDE

# Execute a classe MainScreen (ou classe principal)

🖥️ Interface

A interface foi desenvolvida em Java Swing, com foco em simplicidade e clareza:

Tabuleiro central de Sudoku

Painel lateral de log

Cores para facilitar o aprendizado e correção do jogador

📈 Possíveis Melhorias Futuras

⏱️ Cronômetro de tempo

📊 Sistema de pontuação

💾 Salvar e carregar partidas

🎚️ Níveis de dificuldade

🧪 Testes unitários

🌙 Tema escuro (Dark Mode)

👨‍💻 Autor

Daniel Oliveira
Desenvolvedor Java | Estudante e entusiasta de boas práticas de software
