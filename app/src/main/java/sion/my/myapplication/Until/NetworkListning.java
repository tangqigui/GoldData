package sion.my.myapplication.Until;

public abstract class NetworkListning<T> {
    public void resultSuccess(T t){};
     public void resultFail(String error){};
      public void toString(String xml){};
}
