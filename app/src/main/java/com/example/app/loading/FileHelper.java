package com.example.app.loading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * 想缓存图片等比较耗空间的文件，推荐放在getExternalCacheDir()
 * 当系统版本大于等于4.4时，对通过上面4个API调用得到的目录进行文件的读写操作
 * 不需要申请SD卡的读写权限，所以6.0及以上系统使用时也不需要动态申请读写权限
 */
public class FileHelper {
    private static final String TAG = "file";
//    清除缓存：将外部私有数据下的cache包（/storage/emulated/0/Android/data/包名/cache）清除，将内部数据下的cache包下的内容（/data/data/包名/cache/XXX）清除 。
//    清楚数据：将外部私有数据包（/storage/emulated/0/Android/data/包名）清除，将内部数据下的所有内容（/data/data/包名/XXX）清除；
//    FileHelper.getExternalCacheDirectory(this,"shitou05");外部
//        FileHelper.getInternalCacheDirectory(this,"shitou099");内部
//        FileHelper.getCacheDirectory(this,"img");
//        FileHelper.getFilePath(this,"9999");

/*
    String fileName = generalFileName();
    //type为null为cache文件夹
    File file = new File(FileHelper.getExternalCacheDirectory(getApplicationContext(),null), fileName);
    ///storage/emulated/0/Android/data/com.example.app.loading/files/vocie/b534e80f-dfeb-4922-a0fc-57aafd802ca4.mp3
        Log.d(TAG,file.getAbsolutePath());
        Log.d(TAG,file.getName());
        if (!file.exists()){
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
//        Date now = new Date(System.currentTimeMillis());
//        String fileName ="shitou333.txt";
//        final String fullPath = (file == null ? defaultDir : dir) + fileName;
//        if (!createOrExistsFile(fullPath)) return;
//        if (!createOrExistsFile(file.getAbsolutePath())) return;

    /**
     * 内部存储
     * 1.context.getCacheDir()----->/data/data/com.kay.example/cache
     * 2.context.getFileDir()------>/data/data/com.kay.example/files
     * 3.context.getDir()---------->/data/data/com.kay.example
     */

    /**
     * 外部存储 External
     * 1.外部公有（要权限）---->Environment访问
     * 外部存储的根目录----->Environment.getExternalStorageDirectory()*****可以自己指定目录
     * 外部存储的公共目录--->Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) /sdcard/Movies
     * 2.外部私有----->context访问
     *         context.getExternalCacheDir()------>/sdcard/Android/data/com.example.kay/cache
     *         context.getExternalFilesDir ------->/sdcard/Android/data/com.example.kay/files
     */
//外部存储的公共目录----->更加方便的访问Android给我们提供好的一些公共目录的方法
    @SuppressLint("SimpleDateFormat")
    private static final Format FORMAT = new SimpleDateFormat("MM-dd HH-mm-ss");

    public static String getDownloadApkCachePath() {
        String appCachePath = null;
        if (checkSDCard()) {
            appCachePath = Environment.getExternalStorageDirectory() + "/AllenVersionPath/" ;
        } else {
            appCachePath = Environment.getDataDirectory().getPath() + "/AllenVersionPath/" ;
        }
        File file = new File(appCachePath);
        if (!file.exists()) {
//			mkdir方法是只创建一个。
//			mkdirs方法是创建多个。
            //mkdirs方法是无论父文件夹是否存在都会创建。
            file.mkdirs();
            //mkdir方法是用于创建最后一个/后面的文件夹，最后一个/前面的文件夹必须都存在。这是才会创建成功。否则会创建失败。
//			file.mkdir();
        }
        return appCachePath;
    }

    /**
     * 创建文件（默认外部存储）
     * @param context
     * @param dir
     * @return
     */
    public static String getFilePath(Context context,String dir) {
        String directoryPath = "";
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            //外部存储
            directoryPath = context.getExternalFilesDir(dir).getAbsolutePath();
        } else {//没外部存储就使用内部存储
            directoryPath = context.getFilesDir() + File.separator + dir;
        }

        File file = new File(directoryPath);
        if (!file.exists()) {//判断文件目录是否存在
            file.mkdirs();
        }
        return directoryPath;
    }

    /**
     * 创建一个文件
     * @param filePath
     * @return false返回成功
     */
    private static boolean createOrExistsFile(final String filePath) {
        File file = new File(filePath);
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /**
     * 随机生成文件的名称
     * @return
     */
    private String generalFileName() {
        return UUID.randomUUID().toString() + ".mp3";
    }

    /**
     * 检查外部存储器是否可用来读取和写入
     * @return
     */
    public static boolean checkSDCard() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public File getAlbumStorageDir(String albumName) {
        // 获取用户公共图片目录的目录
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.d(TAG, "Directory not created");
        }
        return file;
    }

    /**
     * 检查外部存储器是否可以至少读取
     * @return
     */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 获取应用专属缓存目录---->不会污染用户存储空间
     * android 4.4及以上系统不需要申请SD卡读写权限
     * 因此也不用考虑6.0系统动态申请SD卡读写权限问题，切随应用被卸载后自动清空
     * @param context 上下文
     * @param type 文件夹类型 可以为空，为空则返回API得到的一级目录
     * @return 缓存文件夹 如果没有SD卡或SD卡有问题则返回内存缓存目录，否则优先返回SD卡缓存目录
     */
    public static File getCacheDirectory(Context context, String type) {
        File appCacheDir = getExternalCacheDirectory(context,type);
        if (appCacheDir == null){
            appCacheDir = getInternalCacheDirectory(context,type);
        }

        if (appCacheDir == null){
            Log.e(TAG,"getCacheDirectory fail ,the reason is mobile phone unknown exception !");
        }else {
            if (!appCacheDir.exists()&&!appCacheDir.mkdirs()){
                Log.e(TAG,"getCacheDirectory fail ,the reason is make directory fail !");
            }
        }
        return appCacheDir;
    }

    /**
     * 获取SD卡缓存目录
     * @param context 上下文
     * @param type 文件夹类型 如果为空则返回 /storage/emulated/0/Android/data/app_package_name/cache
     *             否则返回对应类型的文件夹如Environment.DIRECTORY_PICTURES 对应的文件夹为 .../data/app_package_name/files/Pictures
     * {@link android.os.Environment#DIRECTORY_MUSIC},
     * {@link android.os.Environment#DIRECTORY_PODCASTS},
     * {@link android.os.Environment#DIRECTORY_RINGTONES},
     * {@link android.os.Environment#DIRECTORY_ALARMS},
     * {@link android.os.Environment#DIRECTORY_NOTIFICATIONS},
     * {@link android.os.Environment#DIRECTORY_PICTURES}, or
     * {@link android.os.Environment#DIRECTORY_MOVIES}.or 自定义文件夹名称
     * @return 缓存目录文件夹 或 null（无SD卡或SD卡挂载失败）
     */
    public static File getExternalCacheDirectory(Context context,String type) {
        File appCacheDir = null;
        if( Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (TextUtils.isEmpty(type)){
                appCacheDir = context.getExternalCacheDir();
            }else {
                appCacheDir = context.getExternalFilesDir(type);
            }

            if (appCacheDir == null){// 有些手机需要通过自定义目录
                appCacheDir = new File(Environment.getExternalStorageDirectory(),"Android/data/"+context.getPackageName()+"/cache/"+type);
            }

            if (appCacheDir == null){
                Log.e(TAG,"getExternalDirectory fail ,the reason is sdCard unknown exception !");
            }else {
                if (!appCacheDir.exists()&&!appCacheDir.mkdirs()){
                    Log.e(TAG,"getExternalDirectory fail ,the reason is make directory fail !");
                }
            }
        }else {
            Log.e(TAG,"getExternalDirectory fail ,the reason is sdCard nonexistence or sdCard mount fail !");
        }
        return appCacheDir;
    }


    /**
     * 获取内存缓存目录
     * @param type 子目录，可以为空，为空直接返回一级目录
     * @return 缓存目录文件夹 或 null（创建目录文件失败）
     * 注：该方法获取的目录是能供当前应用自己使用，外部应用没有读写权限，如 系统相机应用
     */
    public static File getInternalCacheDirectory(Context context,String type) {
        File appCacheDir = null;
        if (TextUtils.isEmpty(type)){
            appCacheDir = context.getCacheDir();// /data/data/app_package_name/cache
        }else {
            appCacheDir = new File(context.getFilesDir(),type);// /data/data/app_package_name/files/type
        }

        if (!appCacheDir.exists()&&!appCacheDir.mkdirs()){
            Log.e(TAG,"getInternalDirectory fail ,the reason is make directory fail !");
        }
        return appCacheDir;///data/user/0/com.example.app.loading/files/shitou099
    }
/*
    private AppUtils() {
        throw new Error("Do not need instantiate!");
    }
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri;
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            uri= VersionFileProvider.getUriForFile(context,context.getPackageName()+".versionProvider",file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }else{
            uri=Uri.fromFile(file);
        }
        intent.setDataAndType(uri,
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
*/
}