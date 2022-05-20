package mbeans;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class DotsCounter extends NotificationBroadcasterSupport implements DotsCounterMBean {
    private int dotsNumber;
    private int missedDotsNumber;
    private int notificationNumber = 0;

    public void registerDot(boolean success) {
        ++dotsNumber;
        if (!success) {
            Notification notification =
                    new Notification("j4ytk", this, ++notificationNumber, System.currentTimeMillis(), "User has missed");
            sendNotification(notification);
            ++missedDotsNumber;
        }
    }

    @Override
    public int getDotsNumber() {
        return dotsNumber;
    }

    @Override
    public int getMissedDotsNumber() {
        return missedDotsNumber;
    }
}
