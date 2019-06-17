package sion.my.myapplication.Until;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUntil {
    public static OkHttpClient okHttpClient = new OkHttpClient();
    {
        OkHttpClient.Builder builder = okHttpClient.newBuilder();
        builder.readTimeout(10000, TimeUnit.SECONDS);
        builder.writeTimeout(10000,TimeUnit.SECONDS);
        builder.connectTimeout(5000,TimeUnit.SECONDS);
        okHttpClient=builder.build();
    }
    public static Handler handler = new Handler();

    /*  public static void synGetrequest(String url,Class myclass,NetworkListining listining) throws Exception {
          new Thread(new Runnable() {
              @Override
              public void run() {
                  try {
                      listining.BackResultSuccess(toObject(myclass, responseTostring(Toresponse(Tocall(Torequest(url))))));
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }).start();


      }*/
    public static <T> void enqueueGetrequest(final String url, final Class<T> myclass, final NetworkListining<T> listining) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Tocall(Torequest(url)).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, final IOException e) {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listining.BackResultFail(e);
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {

                            final String string = response.body().string();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                  try{
                                      listining.BackResultSuccess(toObject(myclass, string), response.code());
                                  }catch (Exception e){
                                      listining.BackResultFail(e);
                                  }finally {
                                      if(string!=null){
                                          listining.tostring(string);
                                      }else{
                                          listining.tostring("404！！！！");
                                      }

                                  }

                                }
                            });

                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static <T> void enqueueGetrequest(final String url, final TostringNetworkListining listining) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Tocall(Torequest(url)).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, final IOException e) {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listining.BackResultFail(e);
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                           if(response.code()==200){
                               final String string = response.body().string();
                               handler.post(new Runnable() {
                                   @Override
                                   public void run() {
                                       listining.tostring(string);
                                   }
                               });
                           }

                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static <T> T toObject(Class<T> myclass, String string) {
        return new Gson().fromJson(string, myclass);
    }

    public static String responseTostring(Response toresponse) throws IOException {
        String string = toresponse.body().string();
        return string;
    }

    public static Response Toresponse(Call tocall) throws IOException {
        return tocall.execute();
    }

    public static Call Tocall(Request torequest) {
        Call call = okHttpClient.newCall(torequest);

        return call;
    }

    public static Request Torequest(String url) {
        return new Request.Builder().url(url).get().build();
    }




    public static <T> T NothreadenqueueGetrequest(final String url, final Class<T> myclass) throws IOException {
        Response execute = okHttpClient.newCall(new Request.Builder().get().url(url).build()).execute();
        return   new Gson().fromJson(execute.body().string(),myclass);


    }


}
