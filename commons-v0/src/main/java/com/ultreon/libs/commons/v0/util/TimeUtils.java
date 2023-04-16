package com.ultreon.libs.commons.v0.util;

import com.ultreon.libs.commons.v0.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public final class TimeUtils extends UtilityClass {
    /**
     * @param duration the desired duration to format.
     * @return the formatted duration.
     */
    public static String formatDuration(long duration) {
        LocalDateTime g = LocalDateTime.ofEpochSecond(duration, 0, ZoneOffset.ofTotalSeconds(0));
        int minute = g.getMinute();
        int second = g.getSecond();

        double hourDouble = (double) duration / 60 / 60;
        return formatDuration(minute, second, hourDouble);
    }

    @NotNull
    public static String formatDuration(int minute, int second, double hourDouble) {
        hourDouble -= (double) minute / 60;
        hourDouble -= (double) second / 60 / 60;

        int hour = (int) hourDouble;

        String minuteString = Integer.toString(minute);
        String secondString = Integer.toString(second);

        if (minuteString.length() == 1) minuteString = "0" + minuteString;
        if (secondString.length() == 1) secondString = "0" + secondString;

        return hour + ":" + minuteString + ":" + secondString;
    }
}
