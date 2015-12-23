package com.vaadin.pontus.simpleimageviewer.model;

import java.io.File;

public class ImageDTO {

    private final String name;
    private final File file;

    public ImageDTO(String name, File file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }

}
