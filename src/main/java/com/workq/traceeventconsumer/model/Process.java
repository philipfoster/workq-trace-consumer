package com.workq.traceeventconsumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Map;

/**
 * Represents a JBPM process
 */
@JsonPropertyOrder({"Name", "Node", "ProcessVariables"})
public class Process {

    private String name;
    private Map<String, Object> processVariables;
    private Node node;

    @JsonProperty("Name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Node")
    public Node getNode() {
        return this.node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @JsonProperty("ProcessVariables")
    public Map<String, Object> getProcessVariables() {
        return processVariables;
    }

    public void setProcessVariables(Map<String, Object> processVariables) {
        this.processVariables = processVariables;
    }

    @Override
    public String toString() {
        return "Process{" +
            "name='" + name + '\'' +
//            ", processVariables=" + processVariables +
            ", node=" + node +
            '}';
    }
}