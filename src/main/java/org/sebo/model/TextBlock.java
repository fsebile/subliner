package org.sebo.model;

import subtitleFile.Time;

import static org.joor.Reflect.on;

public class TextBlock {
    private int id;
    private Time start;
    private Time end;
    private String subtitle;

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

    public TableModel getTableModel() {
        String s = on(start)
                .call("getTime", "hh:mm:ss,ms")
                .get();

        String e = on(end)
                .call("getTime", "hh:mm:ss,ms")
                .get();

        return new TableModel(s, e, subtitle);
    }

    @Override
    public String toString() {
        return subtitle;
    }
}
