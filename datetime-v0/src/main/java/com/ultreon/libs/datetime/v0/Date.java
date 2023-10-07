package com.ultreon.libs.datetime.v0;

import com.ultreon.libs.datetime.v0.exceptions.DateTimeException;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.Era;
import java.time.chrono.IsoChronology;
import java.util.Objects;

import static com.ultreon.libs.datetime.v0.MeteorologicalSeason.*;

@SuppressWarnings("unused")
public class Date implements Serializable, Comparable<Date>, Cloneable {
    private int day;
    private Month month;
    private int year;

    public static Date current() {
        LocalDateTime dateTime = LocalDateTime.now();
        int day = dateTime.getDayOfMonth();
        int month = dateTime.getMonthValue();
        int year = dateTime.getYear();

        return new Date(day, Month.from(month), year);
    }

    public Date(int day, int month, int year) {
        this(day, Month.from(month), year);
    }

    public Date(int day, Month month, int year) {
        checkDayOfMonth(day, month, year);
        this.day = day;
        this.month = month;
        this.year = year;
    }

    static void checkDayOfMonth(int day, Month month, int year) {
        int maxDays = month.getDays(year);
        if (day < 1 || day > maxDays)
            throw new DateTimeException("The day of " + month.name() + " should be between 1 and " + maxDays + " but got " + day);
    }

    /*************************************************************
     * Return flag meaning the object is between time1 and time2.
     *
     * @param lo low value.
     * @param hi high value.
     * @return true if the object is between time1 and time2.
     * @throws NullPointerException if ‘lo’ is higher than ‘hi’.
     */
    public static boolean isBetween(Date lo, Date hi) {
        if (lo.toEpochDay() > hi.toEpochDay()) throw new NullPointerException("‘lo’ is higher than ‘hi’");

        return ((lo.toEpochDay() <= hi.toEpochDay()) && (hi.toEpochDay() >= lo.toEpochDay()));
    }

    public long toEpochDay() {
        LocalDate of = LocalDate.of(this.year, this.month.getIndex(), this.day);
        return of.toEpochDay();
    }

    public long toEpochSecond() {
        DateTime of = this.getStart();
        return of.toEpochSecond();
    }

    public long toEpochMilli() {
        return this.toEpochSecond() * 1000;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.day, this.month, this.year);
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Month getMonth() {
        return this.month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getMonthIndex() {
        return this.month.getIndex();
    }

    public void setMonthIndex(int index) {
        this.month = Month.from(index);
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public DayOfWeek getDayOfWeek() {
        LocalDate localDate = this.getLocalDate();
        return localDate.getDayOfWeek();
    }

    public int getDayOfYear() {
        LocalDate localDate = this.getLocalDate();
        return localDate.getDayOfYear();
    }

    public Era getEra() {
        LocalDate localDate = this.getLocalDate();
        return localDate.getEra();
    }

    public MeteorologicalSeason getSeason() {
        if (isBetween(WINTER.getStartDate(this.year), WINTER.getEndDate(this.year))) return WINTER;
        if (isBetween(SPRING.getStartDate(this.year), SPRING.getEndDate(this.year))) return SPRING;
        if (isBetween(SUMMER.getStartDate(this.year), SUMMER.getEndDate(this.year))) return SUMMER;
        if (isBetween(AUTUMN.getStartDate(this.year), AUTUMN.getEndDate(this.year))) return AUTUMN;

        throw new IllegalArgumentException("Expected to find season, but was outside any create the seasons.");
    }

    public IsoChronology getChronology() {
        LocalDate localDate = this.getLocalDate();
        return localDate.getChronology();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public boolean isLeapYear() {
        LocalDate localDate = this.getLocalDate();
        localDate.toString();
        return localDate.getChronology().isLeapYear(this.getYear());
    }

    private LocalDate getLocalDate() {
        return LocalDate.of(this.year, this.month.getIndex(), this.day);
    }

    private LocalDateTime getLocalDateTime() {
        return this.getStart().toLocalDateTime();
    }

    private DateTime getStart() {
        return new DateTime(this.day, this.month, this.year, 0, 0, 0);
    }

    private DateTime getEnd() {
        return new DateTime(this.day, this.month, this.year, 23, 59, 59);
    }

    public TimeSpan toTimeSpan() {
        return new TimeSpan(this.getStart(), this.getEnd());
    }

    @Override
    public int compareTo(@NotNull Date other) {
        return Long.compare(this.toEpochDay(), other.toEpochDay());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return this.day == date.day &&
                this.month == date.month &&
                this.year == date.year;
    }

    public boolean equalsIgnoreYear(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return this.day == date.day &&
                this.month == date.month;
    }
    @Override
    public Date clone() {
        try {
            return (Date) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
