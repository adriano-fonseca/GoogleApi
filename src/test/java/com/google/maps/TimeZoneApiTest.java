/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.google.maps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.company.util.UtilLocale;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class TimeZoneApiTest {
  
  private GeoApiContext context;
  private static final boolean PROXYENABLE = true;

  private LatLng sydney;
  private LatLng auckland;

  public TimeZoneApiTest() {
    this.context = UtilLocale.getContext();
    this.context = context.setQueryRateLimit(3)
        .setConnectTimeout(1, TimeUnit.SECONDS)
        .setReadTimeout(1, TimeUnit.SECONDS)
        .setWriteTimeout(1, TimeUnit.SECONDS);
  }

  @Before
  public void setUp() throws Exception {
    if (sydney == null) {
//    Get address from lat, lon
//    GeocodingResult[] results = GeocodingApi.reverseGeocode(context, new LatLng(-44.490947, 171.220966)).await();
      GeocodingResult[] results = GeocodingApi.geocode(context, "Sydney").await();
      GeocodingResult[] results2 = GeocodingApi.geocode(context, "Auckland").await();
      sydney = results[0].geometry.location;
      auckland = results2[0].geometry.location;
    }
  }


  @Test
  public void testGetTimeZoneAuckland() throws Exception {
    TimeZone tzAuckland = TimeZoneApi.getTimeZone(context, auckland).await();
//    TimeZone tz = TimeZoneApi. .getTimeZone(context, new LatLng(-44.490947, 171.220966)).await();
    
    assertNotNull(tzAuckland);
    assertEquals(TimeZone.getTimeZone("Pacific/Auckland"), tzAuckland);

    // GMT+10
    assertEquals(43200000, tzAuckland.getRawOffset());
    // DST is +1h
    assertEquals(3600000, tzAuckland.getDSTSavings());

    assertTrue(tzAuckland.inDaylightTime(new Date(1388494800000L)));
  }
  
  @Test
  public void testGetTimeZoneSydney() throws Exception {
    TimeZone tzSydney = TimeZoneApi.getTimeZone(context, sydney).await();
    assertNotNull(tzSydney);
    assertEquals(TimeZone.getTimeZone("Australia/Sydney"), tzSydney);

    // GMT+10
    assertEquals(36000000, tzSydney.getRawOffset());
    // DST is +1h
    assertEquals(3600000, tzSydney.getDSTSavings());
    assertTrue(tzSydney.inDaylightTime(new Date(1388494800000L)));
  }
  
  @Test
  public void printingFormats() throws Exception {
    context.setQueryRateLimit(3);
    TimeZone tzLocation = TimeZoneApi.getTimeZone(context, new LatLng(-44.490947, 171.220966)).await();
    System.out.println(tzLocation.getDisplayName());
    Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    //2013-07-10 02:52:49       2013-07-10T14:52:49
    now.set(2013, 6, 10, 02, 52, 49);

    DateTime Utc = new DateTime(now.getTimeInMillis(), DateTimeZone.forID("UTC"));
    DateTime AU = new DateTime(now.getTimeInMillis(), DateTimeZone.forID("Australia/Sydney"));
    DateTime NZ = new DateTime(now.getTimeInMillis(), DateTimeZone.forID("Pacific/Auckland"));
    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    System.out.println("UTC: " + fmt.print(Utc));
    System.out.println("AU: " + AU.toString());
    System.out.println("NZ: " + NZ.toString());

    //Convert from Joda DateTime to Calendar
    Calendar cal = NZ.toCalendar(new Locale("pt", "BR"));
    DateFormat df = DateFormat.getDateTimeInstance();
    System.out.println("BR:" + df.format(cal.getTime())); //23:52:49
  }


}