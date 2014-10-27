package cn.bingoogolapple.gradle.one;

import cn.bingoogolapple.gradle.two.Two;

/**
 * Created by bingoogolapple on 14-10-27.
 */
public class One {

    public String getName() {
        Two two = new Two();
        return "One  " + two.getName();
    }
}
