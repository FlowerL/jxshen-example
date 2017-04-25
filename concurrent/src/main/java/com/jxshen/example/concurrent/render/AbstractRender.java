package com.jxshen.example.concurrent.render;

import java.util.List;

public abstract class AbstractRender implements Render {

    @Override
    public void renderText(CharSequence source) {
        // TODO Auto-generated method stub

    }

    @Override
    public void renderImage(ImageData data) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ImageInfo> scanForImageInfo(CharSequence source) {
        // TODO Auto-generated method stub
        return null;
    }

}
