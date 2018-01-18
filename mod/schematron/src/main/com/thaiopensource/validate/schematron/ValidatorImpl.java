package com.thaiopensource.validate.schematron;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.thaiopensource.resolver.Resolver;
import com.thaiopensource.resolver.xml.transform.Transform;
import com.thaiopensource.util.PropertyMap;
import com.thaiopensource.validate.ResolverFactory;
import com.thaiopensource.validate.ValidateProperty;
import com.thaiopensource.validate.Validator;
import com.thaiopensource.validate.prop.schematron.SchematronProperty;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ErrorHandler;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;

class ValidatorImpl implements Validator {
  private final Templates templates;
  private final SAXTransformerFactory factory;
  private final ContentHandler outputHandler;
  private TransformerHandler transformerHandler;
  private final Resolver resolver;
  private final PropertyMap properties;

  ValidatorImpl(Templates templates, SAXTransformerFactory factory, PropertyMap properties) {
    this.templates = templates;
    this.factory = factory;
    this.properties = properties;
    ErrorHandler eh = properties.get(ValidateProperty.ERROR_HANDLER);
    outputHandler = new OutputHandler(eh);
    resolver = ResolverFactory.createResolver(properties).getResolver();
    initTransformerHandler();
  }

  public ContentHandler getContentHandler() {
    return transformerHandler;
  }

  public DTDHandler getDTDHandler() {
    return transformerHandler;
  }

  public void reset() {
    initTransformerHandler();
  }

  private void initTransformerHandler() {
    try {
      transformerHandler = factory.newTransformerHandler(templates);
      Transformer transformer = transformerHandler.getTransformer();
      // When you specify a URIResolver, XSLTC uses a DOMCache, which
      // doesn't seem to work too well.
      if (!SchemaReaderImpl.isXsltc(factory.getClass()))
        transformer.setURIResolver(Transform.createSAXURIResolver(resolver));
      // Supplies command-line parameters to the XSL compiled Schematron (-P command line argument)
      JsonObject xslParameters = properties.get(SchematronProperty.XSLPARAMS);
      if (xslParameters != null) {
        Set<Map.Entry<String,JsonElement>> xslParams = xslParameters.entrySet();
        Iterator<Map.Entry<String,JsonElement>> xslParamsIt = xslParams.iterator();
        while(xslParamsIt.hasNext()) {
          Map.Entry<String,JsonElement> xslParam = xslParamsIt.next();
          transformer.setParameter(xslParam.getKey(), xslParam.getValue().getAsString());
        }
      }
      // XXX set up transformer with an ErrorListener that just throws
      // XXX (what about errors from document() calls?)
    }
    catch (TransformerConfigurationException e) {
      throw new RuntimeException("could not create transformer");
    }
    transformerHandler.setResult(new SAXResult(outputHandler));
  }
}
