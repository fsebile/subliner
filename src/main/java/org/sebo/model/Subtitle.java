package org.sebo.model;

import java.util.List;


public interface Subtitle {
    void save(String file);

    void add(TextBlock block);

    List<TextBlock> getAll();

    TextBlock get(int i);
}
