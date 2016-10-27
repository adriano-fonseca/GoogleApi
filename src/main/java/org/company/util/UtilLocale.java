package org.company.util;

import com.google.maps.GeoApiContext;

public class UtilLocale {
  /*Proxy details*/

//  final static String         apiKey     = "AIzaSyAb0Yh5FfwqJgbMVLPJP97_DMkNWAeY54w"; //deleted
  final static String         apiKey     = "AIzaSyC9eRxrOFK3kWR2jhGBn1F4fPxqZEyJ6LI";
  

  public static GeoApiContext getContext() {
    GeoApiContext geoApiContext = new GeoApiContext();
    geoApiContext.setApiKey(apiKey);
    return geoApiContext;
  }

//  public static String getTimeZone(DataRecordDTO dto) {
//    URL url;
//    StringBuilder sb = new StringBuilder();
//    try {
//      Long timestamp = dto.getDateTime().getTimeInMillis() / 1000L;
//      StringBuilder location = new StringBuilder();
//      location.append(dto.getLatitude());
//      location.append(",");
//      location.append(dto.getLongitude());
//
//      url = new URL("https://maps.googleapis.com/maps/api/timezone/json?location=" + location + "&timestamp=" + timestamp + "&key=AIzaKey");
//      Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.procergs.reders", 3128));
//      HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
//      connection.connect();
//      BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//      String line;
//      while ((line = rd.readLine()) != null) {
//        sb.append(line);
//      }
//      rd.close();
//      connection.disconnect();
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//
//    return sb.toString();
//  }
}
