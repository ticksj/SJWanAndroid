package com.foxhis.c_network.response;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.foxhis.c_network.exception.HttpException;
import com.foxhis.c_network.listener.FileDownLoadListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.foxhis.c_network.ConstantValue.FAIL_IO;
import static com.foxhis.c_network.ConstantValue.FAIL_NETWORK;
import static com.foxhis.c_network.ConstantValue.FAIL_OTHER;


public class CommonFileCallback implements Callback {

    private static final int PROGRESS_MESSAGE = 0x01;
    private Handler handler;
    private FileDownLoadListener fileDownLoadListener;
    private String mFilePath;
    private int mProgress;

    public CommonFileCallback(FileDownLoadListener listener,String filePath) {
        fileDownLoadListener = listener;
        mFilePath = filePath;
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case PROGRESS_MESSAGE:
                        fileDownLoadListener.onProgress((int) msg.obj);
                        break;
                }
            }
        };
    }

    @Override
    public void onFailure(final Call call, final IOException ioexception) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                fileDownLoadListener.onFailure(new HttpException(FAIL_NETWORK, ioexception.toString()));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final File file = handleResponse(response);
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (file != null) {
                    fileDownLoadListener.onSuccess(file);
                } else {
                    fileDownLoadListener.onFailure(new HttpException(FAIL_IO, ""));
                }
            }
        });
    }

    /**
     * 此时还在子线程中，不则调用回调接口
     *
     * @param response
     * @return
     */
    private File handleResponse(Response response) {
        if (response == null) {
            return null;
        }
        if (TextUtils.isEmpty(mFilePath)) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    fileDownLoadListener.onFailure(new HttpException(FAIL_OTHER,"本地存储路径为空"));
                }
            });
            return null;
        }
        InputStream inputStream = null;
        File file = null;
        FileOutputStream fos = null;
        byte[] buffer = new byte[2048];
        int length;
        int currentLength = 0;
        double sumLength;
        try {
            checkLocalFilePath(mFilePath);
            file = new File(mFilePath);
            fos = new FileOutputStream(file);
            inputStream = response.body().byteStream();
            sumLength = (double) response.body().contentLength();

            while ((length = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
                currentLength += length;
                mProgress = (int) (currentLength / sumLength * 100);
                handler.obtainMessage(PROGRESS_MESSAGE, mProgress).sendToTarget();
            }
            fos.flush();
        } catch (Exception e) {
            file = null;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (inputStream != null) {

                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private void checkLocalFilePath(String localFilePath) {
        File path = new File(localFilePath.substring(0,
                localFilePath.lastIndexOf("/") + 1));
        File file = new File(localFilePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}