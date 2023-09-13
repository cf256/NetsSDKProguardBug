# NetsSDKProguardBug
Sample demostrating proguard bug with the Nets SDK. 

## How to build: 
* Add a `local.properties` file in the root folder. I.e. in the same folder as `settings.gradle.kts`, `gradle.properties`, etc.
* Add these lines: 

```
NETS_MERCHANT=MerchantId
TRANSACTION_ID=TransactionId
```

Run app.

In debug mode the app works fine, but in release the app crashes when opening the nets activity, closing it, and opening it again. 
See video:

## Stacktrace:
```
Process: com.linchausen.netssdkproguardbug, PID: 7565
	java.lang.RuntimeException: Unable to start activity ComponentInfo{com.linchausen.netssdkproguardbug/eu.nets.pia.ui.main.PiaActivity}: java.lang.NullPointerException: Attempt to invoke virtual method 'boolean java.lang.Class.isInterface()' on a null object reference
		at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3645)
		at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3782)
		at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:101)
		at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135)
		at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95)
		at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2307)
		at android.os.Handler.dispatchMessage(Handler.java:106)
		at android.os.Looper.loopOnce(Looper.java:201)
		at android.os.Looper.loop(Looper.java:288)
		at android.app.ActivityThread.main(ActivityThread.java:7872)
		at java.lang.reflect.Method.invoke(Native Method)
		at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:548)
		at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:936)
	Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'boolean java.lang.Class.isInterface()' on a null object reference
		at java.lang.Class.isAssignableFrom(Class.java:589)
		at android.os.Parcel.readParcelableCreatorInternal(Parcel.java:4897)
		at android.os.Parcel.readParcelableInternal(Parcel.java:4804)
		at android.os.Parcel.readParcelable(Parcel.java:4795)
		at androidx.activity.l.h(SourceFile:3)
		at e2.p.createFromParcel(SourceFile:324)
		at android.os.Parcel.readParcelableInternal(Parcel.java:4813)
		at android.os.Parcel.readValue(Parcel.java:4564)
		at android.os.Parcel.readValue(Parcel.java:4347)
		at android.os.Parcel.-$$Nest$mreadValue(Unknown Source:0)
		at android.os.Parcel$LazyValue.apply(Parcel.java:4442)
		at android.os.Parcel$LazyValue.apply(Parcel.java:4401)
		at android.os.BaseBundle.getValueAt(BaseBundle.java:394)
		at android.os.BaseBundle.getValue(BaseBundle.java:374)
		at android.os.BaseBundle.getValue(BaseBundle.java:357)
		at android.os.BaseBundle.getValue(BaseBundle.java:350)
		at android.os.Bundle.getParcelable(Bundle.java:913)
		at android.content.Intent.getParcelableExtra(Intent.java:8922)
		at d1.c.onCreate(SourceFile:28)
		at eu.nets.pia.ui.main.PiaActivity.onCreate(SourceFile:5)
		at android.app.Activity.performCreate(Activity.java:8305)
		at android.app.Activity.performCreate(Activity.java:8284)
		at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1417)
		at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3626)
		at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3782) 
		at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:101) 
		at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135) 
		at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95) 
		at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2307) 
		at android.os.Handler.dispatchMessage(Handler.java:106) 
		at android.os.Looper.loopOnce(Looper.java:201) 
		at android.os.Looper.loop(Looper.java:288) 
		at android.app.ActivityThread.main(ActivityThread.java:7872) 
		at java.lang.reflect.Method.invoke(Native Method) 
		at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:548) 
		at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:936) 
```
