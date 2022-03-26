package ust.tad.plugintemplate.analysistask;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AnalysisTaskStartRequest {

    private UUID taskId;

    private UUID transformationProcessId;

    private String technology;

    private String analysisType;

    private List<String> commands;


    public AnalysisTaskStartRequest() {
    }

    public AnalysisTaskStartRequest(UUID taskId, UUID transformationProcessId, String technology, String analysisType, List<String> commands) {
        this.taskId = taskId;
        this.transformationProcessId = transformationProcessId;
        this.technology = technology;
        this.analysisType = analysisType;
        this.commands = commands;
    }

    public UUID getTaskId() {
        return this.taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getTransformationProcessId() {
        return this.transformationProcessId;
    }

    public void setTransformationProcessId(UUID transformationProcessId) {
        this.transformationProcessId = transformationProcessId;
    }

    public String getTechnology() {
        return this.technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getAnalysisType() {
        return this.analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    public List<String> getCommands() {
        return this.commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public AnalysisTaskStartRequest taskId(UUID taskId) {
        setTaskId(taskId);
        return this;
    }

    public AnalysisTaskStartRequest transformationProcessId(UUID transformationProcessId) {
        setTransformationProcessId(transformationProcessId);
        return this;
    }

    public AnalysisTaskStartRequest technology(String technology) {
        setTechnology(technology);
        return this;
    }

    public AnalysisTaskStartRequest analysisType(String analysisType) {
        setAnalysisType(analysisType);
        return this;
    }

    public AnalysisTaskStartRequest commands(List<String> commands) {
        setCommands(commands);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AnalysisTaskStartRequest)) {
            return false;
        }
        AnalysisTaskStartRequest analysisTaskStartRequest = (AnalysisTaskStartRequest) o;
        return Objects.equals(taskId, analysisTaskStartRequest.taskId) && Objects.equals(transformationProcessId, analysisTaskStartRequest.transformationProcessId) && Objects.equals(technology, analysisTaskStartRequest.technology) && Objects.equals(analysisType, analysisTaskStartRequest.analysisType) && Objects.equals(commands, analysisTaskStartRequest.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, transformationProcessId, technology, analysisType, commands);
    }

    @Override
    public String toString() {
        return "{" +
            " taskId='" + getTaskId() + "'" +
            ", transformationProcessId='" + getTransformationProcessId() + "'" +
            ", technology='" + getTechnology() + "'" +
            ", analysisType='" + getAnalysisType() + "'" +
            ", commands='" + getCommands() + "'" +
            "}";
    }

    
}
