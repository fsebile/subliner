package org.sebo.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by f on 5/10/2017.
 */
public class TableModel {

    //    private SimpleIntegerProperty id;
    private SimpleStringProperty start;
    private SimpleStringProperty end;
    private SimpleStringProperty subtitle;
    private SimpleStringProperty translation;

    public TableModel() {
//        id = new SimpleIntegerProperty();
        start = new SimpleStringProperty();
        end = new SimpleStringProperty();
        subtitle = new SimpleStringProperty();
        translation = new SimpleStringProperty();
    }

    public TableModel(String start, String end, String subtitle) {
//        this.id = new SimpleIntegerProperty(id);
        this.start = new SimpleStringProperty(start);
        this.end = new SimpleStringProperty(end);
        this.subtitle = new SimpleStringProperty(subtitle);
        this.translation = new SimpleStringProperty();
    }

//    public int getId() {
//        return id.get();
//    }
//
//
//    public void setId(int id) {
//        this.id.set(id);
//    }

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
}
