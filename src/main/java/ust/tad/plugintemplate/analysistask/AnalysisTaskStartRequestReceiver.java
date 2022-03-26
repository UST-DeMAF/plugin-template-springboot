package ust.tad.plugintemplate.analysistask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class AnalysisTaskStartRequestReceiver {

    private static final Logger LOG =
      LoggerFactory.getLogger(AnalysisTaskStartRequestReceiver.class);

    private String queueName;

    AnalysisTaskStartRequestReceiver(String queueName) {
        this.queueName = queueName;
    }

    //@RabbitListener(queues = queueName)
    public void receiveAnalysisTaskStartRequest(AnalysisTaskStartRequest analysisTaskStartRequest) {
        LOG.info("received AnalysisTaskStartRequest: " + analysisTaskStartRequest.toString());
    }
    
}
