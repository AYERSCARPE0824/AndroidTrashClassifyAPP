package com.car;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.car.client.SocketClient;
import com.car.information.NecessaryInformation;
import com.car.pojo.EnvironmentInfo;
import com.car.request.EnvironmentInfoRequest;
import com.car.request.PicRecRequest;
import com.car.request.WordRecRequest;
import com.car.view.ViewPagerSlide;
import com.google.gson.Gson;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SpeechClassificationActivity extends AppCompatActivity {
    public static final int TAKE_POTHO=1;
    private ImageView imageView;
    private Button button;
    private ViewPagerSlide viewPager;
    private Uri uri;

    private TextView temp;
    private TextView humidity;
    private TextView ultra_distance;
    private TextView tv_result;
    private TextView tv_word;
    private EditText et_host_rec;

    public Bitmap bitmap;

    //所需申请的权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.INTERNET,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA
    };

    public void setHost(View view) {
        et_host_rec = (EditText) findViewById(R.id.et_host_rec);
        NecessaryInformation.host = et_host_rec.getText().toString();
        Toast.makeText(SpeechClassificationActivity.this,"设置成功",Toast.LENGTH_SHORT).show();
    }

    public static class AudioUtils {
        private static AudioUtils audioUtils;
        private SpeechSynthesizer mySynthesizer;
        public AudioUtils(){

        }

        public static AudioUtils getInstance() {
            if (audioUtils == null) {
                synchronized (AudioUtils.class) {
                    if (audioUtils == null) {
                        audioUtils = new AudioUtils();
                    }
                }
            }
            return audioUtils;
        }

        private InitListener myInitListener = new InitListener() { @Override public void onInit(int code) {  } };

        public void init(Context context) {
            mySynthesizer = SpeechSynthesizer.createSynthesizer(context, myInitListener);
            mySynthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
            //发音人xiaoyan
            mySynthesizer.setParameter(SpeechConstant.SPEED,"50");
            //语速50
            mySynthesizer.setParameter(SpeechConstant.PITCH, "50");
            //语调50
            mySynthesizer.setParameter(SpeechConstant.VOLUME, "100");
            //音量100
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_classification);
        applypermission();//申请app所需要用到的权限
        // 将“xxxxxx”替换成您申请的APPID，申请地址：http://www.xfyun.cn
        // 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=914d6187");

        imageView=(ImageView)findViewById(R.id.picture);
        button=(Button)findViewById(R.id.btn_takephoto);
        button.setOnClickListener(new View.OnClickListener() {
            //拍照
            @Override
            public void onClick(View view) {
                File outImage=new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if(outImage.exists())
                    {
                        outImage.delete();
                    }
                    outImage.createNewFile();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24)
                {
                    uri= FileProvider.getUriForFile(SpeechClassificationActivity.this,"com.example.gdzc.cameraalbumtest.fileprovider",outImage);
                }
                else
                {
                    uri=Uri.fromFile(outImage);
                }
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(intent,TAKE_POTHO);
            }
        });
    }
    //显示图片
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_POTHO:
                if (resultCode == RESULT_OK) {
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 4;
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri),null,options);
//                        String filepath = uri.getPath();
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
    //定义判断权限申请的函数，在onCreate中调用就行
    public void applypermission(){
        if(Build.VERSION.SDK_INT>=23){
            boolean needapply=false;
            for(int i=0;i<PERMISSIONS_STORAGE.length;i++){
                int chechpermission= ContextCompat.checkSelfPermission(getApplicationContext(),
                        PERMISSIONS_STORAGE[i]);
                if(chechpermission!= PackageManager.PERMISSION_GRANTED){
                    needapply=true;
                }
            }
            if(needapply){
                ActivityCompat.requestPermissions(SpeechClassificationActivity.this,PERMISSIONS_STORAGE,1);
            }
        }
    }
    //重载用户是否同意权限的回调函数，进行相应处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int i=0;i<grantResults.length;i++){      //检查权限是否获取
            if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                //同意后的操作
                Toast.makeText(SpeechClassificationActivity.this, permissions[i]+"已授权",Toast.LENGTH_SHORT).show();//提示
            }
            else {
                //不同意后的操作
                Toast.makeText(SpeechClassificationActivity.this,permissions[i]+"拒绝授权",Toast.LENGTH_SHORT).show();//提示

            }
        }
    }
    public class use{
        /**
         *@TODO 科大讯飞语音听写
         * linowl 2020.09.11
         */
        public void initSpeech(final Context context) {
            //1.创建RecognizerDialog对象
            RecognizerDialog mDialog = new RecognizerDialog(context, null);
            //识别器定义
            final TextView txtdisplay = (TextView) findViewById(R.id.tv_word);
            //定义局部变量txtdisplay TextView标签 id为textView
            //2.设置accent、language等参数
            mDialog.setParameter(SpeechConstant.DOMAIN,"iat");
            //应用领域  iat：日常用语   medical：医疗
            mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            //语言 zh_cn：中文 en_us：英文 ja_jp：日语 ko_kr：韩语 ru-ru：俄语 fr_fr：法语 es_es：西班牙语
            mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
            //方言  默认mandarin
            //3.设置回调接口
            mDialog.setListener(new RecognizerDialogListener() {
                @Override
                public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                    if (!isLast) {
                        //解析语音
                        //返回的result为识别后的汉字,直接赋值到TextView上即可
                        String result = parseVoice(recognizerResult.getResultString());
                        // Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                        NecessaryInformation.result_recognition = String.copyValueOf(result.toCharArray());
                        txtdisplay.setText(result);
                        //识别成功在textView显示
                    }
                }
                @Override
                public void onError(SpeechError speechError) {
                }
            });
            //4.显示dialog，接收语音输入
            mDialog.show();
        }
    }
    /**
     * 解析语音json
     */
    public String parseVoice(String resultString) {
        Gson gson = new Gson();
        Voice voiceBean = gson.fromJson(resultString, Voice.class);

        StringBuffer sb = new StringBuffer();
        ArrayList<Voice.WSBean> ws = voiceBean.ws;
        for (Voice.WSBean wsBean : ws) {
            String word = wsBean.cw.get(0).w;
            sb.append(word);
        }
        return sb.toString();
    }
    /**
     * 语音实体类
     */
    public class Voice {

        public ArrayList<WSBean> ws;

        public class WSBean {
            public ArrayList<CWBean> cw;
        }

        public class CWBean {
            public String w;
        }
    }
    //调用use类的initSpeech函数
    public void Recognition(View view){
        new use().initSpeech(this);
    }
    //图片识别
    public void pic_Rec(View view) {
        if (bitmap == null) {
            Toast.makeText(SpeechClassificationActivity.this,"请拍照",Toast.LENGTH_SHORT).show();
            return;
        }
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bout);
        byte[] bytes = bout.toByteArray();
        PicRecRequest picRecRequest = new PicRecRequest(bytes);
        NecessaryInformation.picRecStatus = false;
        SocketClient socketClient = new SocketClient(picRecRequest);
        socketClient.start();
        try {
            new Thread().sleep(1800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(NecessaryInformation.picRecStatus==false||NecessaryInformation.recResult==null){
            Toast.makeText(SpeechClassificationActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
        } else if(!NecessaryInformation.recResult.isRes()){
            tv_result = (TextView) findViewById(R.id.tv_result);
            tv_result.setText("未查询到该垃圾对应分类");
        } else {
            Toast.makeText(SpeechClassificationActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
            tv_result = (TextView) findViewById(R.id.tv_result);
            tv_result.setText(NecessaryInformation.recResult.getClassName());
        }
    }
    //语音识别
    public void word_Rec(View view) {
        tv_word = (TextView) findViewById(R.id.tv_word);
        String word = tv_word.getText().toString();
        WordRecRequest wordRecRequest = new WordRecRequest(word);
        NecessaryInformation.wordRecStatus = false;
        SocketClient socketClient = new SocketClient(wordRecRequest);
        socketClient.start();
        try {
            new Thread().sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (NecessaryInformation.wordRecStatus==false||NecessaryInformation.recResult==null){
            Toast.makeText(SpeechClassificationActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
        } else if(!NecessaryInformation.recResult.isRes()){
//            Toast.makeText(SpeechClassificationActivity.this,"未查询到该垃圾对应分类",Toast.LENGTH_SHORT).show();
            tv_result = (TextView) findViewById(R.id.tv_result);
            tv_result.setText("未查询到该垃圾对应分类");
        } else {
            Toast.makeText(SpeechClassificationActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
            tv_result = (TextView) findViewById(R.id.tv_result);
            tv_result.setText(NecessaryInformation.recResult.getClassName());
        }
    }
    //显示环境参数
    public void refresh(View view) {

        temp = (TextView) findViewById(R.id.temp);
        humidity = (TextView) findViewById(R.id.humidity);
        ultra_distance = (TextView) findViewById(R.id.ultra_distance);

        NecessaryInformation.environmentInfoStatus = false;
        EnvironmentInfoRequest environmentInfoRequest = new EnvironmentInfoRequest();
        SocketClient socketClient = new SocketClient(environmentInfoRequest);
        socketClient.start();

        try {
            new Thread().sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (NecessaryInformation.environmentInfoStatus==false||NecessaryInformation.environmentInfoResult==null){
            Toast.makeText(SpeechClassificationActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SpeechClassificationActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
            EnvironmentInfo environmentInfo = NecessaryInformation.environmentInfoResult.getEnvironmentInfo();
            temp.setText(String.valueOf(environmentInfo.getTemp()));
            humidity.setText(String.valueOf(environmentInfo.getHumidity()));
            ultra_distance.setText(String.valueOf(environmentInfo.getUltra_distance()));
        }
    }
    //跳转到地图页面
    public void goToStationMap(View view) {
        Intent intent = new Intent();
        intent.setClass(SpeechClassificationActivity.this, StationMapActivity.class);
        startActivity(intent);
    }
}