package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

@Data
@Slf4j
@Service
public class ConsumeCPU {

    @PostConstruct
    void init() {
        long initTime = System.currentTimeMillis();
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        CentralProcessor processor = hardware.getProcessor();
        CentralProcessor.ProcessorIdentifier processorIdentifier = processor.getProcessorIdentifier();
        
        log.info("-----------------CPU DETAILS----------------------");
        log.info("Processor Vendor: " + processorIdentifier.getVendor());
        log.info("Processor Name: " + processorIdentifier.getName());
        log.info("Processor ID: " + processorIdentifier.getProcessorID());
        log.info("Identifier: " + processorIdentifier.getIdentifier());
        log.info("Microarchitecture: " + processorIdentifier.getMicroarchitecture());
        log.info("Frequency (Hz): " + processorIdentifier.getVendorFreq());
        log.info("Frequency (GHz): " + processorIdentifier.getVendorFreq() / 1000000000.0);
        log.info("Number of physical packages: " + processor.getPhysicalPackageCount());
        log.info("Number of physical CPUs: " + processor.getPhysicalProcessorCount());
        log.info("Number of logical CPUs: " + processor.getLogicalProcessorCount());

        log.info("-------------------------------------------------");
        sortIterationTask();
        log.info("-------------------------------------------------");
        longIterationTask();
        log.info("-------------------------------------------------");
        process();
        log.info("-------------------------------------------------");
        sortIterationTask();
        log.info("-------------------------------------------------");
        longIterationTask();
        log.info("-------------------------------------------------");
        process();
        log.info("--------------------Exit------------------------");
        long finishTime = System.currentTimeMillis();
        log.info("ALL DURATION: " + (finishTime - initTime));
        System.exit(0);
    }
    
    //4,294,967,295
    void sortIterationTask() {
        int numberLong = Integer.MIN_VALUE;
        long initTime = System.currentTimeMillis();
        log.info("Init Sort Task: " + initTime);
        while (numberLong < Integer.MAX_VALUE - 1) {
            numberLong++;
        }
        long finishTime = System.currentTimeMillis();
        log.info("Finish Sort Task: " + finishTime);
        log.info("Duration Sort miliseconds: " + (finishTime - initTime));
    }
    
    //100,000,000,000
    void longIterationTask() {
        long numberLong = 0;
        long initTime = System.currentTimeMillis();
        log.info("Init Long Task: " + initTime);
        long longLimit = new Long("100000000000");
        while (numberLong < longLimit) {
            numberLong++;
        }
        long finishTime = System.currentTimeMillis();
        log.info("Finish Long Task: " + finishTime);
        log.info("Duration Long miliseconds: " + (finishTime - initTime));
    }
    
    //2,147,483,647
    void process() {
        int numberLong = 0;
        long initTime = System.currentTimeMillis();
        log.info("Init Process Task: " + initTime);
        while (numberLong < Integer.MAX_VALUE - 1) {
            System.currentTimeMillis();
            numberLong++;
        }
        long finishTime = System.currentTimeMillis();
        log.info("Finish Process Task: " + finishTime);
        log.info("Duration Process miliseconds: " + (finishTime - initTime));
    }
    
}
