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

package de.knightsoftnet.validators.server;

import de.knightsoftnet.validators.shared.beans.IbanWithSpacesTestBean;
import de.knightsoftnet.validators.shared.testcases.IbanWithSpacesTestCases;

import org.junit.Test;

/**
 * test for iban with spaces validator.
 *
 * @author Manfred Tremmel
 *
 */
public class IbanWithSpacesTest extends AbstractValidationTest<IbanWithSpacesTestBean> {

  /**
   * empty iban is allowed.
   */
  @Test
  public final void testEmptyIbanIsAllowed() {
    super.validationTest(IbanWithSpacesTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct iban is allowed.
   */
  @Test
  public final void testCorrectIbanIsAllowed() {
    for (final IbanWithSpacesTestBean testBean : IbanWithSpacesTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testWrongCountryIbanIsWrong() {
    for (final IbanWithSpacesTestBean testBean : IbanWithSpacesTestCases.getWrongTestBeans()) {
      super
          .validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.IbanValidator");
    }
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testToSmallIbanIsWrong() {
    for (final IbanWithSpacesTestBean testBean : IbanWithSpacesTestCases.getToSmallTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.SizeWithoutSeparatorsValidator");
    }
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testToBigIbanIsWrong() {
    for (final IbanWithSpacesTestBean testBean : IbanWithSpacesTestCases.getToBigTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.SizeWithoutSeparatorsValidator");
    }
  }

  /**
   * iban with checksum error.
   */
  @Test
  public final void testChecksumErrorIbanIsWrong() {
    for (final IbanWithSpacesTestBean testBean : IbanWithSpacesTestCases
        .getWrongChecksumTestBeans()) {
      super
          .validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.IbanValidator");
    }
  }
}
