package io.github.rhysbeynon.spacer;

import io.github.rhysbeynon.spacer.utilities.Utilities;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class ObjectLoader {

    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();

    //public Model loadModel(float[] vertices) {

    //}

    //private int createVAO() {

    //}

    //private int createVBO() {

   // }

    private void storeDataInAttributeList(int attributeNumber, int vertexCount,float[] data) {
        int vbo = GL15.glGenBuffers();
        vbos.add(vbo);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = Utilities.storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    private void unbind() {
        GL30.glBindVertexArray(0);
    }

    private void cleanup() {
        for(int vao : vaos)
            GL30.glDeleteVertexArrays(vao);
        for(int vbo : vbos)
            GL30.glDeleteBuffers(vbo);
    }



}
