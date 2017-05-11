package org.sebo;

import org.sebo.model.SRTSubtitle;
import org.sebo.model.STLSubtitle;
import org.sebo.model.Subtitle;
import org.sebo.model.TextBlock;
import subtitleFile.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.joor.Reflect.on;

/**
 * Created by f on 5/11/2017.
 */
public final class SubtitleUtil {

    private static Subtitle readSRT(String file) throws IOException {
        FormatSRT srt = new FormatSRT();

        TimedTextObject sub = srt.parseFile(file, Files.newInputStream(Paths.get(file)));

        return getSubtitle(sub, new SRTSubtitle());
    }

    private static String convert2String(Time start) {
        return on(start)
                .call("getTime", "hh:mm:ss,ms")
                .get();
    }


    private static Subtitle readSTL(String file) throws IOException, FatalParsingException {
        FormatSTL stl = new FormatSTL();

        TimedTextObject sub = stl.parseFile(file, Files.newInputStream(Paths.get(file)));

        return getSubtitle(sub, new STLSubtitle());
    }

    private static Subtitle getSubtitle(TimedTextObject sub, Subtitle subtitle) {
        for (Integer id : sub.captions.keySet()) {
            TextBlock block = new TextBlock();
            Caption caption = sub.captions.get(id);

            block.setSubtitle(caption.content);
            block.setStart(convert2String(caption.start));
            block.setEnd(convert2String(caption.end));

            subtitle.add(block);
        }
        return subtitle;
    }

    public static Subtitle loadSubtitle(File file) throws Exception {
        Subtitle subtitle;
        if (file.getName().endsWith(".srt")) {
            subtitle = readSRT(file.getAbsolutePath());
        } else if (file.getName().endsWith(".stl")) {
            subtitle = readSTL(file.getAbsolutePath());
        } else {
            System.out.println("Uygun formatta dosya olmalı");
            throw new Exception("Uygun formatta dosya olmalı");
        }
        return subtitle;
    }
}
