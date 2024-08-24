package io.github.rhysbeynon.spacer.utilities;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class Utilities {

    public static FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data).flip();
        return buffer;
    }

}
