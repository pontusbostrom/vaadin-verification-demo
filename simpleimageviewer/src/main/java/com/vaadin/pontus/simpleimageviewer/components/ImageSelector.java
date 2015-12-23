package com.vaadin.pontus.simpleimageviewer.components;

import java.util.List;

import com.vaadin.pontus.simpleimageviewer.model.ImageDTO;
import com.vaadin.server.StreamResource;

public interface ImageSelector {
    public void add(ImageDTO image);

    public void setSelected(ImageDTO image);

    public void setImageList(List<ImageDTO> images);

    public void show(StreamResource resource, String name);

}
