package main.utilities;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
public class ImageCacheManager {
    private static final Map<String, BufferedImage> IMAGE_CACHE = new HashMap<>();

    /**
     * Add an image to the cache
     *
     * @param key   the key to access the image
     * @param image the image to add
     */
    public static void addImage(String key, BufferedImage image) {
        IMAGE_CACHE.put(key, image);
    }

    /**
     * Get an image from the cache
     *
     * @param key the key to access the image
     * @return the image
     */
    public static BufferedImage getImage(String key) {
        return IMAGE_CACHE.get(key);
    }

    /**
     * Check if an image is cached
     *
     * @param key the key to access the image
     * @return true if the image is cached, false otherwise
     */
    public static boolean isImageCached(String key) {
        return IMAGE_CACHE.containsKey(key);
    }
}
