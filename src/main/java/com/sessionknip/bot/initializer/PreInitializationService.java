package com.sessionknip.bot.initializer;

import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import java.util.List;

@Component(role = PreInitializationService.class, hint = "preInitialization")
public class PreInitializationService extends AbstractInitializationService {

    @Requirement
    private List<InitializationProcess> initializationProcesses;

    @Override
    List<? extends InitializationProcess> getInitializationProcesses() {
        return initializationProcesses;
    }
}
