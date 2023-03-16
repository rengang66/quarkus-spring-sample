package com.iiit.quarkus.sample.extension.project.deployment;

import io.quarkus.builder.item.SimpleBuildItem;
import io.quarkus.runtime.RuntimeValue;

import com.iiit.quarkus.sample.extension.project.StartupService;

public final class StartupServiceBuildItem extends SimpleBuildItem {
    private final RuntimeValue<StartupService> service;

    public StartupServiceBuildItem(RuntimeValue<StartupService> service) {
        this.service = service;
    }

    public RuntimeValue<StartupService> getService() {
        return this.service;
    }
}
