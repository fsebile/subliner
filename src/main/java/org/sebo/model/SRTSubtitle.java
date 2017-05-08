package org.sebo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by f on 5/8/2017.
 */
public class SRTSubtitle implements Subtitle {

    List<TextBlock> textBlocks = new ArrayList<>();

    @Override
    public Subtitle read(String file) {
        return null;
    }

    @Override
    public void write() {

    }

    @Override
    public List<TextBlock> getAll() {
        return null;
    }

    @Override
    public TextBlock get(int i) {
        return null;
    }
}
