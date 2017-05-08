package org.sebo.model;

import java.util.List;

/**
 * Created by f on 5/8/2017.
 */
public interface Subtitle {

    Subtitle read(String file);

    void write();

    List<TextBlock> getAll();

    TextBlock get(int i);
    
}
