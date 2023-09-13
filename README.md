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

