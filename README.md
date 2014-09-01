#NiftyNotification 

effects for android notifications.base on ([Crouton][1])

[![Android Arsenal](http://img.shields.io/badge/%20%20%20Android%20%20Arsenal%20%20%20-%20%20%20NiftyNotification%20%20%20-blue.svg)](http://android-arsenal.com/details/1/888)
[![Build Status](https://api.travis-ci.org/sd6352051/NiftyNotification.svg?branch=master)](https://travis-ci.org/sd6352051/NiftyNotification)


# ScreenShot

![Image][2]


# Usage
``` java
  NiftyNotificationView.build(this,msg, effect,R.id.mLyout)
      .setIcon(R.drawable.lion)    //You must call this method if you use ThumbSlider effect
      show();

```
#

```xml

<!-- ViewGroup-->
<RelativeLayout
      android:id="@+id/mLyout"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      >

</RelativeLayout>
```

# Configuration

``` java
Configuration cfg=new Configuration.Builder()
      .setAnimDuration(700)
      .setDispalyDuration(1500)
      .setBackgroundColor("#FFBDC3C7")
      .setTextColor("#FF444444")
      .setIconBackgroundColor("#FFFFFFFF")
      .setTextPadding(5)                      //dp
      .setViewHeight(48)                      //dp
      .setTextLines(2)                        //You had better use setViewHeight and setTextLines together
      .setTextGravity(Gravity.CENTER)         //only text def  Gravity.CENTER,contain icon Gravity.CENTER_VERTICAL
      .build();

NiftyNotificationView.build(this,msg, effect,R.id.mLyout,cfg)
      .setIcon(R.drawable.lion)               //remove this line ,only text
      .setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
      //add your code
      }
      })
      .show();

```

and copy this into your `libs` directory.
-   [`NineOldAndroid-2.4.0`](https://github.com/downloads/JakeWharton/NineOldAndroids/nineoldandroids-2.4.0.jar)

-   [`NiftyNotification-1.0`](https://github.com/sd6352051/NiftyNotification/blob/master/releases/niftynotification-1.0.jar?raw=true)

  
# Effects
`Flip`, `Jelly`, `Scale`, `SlideIn`, `SlideOnTop`, `Standard`, `ThumbSlider`

# License
Copyright 2014 gitonway.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


[1]: https://github.com/keyboardsurfer/Crouton
[2]: https://raw.githubusercontent.com/sd6352051/NiftyNotification/master/screenshot/ss.gif
