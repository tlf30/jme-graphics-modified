package io.tlf.graphics;

import com.jme3.app.Application;
import com.jme3.post.FilterPostProcessor;
import com.jme3.system.JmeContext;

public class ContextSettings {

    private final Application app;
    private final JmeContext jmeContext;
    private final FilterPostProcessor fpp;

    ContextSettings(Application app, FilterPostProcessor fpp) {
        this.app = app;
        this.jmeContext = app.getContext();
        this.fpp = fpp;
    }

    /**
     * @return The target FPS of the app
     */
    public int getFrameRate() {
        return jmeContext.getSettings().getFrameRate();
    }

    /**
     *
     * @param frameRate The target FPS of the app
     */
    public void setFrameRate(int frameRate) {
        jmeContext.getSettings().setFrameRate(frameRate);
    }

    /**
     *
     * @return if gamma correction is enabled
     */
    public boolean isGammaCorrection() {
        return jmeContext.getSettings().isGammaCorrection();
    }

    /**
     *
     * @param gammaCorrection If gamma correction should be enabled
     */
    public void setGammaCorrection(boolean gammaCorrection) {
        jmeContext.getSettings().setGammaCorrection(gammaCorrection);
    }

    /**
     *
     * @return The width of the camera's viewport
     */
    public int getResolutionWidth() {
        return app.getViewPort().getCamera().getWidth();
    }

    /**
     *
     * @return The height of the camera's viewport
     */
    public int getResolutionHeight() {
        return app.getViewPort().getCamera().getHeight();
    }

    /**
     * Set the resolution of context in pixels
     * @param width The width of the context in px
     * @param height The height of the context in px
     */
    public void setResolution(int width, int height) {
        jmeContext.getSettings().setResolution(width, height);
    }

    /**
     * Set the height, width, and frequency of the context on the screen
     * @param res The target Resolution
     */
    public void setResolution(Graphics.Resolution res) {
        jmeContext.getSettings().setResolution(res.getWidth(), res.getHeight());
        jmeContext.getSettings().setFrequency(res.getHz());
    }

    /**
     *
     * @return The current Resolution containing width, height, and frequency
     */
    public Graphics.Resolution getResolution() {
        return new Graphics.Resolution(
                jmeContext.getSettings().getWidth(),
                jmeContext.getSettings().getHeight(),
                jmeContext.getSettings().getFrequency()
        );
    }

    /**
     *
     * @return If the context is fullscreen
     */
    public boolean isFullScreen() {
        return jmeContext.getSettings().isFullscreen();
    }

    /**
     *
     * @param fullScreen Set if the context should be fullscreen
     */
    public void setFullScreen(boolean fullScreen) {
        jmeContext.getSettings().setFullscreen(fullScreen);
    }

    /**
     *
     * @return if V-Sync is enabled on the context
     */
    public boolean isVsync() {
        return jmeContext.getSettings().isVSync();
    }

    /**
     *
     * @param vsync if V-Sync should be enabled
     */
    public void setVsync(boolean vsync) {
        jmeContext.getSettings().setVSync(vsync);
    }

    /**
     *
     * @param powerLevel Set the post processing samples used to render the context
     */
    public void setNumSamples(PowerLevel powerLevel) {
        fpp.setNumSamples(powerLevel.toInteger());
        app.getContext().getSettings().setSamples(powerLevel.toInteger());
    }

    /**
     * Applied the current settings to the context.
     * This must be called after any settings changes to the context.
     * This will force the context to restart, causing the windows to close and re-open with the new settings.
     * This will not change the running state of the app.
     */
    public void apply() {
        jmeContext.restart();
    }

}
