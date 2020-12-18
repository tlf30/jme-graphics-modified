package io.tlf.graphics;

import com.jme3.app.Application;
import com.jme3.post.FilterPostProcessor;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

import java.util.HashSet;
import java.util.Objects;

public class Graphics {

    private static Graphics INSTANCE;

    private final Application app;

    private final FilterPostProcessor fpp;
    private final PostProcessor postProcessor;

    private final ContextSettings contextSettings;
    private final CameraSettings cameraSettings;

    private AnistropicFilterAssetListener anistropicListener;

    private Graphics(Application app) {
        this.app = app;

        fpp = new FilterPostProcessor(app.getAssetManager());
        app.getViewPort().addProcessor(fpp);
        postProcessor = new PostProcessor(fpp);

        contextSettings = new ContextSettings(app, fpp);
        cameraSettings = new CameraSettings(app);
    }

    /**
     * Initializes this graphics object.
     *
     * @param application the jmonkey application.
     */
    public static void initialize(Application application) {
        INSTANCE = new Graphics(application);
    }

    /**
     *
     * @return The current global instance
     */
    public static Graphics getInstance() {
        return INSTANCE;
    }

    /**
     * Gets the Post Processing methods
     * Deals with all things post-process based.
     *
     * @return the post processor object.
     */
    public PostProcessor getPostProcessor() {
        return postProcessor;
    }

    /**
     * Gets the Context settings.
     * Details with all things context-based.
     * Any changes to the context must be followed up with getContextSettings.apply();
     *
     * @return the context object.
     */
    public ContextSettings getContextSettings() {
        return contextSettings;
    }

    /**
     * Gets the camera settings.
     * Deals with all things camera-based.
     *
     * @return The camers settings object.
     */
    public CameraSettings getCameraSettings() {
        return cameraSettings;
    }

    /**
     * Set the AA level.
     * @param level The AnistropicFilteringLevel
     */
    public void setAnistropicFilteringLevel(PowerLevel level) {

        if (level == PowerLevel.OFF) {
            if (anistropicListener != null) {
                app.getAssetManager().removeAssetEventListener(anistropicListener);
                anistropicListener = null;
            }
        } else {
            if (anistropicListener == null) {
                anistropicListener = new AnistropicFilterAssetListener(level.toInteger());
                app.getAssetManager().addAssetEventListener(anistropicListener);
            } else {
                anistropicListener.setLevel(level.toInteger());
            }
        }
    }

    /**
     * This must be called from the OpenGL context thread.
     * @return An array of all supported resolutions by the system.
     */
    public Resolution[] getResolutions() {
        PointerBuffer monitors = GLFW.glfwGetMonitors();
        HashSet<Resolution> res = new HashSet<>();

        for ( int i = 0; i < monitors.limit(); i++ ) {
            long monitor = monitors.get(i);

            GLFWVidMode.Buffer modes = GLFW.glfwGetVideoModes(monitor);

            int modeCount = modes.sizeof();

            for ( int j = 0; j < modeCount; j++ ) {
                modes.position(j);

                int width = modes.width();
                int height = modes.height();
                int rate = modes.refreshRate();

                res.add(new Resolution(width, height, rate));
            }
        }
        return res.toArray(new Resolution[0]);
    }

    public static class Resolution {
        private int width;
        private int height;
        private int hz;

        public Resolution(int width, int height, int hz) {
            this.setWidth(width);
            this.setHeight(height);
            this.setHz(hz);
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Resolution that = (Resolution) o;
            return getWidth() == that.getWidth() && getHeight() == that.getHeight() && getHz() == that.getHz();
        }

        @Override
        public String toString() {
            return width + " x " + height + " @ " + hz  + "hz";
        }

        @Override
        public int hashCode() {
            return Objects.hash(getWidth(), getHeight(), getHz());
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHz() {
            return hz;
        }

        public void setHz(int hz) {
            this.hz = hz;
        }
    }

}
