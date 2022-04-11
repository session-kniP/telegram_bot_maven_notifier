package com.sessionknip.bot.initializer;

import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import java.util.List;

@Component(role = PostInitializationService.class, hint = "postInitialization")
public class PostInitializationService extends AbstractInitializationService {

    @Requirement
    private List<PostInitializationProcess> initializationProcesses;

    @Override
    List<? extends InitializationProcess> getInitializationProcesses() {
        return initializationProcesses;
    }
}
