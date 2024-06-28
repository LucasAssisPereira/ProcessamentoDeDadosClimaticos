# Projeto de Processamento de Dados Climáticos

## Descrição

Este projeto realiza o processamento de dados climáticos de várias capitais, utilizando diferentes quantidades de threads para medir a performance do processamento concorrente. Ele obtém dados históricos de temperatura para o mês de janeiro de 2024 de uma API pública e calcula a temperatura mínima, máxima e média diária para cada capital.

## Estrutura do Projeto

```
plaintextCopiar código
src/
|-- main/
|   |-- java/
|       |-- experiment/
|           |-- NineThreadExperiment.java  // Lógica para experimento de nove threads
|           |-- SingleThreadExperiment.java  // Lógica para experimento de uma thread
|           |-- ThreeThreadExperiment.java  // Lógica para experimento de três threads
|           |-- TwentySevenThreadExperiment.java  // Lógica para experimento de vinte e sete threads
|       |-- model/
|           |-- Capital.java  // Enum de capitais
|           |-- WeatherData.java  // Classe para dados climáticos
|       |-- service/
|           |-- WeatherAPI.java  // Lógica de requisição à API
|           |-- WeatherProcessor.java  // Lógica de processamento de dados
|       |-- Main.java  // Ponto de entrada do programa com menu interativo

```

## Como Executar

### Usando uma IDE

Recomenda-se o uso de uma IDE como IntelliJ IDEA, Eclipse ou NetBeans para facilitar o processo de configuração e execução do projeto.

1. **Importe o Projeto na IDE**
    - **IntelliJ IDEA**: File -> Open -> Selecione o diretório do projeto.
    - **Eclipse**: File -> Import... -> Existing Maven Projects -> Selecione o diretório do projeto.
    - **NetBeans**: File -> Open Project... -> Selecione o diretório do projeto.
2. **Execute o Programa**
    - Localize a classe `Main.java` na estrutura do projeto.
    - Clique com o botão direito na classe e selecione "Run `Main.main()`".
3. **Escolha uma Opção no Menu Interativo**
    - 1: Experimento com uma thread
    - 2: Experimento com três threads
    - 3: Experimento com nove threads
    - 4: Experimento com vinte e sete threads
    - 5: Sair

### Usando Linha de Comando

Se preferir usar a linha de comando:

1. **Clone o Repositório**
    
    ```bash
    git clone https://github.com/LucasAssisPereira/ProcessamentoDeDadosClimaticos
    cd ProcessamentoDeDadosClimaticos
    
    ```
    
2. **Compile o Projeto com Maven**
    
    ```bash
    mvn clean install
    
    ```
    
3. **Execute o Programa**
    
    ```bash
    mvn exec:java -Dexec.mainClass="Main"
    
    ```
    

## Funcionamento

### Requisição à API

A classe `WeatherAPI` é responsável por fazer a requisição HTTP à API pública para obter os dados de temperatura. O formato da URL é:

```
plaintextCopiar código
https://api.open-meteo.com/v1/forecast?latitude=<LATITUDE>&longitude=<LONGITUDE>&start_date=2024-01-01&end_date=2024-01-31&hourly=temperature_2m

```

### Processamento dos Dados

A classe `WeatherProcessor` é responsável por calcular as temperaturas mínima, máxima e média diárias a partir dos dados obtidos da API.

### Experimentos com Threads

Os experimentos são divididos nas classes `SingleThreadExperiment`, `ThreeThreadExperiment`, `NineThreadExperiment` e `TwentySevenThreadExperiment`, cada uma representando a quantidade de threads utilizadas no processamento.x
