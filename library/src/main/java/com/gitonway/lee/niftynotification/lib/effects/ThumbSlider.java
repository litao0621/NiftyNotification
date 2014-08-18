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

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

public class ThumbSlider extends BaseEffect{

    long s = (mDuration-200)/2,
         m = 200,
         e =(mDuration-200)/2;

    View iconView;
    View msgView;

    @Override
    protected void setInAnimation(View view) {
        iconView=view.findViewById(android.R.id.icon);
        if(iconView!=null){
        msgView=view.findViewById(android.R.id.message);
        ViewHelper.setAlpha(msgView,0f);
        ViewHelper.setPivotX(msgView, 0);
        ViewHelper.setPivotY(msgView, 0);
        ObjectAnimator msgScaleAnim=ObjectAnimator.ofFloat(msgView, "scaleX", 0, .5f, 1, 1.1f, 1).setDuration(s * 2);
        ObjectAnimator msgAlphaAnim=ObjectAnimator.ofFloat(msgView, "alpha", 1).setDuration(s * 2);
        msgScaleAnim.setStartDelay(s+m);
        msgAlphaAnim.setStartDelay(s+m);
        getAnimatorSet().playTogether(

                ObjectAnimator.ofFloat(iconView, "scaleX", 0, .5f, 1,.9f,1,1.2f,1).setDuration(s),
                ObjectAnimator.ofFloat(iconView,"scaleY",0,.5f,1,1.2f,1,.9f,1).setDuration(s),
                msgScaleAnim,
                msgAlphaAnim

        );
        }
    }

    @Override
    protected void setOutAnimation(View view) {
        iconView=view.findViewById(android.R.id.icon);
        if(iconView!=null) {
            msgView = view.findViewById(android.R.id.message);
            ObjectAnimator iconScaleXAnim = ObjectAnimator.ofFloat(iconView, "scaleX", 1, 1.2f, 1, .9f, 1, .5f, 0).setDuration(e * 2);
            ObjectAnimator iconScaleYAnim = ObjectAnimator.ofFloat(iconView, "scaleY", 1, .9f, 1, 1.2f, 1, .5f, 0).setDuration(e * 2);
            ObjectAnimator iconAlphaAnim = ObjectAnimator.ofFloat(iconView, "alpha", 1, 0).setDuration(e * 2);

            iconScaleXAnim.setStartDelay(e + m);
            iconScaleYAnim.setStartDelay(e + m);
            iconAlphaAnim.setStartDelay(e + m);
            getAnimatorSet().playTogether(

                    ObjectAnimator.ofFloat(msgView, "scaleX", 1, 1.1f, 1, .5f, 0).setDuration(e),
                    iconScaleXAnim,
                    iconScaleYAnim,
                    iconAlphaAnim

            );
        }
    }

    @Override
    protected long getAnimDuration(long duration) {
        return duration*2+200;
    }
}
