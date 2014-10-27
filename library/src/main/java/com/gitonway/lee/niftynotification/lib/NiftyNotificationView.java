package com.gitonway.lee.niftynotification.lib;
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

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NiftyNotificationView {

    private static final String NULL_PARAMETERS_ARE_NOT_ACCEPTED = "Null parameters are not accepted";

    private static final int TEXT_ID = android.R.id.message;

    private static final int IMAGE_ID = android.R.id.icon;

    private Configuration configuration = null;

    private final CharSequence text;

    private final Effects effects;

    private Activity activity;

    private ViewGroup viewGroup;

    private FrameLayout notifyView;

    private Drawable iconDrawable;

    private int  iconRes;

    private boolean isDefault;

    private View.OnClickListener onClickListener;


    private NiftyNotificationView(Activity activity, CharSequence text, Effects effects, ViewGroup viewGroup) {
        if ((activity == null) || (text == null)) {
            throw new IllegalArgumentException(NULL_PARAMETERS_ARE_NOT_ACCEPTED);
        }
        isDefault=true;
        this.activity = activity;
        this.text = text;
        this.effects = effects;
        this.viewGroup = viewGroup;
        this.configuration = new Configuration.Builder().build();
        init(effects);
    }

    private NiftyNotificationView(Activity activity, CharSequence text, Effects effects, ViewGroup viewGroup, Configuration configuration) {
        if ((activity == null) || (text == null) || (configuration == null)) {
            throw new IllegalArgumentException(NULL_PARAMETERS_ARE_NOT_ACCEPTED);
        }
        isDefault=false;
        this.activity = activity;
        this.text = text;
        this.effects = effects;
        this.viewGroup = viewGroup;
        this.configuration = configuration;
        init(effects);
    }

    private void init(Effects effects){
        this.iconDrawable=null;
        this.iconRes=0;
    }
    public static NiftyNotificationView build(Activity activity, CharSequence text, Effects effects, int viewGroupResId) {
        return new NiftyNotificationView(activity, text, effects, (ViewGroup) activity.findViewById(viewGroupResId));
    }

    public static NiftyNotificationView build(Activity activity, CharSequence text, Effects effects, int viewGroupResId, Configuration configuration) {
        return new NiftyNotificationView(activity, text, effects, (ViewGroup) activity.findViewById(viewGroupResId), configuration);
    }


    public long getInDuration() {
        return effects.getAnimator().getDuration();
    }

    public long getOutDuration() {
        return effects.getAnimator().getDuration();
    }

    public long getDispalyDuration() {
        return this.configuration.dispalyDuration;
    }

    public Effects getEffects() {
        return effects;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    Activity getActivity() {
        return activity;
    }

    boolean isShowing() {
        return (null != activity) && isNotifyViewNotNull();
    }

    private boolean isNotifyViewNotNull() {

        return (null != notifyView) && (null != notifyView.getParent());

    }

    void detachActivity() {
        activity = null;
    }

    void detachViewGroup() {
        viewGroup = null;
    }

    ViewGroup getViewGroup() {
        return viewGroup;
    }

    View getView() {

        if (null == this.notifyView) {
            initializeNotifyView();
        }

        return notifyView;
    }

    private void initializeNotifyView() {
        if (this.activity!=null) {

            this.notifyView = initializeCroutonViewGroup();

            RelativeLayout contentView = initializeContentView();
            this.notifyView.addView(contentView);
        }
    }

    private FrameLayout initializeCroutonViewGroup() {

        FrameLayout notifyView = new FrameLayout(this.activity);

        if (null != onClickListener) {
            notifyView.setOnClickListener(onClickListener);
        }

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        notifyView.setLayoutParams(lp);


        return notifyView;
    }

    private RelativeLayout initializeContentView() {

        RelativeLayout contentView = new RelativeLayout(this.activity);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(lp);


        ImageView image = null;
        if ((null != iconDrawable) || (0 != iconRes)) {
            image = initializeImageView();
            contentView.addView(image, image.getLayoutParams());
        }

        TextView text = initializeTextView();

        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        if (null != image) {
            textParams.addRule(RelativeLayout.RIGHT_OF, image.getId());
        }

        textParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        contentView.addView(text, textParams);
        return contentView;
    }

    private TextView initializeTextView() {
        int padding = px2dip(this.configuration.textPadding);
        int viewHeight = px2dip(this.configuration.viewHeight);
        TextView text = new TextView(this.activity);
        text.setMaxHeight(viewHeight);
        text.setMaxHeight(viewHeight);
        text.setId(TEXT_ID);
        text.setText(this.text);
        text.setMaxLines(this.configuration.textLines);
        text.setEllipsize(TextUtils.TruncateAt.END);
        text.setPadding(padding*2, padding, padding*2, padding);
        text.setTextColor(Color.parseColor(this.configuration.textColor));
        text.setBackgroundColor(Color.parseColor(this.configuration.backgroundColor));

        if ((null != iconDrawable) || (0 != iconRes)) {
            text.setMinHeight(viewHeight);
            text.setGravity(isDefault?Gravity.CENTER_VERTICAL:this.configuration.textGravity);
        }else {
            text.setGravity(isDefault?Gravity.CENTER:this.configuration.textGravity);
        }
        return text;
    }
    private ImageView initializeImageView() {
        int maxValue=px2dip(this.configuration.viewHeight);
        ImageView image = new ImageView(this.activity);
        image.setMinimumHeight(maxValue);
        image.setMinimumWidth(maxValue);
        image.setMaxWidth(maxValue);
        image.setMaxHeight(maxValue);
        image.setId(IMAGE_ID);
        image.setBackgroundColor(Color.parseColor(this.configuration.iconBackgroundColor));
        image.setAdjustViewBounds(true);
        image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        if (null != iconDrawable) {
            image.setImageDrawable(iconDrawable);
        }

        if (iconRes!= 0) {
            image.setImageResource(iconRes);
        }

        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        imageParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        imageParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

        image.setLayoutParams(imageParams);
        return image;
    }

    public int px2dip(float pxValue) {
        final float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (pxValue * scale + 0.5f);
    }
    /*******************Call these methods************************/


    public NiftyNotificationView setIcon(Drawable iconDrawable){
        this.iconDrawable=iconDrawable;
        return this;
    }

    public NiftyNotificationView setIcon(int iconRes){
        this.iconRes=iconRes;
        return this;
    }

    public NiftyNotificationView setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public void show() {

        show(true);
    }
    public void show(boolean repeat) {

        Manager.getInstance().add(this,repeat);
    }

    public void showSticky() {

        Manager.getInstance().addSticky(this);
    }
    //only remove sticky notification
    public void removeSticky(){
        Manager.getInstance().removeSticky();
    }

    public void hide() {

        Manager.getInstance().removeNotify(this);
    }


}
