package com.vaadin.pontus.simpleimageviewer.components;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.vaadin.pontus.simpleimageviewer.model.ImageDTO;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;

public class ImageSelectorPresenter {

    private final ImageSelector view;
    private final List<ImageDTO> images;
    @Nullable
    private ImageDTO chosen;

    public ImageSelectorPresenter(ImageSelector view) {
        this.view = view;
        images = new ArrayList<ImageDTO>();
        chosen = null;

    }

    public void initView() {
        view.setImageList(images);
        view.setSelected(chosen);
    }

    public void showClicked(ImageDTO image) {
        chosen = image;
        view.show(
                new StreamResource(new ImageSource(image.getFile()), image
                        .getName()), image.getName());
        // image can be null above, which would lead to a NPE without a check.
        // This problem is detected with the basic infer given a suitable model
        // of the ComboBox
        // getFile() can also be null, but the problem it causes is not detected
        // without providing extra annotations
    }

    public void add(ImageDTO image) {
        images.add(image);
        view.setImageList(images);
        view.setSelected(chosen);
    }

    public List<ImageDTO> getImages() {
        return images;
    }

}

@SuppressWarnings("serial")
class ImageSource implements StreamSource {

    private final File file;

    public ImageSource(File file) {
        this.file = file;
    }

    @Override
    public InputStream getStream() {
        try {
            BufferedInputStream stream = new BufferedInputStream(
                    new FileInputStream(file));
            return stream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Returning null here is ok, an missing image symbol will be shown
        return null;
    }
}
