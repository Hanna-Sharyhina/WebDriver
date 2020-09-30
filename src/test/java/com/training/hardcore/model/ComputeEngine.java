package com.training.hardcore.model;

public class ComputeEngine {

    private String numberOfInstance;
    private String instanceType;
    private String numberOfGPUs;
    private String typeOfGPU;
    private String localSSD;
    private String dataCenterLocation;
    private String commitmentUsage;
    private String expectedTotalCost;

    public ComputeEngine(String numberOfInstance, String instanceType, String numberOfGPUs, String typeOfGPU, String localSSD,
                         String dataCenterLocation, String commitmentUsage, String expectedTotalCost) {
        this.numberOfInstance = numberOfInstance;
        this.instanceType = instanceType;
        this.numberOfGPUs = numberOfGPUs;
        this.typeOfGPU = typeOfGPU;
        this.localSSD = localSSD;
        this.dataCenterLocation = dataCenterLocation;
        this.commitmentUsage = commitmentUsage;
        this.expectedTotalCost = expectedTotalCost;
    }

    public String getNumberOfInstance() {
        return numberOfInstance;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public String getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public String getTypeOfGPU() {
        return typeOfGPU;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public String getDataCenterLocation() {
        return dataCenterLocation;
    }

    public String getCommitmentUsage() {
        return commitmentUsage;
    }

    public String getExpectedTotalCost() {
        return expectedTotalCost;
    }
}
