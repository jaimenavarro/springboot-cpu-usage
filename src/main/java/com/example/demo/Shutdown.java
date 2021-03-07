package com.example.demo;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class Shutdown implements ApplicationContextAware {

    private static final String NO_CONTEXT_MESSAGE = "No context to shutdown.";

    private static final String SHUTDOWN_MESSAGE = "Shutting down, bye...";

    private ConfigurableApplicationContext context;

    @WriteOperation
    public void shutdown() {
        if (this.context == null) {
            log.info(NO_CONTEXT_MESSAGE.toString());
        }
        try {
            log.info(SHUTDOWN_MESSAGE.toString());
        }
        finally {
            Thread thread = new Thread(this::performShutdown);
            thread.setContextClassLoader(getClass().getClassLoader());
            thread.start();
        }
    }

    private void performShutdown() {
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        this.context.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (context instanceof ConfigurableApplicationContext) {
            this.context = (ConfigurableApplicationContext) context;
        }
    }

}