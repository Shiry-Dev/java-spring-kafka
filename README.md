# __Atividade de treinamento em mensageria__
---
Este treinamento tem como função ter um entendimento melhor de como funciona
duas mensagerias, onde uma delas seria a porta de entrada e a segunda a porta se saída.
Resumindo tudo, esta atividade consiste em usar o Docker, onde o mesmo terá dois
servidores Kafka, o primeiro seria a mensagem de entrada, para ele ter algo preenchido
a nossa primeira atividade seria a criação de um sistema em Java onde o mesmo teria
como função enviar novas mensagens para esse Kafka.
A segunda atividade seria na verdade um sistema em Java, onde este terá como função
de ler essas mensagens do primeiro Kafka, interpretar a mesma, e enviar o resultado
para o segundo Kafka.
__Exemplo de mensagem para o primeiro Kafka:__

    {
        “valor1”: 10.23,
        “valor2”: 3.2
        “operador”: “*”
    }
__E esta seria mensagem a ser enviada para o segundo Kafka:__

    {
        “resultado”: 32.736
    }
Para um entendimento melhor, iremos executar as quatro operações básicas e neste
exemplo acima citado seria: __10.23 * 3.2__
