package news.feicui.com.cehuacaidan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.Log;
import android.util.Printer;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by mooon on 2016/7/20.
 */
public class NwetworkUtil {
    private RequestQueue requestQueue;
    public NwetworkUtil(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }
    public void  getStringResuit(String url, final TextView textView){
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                textView.setText(s);
                Log.d("debug", s + "联网获取成功！！！！！！！！！！！！");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("debug","联网获取失败失败失败！！！！！！！！！！！！");
            }
        });
        requestQueue.add(request);
    }
    public void setImagePic(final ImageView imageView, String url){
//        参数1 url  参数2  成功回调接口 参数3 最大宽度  参数4  最大高度
//         参数5  颜色制式   参数6     失败回调接口
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                     Bitmap newBitmap = createRonudCornerPicture(bitmap);
                     imageView.setImageBitmap(newBitmap);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                    imageView.setImageResource(R.mipmap.ic_launcher);
            }
        });
requestQueue.add(imageRequest);
    }

      public Bitmap createCriclePicture(Bitmap bm){
          Paint paint = new Paint();//画笔
          paint.setAntiAlias(true);//抗锯齿

          int min = Math.min(bm.getWidth(),bm.getHeight());
          Bitmap target = Bitmap.createBitmap(min,min,Bitmap.Config.ARGB_8888);
          Canvas canvas = new Canvas(target);//画布

          canvas.drawCircle(min/2,min/2,min/2,paint);

          //设置画笔去交集
          paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
             //在画布上绘制图片
          canvas.drawBitmap(bm,min/2,min/2,paint);


          return target;
      }
    public Bitmap createRonudCornerPicture(Bitmap bm){
        Paint paint = new Paint();//画笔
        paint.setAntiAlias(true);//抗锯齿

        int min = Math.min(bm.getWidth(),bm.getHeight());
        Bitmap target = Bitmap.createBitmap(bm.getWidth(),bm.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);//画布

        RectF rectF = new RectF(0,0,bm.getWidth(),bm.getHeight());//矩形
        canvas.drawRoundRect(rectF,50,50,paint);
        //设置画笔去交集
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        //在画布上绘制图片
        canvas.drawBitmap(bm,0,0,paint);


        return target;
    }

}
