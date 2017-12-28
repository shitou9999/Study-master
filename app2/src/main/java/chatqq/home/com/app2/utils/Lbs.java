package chatqq.home.com.app2.utils;

import android.content.Context;

public class Lbs {
//	private AMapLocationClient locationClient = null;
//	private AMapLocationClientOption locationOption = null;
//	private Context lbsContext;
//	static final String TAG = "UCPOS_LBS";


//implements AMapLocationListener
//	@Override
//	public void onLocationChanged(AMapLocation location) {
//		if (location != null) {
//			if (location.getErrorCode() == 0) {
//				if (lbs != null) {
//					setRetAMapLocation(location);
//					if (msgHandler != null) {
//						Message msg = msgHandler.obtainMessage();
//						msg.what = Constant.LOC_OK;
//						msgHandler.sendMessage(msg);
//						Log.d("shitou","定位成功");
//					}
//				}
//			}
//		}
//	}


	public Lbs(Context context) {
//		lbsContext = context;
	}

	/**
	 * 销毁定位
	 */
	// 根据控件的选择，重新设置定位参数
//	private void initOption() {
//		// 设置是否需要显示地址信息
//		locationOption.setOnceLocation(true);
//		locationOption.setNeedAddress(true);
//		/**
//		 * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位 注意：只有在高精度模式下的单次定位有效，其他方式无效
//		 */
//		locationOption.setGpsFirst(true);
//		locationOption.setInterval(2000);
//	}
//
//	public void setLocationListener(AMapLocationListener listener) {
//		locationClient.setLocationListener(listener);
//	}
//
//	public void stopLocation() {
//		if (null != locationClient) {
//			locationClient.stopLocation();
//			locationClient.onDestroy();
//			locationClient = null;
//			locationOption = null;
//		}
//	}
//
//	public void startLocation() {
//		stopLocation();
//		locationClient = new AMapLocationClient(lbsContext);
//		locationOption = new AMapLocationClientOption();
//
//		// 设置定位模式为高精度模式
//		locationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
	/**context转为监听*/
//		setLocationListener((AMapLocationListener) lbsContext);
//		initOption();
//		locationClient.setLocationOption(locationOption);
//		// 启动定位
//		locationClient.startLocation();
//	}

}
