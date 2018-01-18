package com.thaiopensource.validate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONObjectOption implements Option {
  private final JSONObjectPropertyId pid;

  public JSONObjectOption(JSONObjectPropertyId pid) {
    this.pid = pid;
  }

  @Override
  public JSONObjectPropertyId getPropertyId() {
    return pid;
  }

  @Override
  public JsonObject valueOf(String arg) throws OptionArgumentException {
    if (arg == null || arg.trim() == "") {
      return defaultValue();
    }
    else {
      return parse(arg);
    }
  }

  public JsonObject defaultValue() throws OptionArgumentPresenceException {
    throw new OptionArgumentPresenceException();
  }

  public JsonObject parse(String value) throws OptionArgumentFormatException {
    JsonParser parser = new JsonParser();
    JsonElement element = parser.parse(value);
    if (element.isJsonObject()) {
	return element.getAsJsonObject();
    }
    else {
        throw new OptionArgumentFormatException();
    }
  }

  @Override
  public Object combine(Object[] values) {
    return null;
  }
}
