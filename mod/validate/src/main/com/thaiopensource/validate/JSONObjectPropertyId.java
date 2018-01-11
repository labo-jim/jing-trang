package com.thaiopensource.validate;

import com.google.gson.JsonObject;
import com.thaiopensource.util.PropertyId;

/**
 * A PropertyId whose value is constrained to be an instance of
 * String.
 *
 * @see String
 */

public class JSONObjectPropertyId extends PropertyId<JsonObject> {
   public JSONObjectPropertyId(String name) {
      super(name, JsonObject.class);
    }
}
