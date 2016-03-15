package com.ailk.openplatform.exception;

public class SDKException extends RuntimeException
{
  private static final long serialVersionUID = 475022994858770424L;

  public SDKException()
  {
  }

  public SDKException(String message)
  {
    super(message);
  }

  public SDKException(String detailMessage, Throwable throwable)
  {
    super(detailMessage, throwable);
  }

  public SDKException(Throwable throwable)
  {
    super(throwable);
  }
}