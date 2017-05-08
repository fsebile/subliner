package org.sebo.model;

import subtitleFile.Time;

public class TextBlock {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public subtitleFile.Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public subtitleFile.Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    private int id;
    private Time start;
    private Time end;
    private String subtitle;

    @Override
    public String toString() {
        return subtitle;
    }
}
