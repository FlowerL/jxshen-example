package com.jxshen.example.concurrent.render;

import java.util.ArrayList;
import java.util.List;

/**
 * reference from : java concurrency in practice
 * 1. render text
 * 2. download image
 * 3. render image
 */
public class SingleThreadRender extends AbstractRender {

    @Override
    public void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageDataList = new ArrayList<ImageData>();
        List<ImageInfo> imageInfoList = scanForImageInfo(source);
        for (ImageInfo imageInfo : imageInfoList) {
            imageDataList.add(imageInfo.downloadImage());
        }
        for (ImageData imageData : imageDataList) {
            renderImage(imageData);
        }
    }

}
