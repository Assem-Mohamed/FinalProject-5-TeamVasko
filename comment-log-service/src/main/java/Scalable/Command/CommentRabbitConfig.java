package Scalable.Command;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class CommentRabbitConfig {

    public static final String COMMENT_QUEUE = "comment.queue";
    public static final String COMMENT_EXCHANGE = "comment.exchange";
    public static final String COMMENT_ROUTING_KEY = "comment.routingKey";

    @Bean
    public Queue commentQueue() {
        return new Queue(COMMENT_QUEUE, true);
    }

    @Bean
    public DirectExchange commentExchange() {
        return new DirectExchange(COMMENT_EXCHANGE);
    }

    @Bean
    public Binding commentBinding() {
        return BindingBuilder.bind(commentQueue()).to(commentExchange()).with(COMMENT_ROUTING_KEY);
    }
}

