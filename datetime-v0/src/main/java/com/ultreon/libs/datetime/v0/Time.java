package com.ultreon.libs.datetime.v0;

import com.ultreon.libs.datetime.v0.exceptions.DateTimeError;
import com.ultreon.libs.datetime.v0.exceptions.DateTimeException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@SuppressWarnings("unused")
public class Time implements Comparable<Time>, Serializable, Cloneable {
    private int hour;
    private int minute;
    private int second;
    private int nano;

    public static Time current() {
        LocalDateTime dateTime = LocalDateTime.now();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        int second = dateTime.getSecond();
        int nano = dateTime.getNano();

        return new Time(hour, minute, second, nano);
    }

    public Time(int hour, int minute, int second) {
        this(hour, minute, second, 0);
    }

    public Time(int hour, int minute, int second, int nano) {
        if (hour < 0 || hour > 23) throw new DateTimeException("Hour must be between 0 and 23");
        if (minute < 0 || minute > 59) throw new DateTimeException("Minute must be between 0 and 59");
        if (second < 0 || second > 59) throw new DateTimeException("Second must be between 0 and 59");
        if (nano < 0 || nano > 999_999_999) throw new DateTimeException("Nano must be between 0 and 999'999'999");

        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.nano = nano;
    }

    public long toNanos() {
        return this.hour * 3_600_000_000_000L + (this.minute * 60_000_000_000L) + (this.second * 1_000_000_000L) + this.nano;
    }

    public long toMillis() {
        return this.hour * 3_600_000L + (this.minute * 60_000L) + (this.second * 1_000L) + (this.nano / 1_000_000);
    }

    /**
     * @return total seconds.
     */
    public int toSeconds() {
        int sec = this.second;
        sec += this.minute * 60;
        sec += this.hour * 3600;

        return sec;
    }

    /**
     * @return total minutes.
     */
    public float toMinutes() {
        float min = (float) this.second / 60;
        min += (float) this.minute;
        min += (float) this.hour * 60;

        return min;
    }

    /**
     * @return total hours.
     */
    public float toHours() {
        float hor = (float) this.second / 3600;
        hor += (float) this.minute / 60;
        hor += (float) this.hour;

        return hor;
    }

    /**
     * Check whether the time is between a some time.
     *
     * @param lo low value.
     * @param hi high value.
     * @return true if the object is between time1 and time2.
     * @throws NullPointerException if ‘lo’ is higher than ‘hi’.
     */
    public boolean isBetween(Time lo, Time hi) {
        if (lo.toNanos() > hi.toNanos()) throw new DateTimeException("Invalid ordering of time, lower end time is higher than higher end time.");
        return ((lo.toSeconds() <= this.toSeconds()) && (hi.toSeconds() >= this.toSeconds()));
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        if (hour < 0 || hour > 23) throw new DateTimeException("Hour must be between 0 and 23");
        this.hour = hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int minute) {
        if (minute < 0 || minute > 59) throw new DateTimeException("Minute must be between 0 and 59");
        this.minute = minute;
    }

    public int getSecond() {
        return this.second;
    }

    public void setSecond(int second) {
        if (second < 0 || second > 59) throw new DateTimeException("Second must be between 0 and 59");
        this.second = second;
    }

    public int getNano() {
        return this.nano;
    }

    public void setNano(int nano) {
        if (nano < 0 || nano > 999_999_999) throw new DateTimeException("Nano must be between 0 and 999'999'999");
        this.nano = nano;
    }

    @Override
    public int compareTo(Time o) {
        return Long.compare(this.toNanos(), o.toNanos());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        return this.toNanos() == ((Time) o).toNanos();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(this.toNanos());
    }

    @Override
    public Time clone() {
        try {
            return (Time) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    public boolean isAfter(Time other) {
        return this.toNanos() > other.toNanos();
    }

    public boolean isBefore(Time other) {
        return this.toNanos() < other.toNanos();
    }

    public boolean isAfterOrEqual(Time other) {
        return this.toNanos() >= other.toNanos();
    }

    public boolean isBeforeOrEqual(Time other) {
        return this.toNanos() <= other.toNanos();
    }

    public DayPeriod getDayPeriod() {
        if (DayPeriod.NIGHT.isWithin(this)) return DayPeriod.NIGHT;
        if (DayPeriod.MORNING.isWithin(this)) return DayPeriod.MORNING;
        if (DayPeriod.AFTERNOON.isWithin(this)) return DayPeriod.AFTERNOON;
        if (DayPeriod.NIGHT.isWithin(this)) return DayPeriod.NIGHT;
        throw new DateTimeError("Can't find valid day period.");
    }
}
