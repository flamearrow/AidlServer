# AidlServer
a server APK to implement Aidl IPC

To call the service from client app:
* copy src/main/aidl/com/ml/gb/aidlserver/IShoutingService.aidl, rebuild project
* use Intent(IShoutingService.ACTION).also { it.`package` = "com.ml.gb.aidlserver"} in your Activity#bindService call
* in your ServiceConnection#onServiceConnected, use IShoutingService.Stub.asInterface(service) to get a IShoutingService instance
* shout
        
