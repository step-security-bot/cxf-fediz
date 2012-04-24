//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.28 at 02:33:06 PM CEST 
//

package org.apache.cxf.fediz.core.config;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for argumentType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="argumentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Class"/>
 *     &lt;enumeration value="String"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "argumentType")
@XmlEnum
public enum ArgumentType {

    @XmlEnumValue("Class")
    CLASS("Class"), @XmlEnumValue("String")
    STRING("String");
    private final String value;

    ArgumentType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ArgumentType fromValue(String v) {
        for (ArgumentType c : ArgumentType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
