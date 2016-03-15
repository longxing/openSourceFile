package imusic4j.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.bind.annotation.XmlElement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("unused")
public class JsonObjectUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String json = "{\"crbtresponse\": { \"code\":\"0000\",\"desc\":\"成功\"}}";
		String j = "{\"a\":\"5\",\"b\":\"2\"}";
		String str = "{'map':{'a2':'A2','a1':'A1'},'rcvoList':[{'age':'20','name':'小裴'},{'age':'58','name':'小庞'}],'tname':'xxname'}";
		String s = "{'code':'0000','desc':'成功'}";
		String albums = "{'code':'0000','desc':'成功','start':1,'len':5,'count':14,'album':[{'id':13975,'name':'一年半载','url':'/pro/2007/album.51363.1205917575.300.jpg_RsT_200x200.jpg','language':'粤语','publishTime':'1999-12-01T00:00:00+08:00','intro':'1999年12月1日由环球唱','presses':'环球唱片','publishers':'环球唱片','actorName':'李克勤','areaName':'中国'},{'id':13900,'name':'一年','url':'/pro/2007/album.51363.1205917575.300.jpg_RsT_200x400.jpg','language':'粤语6','publishTime':'1999-12-01T00:00:00+08:00','intro':'1999年','presses':'环球唱片k','publishers':'环球唱片k','actorName':'李克勤','areaName':'中国tr'}]}";
		JSONObject jsonObject = JSONObject.fromObject(str);
		// System.out.println(jsonObject.getString("tname"));
		// Map<String,Class> m = new HashMap<String,Class>();
		// m.put("rcvoList", Person.class);
		// Object p = (Map)jsonObject.toBean(jsonObject,Person.class,Map.class);
		//get();
//		 test();
		/*QueryAlbumsResponse response = new QueryAlbumsResponse();
		response = (QueryAlbumsResponse) transform(response,albums);
		System.out.println("hello===="+response.getDesc()+"|"+response.getCode()+"|"+response.getStart()+"|"+response.getCount()+"|"+response.getLen());
*/	}

	public static Object getObject(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		// System.out.println(jsonObject.getString("crbtresponse"));
		return (BasicResponse) JSONObject.toBean(jsonObject);
	}

	@SuppressWarnings("unchecked")
	public static void get() {
		String mapStr = "{'age':30,'name':'Michael','baby':['Lucy','Lily']}";
		String str = "{'map':{'a2':'A2','a1':'A1'},'rcvoList':[{'age':'20','name':'小裴'},{'age':'58','name':'小庞'}],'tname':'xxname'}";
		JSONObject json3 = JSONObject.fromObject(str);
//		Map<String, Person> map = (Map) JSONObject.toBean(json3, Map.class);
//		for (Map.Entry<String, Person> entry : map.entrySet()) {
//			System.out.println(entry.getKey() + " | " + entry.getValue());
//			if(entry.getKey().equals("rcvoList")){
//				System.out.println("age="+entry.getValue().getAge());
//				System.out.println("name="+entry.getValue().getName());
//			}
//		}
		JSONArray jsonArray = JSONArray.fromObject(json3.get("rcvoList"));  
		JSONObject person = jsonArray.getJSONObject(0);
		String age = person.getString("age");
		System.out.println(age);
//		System.out.println("age="+((Person)jsonArray.get(0)).getAge());
//      JSONObject jsonObj = jsonArray.getJSONObject(0);  
//      JSONArray dataArray = JSONArray.fromObject(jsonObj.get("data"));
//		System.out.println(map.get("map"));
//		System.out.println(json3.getString("rcvoList"));
		
	}
	
	public static void test(){
		String str = "{'map':{'a2':'A2','a1':'A1'},'rcvoList':[{'age':'20','name':'小裴'},{'age':'58','name':'小庞'},{'age':'20','name':'小红','size':'33'}],'tname':'xxname'}";
		JSONObject jsonObject = JSONObject.fromObject(str);
		JSONObject mapp = jsonObject.getJSONObject("map");
		String a2 = mapp.getString("a2");
		String a1 = mapp.getString("a1");
		JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("rcvoList"));
		System.out.println(jsonObject.containsKey("age"));
		for(int i = 0;i<jsonArray.size();i++){
			if(jsonArray.getJSONObject(i).containsKey("age")){
				System.out.println("Hello");
			}
//			System.out.println(jsonArray.getJSONObject(i).getString("age")+"     "+jsonArray.getJSONObject(i).getString("name")+"     "+jsonArray.getJSONObject(i).getString("size"));
		}
			
	//	JSONObject rcvo = jsonArray.getJSONObject(0);
	}
	
	public static Object transform(Object response,String result){
		JSONObject jsonObject = JSONObject.fromObject(result);
		jsonObject = JSONObject.fromObject(jsonObject);
		int size = jsonObject.size();
		Field[] fiels = response.getClass().getDeclaredFields();
		for(int i=0;i<fiels.length;i++){
//			System.out.println("class="+fiels[i].getType().getName()+"  |  "+"fiel="+fiels[i].getName());
			String type = fiels[i].getType().getName();
			String name = fiels[i].getName();
			if(type.equals("int") || type.equals("java.lang.String")){
				try {
					if(jsonObject.containsKey(name)){
						getMethod(response, fiels[i]).invoke(response, jsonObject.get(name));
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}else{
				if(!type.equals("long") ){
					if(fiels[i].getAnnotations().length!=0){
						name = fiels[i].getAnnotation(XmlElement.class).name();
					}
					if(jsonObject.containsKey(name)){
						JSONArray jsonArray = JSONArray.fromObject(jsonObject.get(name));
						for(int j=0;j<jsonArray.size();j++){
							jsonObject = jsonArray.getJSONObject(j);
							try {
								getMethod(response, fiels[i]).invoke(response, jsonObject.get(name));
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return response;
	}
	
	public static Method getMethod(Object object, Field method) {
		String setMethodName = "set" + method.getName().substring(0, 1).toUpperCase() + method.getName().substring(1);
		Method setMethod = null;
		try {
			setMethod = object.getClass().getMethod(setMethodName,
					new Class[] { method.getType()});
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return setMethod;
	}
}
