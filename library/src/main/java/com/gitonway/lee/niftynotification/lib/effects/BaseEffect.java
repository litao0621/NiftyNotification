package com.gitonway.lee.niftynotification.lib.effects;
/*
 * Copyright 2014 gitonway
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.view.View;

import com.gitonway.lee.niftynotification.lib.Configuration;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.view.ViewHelper;

public abstract class BaseEffect {

    public static final int DURATION;

    static {
        DURATION = Configuration.ANIM_DURATION;
    }
    public long mDuration=DURATION ;

    private AnimatorSet mAnimatorSet;
    {
        mAnimatorSet = new AnimatorSet();
    }

    protected abstract void setInAnimation(View view);

    protected abstract void setOutAnimation(View view);

    protected abstract long getAnimDuration(long duration);

    public void in(View view) {
        reset(view);
        setInAnimation(view);
        mAnimatorSet.start();
    }

    public void out(View view) {
        reset(view);
        setOutAnimation(view);
        mAnimatorSet.start();
    }
    public void reset(View view) {
        ViewHelper.setPivotX(view, view.getWidth() / 2.0f);
        ViewHelper.setPivotY(view, view.getHeight() / 2.0f);
    }
    public BaseEffect setDuration(long duration) {
        this.mDuration = duration;
        return this;
    }
    public long getDuration(){
        return getAnimDuration(mDuration);
    }

    public AnimatorSet getAnimatorSet() {
        return mAnimatorSet;
    }

}
