package com.sessionknip.notifier;

import com.sessionknip.bot.BotInitializerException;
import com.sessionknip.bot.BuildNotificationBot;
import com.sessionknip.bot.initializer.TelegramBotInitializer;
import fr.jcgay.maven.notifier.AbstractNotifier;
import fr.jcgay.maven.notifier.Notifier;
import org.apache.maven.execution.BuildFailure;
import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.BuildSummary;
import org.apache.maven.execution.MavenExecutionResult;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Duration;

@Component(role = Notifier.class, hint = "telegram-bot")
public class TelegramBotNotifier extends AbstractNotifier {

    @Requirement
    private TelegramBotInitializer initializer;

    @Override
    protected void fireNotification(MavenExecutionResult event) {
        try {
            BuildNotificationBot notificationBot = initializer.initialize();
            if (!notificationBot.isInitialized() || !notificationBot.isEnabled()) {
                return;
            }
            BuildSummary buildSummary = event.getBuildSummary(event.getProject());

            StringBuilder sb = new StringBuilder();

            Duration duration = Duration.ofMillis(buildSummary.getTime());
            String durationString = getTimeStringFromDuration(duration);

            if (buildSummary instanceof BuildSuccess) {
                sb.append("Successfully built. Time elapsed: ");
                sb.append(durationString);
                sb.append("\n");
            } else {
                sb.append("Build failed with following error:");
                sb.append("\n");
                sb.append(((BuildFailure) buildSummary).getCause().toString());
                sb.append("\n");
                sb.append("Time elapsed: ");
                sb.append(durationString);
            }

            notificationBot.execute(new SendMessage(notificationBot.getChatId(), sb.toString()));
        } catch (BotInitializerException | TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTimeStringFromDuration(Duration duration) {
        return duration.toMinutes() + "m " + duration.getSeconds() + "s";
    }

    @Override
    public String getNotifierId() {
        return "telegram-bot";
    }
}
