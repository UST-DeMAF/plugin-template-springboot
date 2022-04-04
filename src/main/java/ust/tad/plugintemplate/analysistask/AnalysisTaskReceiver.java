package ust.tad.plugintemplate.analysistask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalysisTaskReceiver {

    private static final Logger LOG =
      LoggerFactory.getLogger(AnalysisTaskReceiver.class);

    @Autowired
    private MessageConverter jsonMessageConverter;

    @Autowired
    private AnalysisTaskResponseSender analysisTaskResponseSender;

    /**
     * Receives a message from the analysis task request queue.
     * Based on the type of the message given by the formatIndicator header, it calls a respective function.
     * 
     * @param message
     * @throws JsonProcessingException
     */
    public void receive(Message message) {     
        try {
            switch (message.getMessageProperties().getHeader("formatIndicator").toString()) {
                case "AnalysisTaskStartRequest":
                    receiveAnalysisTaskStartRequest(message);
                    break;
                case "AnalysisTaskResumeRequest":
                    receiveAnalysisTaskResumeRequest(message);
                    break;        
                default:
                    respondWithErrorMessage("Could not process message: Unknown format of request message.");
                    break;
            }   
        } catch (NullPointerException e) {
            respondWithErrorMessage("Could not process message: Header with formatIndicator missing.");
        }                   
    }

    /**
     * Receives a message of type AnalysisTaskStartRequest.
     * Transforms the message into an entity of type AnalysisTaskStartRequest.
     * Starts the analysis process of the plugin.
     * 
     * @param message
     */
    private void receiveAnalysisTaskStartRequest(Message message) {
        ObjectMapper mapper = new ObjectMapper();
        
        AnalysisTaskStartRequest analysisTaskStartRequest = mapper.convertValue(
            jsonMessageConverter.fromMessage(message), 
            AnalysisTaskStartRequest.class);

        LOG.info(String.format("received AnalysisTaskStartRequest: %s", analysisTaskStartRequest.toString()));
        //TODO start analysis process
    }

    /**
     * Receives a message of type AnalysisTaskResumeRequest.
     * Transforms the message into an entity of type AnalysisTaskResumeRequest.
     * Resumes the analysis process of the plugin.
     * 
     * @param message
     */
    private void receiveAnalysisTaskResumeRequest(Message message) {
        ObjectMapper mapper = new ObjectMapper();
        AnalysisTaskResumeRequest analysisTaskResumeRequest = mapper.convertValue(
            jsonMessageConverter.fromMessage(message), 
            AnalysisTaskResumeRequest.class);
        LOG.info(String.format("received AnalysisTaskResumeRequest: %s", analysisTaskResumeRequest.toString()));
        //TODO resume analysis process
    }

    /**
     * Creates and sends an AnalysisTaskResponse containing an error message.
     */
    private void respondWithErrorMessage(String errorMessage) {
        AnalysisTaskResponse analysisTaskResponse = new AnalysisTaskResponse();
        analysisTaskResponse.setSuccess(false);
        analysisTaskResponse.setErrorMessage(errorMessage);
        analysisTaskResponseSender.send(analysisTaskResponse);
    }

}
