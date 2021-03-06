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

package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator;
import de.knightsoftnet.validators.shared.impl.Isbn13FormatedValidator;
import de.knightsoftnet.validators.shared.impl.IsbnFormatedValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated element must be a valid International Standard Book Number in the long (13 digits)
 * or short (10 digits) format.<br />
 * Supported types are Strings, other Objects are transfered to Strings, <code>null</code> elements
 * are considered valid. Minus signs as separators must be set on the correct positions.<br />
 * There are format, size and checksum tests by apache commons validation routines.<br />
 *
 * @author Manfred Tremmel
 *
 */
@Documented
@Constraint(validatedBy = IsbnFormatedValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@AlternateSize(size1 = Isbn10FormatedValidator.ISBN10_LENGTH,
    size2 = Isbn13FormatedValidator.ISBN13_LENGTH)
public @interface IsbnFormated {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationIsbnFormatedMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code @IsbnFormated} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
      ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * isbn value.
     */
    IsbnFormated[] value();
  }
}
