package com.vaadin.pontus.simpleimageviewer.views.impl;

import com.vaadin.pontus.simpleimageviewer.components.ImageSelectorImpl;
import com.vaadin.pontus.simpleimageviewer.components.ImageUploaderImpl;
import com.vaadin.pontus.simpleimageviewer.model.ImageDTO;
import com.vaadin.pontus.simpleimageviewer.views.ImageView;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MainViewImpl extends VerticalLayout implements ImageView {

    private final Panel imagePanel;
    private final ImageSelectorImpl selector;
    private final ImageUploaderImpl uploader;

    public MainViewImpl() {
        VerticalLayout layout = new VerticalLayout();
        Label title = new Label("<h1> Image viewer</h1>", ContentMode.HTML);
        imagePanel = new Panel();
        imagePanel.setWidth("500px");
        imagePanel.setHeight("300px");
        imagePanel.setCaption("Image");

        selector = new ImageSelectorImpl(this);
        uploader = new ImageUploaderImpl(this);

        layout.setSpacing(true);
        layout.addComponent(title);
        layout.addComponent(imagePanel);
        layout.addComponent(selector);
        layout.addComponent(uploader);
        layout.setSizeUndefined();

        addComponent(layout);
        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

        setHeight("100%");
        setWidth("100%");

    }

    @Override
    public void show(StreamResource image, String imageName) {
        Image imageComponent = new Image(imageName, image);
        imageComponent.setWidth("500px");
        imagePanel.setContent(imageComponent);

    }

    @Override
    public void add(ImageDTO image) {
        selector.add(image);

    }

}
