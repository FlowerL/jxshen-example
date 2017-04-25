package com.jxshen.example.concurrent.render;

import java.util.List;

/**
 * reference from : java concurrency in practice
 */
public interface Render {

    void renderPage(CharSequence source);
    
    void renderText(CharSequence source);
    
    void renderImage(ImageData data);
    
    List<ImageInfo> scanForImageInfo(CharSequence source);
    
}
