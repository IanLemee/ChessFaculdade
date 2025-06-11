# ChessMath - O Xadrez das Estruturas Matemáticas

## 📋 Descrição
ChessMath é um jogo educacional inovador que combina o clássico jogo de xadrez com conceitos fundamentais de Estruturas Matemáticas. Desenvolvido como projeto para a disciplina de Estruturas Matemáticas, o jogo torna o aprendizado de conceitos abstratos mais visual e interativo.

## 🎯 Objetivos Educacionais
- **Visualizar Vetores**: Cada movimento é representado como um vetor 2D
- **Compreender Funções**: Cada peça possui uma função matemática específica
- **Trabalhar com Matrizes**: O tabuleiro é uma matriz 8x8
- **Aplicar Geometria Analítica**: Coordenadas cartesianas e transformações

## 🎮 Como Jogar
1. **Executar o Jogo**: Execute a classe `ChessMathGame.java`
2. **Selecionar Peça**: Clique em uma peça para ver seus movimentos válidos
3. **Analisar Matemática**: O painel lateral mostra a análise matemática
4. **Mover Peça**: Clique em uma posição válida (círculo verde) para mover
5. **Observar Vetores**: As setas azuis mostram os vetores de movimento

## 📚 Conceitos Matemáticos Integrados

### Vetores e Produto Cartesiano
- Posições representadas como pares ordenados (x,y)
- Movimentos calculados como vetores com magnitude e direção
- Operações vetoriais: soma, subtração, produto escalar

### Funções Matemáticas por Peça
- **Torre**: f(x,y) = (x+n,y) ou (x,y+n), n ∈ ℤ
- **Bispo**: f(x,y) = (x±n, y±n), n ∈ ℤ, |Δx| = |Δy|
- **Cavalo**: f(x,y) = {(x±2,y±1), (x±1,y±2)}, |v| = √5
- **Rainha**: f(x,y) = Torre(x,y) ∪ Bispo(x,y)
- **Rei**: f(x,y) = (x+δx, y+δy), |δx|≤1, |δy|≤1
- **Peão**: Função definida por partes com condições

### Matrizes e Sistemas
- Tabuleiro como matriz 8x8
- Estado do jogo representado matricialmente
- Transformações lineares aplicáveis

## 🛠️ Tecnologias Utilizadas
- **Java 8+**: Linguagem principal
- **Swing**: Interface gráfica
- **IntelliJ IDEA**: IDE recomendada
- **Git**: Controle de versão

## 📁 Estrutura do Projeto
```
ChessMath/
├── src/main/java/chessmath/
│   ├── ChessMathGame.java          # Classe principal
│   ├── model/                      # Modelo de dados
│   │   ├── Board.java             # Tabuleiro (matriz 8x8)
│   │   ├── Piece.java             # Classe abstrata das peças
│   │   ├── Position.java          # Sistema de coordenadas
│   │   ├── Vector2D.java          # Operações vetoriais
│   │   └── pieces/                # Peças específicas
│   │       ├── King.java          # Rei
│   │       ├── Queen.java         # Rainha
│   │       ├── Rook.java          # Torre
│   │       ├── Bishop.java        # Bispo
│   │       ├── Knight.java        # Cavalo
│   │       └── Pawn.java          # Peão
│   ├── view/                      # Interface gráfica
│   │   ├── GamePanel.java         # Painel principal
│   │   └── MathPanel.java         # Painel matemático
│   └── controller/                # Lógica de controle
│       └── GameController.java    # Controlador do jogo
├── .idea/                         # Configurações IntelliJ
├── out/                          # Arquivos compilados
├── ChessMath.iml                 # Arquivo do módulo
└── README.md                     # Este arquivo
```

## 🚀 Como Executar

### Pré-requisitos
- Java 8 ou superior
- IntelliJ IDEA (recomendado)

### Passos para Execução
1. **Clonar/Baixar o Projeto**
   ```bash
   git clone [https://github.com/IanLemee/ChessFaculdade.git]
   ```

2. **Abrir no IntelliJ IDEA**
   - File → Open → Selecionar pasta ChessMath
   - Aguardar indexação do projeto

3. **Configurar JDK**
   - File → Project Structure → Project
   - Definir Project SDK como Java 8+

4. **Executar o Jogo**
   - Localizar `ChessMathGame.java`
   - Clicar com botão direito → Run 'ChessMathGame.main()'
   - Ou usar Shift+F10

### Execução via Terminal
```bash
cd ChessMath/src/main/java
javac chessmath/*.java chessmath/*/*.java chessmath/*/*/*.java
java chessmath.ChessMathGame
```

## 🎨 Interface do Jogo

### Tabuleiro Principal
- **Quadrados Coloridos**: Tabuleiro tradicional 8x8
- **Coordenadas**: Sistema cartesiano (A1-H8)
- **Seleção**: Quadrado amarelo indica peça selecionada
- **Movimentos Válidos**: Círculos verdes para movimentos, vermelhos para capturas
- **Vetores**: Setas azuis mostram direção e magnitude dos movimentos

### Painel Matemático
- **Informações da Peça**: Nome, posição, função matemática
- **Lista de Movimentos**: Todos os movimentos válidos com vetores
- **Análise Estatística**: Magnitude média, alcance máximo
- **Conceitos Aplicados**: Lista dos conceitos matemáticos em uso

## 🎓 Valor Educacional

### Para Estudantes
- **Visualização Concreta**: Conceitos abstratos tornam-se visíveis
- **Aprendizado Ativo**: Interação direta com os conceitos
- **Conexão Prática**: Matemática aplicada em contexto familiar
- **Feedback Imediato**: Resultados instantâneos dos cálculos

### Para Professores
- **Ferramenta Pedagógica**: Recurso visual para aulas
- **Demonstrações**: Exemplos práticos de conceitos teóricos
- **Engajamento**: Aumenta interesse dos alunos
- **Avaliação**: Possibilidade de criar exercícios baseados no jogo

## 🔧 Funcionalidades Técnicas

### Cálculos Matemáticos
- Operações vetoriais em tempo real
- Cálculo de distâncias euclidianas
- Análise de padrões de movimento
- Estatísticas de jogabilidade

### Interface Responsiva
- Redimensionamento automático
- Feedback visual imediato
- Controles intuitivos
- Informações contextuais

## 📈 Possíveis Extensões

### Funcionalidades Futuras
- **Modo Tutorial**: Introdução gradual aos conceitos
- **Desafios Matemáticos**: Problemas específicos para resolver
- **Análise de Partidas**: Estatísticas completas dos jogos
- **Modo Multiplayer**: Competição entre estudantes
- **Exportação de Dados**: Relatórios para análise

### Conceitos Adicionais
- **Derivadas**: Análise de velocidade de movimento
- **Integrais**: Cálculo de áreas de influência
- **Probabilidade**: Chances de vitória
- **Otimização**: Melhores estratégias matemáticas

## 👥 Equipe de Desenvolvimento
- Ian Francisco de Campos, RA: 12523127628 - Desenvolvedor Principal

## 📝 Licença
Este projeto foi desenvolvido para fins educacionais como parte da disciplina de Estruturas Matemáticas.

## 🤝 Contribuições
Sugestões e melhorias são bem-vindas! Entre em contato com a equipe de desenvolvimento.

**ChessMath** - Transformando o aprendizado de matemática através do jogo! ♟️📐
