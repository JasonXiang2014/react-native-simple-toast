package cn.newbanker.module;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;

import java.util.Map;

public class ToastModule extends ReactContextBaseJavaModule {

  private static final String DURATION_SHORT_KEY = "SHORT";
  private static final String DURATION_LONG_KEY = "LONG";

  private static final String GRAVITY_TOP_KEY = "TOP";
  private static final String GRAVITY_BOTTOM_KEY = "BOTTOM";
  private static final String GRAVITY_CENTER = "CENTER";

  private View mToast;

  public ToastModule(ReactApplicationContext reactContext) {
    super(reactContext);

    LayoutInflater li = (LayoutInflater) reactContext.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mToast = li.inflate(R.layout.toast, null);
  }

  @Override
  public String getName() {
    return "ToastModule";
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = MapBuilder.newHashMap();
    constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
    constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
    constants.put(GRAVITY_TOP_KEY, Gravity.TOP | Gravity.CENTER_HORIZONTAL);
    constants.put(GRAVITY_BOTTOM_KEY, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    constants.put(GRAVITY_CENTER, Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
    return constants;
  }

  @ReactMethod
  public void show(final String message, final int duration) {
    UiThreadUtil.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast toast = Toast.makeText(getReactApplicationContext(), message, duration);
        toast.setView(mToast);
        TextView msg = mToast.findViewById(R.id.message);
        msg.setText(message);
        toast.show();
      }
    });
  }

  @ReactMethod
  public void showWithGravity(final String message, final int duration, final int gravity) {
    UiThreadUtil.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast toast = Toast.makeText(getReactApplicationContext(), message, duration);
        toast.setView(mToast);
        toast.setGravity(gravity, 0, 0);
        TextView msg = mToast.findViewById(R.id.message);
        msg.setText(message);
        toast.show();
      }
    });
  }

  @ReactMethod
  public void showWithGravityAndOffset(
      final String message,
      final int duration,
      final int gravity,
      final int xOffset,
      final int yOffset) {
    UiThreadUtil.runOnUiThread(
        new Runnable() {
          @Override
          public void run() {
            Toast toast = Toast.makeText(getReactApplicationContext(), message, duration);
            toast.setView(mToast);
            TextView msg = mToast.findViewById(R.id.message);
            msg.setText(message);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
          }
        });
  }

}
