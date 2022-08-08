package br.com.spring.kafka.consumer.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.io.IOException;
    @Setter @Getter
    class Mensagem {
        private double valor1;
        private double valor2;
        private String operador;

//        public double getValor1() {
//            return valor1;
//        }
//
//        public void setValor1(double valor1) {
//            this.valor1 = valor1;
//        }
//
//        public double getValor2() {
//            return valor2;
//        }
//
//        public void setValor2(double valor2) {
//            this.valor2 = valor2;
//        }
//
//        public String getOperador() {
//            return operador;
//        }
//
//        public void setOperador(String operador) {
//            this.operador = operador;
//        }

    }

@Service
public class ConsumerSpringKafka {

    @Value("${topic.resultado}")
    private String topicResultado;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(ConsumerSpringKafka.class);

    @KafkaListener(topics = "${topic.spring-kafka}", groupId = "group_id")
    public void consume(String mensagem) throws IOException {
        ObjectMapper mapper = new ObjectMapper();//"ler o JSON"
        JsonNode actualObj = mapper.readTree(mensagem);//desserializa o JSON
        Mensagem minhaMensagem = new Mensagem();
        minhaMensagem.setValor1(actualObj.get("valor1").asDouble());
        minhaMensagem.setValor2(actualObj.get("valor2").asDouble());
        minhaMensagem.setOperador(actualObj.get("operador").asText());
//        logger.info(String.format(" #### -> Consumindo mensagem -> %s", mensagem));

        Resultado resultado = new Resultado();
        this.calcula(resultado, minhaMensagem.getValor1(), minhaMensagem.getValor2(), minhaMensagem.getOperador());


        var writer = mapper.writer().withDefaultPrettyPrinter();
        kafkaTemplate.send(topicResultado, writer.writeValueAsString(resultado));//envia o resultado para o kafka
    }

    @KafkaListener(topics = "${topic.resultado}", groupId = "group_id")
    public void consumeResultado(String mensagem){
        System.out.println("Deu bom !?"+ mensagem);
    }

    class Resultado{
        private double resultado;

        public double getResultado() {
            return resultado;
        }

        public void setResultado(double resultado) {
            this.resultado = resultado;
        }
    }
    private void calcula(Resultado resultado, double valor1, double valor2, String operador) {
        switch (operador){
            case "+":
                resultado.setResultado(valor1 + valor2);
                break;
            case "-":
                resultado.setResultado(valor1 - valor2);
                break;
            case "*":
                resultado.setResultado(valor1 * valor2);
                break;
            case "/":
                resultado.setResultado(valor1 / valor2);
                break;
            default:
                throw new IllegalArgumentException("Operacao nao suportada");
        }
    }
}