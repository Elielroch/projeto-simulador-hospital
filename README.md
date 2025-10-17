Simulador de Hospital em Java

Descrição do Projeto

Este projeto consiste em uma simulação simplificada do funcionamento de um hospital, desenvolvido em Java. A simulação aborda a interação de pacientes com recursos hospitalares limitados, como médicos, salas de cirurgia e leitos. Cada paciente é representado por uma Thread que executa um fluxo de atividades, incluindo consultas, exames, cirurgias e repouso em leitos.

O objetivo principal é demonstrar conceitos de concorrência e sincronização em programação, utilizando Semaphores e BlockingQueues para gerenciar o acesso aos recursos compartilhados, evitando condições de corrida e garantindo a ordem das operações. A simulação permite observar o comportamento dos pacientes e a utilização dos recursos ao longo do tempo.

Funcionalidades Principais

•
Pacientes: Representados por threads, cada paciente segue um fluxo de tratamento no hospital.

•
Recursos: Médicos, salas de cirurgia e leitos são recursos limitados, gerenciados por semáforos e filas.

•
Fluxo de Tratamento: Os pacientes podem passar por:

•
Consulta (requer um médico)

•
Exames (não requer recursos específicos além do tempo)

•
Cirurgia (requer um médico e uma sala de cirurgia)

•
Repouso em Leito (requer um leito)



•
Concorrência: Utilização de java.util.concurrent.Semaphore e java.util.concurrent.BlockingQueue para controle de acesso e sincronização.

Recursos Disponíveis na Simulação

Recurso
Quantidade
Médicos
5
Salas de Cirurgia
2
Leitos
10
Pacientes
25


Estrutura do Projeto

O projeto está organizado na seguinte estrutura de diretórios:

Plain Text


hospital_simulator/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── hospital/
│                   ├── Bed.java
│                   ├── Doctor.java
│                   ├── Hospital.java
│                   ├── Patient.java
│                   └── SurgeryRoom.java
├── bin/ (diretório para arquivos compilados .class)



•
Hospital.java: Classe principal que inicializa os recursos e os pacientes, gerenciando a simulação.

•
Patient.java: Classe que implementa a lógica de um paciente como uma thread, definindo seu fluxo de tratamento.

•
Doctor.java, SurgeryRoom.java, Bed.java: Classes simples que representam os recursos hospitalares.

Como Executar o Projeto

Para compilar e executar o projeto, siga os passos abaixo:

Pré-requisitos

Certifique-se de ter o Java Development Kit (JDK) 17 ou superior instalado em sua máquina. Você pode verificar a versão do Java com os comandos java -version e javac -version no seu terminal.

Passos para Execução


1.
Abra um terminal ou prompt de comando e navegue até o diretório raiz do projeto hospital_simulator.

2.
Compile o código (se necessário): Os arquivos .class já estão incluídos na pasta bin. No entanto, se você realizar modificações no código-fonte (.java), será necessário recompilar. Execute o comando:javac -d bin src/main/java/com/hospital/*.java

3.
Execute a simulação: Para iniciar a simulação e ver a saída diretamente no terminal, execute:java -cp bin com.hospital.Hospital

Considerações sobre Deadlock

O projeto foi estruturado para minimizar a ocorrência de deadlocks através do uso de Semaphores e BlockingQueues que gerenciam o acesso aos recursos de forma ordenada. No entanto, em sistemas concorrentes complexos, deadlocks podem surgir sob certas condições. A simulação pode ser estendida para explorar cenários onde deadlocks poderiam ser simulados ou prevenido.






