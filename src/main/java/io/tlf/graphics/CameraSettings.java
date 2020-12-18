package io.tlf.graphics;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image;
import com.jme3.texture.Texture2D;

/**
 * Sets camera view frustums and field of view correctly using convenience methods.
 * The camera settings allow changing each individual field, however changing some settings causes undesired behavior.
 * This convenience class simply lets you change the required settings in an easier manner.
 */
public class CameraSettings {

    private final Application app;
    private final Camera cam;

    private float fov = 45; // the default JME FOV

    CameraSettings(Application app) {
        this.app = app;
        this.cam = app.getCamera();
    }

    /**
     * Aspect ration calculated as camera width divided by camera height
     * @return The aspect ration of the camera
     */
    public float getAspectRatio() {
        return (float) cam.getWidth() / (float) cam.getHeight();
    }

    /**
     * @return The camera's near frustum
     */
    public float getFrustumNear() {
        return cam.getFrustumNear();
    }

    /**
     * @param near The camera's near frustum
     */
    public void setFrustumNear(float near) {
        cam.setFrustumPerspective(fov, getAspectRatio(), near, cam.getFrustumFar());
    }

    /**
     * @return The camera's far frustum
     */
    public float getFrustumFar() {
        return cam.getFrustumFar();
    }

    /**
     * @param far The camera's far frustum
     */
    public void setFrustumFar(float far) {
        cam.setFrustumPerspective(fov, getAspectRatio(), cam.getFrustumNear(), far);
    }

    /**
     *
     * @return The camera's field of view
     */
    public float getFieldOfView() {
        return this.fov;
    }

    /**
     *
     * @param fov The camera's field of view
     */
    public void setFieldOfView(float fov) {
        this.fov = fov;
        cam.setFrustumPerspective(fov, getAspectRatio(), cam.getFrustumNear(), cam.getFrustumFar());
    }

    /**
     * Set the near frustum, far frustum and field of view.
     *
     * @param near the near view frustum.
     * @param far  the far view frustum.
     * @param fov  the field of view.
     */
    public void set(float near, float far, float fov) {
        this.fov = fov;
        cam.setFrustumPerspective(fov, getAspectRatio(), near, far);
    }

    private Texture2D deptTexture;

    /**
     * Lazily initializes and returns the depth texture.
     *
     * @return the depth texture of the main camera.
     */
    public Texture2D getDepthTexture() {

        if (deptTexture == null) {
            FrameBuffer depthBuffer = new FrameBuffer(app.getCamera().getWidth(), app.getCamera().getHeight(), 0);
            depthBuffer.setDepthBuffer(Image.Format.Depth);
            deptTexture = new Texture2D(depthBuffer.getWidth(), depthBuffer.getHeight(), Image.Format.Depth);
            depthBuffer.setDepthTexture(deptTexture);

            ViewPort depthView = app.getRenderManager().createPreView("depthView", app.getCamera());
            depthView.setOutputFrameBuffer(depthBuffer);
            depthView.setClearDepth(true);
            depthView.attachScene(((SimpleApplication) app).getRootNode());
        }

        return deptTexture;
    }

}
