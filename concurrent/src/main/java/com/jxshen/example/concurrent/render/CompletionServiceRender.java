package com.jxshen.example.concurrent.render;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Reference from : java concurrency in practice
 * 1. download image in concurrency
 * 2. download images & render text in concurrency
 * 3. once any image donwload, render it (not concurrency)
 */
public class CompletionServiceRender extends AbstractRender {

    final ExecutorService executor;
    
    public CompletionServiceRender(ExecutorService executor) {
        super();
        this.executor = executor;
    }

    @Override
    public void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfoList = scanForImageInfo(source);
        CompletionService<ImageData> completionService =
                new ExecutorCompletionService<ImageData>(executor);
        for (final ImageInfo imageInfo : imageInfoList) {
            completionService.submit(new Callable<ImageData>(){
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }
        renderText(source);
        
        try {
            for (int i = 0; i < imageInfoList.size(); i++) {
                Future<ImageData> future = completionService.take();
                ImageData imageData = future.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
