package com.vaadin.pontus.simpleimageviewer.components;

import java.util.List;

import javax.annotation.Nullable;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.pontus.simpleimageviewer.model.ImageDTO;
import com.vaadin.pontus.simpleimageviewer.views.ImageView;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class ImageSelectorImpl extends HorizontalLayout implements
        ImageSelector {

    private final ComboBox selector;
    private final Button showButton;
    private final ImageView viewer;
    private final ImageSelectorPresenter presenter;

    public ImageSelectorImpl(ImageView viewer) {
        selector = new ComboBox("Images");
        showButton = new Button("Show");
        addComponent(selector);
        addComponent(showButton);
        setComponentAlignment(selector, Alignment.BOTTOM_LEFT);
        setComponentAlignment(showButton, Alignment.BOTTOM_LEFT);
        setSpacing(true);
        this.viewer = viewer;
        presenter = new ImageSelectorPresenter(this);

        showButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                ImageDTO image = (ImageDTO) selector.getValue();
                // getValue() can return null, a model of the Combobox is needed
                // to make Infer aware of this.
                presenter.showClicked(image);

            }

        });
    }

    public void add(ImageDTO image) {
        presenter.add(image);
    }

    public List<ImageDTO> getImages() {
        return presenter.getImages();
    }

    public void setImageList(List<ImageDTO> images) {
        BeanItemContainer<ImageDTO> container = new BeanItemContainer<ImageDTO>(
                ImageDTO.class, images);
        selector.setContainerDataSource(container);
        selector.setItemCaptionPropertyId("name");
    }

    public void setSelected(@Nullable ImageDTO image) {
        selector.setValue(image);
    }

    public void show(StreamResource resource, String name) {
        viewer.show(resource, name);
    }

}
