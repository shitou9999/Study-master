package chatqq.home.com.app2.utils;

/**
 * @ClassName: GDLocationUtil
 * @Description: 高德地图定位工具类
 * 定位功能初始化设置时耗时较多，如果放在Activity中操作，难免会有Activity切换较慢的问题。
 * 通过该工具类，可以实现一次初始化，多处随时调用。具有占用资源少，使用方便，便于复用，速度快，
 * 可根据不同需求获取不同结果的优点。
 * 通常来说单独的定位功能是用于确定用户所在城市、位置，仅作显示并通过上传位置信息对用户提供相应周边服务用，
 * 因此无需重复定位。重复定位耗电量较多且大多无实际作用，因此此处只做单次定位的处理。
 */
public class GDLocationUtil {
//    private static AMapLocationClient mlocationClient;
//    public static AMapLocationClientOption mLocationOption = null;
//    public static AMapLocation sLocation = null;
//    /**
//     * @param context
//     * @Title: init
//     * @Description: 初始化地图导航，在Application onCreate中调用，只需调用一次
//     */
//    public static void init(Context context) {
//        //初始化定位
//        mlocationClient = new AMapLocationClient(context);
//        // 初始化定位参数
//        mLocationOption = new AMapLocationClientOption();
//        // 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式.SDK默认选择使用高精度定位模式
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        mLocationOption.setInterval(2000);
//        //该方法默认为false。
////        mLocationOption.setOnceLocation(true);
//        mlocationClient.setLocationOption(mLocationOption);
//        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
//        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
//        // 在定位结束后，在合适的生命周期调用onDestroy()方法
//        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//    }
//    /**
//     * @Description: 定位结果回调
//     */
//    public interface MyLocationListener {
//        public void result(AMapLocation location);
//    }
//    /**
//     * @param listener
//     * @Title: getLocation
//     * @Description: 获取位置，如果之前获取过定位结果，则不会重复获取
//     */
//    public static void getLocation(MyLocationListener listener) {
//        if (sLocation == null) {
//            getCurrentLocation(listener);
//        } else {
//            listener.result(sLocation);
//        }
//    }
//    /**
//     * @param listener
//     * @Title: getCurrentLocation
//     * @Description: 获取位置，重新发起获取位置请求
//     */
//    public static void getCurrentLocation(final MyLocationListener listener) {
//        if (mlocationClient == null) {
//            return;
//        }
//        mlocationClient.setLocationListener(location -> {
//            if (location != null) {
//                if (location.getErrorCode() == 0) {
//                    //定位成功，取消定位
//                    mlocationClient.stopLocation();
//                    sLocation = location;
//                    listener.result(location);
//                }
//            } else {
//                //获取定位数据失败
//            }
//        });
//        mlocationClient.startLocation();
//    }
//    /**
//     * @Title: destroy
//     * @Description: 销毁定位，必须在退出程序时调用，否则定位会发生异常
//     */
//    public static void destroy() {
//        mlocationClient.onDestroy();
//    }
//    // 获取之前定位位置，如果之前未曾定位，则重新定位
//        GDLocationUtil.getLocation(new MyLocationListener() {
//
//        @Override
//        public void result(AMapLocation location) {
//            //针对location进行相关操作，如location.getCity()，无需验证location是否为null;
//        }
//    });
//    // 获取当前位置，无论是否定位过，重新进行定位
//        GDLocationUtil.getCurrentLocation(new MyLocationListener() {
//
//        @Override
//        public void result(AMapLocation location) {
//            //针对location进行相关操作，如location.getCity()，无需验证location是否为null;
//        }
//    });
//
}