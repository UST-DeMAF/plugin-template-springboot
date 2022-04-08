package ust.tad.plugintemplate.models;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import ust.tad.plugintemplate.models.tadm.annotatedentities.AnnotatedDeploymentModel;
import ust.tad.plugintemplate.models.tsdm.TechnologySpecificDeploymentModel;

@Service
public class ModelsService {
    
    @Autowired
    private WebClient modelsServiceApiClient;
    
    @Value("${models-service.url}")
    private String modelsServiceURL;

    /**
     * Retrive a technology-specific deployment model from the model service.
     * 
     * @param transformationProcessId
     * @return
     */
    public TechnologySpecificDeploymentModel getTechnologySpecificDeploymentModel(UUID transformationProcessId) {
        return modelsServiceApiClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/technology-specific/"+transformationProcessId)
                .build())
           .accept(MediaType.APPLICATION_JSON)
           .retrieve()
           .bodyToMono(TechnologySpecificDeploymentModel.class)
           .block();
    }

    /**
     * Retrive a technology-agnostic deployment model from the model service.
     * 
     * @param transformationProcessId
     * @return
     */
    public AnnotatedDeploymentModel getTechnologyAgnosticDeploymentModel(UUID transformationProcessId) {
         return modelsServiceApiClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/technology-agnostic/"+transformationProcessId)
                .build())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(AnnotatedDeploymentModel.class)
            .block();
    }
}
