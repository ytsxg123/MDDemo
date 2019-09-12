SDK导入
#SDK导入
 ```implementation files('libs/meta-1.4.1.aar')```
工程配置

添加okHttp、greendao的支持

    dependencies{
        ...
        //注意：使用 `3.12.0` 以下的版本，否在不兼容`5.0`以下的设备
        implemention 'com.squareup.okhttp3:okhttp:3.12.0'
        //grendao
        implementation 'org.greenrobot:greendao:3.2.2'
    }
    

添加Java 8的支持

    android{
        ...
            compileOptions {
            sourceCompatibility JavaVersion.VERSION_1_8
            targetCompatibility JavaVersion.VERSION_1_8
        }
        ...
    }

AndroidManifest.xml配置appId和内容提供者

    <?xml version="1.0" encoding="utf-8"?>
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
              package="a.b.c">
        <application>
             //配置appId
            <meta-data
                android:name="META_GG_APP_ID"
                android:value="您的appId"/>
             //添加内容提供者
            <provider
        		android:exported="false"
        		android:grantUriPermissions="true"
        		android:authorities="您的applicationId.MetaProvider"
        		android:name="com.meta.video.adplatform.MetaFileProvider">
        			<meta-data
            			android:name="android.support.FILE_PROVIDER_PATHS"
            			android:resource="@xml/file_provider"/>
    		</provider>
        </application>
    </manifest>

file_privider配置

    <?xml version="1.0" encoding="utf-8"?>
    <paths xmlns:android="http://schemas.android.com/apk/res/android">
        <external-path
            name="apk_external_path"
            path="MetaCache/"/>
    </paths>

SDK常用Api调用

初始化SDK

    MetaADClient.init(Context context,boolean debug)
    //注册回调接口，此接口不注册，会有部分回调回调不到
    MetaADClient.getInstance().registerRewardVideoCallback(MetaRewardVideoAdListener);

视频回调接口

    public interface MetaRewardVideoAdListener {
     
        /**
         * 缓存池数量增加
         */
        int TYPE_INSERT = 1;
        /**
         * 缓存池数量减少
         */
        int TYPE_REMOVE = 2;
     
        /**
         * @hide
         */
        @IntDef({TYPE_INSERT, TYPE_REMOVE})
        @Retention(RetentionPolicy.SOURCE)
        @interface ModeType {
        }
     
     
        /**
         * 加载广告成功
         */
        void onLoadAdSuccess();
     
        /**
         * 广告开始展示
         */
        void onAdShow();
     
        /**
         * 点击关闭按钮
         *
         */
        void onAdClose(MetaRewardCloseEntity entity);
     
        /**
         * 视频播放完成
         */
        void onVideoComplete();
     
        /**
         * 各种异常
         */
        void onError(AdError error);
     
        /**
         * 视频点击，一个视频只回调一次
        * @param entity 回调信息
         */
        void onVideoClick();
     
        /**
         * 每次广告缓存池里的广告变动都会回调这个接口，包括inert和remove
         *
         * @param currentCount 当前广告池里的数量
         * @param type 类型 {link #TYPE_INSERT 广告增加}  {link #TYPE_REMOVE广告减少}
         */
        void onAdCachePollCountChange(int currentCount, @ModeType int type);

设置是否静音播放

    /**
    * @return MetaVideoVolumeMode.SILENCE 静音  MetaVideoVolumeMode.NORMAL 正常播放
    */
    MetaVideoADConfig.getVideoADConfig.mRewardVoiceMode= ()-> return MetaVideoVolumeMode.SILENCE;

广告接口相关操作

是否有激励视频广告

    /**
     * 是否加载成功广告
     * @return true:广告加载成功
     */
    MetaADClient.getInstance().getFillAdManager().isLoadRewardAd();
    

填充激励视频广告

    AdRequest request=new AdRequest.Builder()
                    .unitId("代码位：unitId")
                    .build();
    MetaADClient.getInstance().getFillAdManager().loadRewardAd(request);
    

页面跳转

跳转到激励视频页面

    //MetaRewardVideoAdListener全局要使用同一个接口对象
    MetaADClient.getRouteManager.startRewardVideo(Activity activity,MetaRewardVideoAdListener listener);
    




