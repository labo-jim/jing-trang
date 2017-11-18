package com.thaiopensource.validate.schematron;

import net.sf.saxon.lib.FeatureKeys;
import net.sf.saxon.TransformerFactoryImpl;
import javax.xml.transform.TransformerFactory;
import net.sf.saxon.Configuration;
import net.sf.saxon.jaxp.SaxonTransformerFactory;

/**
 * Xslt3SaxonSchemaReaderFactory / Schematron XSLT 3.0 compatibility
 * Extends NewSaxonSchemaReaderFactory so that the method newTransformerFactory() returns a SaxonTransformerFactory object.
 * This ensures that a ProfessionalTransformerFactory or a EnterpriseTransformerFactory is automatically created by Saxon if the appropriate license file can be found in the classpath.
 * That way, older Saxon versions (before 9.8) that offers XSLT 3.0 capabilities only with PE/EE editions can be used.
 * Since version 9.8, Saxon can run XSLT 3.0 stylesheets even with the standard HE edition, and thus should be used to avoid any XSLT 3.0 related issues.
 * NewSaxonSchemaReaderFactory class is left as is to avoid issues with systems that already overrides it (ex. : oXygen).
 */
public class Xslt3SaxonSchemaReaderFactory extends NewSaxonSchemaReaderFactory {

    @Override
    public TransformerFactoryImpl newTransformerFactory() {
        TransformerFactoryImpl transformerFactory = (TransformerFactoryImpl) super.newTransformerFactory();
        return transformerFactory;
    }

    @Override
    public void initTransformerFactory(TransformerFactory factory) {
        super.initTransformerFactory(factory);
        try {
            SaxonTransformerFactory saxonFactory = (SaxonTransformerFactory) factory;
            // Ensures that at least a PE license is available before switching on XSLT 3.0 feature
            // From Saxon 9.8, an XSLT 3.0 processor is always used by default, so we can leave the "2.0" set by super.initTransformerFactory(factory) when HE edition is used
            Boolean isSaxonLicensed = saxonFactory.getConfiguration().isLicensedFeature(Configuration.LicenseFeature.PROFESSIONAL_EDITION);
            if (isSaxonLicensed) {
                // Forces the default XSLT processor to be in XSLT 3.0
                factory.setAttribute(FeatureKeys.XSLT_VERSION, "3.0");
            } else {
                // Leaves the XSLT 2.0 processor set by super.initTransformerFactory(factory)
            }
        }
        catch (IllegalArgumentException e) { }
    }

}
