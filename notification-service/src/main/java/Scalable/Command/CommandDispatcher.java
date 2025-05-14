package Scalable.Command;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandDispatcher {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CommandDispatcher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void dispatch(String exchange, String routingKey, Object commandPayload) {
        rabbitTemplate.convertAndSend(exchange, routingKey, commandPayload);
    }
}



