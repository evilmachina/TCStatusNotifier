package slickstreamer;

import jetbrains.buildServer.Build;
import jetbrains.buildServer.BuildType;
import jetbrains.buildServer.notification.Notificator;
import jetbrains.buildServer.notification.NotificatorRegistry;
import jetbrains.buildServer.responsibility.ResponsibilityEntry;
import jetbrains.buildServer.responsibility.TestNameResponsibilityEntry;
import jetbrains.buildServer.serverSide.*;
import jetbrains.buildServer.serverSide.mute.MuteInfo;
import jetbrains.buildServer.tests.TestName;
import jetbrains.buildServer.users.NotificatorPropertyKey;
import jetbrains.buildServer.users.PropertyKey;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.users.User;
import jetbrains.buildServer.vcs.SelectPrevBuildPolicy;
import jetbrains.buildServer.vcs.VcsModification;
import jetbrains.buildServer.vcs.VcsRoot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.*;

public class Notifier implements Notificator {

    private static final String TYPE = "pushNotifier";

    private static final String TYPE_NAME = "Push Notifier";

    private static final String PUSH_TO_URL = "pushToURL";

    private static final PropertyKey pushToURL = new NotificatorPropertyKey(TYPE, PUSH_TO_URL);

    public Notifier(NotificatorRegistry notificatorRegistry) throws IOException {
        ArrayList<UserPropertyInfo> userProps = new ArrayList<UserPropertyInfo>();
        userProps.add(new UserPropertyInfo(PUSH_TO_URL, "Push status to Url"));
        notificatorRegistry.register(this, userProps);
    }

    public void notifyBuildStarted(@NotNull SRunningBuild build, @NotNull Set<SUser> users) {
         notify(build, users, "BuildStarted");
    }

    public void notifyBuildSuccessful(@NotNull SRunningBuild build, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyBuildFailed(@NotNull SRunningBuild build, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyBuildFailedToStart(@NotNull SRunningBuild build, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyLabelingFailed(@NotNull Build build, @NotNull VcsRoot root, @NotNull Throwable exception, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyBuildFailing(@NotNull SRunningBuild build, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyBuildProbablyHanging(@NotNull SRunningBuild build, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyResponsibleChanged(@NotNull SBuildType buildType, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyResponsibleAssigned(@NotNull SBuildType buildType, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyResponsibleChanged(@Nullable TestNameResponsibilityEntry oldValue, @NotNull TestNameResponsibilityEntry newValue, @NotNull SProject project, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyResponsibleAssigned(@Nullable TestNameResponsibilityEntry oldValue, @NotNull TestNameResponsibilityEntry newValue, @NotNull SProject project, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyResponsibleChanged(@NotNull Collection<TestName> testNames, @NotNull ResponsibilityEntry entry, @NotNull SProject project, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyResponsibleAssigned(@NotNull Collection<TestName> testNames, @NotNull ResponsibilityEntry entry, @NotNull SProject project, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyTestsMuted(@NotNull Collection<STest> tests, @NotNull MuteInfo muteInfo, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyTestsUnmuted(@NotNull Collection<STest> tests, @NotNull MuteInfo muteInfo, @Nullable SUser user, @NotNull Set<SUser> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public String getNotificatorType() {
        return TYPE;
    }

    @NotNull
    public String getDisplayName() {
        return TYPE_NAME;
    }

    private void notify(SRunningBuild build, Set<SUser> users, String event) {

        for (SUser notifyUser : users) {
            notify(notifyUser, build, event);
        }
    }

    private void notify(SUser notifyUser, SRunningBuild build, String event) {
        DataPusher pusher = new DataPusher();
        String url = notifyUser.getPropertyValue(pushToURL);
        Map<String, String> data = new HashMap<String, String>();
        data.put("event", event);
        pusher.push(url, data);
    }

}