package com.thaiopensource.validate.auto;

import com.thaiopensource.util.PropertyMap;
import com.thaiopensource.validate.IncorrectSchemaException;
import com.thaiopensource.validate.Schema;
import com.thaiopensource.validate.SchemaReader;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.transform.sax.SAXSource;
import java.io.IOException;

public class SchemaReaderSchemaReceiver implements SchemaReceiver {
  private final SchemaReader schemaLanguage;
  private final PropertyMap properties;

  public SchemaReaderSchemaReceiver(SchemaReader schemaLanguage, PropertyMap properties) {
    this.schemaLanguage = schemaLanguage;
    this.properties = properties;
    System.out.println("JIM in da place !");
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    for (StackTraceElement trace : stackTrace) {
		System.out.println("JIM" + trace);
	}
  }

  public SchemaFuture installHandlers(XMLReader xr) throws SAXException {
    throw new ReparseException() {
      public Schema reparse(SAXSource source) throws IncorrectSchemaException, SAXException, IOException {
        return schemaLanguage.createSchema(source, properties);
      }
    };
  }
}
