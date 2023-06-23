package luis.castillo.springkafkademo.consumer;

import demo.kafka.event.PaymentSent;
import demo.kafka.event.SendPayment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import luis.castillo.springkafkademo.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentService paymentService;

    @KafkaListener(topics = "send-payment")
    public void listen(SendPayment sendPayment){
        log.info("Received payment: {}", sendPayment);
        paymentService.processPayment(sendPayment);
    }
}
