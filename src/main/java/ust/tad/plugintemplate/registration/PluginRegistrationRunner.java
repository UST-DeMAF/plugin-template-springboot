package ust.tad.plugintemplate.registration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import ust.tad.plugintemplate.analysistask.AnalysisTaskStartRequestReceiver;

@Component
public class PluginRegistrationRunner implements ApplicationRunner{

    private static final Logger LOG =
      LoggerFactory.getLogger(PluginRegistrationRunner.class);

    @Autowired
    private GenericApplicationContext context;

    @Autowired
    private WebClient pluginRegistrationApiClient;

    @Value("${plugin.technology}")
    private String pluginTechnology;

    @Value("${plugin.analysis-type}")
    private String pluginAnalysisType;

    @Value("${messaging.analysistask.response.exchange.name}")
    private String responseExchangeName;

    @Override
    public void run(ApplicationArguments args) throws JsonProcessingException {

        LOG.info("Registering Plugin");

        String body = createPluginRegistrationBody();
        LOG.info("Created body: " + body);

        PluginRegistrationResponse response = pluginRegistrationApiClient.post()
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(body))
            .retrieve()
            .bodyToMono(PluginRegistrationResponse.class)
            .block();

        LOG.info("Received response: " + response.toString());

        //register rabbitlistener to requestqueue
        //context.registerBean(AnalysisTaskStartRequestReceiver.class, 
         //   () -> new AnalysisTaskStartRequestReceiver(response.getRequestQueueName()));

        //register bean with response exchange
        context.registerBean(responseExchangeName, DirectExchange.class, 
            () -> new DirectExchange(response.getResponseExchangeName(), true, false));
    }

    private String createPluginRegistrationBody() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode plugin = mapper.createObjectNode();
        plugin.put("technology", pluginTechnology);
        plugin.put("analysisType", pluginAnalysisType);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(plugin);
    }
    
}
