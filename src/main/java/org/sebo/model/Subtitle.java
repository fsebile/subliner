package org.sebo.model;

import subtitleFile.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public interface Subtitle {

    static Subtitle readSRT(String file) throws IOException {

        SRTSubtitle subtitle = new SRTSubtitle();

        FormatSRT srt = new FormatSRT();
        TimedTextObject sub = srt.parseFile(file, Files.newInputStream(Paths.get(file)));
        for (Integer id : sub.captions.keySet()) {
            TextBlock block = new TextBlock();
            Caption caption = sub.captions.get(id);

            block.setSubtitle(caption.content);
            block.setStart(caption.start);
            block.setEnd(caption.end);

            subtitle.add(block);
        }

        return subtitle;
    }

    static Subtitle readSTL(String file) throws IOException, FatalParsingException {
        //TODO Sebo yazacak!
        STLSubtitle subtitle = new STLSubtitle();

        FormatSTL stl = new FormatSTL();
        TimedTextObject sub = stl.parseFile(file, Files.newInputStream(Paths.get(file)));
        for (Integer id : sub.captions.keySet()) {
            TextBlock block = new TextBlock();
            Caption caption = sub.captions.get(id);

            block.setSubtitle(caption.content);
            block.setStart(caption.start);
            block.setEnd(caption.end);

            subtitle.add(block);
        }

        return subtitle;
    }

    void write();

    void add(TextBlock block);

    List<TextBlock> getAll();

    TextBlock get(int i);

}
