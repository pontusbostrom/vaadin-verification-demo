package com.vaadin.pontus.simpleimageviewer.components;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;
import com.vaadin.pontus.simpleimageviewer.model.ImageDTO;

public class ImageUploaderPresenter {

    private final ImageUploader viewer;
    private ImageDTO image;

    public ImageUploaderPresenter(ImageUploader viewer) {
        this.viewer = viewer;
        image = null;
    }

    public void uploadSucceeded() {
        if (image != null) {
            viewer.add(image);
        }
        image = null;

    }

    @SuppressWarnings("resource")
    public OutputStream receiveUpload(String filename, String mimeType) {
        File file = null;
        OutputStream stream = null;
        String fileEnding = null;
        String[] fileParts = filename.split("\\.");
        if (fileParts.length > 1) {
            fileEnding = fileParts[fileParts.length - 1];
        }
        try {
            file = createFile(fileEnding);
            stream = new FileOutputStream(file);
        } catch (IOException e) {
            stream = ByteStreams.nullOutputStream();
        }
        image = new ImageDTO(filename, file);
        // File can be null. The problem
        // it leads to in the ImageSelector is not detected by the basic infer
        // Annotations are needed.
        return stream;

    }

    private static File createFile(String fileEnding) throws IOException {
        // This methods can be called with fileEnding==null and Infer finds
        // this problem.
        if (fileEnding.equals("png") || fileEnding.equals("jpg")
                || fileEnding.equals("jpeg")) {
            return File.createTempFile("img", fileEnding);
        } else {
            throw new IOException();
        }
    }

    public String[] splitOnDot(String s) {
        return s.split("\\.");
    }

}
