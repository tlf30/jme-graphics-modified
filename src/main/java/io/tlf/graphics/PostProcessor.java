package io.tlf.graphics;

import com.jme3.post.Filter;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.*;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.water.WaterFilter;

public class PostProcessor {

    private final FilterPostProcessor fpp;

    PostProcessor(FilterPostProcessor fpp) {
        this.fpp = fpp;
    }

    public FilterPostProcessor getFilterPostProcessor() {
        return fpp;
    }

    public void setBloomEnabled(boolean enabled) {
        setFilterEnabled(BloomFilter.class, enabled);
    }

    public boolean isBloomEnabled() {
        return isFilterEnabled(BloomFilter.class);
    }

    public BloomFilter getBloomFilter() {
        return getFilter(BloomFilter.class);
    }

    public void setColorOverlayEnabled(boolean enabled) {
        setFilterEnabled(ColorOverlayFilter.class, enabled);
    }

    public boolean isColorOverlayEnabled() {
        return isFilterEnabled(ColorOverlayFilter.class);
    }

    public ColorOverlayFilter getColorOverlayFilter() {
        return getFilter(ColorOverlayFilter.class);
    }

    public void setComposeEnabled(boolean enabled) {
        setFilterEnabled(ComposeFilter.class, enabled);
    }

    public boolean isComposeEnabled() {
        return isFilterEnabled(ComposeFilter.class);
    }

    public ComposeFilter getComposeFilter() {
        return getFilter(ComposeFilter.class);
    }

    public void setCrossHatchEnabled(boolean enabled) {
        setFilterEnabled(CrossHatchFilter.class, enabled);
    }

    public boolean isCrossHatchEnabled() {
        return isFilterEnabled(CrossHatchFilter.class);
    }

    public CrossHatchFilter getCrossHatchFilter() {
        return getFilter(CrossHatchFilter.class);
    }

    public void setDepthOfFieldEnabled(boolean enabled) {
        setFilterEnabled(DepthOfFieldFilter.class, enabled);
    }

    public boolean isDepthOfFieldEnabled() {
        return isFilterEnabled(BloomFilter.class);
    }

    public DepthOfFieldFilter getDepthOfFieldFilter() {
        return getFilter(DepthOfFieldFilter.class);
    }

    public void setFadeEnabled(boolean enabled) {
        setFilterEnabled(FadeFilter.class, enabled);
    }

    public boolean isFadeEnabled() {
        return isFilterEnabled(FadeFilter.class);
    }

    public FadeFilter getFadeFilter() {
        return getFilter(FadeFilter.class);
    }

    public void setFogEnabled(boolean enabled) {
        setFilterEnabled(FogFilter.class, enabled);
    }

    public boolean isFogEnabled() {
        return isFilterEnabled(FogFilter.class);
    }

    public FogFilter getFogFilter() {
        return getFilter(FogFilter.class);
    }

    public void setFxaaEnabled(boolean enabled) {
        setFilterEnabled(FXAAFilter.class, enabled);
    }

    public boolean isFxaaEnabled() {
        return isFilterEnabled(FXAAFilter.class);
    }

    public FXAAFilter getFxaaFilter() {
        return getFilter(FXAAFilter.class);
    }

    public void setLightScatteringEnabled(boolean enabled) {
        setFilterEnabled(LightScatteringFilter.class, enabled);
    }

    public boolean isLightScatteringEnabled() {
        return isFilterEnabled(LightScatteringFilter.class);
    }

    public LightScatteringFilter getLightScatteringFilter() {
        return getFilter(LightScatteringFilter.class);
    }

    public void setPosterizationEnabled(boolean enabled) {
        setFilterEnabled(PosterizationFilter.class, enabled);
    }

    public boolean isPosterizationEnabled() {
        return isFilterEnabled(PosterizationFilter.class);
    }

    public PosterizationFilter getPosterizationFilter() {
        return getFilter(PosterizationFilter.class);
    }

    public void setRadialBlurEnabled(boolean enabled) {
        setFilterEnabled(RadialBlurFilter.class, enabled);
    }

    public boolean isRadialBlurEnabled() {
        return isFilterEnabled(RadialBlurFilter.class);
    }

    public RadialBlurFilter getRadialBlurFilter() {
        return getFilter(RadialBlurFilter.class);
    }

    public void setToneMapEnabled(boolean enabled) {
        setFilterEnabled(ToneMapFilter.class, enabled);
    }

    public boolean isToneMapEnabled() {
        return isFilterEnabled(ToneMapFilter.class);
    }

    public ToneMapFilter getToneMapFilter() {
        return getFilter(ToneMapFilter.class);
    }

    public void setTranslucentBucketEnabled(boolean enabled) {
        setFilterEnabled(TranslucentBucketFilter.class, enabled);
    }

    public boolean isTranslucentBucketEnabled() {
        return isFilterEnabled(TranslucentBucketFilter.class);
    }

    public TranslucentBucketFilter getTranslucentBucketFilter() {
        return getFilter(TranslucentBucketFilter.class);
    }

    public void setAmbientOcclusionEnabled(boolean enabled) {
        setFilterEnabled(SSAOFilter.class, enabled);
    }

    public boolean isAmbientOcclusionEnabled() {
        return isFilterEnabled(SSAOFilter.class);
    }

    public SSAOFilter getAmbientOcclusionFilter() {
        return getFilter(SSAOFilter.class);
    }

    public void setWaterEnabled(boolean enabled) {
        setFilterEnabled(WaterFilter.class, enabled);
    }

    public boolean isWaterEnabled() {
        return isFilterEnabled(WaterFilter.class);
    }

    public WaterFilter getWaterFilter() {
        return getFilter(WaterFilter.class);
    }

    public <T extends Filter> T getFilter(Class<T> filterType) {
        return fpp.getFilter(filterType);
    }

    public boolean isFilterEnabled(Class<? extends Filter> clazz) {
        return fpp.getFilter(clazz) != null;
    }

    public void setFilterEnabled(Class<? extends Filter> clazz, boolean enabled) {

        Filter filter = fpp.getFilter(clazz);

        String className = clazz.getSimpleName().toLowerCase();

        if (enabled) {

            switch (className) {
                case "bloomfilter":
                    filter = new BloomFilter();
                    break;
                case "coloroverlayfilter":
                    filter = new ColorOverlayFilter();
                    break;
                case "composefilter":
                    filter = new ComposeFilter();
                    break;
                case "crosshatchfilter":
                    filter = new CrossHatchFilter();
                    break;
                case "depthoffieldfilter":
                    filter = new DepthOfFieldFilter();
                    break;
                case "fadefilter":
                    filter = new FadeFilter();
                    break;
                case "fogfilter":
                    filter = new FogFilter();
                    break;
                case "fxaafilter":
                    filter = new FXAAFilter();
                    break;
                case "lightscatteringfilter":
                    filter = new LightScatteringFilter();
                    break;
                case "posterizationfilter":
                    filter = new PosterizationFilter();
                    break;
                case "radialblurfilter":
                    filter = new RadialBlurFilter();
                    break;
                case "tonemapfilter":
                    filter = new ToneMapFilter();
                    break;
                case "translucentbucketfilter":
                    filter = new TranslucentBucketFilter();
                    break;

                case "ssaofilter":
                    filter = new SSAOFilter();
                    break;
                case "waterfilter":
                    filter = new WaterFilter();
                    break;

                default:
                    throw new RuntimeException("Unknown Filter: " + clazz);
            }

            fpp.addFilter(filter);

        } else {
            if (filter != null) {
                fpp.removeFilter(filter);
            }
        }

    }

}
