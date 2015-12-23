package com.vaadin.pontus.simpleimageviewer.views;

import com.vaadin.pontus.simpleimageviewer.model.ImageDTO;
import com.vaadin.server.StreamResource;

public interface ImageView {

    public void show(StreamResource image, String imageName);

    public void add(ImageDTO image);

}
