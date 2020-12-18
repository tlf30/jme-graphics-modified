package io.tlf.graphics;

import com.jme3.asset.AssetEventListener;
import com.jme3.asset.AssetKey;
import com.jme3.asset.TextureKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A convenience class to register anistropic filtering for jme textures.
 */
public class AnistropicFilterAssetListener implements AssetEventListener {

    private final String[] extensions = {".png", ".jpg", ".dds", ".gif"};
    private int level;

    private final List<TextureKey> loadedTextures = new ArrayList<>();

    public AnistropicFilterAssetListener(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        loadedTextures.forEach(key -> key.setAnisotropy(this.level));
    }

    @Override
    public void assetLoaded(AssetKey key) {

        boolean supported = Arrays.stream(extensions).anyMatch(ext -> ext.equals(key.getExtension()));

        if (supported) {
            TextureKey tkey = (TextureKey) key;
            tkey.setAnisotropy(this.level);
            loadedTextures.add(tkey);
        }
    }

    @Override
    public void assetRequested(AssetKey key) {

    }

    @Override
    public void assetDependencyNotFound(AssetKey parentKey, AssetKey dependentAssetKey) {

    }
}
