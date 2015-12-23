package com.vaadin.pontus.simpleimageviewer.components;

import java.io.OutputStream;

import com.vaadin.pontus.simpleimageviewer.model.ImageDTO;
import com.vaadin.pontus.simpleimageviewer.views.ImageView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

@SuppressWarnings("serial")
public class ImageUploaderImpl extends HorizontalLayout implements
        ImageUploader {

    private final ImageView viewer;
    private final ImageUploaderPresenter presenter;

    public ImageUploaderImpl(ImageView viewer) {
        this.viewer = viewer;
        ImageUploaderHandler handler = new ImageUploaderHandler();
        Upload upload = new Upload("Upload image", handler);
        upload.addSucceededListener(handler);
        upload.setButtonCaption("Upload");
        addComponent(upload);
        presenter = new ImageUploaderPresenter(this);
    }

    public void add(ImageDTO image) {
        viewer.add(image);
    }

    class ImageUploaderHandler implements Receiver, SucceededListener {

        @Override
        public void uploadSucceeded(SucceededEvent event) {
            presenter.uploadSucceeded();

        }

        @Override
        public OutputStream receiveUpload(String filename, String mimeType) {
            return presenter.receiveUpload(filename, mimeType);
        }

    }

}
