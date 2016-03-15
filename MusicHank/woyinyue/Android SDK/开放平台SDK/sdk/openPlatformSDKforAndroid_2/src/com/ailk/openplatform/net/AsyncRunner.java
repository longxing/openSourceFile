package com.ailk.openplatform.net;

import android.os.AsyncTask;

import com.ailk.openplatform.exception.SDKException;
import com.ailk.openplatform.task.Parameters;

public class AsyncRunner
{ 

  public static String request(String url, Parameters params, String httpMethod)
    throws SDKException
  {
    return HttpManager.openUrl(url, httpMethod, params);
  }

  public static void requestAsync(String url, Parameters params, String httpMethod, RequestListener listener)
  {
    new RequestRunner(url, params, httpMethod, listener).execute(new Void[1]);
  }

  private static class AsyncTaskResult<T>
  {
    private T result;
    private SDKException error;

    public T getResult()
    {
      return this.result;
    }

    public SDKException getError() {
      return this.error;
    }

    public AsyncTaskResult(T result)
    {
      this.result = result;
    }

    public AsyncTaskResult(SDKException error)
    {
      this.error = error;
    }
  }

  private static class RequestRunner extends AsyncTask<Void, Void, AsyncRunner.AsyncTaskResult<String>>
  {
    private final String mUrl;
    private final Parameters mParams;
    private final String mHttpMethod;
    private final RequestListener mListener;

    public RequestRunner(String url, Parameters params, String httpMethod, RequestListener listener)
    {
      this.mUrl = url;
      this.mParams = params;
      this.mHttpMethod = httpMethod;
      this.mListener = listener;
    }

    protected AsyncRunner.AsyncTaskResult<String> doInBackground(Void[] params)
    {
      try {
        String result = HttpManager.openUrl(this.mUrl, this.mHttpMethod, this.mParams);
        return new AsyncRunner.AsyncTaskResult(result);
      }
      catch (SDKException e) {
        return new AsyncRunner.AsyncTaskResult(e);
      }
    }

    protected void onPreExecute()
    {
    }

    protected void onPostExecute(AsyncRunner.AsyncTaskResult<String> result)
    {
      SDKException exception = result.getError();
      if (exception != null)
        this.mListener.onSDKException(exception);
      else
        this.mListener.onComplete((String)result.getResult());
    }
  }
}