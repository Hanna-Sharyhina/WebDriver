package com.training.hardcore.service;

import com.training.hardcore.model.ComputeEngine;

public class ComputeEngineCreator {

    public static final String NUMBER_OF_INSTANCE = "testdata.computeengine.numberOfInstance";
    public static final String INSTANCE_TYPE = "testdata.computeengine.instanceType";
    public static final String NUMBER_OF_GPUS = "testdata.computeengine.numberOfGpus";
    public static final String TYPE_OF_GPU = "testdata.computeengine.typeOfGpu";
    public static final String LOCAL_SSD = "testdata.computeengine.localSsd";
    public static final String DATA_CENTER_LOCATION = "testdata.computeengine.dataCenterLocation";
    public static final String COMMITMENT_USAGE = "testdata.computeengine.commitmentUsage";
    public static final String EXPECTED_TOTAL_COST = "testdata.computeengine.expectedTotalCost";

    public static ComputeEngine withParametersFromProperty() {
        return new ComputeEngine(TestDataReader.getTestData(NUMBER_OF_INSTANCE),
                TestDataReader.getTestData(INSTANCE_TYPE),
                TestDataReader.getTestData(NUMBER_OF_GPUS),
                TestDataReader.getTestData(TYPE_OF_GPU),
                TestDataReader.getTestData(LOCAL_SSD),
                TestDataReader.getTestData(DATA_CENTER_LOCATION),
                TestDataReader.getTestData(COMMITMENT_USAGE),
                TestDataReader.getTestData(EXPECTED_TOTAL_COST));
    }
}
