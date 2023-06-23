package luis.castillo.springkafkademo.service.impl;

import demo.kafka.event.PaymentSent;
import demo.kafka.event.SendPayment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import luis.castillo.springkafkademo.producer.PaymentProducer;
import luis.castillo.springkafkademo.service.PaymentService;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentProducer paymentProducer;
    @Override
    public void processPayment(SendPayment sendPayment) {
        log.info("Processing payment......");

        // Simulate external service call
        try {
            Thread.sleep(3 * 1000L);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        // Create outbound event
        PaymentSent outboundEvent = PaymentSent.newBuilder()
                .setPaymentId(sendPayment.getPaymentId())
                .setAmount(sendPayment.getAmount())
                .setCurrency(sendPayment.getCurrency())
                .setFromAccount(sendPayment.getFromAccount())
                .setToAccount(sendPayment.getToAccount())
                .build();

        // publish to output topic
        paymentProducer.publishMessage(outboundEvent);
        log.info("Done!");
    }
}
