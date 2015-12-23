package com.vaadin.pontus.simpleimageviewer;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.pontus.simpleimageviewer.views.impl.MainViewImpl;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("mytheme")
@Widgetset("com.vaadin.pontus.simpleimageviewer.MyAppWidgetset")
public class SimpleImageViewerUI extends UI {

    private MainViewImpl view;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        view = new MainViewImpl();
        setContent(view);

    }

    @WebServlet(urlPatterns = "/*", name = "SimpleImageViewerUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = SimpleImageViewerUI.class, productionMode = false)
    public static class SimpleImageViewerUIServlet extends VaadinServlet {

    }
}
