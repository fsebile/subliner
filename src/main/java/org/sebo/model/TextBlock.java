package org.sebo.model;

import javafx.beans.property.SimpleStringProperty;

public class TextBlock {
    private SimpleStringProperty start;
    private SimpleStringProperty end;
    private SimpleStringProperty subtitle;
    private SimpleStringProperty translation;

    public TextBlock() {
        start = new SimpleStringProperty();
        end = new SimpleStringProperty();
        subtitle = new SimpleStringProperty();
        translation = new SimpleStringProperty();
    }

    public String getStart() {
        return start.get();
    }

    public void setStart(String start) {
        this.start.set(start);
    }

    public String getEnd() {
        return end.get();
    }

    public void setEnd(String end) {
        this.end.set(end);
    }

    public String getSubtitle() {
        return subtitle.get();
    }

    public void setSubtitle(String subtitle) {
        this.subtitle.set(subtitle);
    }

    public String getTranslation() {
        return translation.get();
    }

    public void setTranslation(String translation) {
        this.translation.set(translation);
    }

    @Override
    public String toString() {
        return subtitle.get();
    }
}
