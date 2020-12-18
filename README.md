# Jme Graphics Modified
This originally was a fork of jayfella's jme-graphics, which was lost to time.
But the actual original source was lost, and this was recreated from a decompiled binary.

As this repo now stands, it has custom modifications made by me, specifically to make handling resolutions simpler.

The classpath of the code was modified to bring the code within one of my modules, I have re-modified the classpath
to be generic, but still within my normal classpath namespace. 

I do not know what the original license was, so I am listing this under MIT, do what you would like with it.
Original credits go to jayfella for writing this.

### How to use

First, you need to initialize the app:
```java
Graphics.initialize(myApp)
```

Then you will be able to get the instance, and make changes:
```java
Grapics.instance().getContextSettings().setFrameRate(30);
```
You can make more than one setting change.

Finally, after you have made your changes, you need to apply them:
```java
Graphics.instance().getContextSettings().apply();
```
This will cause the context to restart, but not the entire app.
