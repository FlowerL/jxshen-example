package com.jxshen.example.concurrent.render;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * reference from : java concurrency in practice
 * 1. download image & render text in concurrency
 * 2. render image
 */
public class FutureRender extends AbstractRender {
    
    private final ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfoList = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() {
                List<ImageData> imageDataList = new ArrayList<ImageData>();
                for (ImageInfo imageInfo : imageInfoList) {
                    imageDataList.add(imageInfo.downloadImage());
                }
                return imageDataList;
            }
        };
        Future<List<ImageData>> future = executor.submit(task);
        renderText(source);
        
        try {
            List<ImageData> imageDataList = future.get();
            for (ImageData imageData : imageDataList) {
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
        }
    }

}
