package com.ailk.openplatform.net;

import com.ailk.openplatform.exception.SDKException;

public abstract interface RequestListener
{
  public abstract void onComplete(String paramString);

  public abstract void onSDKException(SDKException paramSDKException);
}