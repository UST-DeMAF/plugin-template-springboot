package ust.tad.plugintemplate.analysistask;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalysisTaskStartRequestReceiver {

    private static final Logger LOG =
      LoggerFactory.getLogger(AnalysisTaskStartRequestReceiver.class);

    @Autowired
    private MessageConverter jsonMessageConverter;

    public void receiveAnalysisTaskStartRequest(Message message) {
        ObjectMapper mapper = new ObjectMapper();
        AnalysisTaskStartRequest analysisTaskStartRequest = mapper.convertValue(
            jsonMessageConverter.fromMessage(message), 
            AnalysisTaskStartRequest.class);
        LOG.info("received AnalysisTaskStartRequest: " + analysisTaskStartRequest.toString());
    }

}
