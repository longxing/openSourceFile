package com.ailk.openplatform.task;

import android.os.Bundle;

import com.ailk.openplatform.exception.SDKException;

public abstract interface SDKAuthListener
{
  public abstract void onComplete(Bundle paramBundle);

  public abstract void onSDKException(SDKException paramSDKException);

  public abstract void onCancel();
}