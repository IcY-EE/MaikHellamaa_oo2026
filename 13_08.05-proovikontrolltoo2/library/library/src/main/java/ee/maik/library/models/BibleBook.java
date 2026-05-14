package ee.maik.library.models;

import lombok.Data;

@Data
public class BibleBook {
    private int bible_id;
    private String language;
    private String version;
}