package luis.castillo.springkafkademo.producer;

import demo.kafka.event.PaymentSent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {
    private final KafkaTemplate<String, PaymentSent> kafkaTemplate;

    public void publishMessage(PaymentSent paymentSent){
        kafkaTemplate.send("payment-sent", paymentSent);
    }
}
