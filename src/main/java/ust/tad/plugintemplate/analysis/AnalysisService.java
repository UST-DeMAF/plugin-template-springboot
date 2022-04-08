package ust.tad.plugintemplate.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ust.tad.plugintemplate.analysistask.AnalysisTaskResponseSender;
import ust.tad.plugintemplate.analysistask.EmbeddedDeploymentModelAnalysisRequest;
import ust.tad.plugintemplate.analysistask.Location;
import ust.tad.plugintemplate.models.ModelsService;
import ust.tad.plugintemplate.models.tadm.annotatedentities.AnnotatedDeploymentModel;
import ust.tad.plugintemplate.models.tsdm.TechnologySpecificDeploymentModel;

@Service
public class AnalysisService {
    
    private static final Logger LOG =
      LoggerFactory.getLogger(AnalysisService.class);

    @Autowired
    ModelsService modelsService;

    @Autowired
    AnalysisTaskResponseSender analysisTaskResponseSender;

    /**
     * Start the analysis of the deployment model.
     * 1. Retrieve deployment models from models service
     * 2. Parse in technology-specific deployment model from locations
     * 3. Update tsdm with new information
     * 4. Transform to EDMM entities and update tadm
     * 5. Send updated models to models service
     * 6. Send AnalysisTaskResponse or EmbeddedDeploymentModelAnalysisRequests if present 
     * 
     * @param taskId
     * @param transformationProcessId
     * @param commands
     * @param locations
     */
    public void startAnalysis(UUID taskId, UUID transformationProcessId, List<String> commands, List<Location> locations) {

        TechnologySpecificDeploymentModel tsdm = modelsService.getTechnologySpecificDeploymentModel(transformationProcessId);
        LOG.info(tsdm.toString());
        AnnotatedDeploymentModel tadm = modelsService.getTechnologyAgnosticDeploymentModel(transformationProcessId);
        LOG.info(tadm.toString());

        List<EmbeddedDeploymentModelAnalysisRequest> embeddedDeploymentModels = new ArrayList<>();

        //TODO Analysis and Transformation Logic goes here

        if(embeddedDeploymentModels.isEmpty()) {
            analysisTaskResponseSender.sendSuccessResponse(taskId);
        } else {
            for(EmbeddedDeploymentModelAnalysisRequest embeddedDeploymentModel : embeddedDeploymentModels) {
                analysisTaskResponseSender.sendEmbeddedDeploymentModelAnalysisRequest(embeddedDeploymentModel);
            }
        }
    }

    
}