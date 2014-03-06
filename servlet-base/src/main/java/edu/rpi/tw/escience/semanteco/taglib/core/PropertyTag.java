package edu.rpi.tw.escience.semanteco.taglib.core;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import edu.rpi.tw.escience.semanteco.util.SemantEcoConfiguration;

/**
 * The PropertyTag class provides a means for SemantEco JSP to access properties
 * declared within the semanteco.properties file generated at build time.
 *
 * @author ewpatton
 *
 */
public class PropertyTag extends TagSupport {
    private static final long serialVersionUID = -2342234130451352895L;
    private final transient Logger log = Logger.getLogger(PropertyTag.class);

    public PropertyTag() {
    }

    @Override
    public int doStartTag() throws JspException {
        String propName = (String) getValue( "name" );
        log.debug( "Evaluating property " + propName );
        String prop = SemantEcoConfiguration.get().getProperty( propName );
        if ( prop != null ) {
            final JspWriter out = pageContext.getOut();
            try {
                out.write( prop );
            } catch (IOException e) {
                log.error( "Unable to write property to JSP", e );
            }
        }
        return SKIP_BODY;
    }

    public void setName(String name) {
        setValue( "name", name );
    }
}
