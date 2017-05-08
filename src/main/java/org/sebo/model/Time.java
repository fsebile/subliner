package org.sebo.model;

/**
 * Created by f on 5/8/2017.
 */
public class Time extends subtitleFile.Time {
    /**
     * Constructor to create a time object.
     *
     * @param format supported formats: "hh:mm:ss,ms", "h:mm:ss.cs" and "h:m:s:f/fps"
     * @param value  string in the correct format
     */
    protected Time(String format, String value) {
        super(format, value);
    }
}
