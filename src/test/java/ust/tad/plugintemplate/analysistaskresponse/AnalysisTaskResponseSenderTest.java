package ust.tad.plugintemplate.analysistaskresponse;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ust.tad.plugintemplate.analysistask.AnalysisTaskResponse;
import ust.tad.plugintemplate.analysistask.AnalysisTaskResponseSender;

@SpringBootTest
public class AnalysisTaskResponseSenderTest {

    @Autowired
    AnalysisTaskResponseSender analysisTaskResponseSender;

    @Test
    public void sendAnalysisTaskResponse() throws JsonProcessingException {
        AnalysisTaskResponse analysisTaskResponse = new AnalysisTaskResponse();
        analysisTaskResponse.setTaskId(UUID.randomUUID());
        analysisTaskResponse.setSuccess(true);

        analysisTaskResponseSender.send(analysisTaskResponse);
    }
    
}
