/*
 * Copyright (c) 2010-2020, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.pac4j.oauth.profile.weixin;

import org.pac4j.core.profile.Gender;
import org.pac4j.core.profile.converter.AbstractAttributeConverter;

public class WeiXinGenderConverter extends AbstractAttributeConverter<Gender> {

    public WeiXinGenderConverter() {
        super(Gender.class);
    }

    @Override
    protected Gender internalConvert(final Object attribute) {
    	// for Weixin: 普通用户性别，1为男性，2为女性
        if (attribute instanceof String) {
            final String s = ((String) attribute).toLowerCase();
            if ("2".equals(s) ||"m".equals(s) || "male".equals(s)) {
                return Gender.MALE;
            } else if ("1".equals(s) || "f".equals(s) || "female".equals(s)) {
                return Gender.FEMALE;
            } else {
                return Gender.UNSPECIFIED;
            }
            // for Vk:
        } else if (attribute instanceof Integer) {
            Integer value = (Integer) attribute;
            if (value == 2) {
                return Gender.MALE;
            } else if (value == 1) {
                return Gender.FEMALE;
            } else {
                return Gender.UNSPECIFIED;
            }
        }
        return null;
    }
}
