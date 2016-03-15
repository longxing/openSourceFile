package com.ailk.openplatform.exception;

public class AuthException extends SDKException
{
  private static final long serialVersionUID = 1L;
  public static final String DEFAULT_AUTH_ERROR_CODE = "-1";
  public static final String DEFAULT_AUTH_ERROR_DESC = "Unknown Error Description";
  public static final String DEFAULT_AUTH_ERROR_TYPE = "Unknown Error Type";
  private final String mErrorType;
  private final String mErrorCode;

  public AuthException(String errorCode, String errorType, String errorDescription)
  {
    super(errorDescription);
    this.mErrorType = errorType;
    this.mErrorCode = errorCode;
  }

  public String getErrorType()
  {
    return this.mErrorType;
  }

  public String getErrorCode()
  {
    return this.mErrorCode;
  }
}