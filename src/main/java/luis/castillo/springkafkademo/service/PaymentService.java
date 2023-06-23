package luis.castillo.springkafkademo.service;

import demo.kafka.event.PaymentSent;
import demo.kafka.event.SendPayment;

public interface PaymentService {
    void processPayment(SendPayment sendPayment);
}
