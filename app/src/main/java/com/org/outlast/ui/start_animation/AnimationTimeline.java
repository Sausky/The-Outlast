package com.org.outlast.ui.start_animation;

import com.org.outlast.R;

import java.util.ArrayList;

/**
 * Created by shen on 15/6/15.
 *
 *
 * 添加动画幕帧到pics列中，动画类型到anims列中
 *
 *
 */
public class AnimationTimeline {

    private static final ArrayList<Integer> pics = new ArrayList<Integer>(){{
        add(R.drawable.anim2);
        add(R.drawable.anim2);
        add(R.drawable.anim3);
        add(R.drawable.anim4);
        add(R.drawable.anim5);
        add(R.drawable.anim6);
        add(R.drawable.anim7);
        add(R.drawable.anim8);
    }};

    private static final ArrayList<Integer> anims = new ArrayList<Integer>(){{
        add(R.anim.next_spot);
        add(R.anim.shake);
        add(R.anim.next_spot);
        add(R.anim.next_spot);
        add(R.anim.next_spot);
        add(R.anim.next_spot);
        add(R.anim.next_spot);
    }};

    private static int size = anims.size();

    private static int index = 0;

    public static boolean isFinish(){
        return index==size;
    }

    public static void next(){
        index++;
    }

    public static int currentRes(){
        return pics.get(index);
    }

    public static int currentAnim(){
        return anims.get(index);
    }
}
