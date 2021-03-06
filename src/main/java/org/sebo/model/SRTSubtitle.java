package org.sebo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by f on 5/8/2017.
 */
public class SRTSubtitle implements Subtitle {

    private List<TextBlock> textBlocks = new ArrayList<>();

    @Override
    public void save(String file) {

    }

    @Override
    public void add(TextBlock block) {
        textBlocks.add(block);
    }

    @Override
    public List<TextBlock> getAll() {
        return textBlocks;
    }

    @Override
    public TextBlock get(int i) {
        return textBlocks.get(i);
    }
}
