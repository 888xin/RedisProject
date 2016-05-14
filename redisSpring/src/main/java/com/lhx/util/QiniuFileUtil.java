package com.lhx.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
* Created by lhx on 15-11-11 下午4:27
*
* @Description
*/
public class QiniuFileUtil {

    protected static Logger LOG = LoggerFactory.getLogger(QiniuFileUtil.class);

    private static final String ACCESS_KEY = "-vqhfR2K9C1GhyBoMHlheiVsKzJ1xV6dOAGa3xYk";
    private static final String SECRET_KEY = "XD55KRxhw03g7WxtT49yaT8jwHUdmUBVZVgeMiPn";
    private static final String BUCKET_NAME = "xin8";

    private static final String returnBody = "{\"key\": $(key), \"hash\": $(etag), \"width\": $(imageInfo.width), \"height\": $(imageInfo.height)}";

    private static final class InstanceHolder {
        private static QiniuFileUtil INSTANCE = new QiniuFileUtil();
    }

    public static QiniuFileUtil getInstance() {
        return InstanceHolder.INSTANCE;
    }

    //文件上传
    public String uploadPhotoByFile(String imageKey, File file)  {
        try {
            UploadManager uploadManager = new UploadManager();
            Response res = uploadManager.put(file, imageKey, getUpToken());
            return res.bodyString() ;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时简单状态信息
            LOG.error(r.toString(),e);
            try {
                // 响应的文本信息
                LOG.error(r.bodyString());
                return r.bodyString() ;
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return null ;
    }

    //字节上传
    public String uploadPhotoByBytes(String imageKey, byte[] data)  {
        try {
            UploadManager uploadManager = new UploadManager();
            Response res = uploadManager.put(data, imageKey, getUpToken());
            return res.bodyString() ;
            // Ret ret = res.jsonToObject(Ret.class);
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时简单状态信息
            LOG.error(r.toString(),e);
            try {
                // 响应的文本信息
                LOG.error(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return null ;
    }

    //删除
    public void deletePhotoByFile(String imageKey){
        BucketManager bucketManager = getBucketManager();
        try {
            bucketManager.delete(BUCKET_NAME, imageKey);
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时简单状态信息
            LOG.error(r.toString(),e);
            try {
                // 响应的文本信息
                LOG.error(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }

    private static String getUpToken(){
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        return auth.uploadToken(BUCKET_NAME, null, 3600, new StringMap().putNotEmpty("returnBody", returnBody));
    }

    private static BucketManager getBucketManager(){
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        return new BucketManager(auth);
    }

    public static interface ImageCategory {

        final static String GOODS = "goods/00/";
        final static String AVATAR = "avatar/00/";

    }

//    public static void main(String[] args) {
//        String path="C:\\Users\\Lifeix\\Desktop\\图片\\football.jpg";
//        File file = new File(path);
//        String str = QiniuFileUtil.getInstance().uploadPhotoByFile(ImageCategory.GOODS + "001.jpg",file);
//        System.out.println(str);
//
//        QiniuFileUtil.getInstance().deletePhotoByFile(ImageCategory.GOODS + "001.jpg");
//    }


//    public static void main(String[] args) throws AuthException, FileNotFoundException {
//        String path = "C:\\Users\\Lifeix\\Desktop\\图片\\TB2aqDviVXXXXcbXXXXXXXXXXXX_!!16225271.jpg";
//        String path = "C:\\Users\\Lifeix\\Desktop\\图片\\chx_c.png";
//        File file = new File(path);
//        InputStream in = new FileInputStream(file);
//        String str = QiniuFileUtil.getInstance().uploadPhotoByFile(file, "jpg", 1,ImageCategory.GOODS);
//        String str = QiniuFileUtil.getInstance().uploadPhotoByInputStream(in, "jpg", 1,ImageCategory.GOODS);
//        System.out.println(str);
//
//        QiniuFileUtil.getInstance().deletePhotoByFile(ImageCategory.GOODS + "001.jpg");
//    }

//    public String getUploadToken(String bucketName, Integer num, Integer mimeType) {
//	Form queryParam = new Form();
//	queryParam.add("bucketName", bucketName);
//	queryParam.add("mime_type", mimeType);
//	queryParam.add("num", num);
//	WebResource resource = client.resource("http://newbj.upload.l99.com/" + URI_UPLOAD_TOKEN);
//	return resource.queryParams(queryParam).get(String.class);
//    }

}
