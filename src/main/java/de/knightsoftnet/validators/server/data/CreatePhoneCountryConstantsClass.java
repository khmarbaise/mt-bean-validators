/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.server.data;

import de.knightsoftnet.validators.shared.data.PhoneCountryConstantsImpl;
import de.knightsoftnet.validators.shared.data.PhoneCountrySharedConstants;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Read gwt constants from properties file on server side.
 *
 * @author Manfred Tremmel
 *
 */
public class CreatePhoneCountryConstantsClass extends CreateClassHelper {

  private static volatile Map<Locale, PhoneCountryConstantsImpl> phoneCountryConstants =
      new ConcurrentHashMap<>();

  /**
   * Instantiates a class via deferred binding.
   *
   * @return the new instance, which must be cast to the requested class
   */
  public static PhoneCountrySharedConstants create() {
    return CreatePhoneCountryConstantsClass.create(Locale.ROOT);
  }

  /**
   * Instantiates a class via deferred binding.
   *
   * @return the new instance, which must be cast to the requested class
   */
  public static PhoneCountrySharedConstants create(final Locale plocale) {
    if (!phoneCountryConstants.containsKey(plocale)) { // NOPMD it's thread save!
      synchronized (PhoneCountryConstantsImpl.class) {
        if (!phoneCountryConstants.containsKey(plocale)) {
          phoneCountryConstants.put(plocale, createPhoneCountryConstants(plocale));
        }
      }
    }
    return phoneCountryConstants.get(plocale);
  }

  /**
   * read phone country names.
   *
   * @return map of phone country code and name
   */
  public static Map<String, String> readPhoneCountryNames(final Locale plocale) {
    return readMapFromProperties("PhoneCountryNameConstants", plocale, "phoneCountryNames");
  }

  /**
   * read phone country codes and country iso codes.
   *
   * @return map of phone country code and country iso code
   */
  public static Map<String, String> readPhoneCountryCodes(final Locale plocale) {
    return readMapFromProperties("PhoneCountryCodeConstants", plocale, "phoneCountryCodes");
  }

  /**
   * read phone region codes for one country.
   *
   * @return map of area code and area name
   */
  public static Map<String, String> readPhoneRegionCodes(final String pphoneCountryCode,
      final Locale plocale) {
    return readMapFromProperties("PhoneRegionCode" + pphoneCountryCode + "Constants", plocale,
        "phoneRegionCodes" + pphoneCountryCode);
  }

  /**
   * read phone trunk an exit code map from property file.
   *
   * @return map of country code and combined string of trunk an exit code
   */
  public static Map<String, String> readPhoneTrunkAndExitCodes(final Locale plocale) {
    return readMapFromProperties("PhoneCountryTrunkAndExitCodesConstants", plocale,
        "phoneTrunkAndExitCodes");
  }
}
